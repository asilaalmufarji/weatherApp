package com.example.weatherapp_asila;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class resultWeather extends AppCompatActivity {
    TextView tv_city;
    TextView tv_Temperature;
    TextView tv_TimeOfSunrise;
    TextView tv_TimeOfSunset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_weather);

        tv_city = findViewById(R.id.textView);
        tv_Temperature = findViewById(R.id.textView2);
        tv_TimeOfSunrise = findViewById(R.id.textView3);
        tv_TimeOfSunset = findViewById(R.id.textView4);

        tv_city.setText("");
        tv_Temperature.setText("");
        tv_TimeOfSunrise.setText("");
        tv_TimeOfSunset.setText("");


        private void api_key(){
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("https://api.weatherbit.io/v2.0/current?&key=7066d60c414342dcac1fb942b9e1f0a2").get().build();

            //7066d60c414342dcac1fb942b9e1f0a2.
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            try {
                Response response= client.newCall(request).execute();
                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NotNull Call call, @NotNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                        String responseData= response.body().string();
                        try {
                            JSONObject json=new JSONObject(responseData);
                            JSONArray array=json.getJSONArray("weather");
                            JSONObject object=array.getJSONObject(0);

                            String Sunrise=object.getString("Sunrise");
                            String Sunset = object.getString("Sunset");

                            JSONObject temp1= json.getJSONObject("main");
                            Double Temperature=temp1.getDouble("temp");

                            setText(tv_city,City);

                            String temps=Math.round(Temperature)+" °C";

                            setText(tv_Temperature,temps);
                            setText(tv_TimeOfSunrise,Sunrise);
                            setText(tv_TimeOfSunset,Sunset);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }
}