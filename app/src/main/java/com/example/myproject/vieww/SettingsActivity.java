package com.example.myproject.vieww;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.drawerlayout.widget.DrawerLayout;
import com.example.myproject.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SettingsActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DrawerLayout drawerLayout;
    BottomNavigationView bottomNavigationView;

    private static final String THEME_PREFS = "themePrefs";
    private static final String THEME_KEY = "themeKey";

    private Switch switchMode;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.switch_mode);

        switchMode = findViewById(R.id.switchMode);

        // Get the shared preferences
        sharedPreferences = getSharedPreferences(THEME_PREFS, MODE_PRIVATE);

        // Check the saved theme preference and update the switch state
        boolean isDarkMode = sharedPreferences.getBoolean(THEME_KEY, false);
        switchMode.setChecked(isDarkMode);

        // Set a listener for the switch
        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Save the current theme preference
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean(THEME_KEY, isChecked);
                editor.apply();

                // Apply the selected theme only when the user interacts with the switch
                applyTheme(isChecked);
            }
        });
    }

    private void applyTheme(boolean isDarkMode) {
        int nightMode = isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(nightMode);
        recreate(); // Recreate the activity only when the user interacts with the switch
    }

}