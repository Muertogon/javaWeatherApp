package com.weather.weatherzp202;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SearchActivity extends AppCompatActivity {
    private JSON currentWeatherService;

    private View weatherContainer;
    private ProgressBar weatherProgressBar;
    private TextView temperature, location, weatherCondition;
    private ImageView weatherConditionIcon;
    private EditText locationField;
    private FloatingActionButton fab;
    private boolean fetchingWeather = false;
    private int textCount = 0;
    private String currentLocation = "London";
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search");
        currentWeatherService = new JSON(this);

        weatherContainer = findViewById(R.id.weather_container);
        weatherProgressBar = findViewById(R.id.weather_progress_bar);
        temperature = findViewById(R.id.temperature);
        location = findViewById(R.id.location);
        weatherCondition = findViewById(R.id.weather_condition);
        weatherConditionIcon = findViewById(R.id.weather_condition_icon);
        locationField = findViewById(R.id.location_field);
        fab = findViewById(R.id.fab);
        textView=(TextView) findViewById(R.id.location);

        textView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SearchActivity.this, NewEntryActivity.class);
                startActivity(intent);
                Toast.makeText(SearchActivity.this, "More Weather Settings", Toast.LENGTH_LONG).show();
            }
        });
        // search bar
        locationField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                count = s.toString().trim().length();
                fab.setImageResource(count == 0 ? R.drawable.ic_refresh : R.drawable.ic_search);
                textCount = count;
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textCount == 0) {
                    refreshWeather();
                } else {
                    searchForWeather(locationField.getText().toString());
                    locationField.setText("");
                }
            }
        });

        // suranda default lokacija
        searchForWeather(currentLocation);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        currentWeatherService.cancel();
    }

    private void refreshWeather() {
        if (fetchingWeather) {
            return;
        }
        searchForWeather(currentLocation);
    }

    private void searchForWeather(@NonNull final String location) {
        toggleProgress(true);
        fetchingWeather = true;
        currentWeatherService.getCurrentWeather(location, currentWeatherCallback);
    }

    private void toggleProgress(final boolean showProgress) {
        weatherContainer.setVisibility(showProgress ? View.GONE : View.VISIBLE);
        weatherProgressBar.setVisibility(showProgress ? View.VISIBLE : View.GONE);
    }

    private final JSON.CurrentWeatherCallback currentWeatherCallback = new JSON.CurrentWeatherCallback() {

        @Override
        public void onCurrentWeather(@NonNull Weather currentWeather) {
            currentLocation = currentWeather.location;
            temperature.setText(String.valueOf(currentWeather.getTempFahrenheit()));
            location.setText(currentWeather.location);
            weatherCondition.setText(currentWeather.weatherCondition);
            weatherConditionIcon.setImageResource(WeatherUtils.getWeatherIconResId
                    (currentWeather.conditionId));
            toggleProgress(false);
            fetchingWeather = false;
        }

        @Override
        public void onError(@Nullable Exception exception) {
            toggleProgress(false);
            fetchingWeather = false;
            Toast.makeText(SearchActivity.this, "There was an error fetching weather, " +
                    "try again.", Toast.LENGTH_SHORT).show();
        }
    };
}
