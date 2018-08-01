package com.example.meet13;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity implements MainManager.View, DailyForecastAdapter.MyCallBack {

    public static final String WEATHER = "weather";
    public static final String WEATHER_INFO = "weatherInfo";
    public static final String BROADCAST = "com.example.meet13.myReceiver";
    public static final int REQUEST_FOR_RESULT = 1;

    private boolean changed = false;

    private List<DailyForecast> dailyForecast;
    private List<HourlyForecast> hourlyForecast;

    private TextView CityName;
    private TextView Description;

    private DailyForecastAdapter dailyForecastAdapter;
    private HourlyForecastAdapter hourlyForecastAdapter;

    private SwipeRefreshLayout SwipeRefreshLayout;

    private ProgressBar progressBar;

    private myReceiver myReceiver;
    private IntentFilter intentFilter;

    private RecyclerView hourlyView;
    private RecyclerView dailyView;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        CityName = (TextView) findViewById(R.id.cityName);
        Description = (TextView) findViewById(R.id.description);
        SwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeLayout);

        dailyView = (RecyclerView) findViewById(R.id.weatherPerDay);
        hourlyView = (RecyclerView) findViewById(R.id.weatherPerHour);

//        dailyView.setLayoutManager(new LinearLayoutManager(this));
//        hourlyView.setLayoutManager(new LinearLayoutManager(this));

        dailyView.setHasFixedSize(true);
        hourlyView.setHasFixedSize(true);

        dailyForecast = new ArrayList<DailyForecast>();
        hourlyForecast = new ArrayList<HourlyForecast>();

        dailyForecastAdapter = new DailyForecastAdapter(dailyForecast, this, this);
        hourlyForecastAdapter = new HourlyForecastAdapter(hourlyForecast, this);

        dailyView.setAdapter(dailyForecastAdapter);
        hourlyView.setAdapter(hourlyForecastAdapter);

        myReceiver = new myReceiver();
        intentFilter = new IntentFilter(BROADCAST);

        MyDataBase myDataBase = MyDataBase.getInstance(this);
        Model model = new Model(myDataBase);
        presenter = new Presenter(this, model);

        updating();

        SwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                updating();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        registerReceiver(myReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(myReceiver);
    }

    private void updating() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        presenter.initializeData(connectivityManager);
    }

    @Override
    public void showWeather(Weather weather) {
        CityName.setText(weather.getTimezone());
        Description.setText(weather.getDaily().getSummary());

        dailyForecast = weather.getDaily().getData();
        hourlyForecast = weather.getHourly().getData();

        dailyForecastAdapter.notifyDataSetChanged();
        hourlyForecastAdapter.notifyDataSetChanged();
    }

    @Override
    public void showProgressBar() {
        if(SwipeRefreshLayout.isRefreshing())
            return;
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        if(SwipeRefreshLayout.isRefreshing())
            return;
        progressBar.setVisibility(View.GONE);
        SwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage() {
        Toast.makeText(this, "Нет Интернет соединения", LENGTH_SHORT).show();
    }

    @Override
    public void onItemClick(DailyForecast dailyForecast) {
        Intent info = new Intent(MainActivity.this, InformationActivity.class);
        startActivity(info);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings: {
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivityForResult(intent, REQUEST_FOR_RESULT);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void startService() {
        Intent service = new Intent(MainActivity.this, myService.class);
        service.putExtra(SettingsActivity.CHANGED, changed);
        changed = false;

        startService(service);
        Log.d("TAG", "service was started");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_FOR_RESULT && resultCode == RESULT_OK) {
            changed = data.getBooleanExtra(SettingsActivity.CHANGED, false);
            if (changed) {
                updating();
            }
        }
    }

    class myReceiver extends BroadcastReceiver {

        private Weather weather;

        @Override
        public void onReceive(Context context, Intent intent) {
            hideProgressBar();

            weather = intent.getParcelableExtra(WEATHER);

            CityName.setText(weather.getTimezone());
            Description.setText(weather.getDaily().getSummary());

            dailyForecast = weather.getDaily().getData();
            dailyForecastAdapter.addDailyData(dailyForecast);

            dailyForecastAdapter.notifyDataSetChanged();

            hourlyForecast = weather.getHourly().getData();
            hourlyForecastAdapter.addHourlyData(hourlyForecast);

            hourlyForecastAdapter.notifyDataSetChanged();

            Log.d("TAG", "MyReceiver was stopped");
        }
    }
}
