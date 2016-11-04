package com.example.jimmyhernandez.rinnocompleto.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.jimmyhernandez.rinnocompleto.R;
import com.example.jimmyhernandez.rinnocompleto.views.model.Example;
import com.example.jimmyhernandez.rinnocompleto.views.model.Forecast;
import com.example.jimmyhernandez.rinnocompleto.views.rest.ApiClient;
import com.example.jimmyhernandez.rinnocompleto.views.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;

public class RinnoClima extends AppCompatActivity {

    private final int SPLASH_TIME = 5000;
    private static final String TAG = "CLIMA";

    // TODO - insert your themoviedb.org API KEY here
    private final static String API_KEY = "4c9c63494dcf49a18b0194907162710";
    private final static String Q = "Santiago";
    private final static int DAYS = 2;

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView tempMinRes;
    private TextView tempMaxRes;
    private TextView windSpeed;
    private ImageView conIcon;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.rinno_clima);



        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        tempMinRes = (TextView) findViewById(R.id.tempMinRes);
        tempMaxRes = (TextView) findViewById(R.id.tempMaxRes);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        conIcon = (ImageView) findViewById(R.id.condIcon);

        if (API_KEY.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please obtain your API KEY", Toast.LENGTH_LONG).show();
            return;
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<Example> call = apiService.getInfo(API_KEY,Q,DAYS);
        call.enqueue(new Callback<Example>() {
            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                String hola = response.body().getLocation().getName();
                cityText.setText(hola);
                Forecast student = response.body().getForecast();
                condDescr.setText(student.getForecastday().get(0).getDay().getCondition().getText());
                temp.setText(String.valueOf(response.body().getCurrent().getTempC()) + "\u00B0C");
                tempMinRes.setText(String.valueOf(student.getForecastday().get(1).getDay().getMintempC()) + "\u00B0C");
                tempMaxRes.setText(String.valueOf(student.getForecastday().get(0).getDay().getMaxtempC()) + "\u00B0C");
                windSpeed.setText(String.valueOf(response.body().getCurrent().getWindKph()) + "km\u002fh");
                String url = "http:"+student.getForecastday().get(0).getDay().getCondition().getIcon();
                Glide.with(RinnoClima.this).load(url).centerCrop().into(conIcon);

                //Log.e(TAG,"Student name" + url);

                //Log.e(TAG, " AQUIIIIIIIIIIIIIIII " + hola);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent miIntent = new Intent(RinnoClima.this,RinnoHora.class);
                RinnoClima.this.startActivity(miIntent);
                RinnoClima.this.finish();
            }
        }, SPLASH_TIME);


    }
}