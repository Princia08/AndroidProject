package com.example.myproject.vieww.fragment.settings;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.myproject.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SettingsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SettingsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match

    private static final String THEME_PREFS = "themePrefs";
    private static final String THEME_KEY = "themeKey";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Switch switchMode;
    private SharedPreferences sharedPreferences;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
        Bundle args = new Bundle();
        args.putString(THEME_PREFS, param1);
        args.putString(THEME_KEY, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(THEME_PREFS);
            mParam2 = getArguments().getString(THEME_KEY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the fragment layout
        View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

        // Find the Switch view within the inflated layout
        switchMode = rootView.findViewById(R.id.switchButton);

        sharedPreferences = requireActivity().getSharedPreferences(THEME_PREFS, Context.MODE_PRIVATE);

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
        return rootView;
    }

    private void applyTheme(boolean isDarkMode) {
        int nightMode = isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO;
        AppCompatDelegate.setDefaultNightMode(nightMode);
       // requireActivity().recreate(); // Recreate the activity only when the user interacts with the switch
    }
}