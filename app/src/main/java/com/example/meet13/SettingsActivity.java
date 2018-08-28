package com.example.meet13;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


public class SettingsActivity extends AppCompatActivity {

    public static final String IS_PREFS_CHANGED = "isChanged";
    private boolean isChanged = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getFragmentManager().beginTransaction()
                .add(R.id.settings_container, new SettingsFragment())
                .commit();

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        preferences.registerOnSharedPreferenceChangeListener((sharedPreferences, key) -> isChanged = true);
    }

    @Override
    public void onBackPressed() {
        Intent upIntent = new Intent(this, MainActivity.class);
        upIntent.putExtra(IS_PREFS_CHANGED, isChanged);
        setResult(RESULT_OK, upIntent);
        finish();
    }

    public static class SettingsFragment extends PreferenceFragment {
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences);
        }
    }
}
