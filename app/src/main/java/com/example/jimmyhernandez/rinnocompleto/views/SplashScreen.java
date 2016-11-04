package com.example.jimmyhernandez.rinnocompleto.views;

import android.os.Bundle;
import com.example.jimmyhernandez.rinnocompleto.R;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class SplashScreen extends Activity {
    private final int SPLASH_TIME = 2000;
    int densidad;
    int ancho;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        setContentView(R.layout.splashscreen);
        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay();

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ancho = display.getWidth();
        densidad = metrics.densityDpi;

		/*
		 * Handler que quitará el splash screen después del SPLASH_TIME y creará
		 * un intent de la actividad principal.
		 */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
				/*
				 * Creamos un Intent que lanzará nuestra Actividad principal (en
				 * nuestro caso Main.java)
				 */
                Intent miIntent = new Intent(SplashScreen.this,RinnoTweets.class);
                SplashScreen.this.startActivity(miIntent);
                SplashScreen.this.finish();
            }
        }, SPLASH_TIME);
    }

    @Override
    public void onPause() {
        super.onPause();
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    @Override
    public void onResume() {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

}