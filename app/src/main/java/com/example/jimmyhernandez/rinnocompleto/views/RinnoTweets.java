package com.example.jimmyhernandez.rinnocompleto.views;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.jimmyhernandez.rinnocompleto.R;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import com.twitter.sdk.android.tweetui.TweetTimelineListAdapter;
import com.twitter.sdk.android.tweetui.UserTimeline;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Jimmy Hernandez on 02-11-2016.
 */

public class RinnoTweets extends ListActivity {

    private String user;
    private final int SPLASH_TIME = 5000;

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "ZURm1p1o53wMmmkBkvZSgIurW";
    private static final String TWITTER_SECRET = "W2hK6PMb0BjFnGIsSwcX46pFlX8oHBOex6YbuYM8q5qm9ZimkM";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = "hernandez_jimmy";

        iniciarTwwets();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent miIntent = new Intent(RinnoTweets.this,RinnoClima.class);
                RinnoTweets.this.startActivity(miIntent);
                RinnoTweets.this.finish();
            }
        }, SPLASH_TIME);
    }


    public void iniciarTwwets(){
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.lista_tweets);

        final UserTimeline userTimeline = new UserTimeline.Builder()
                .screenName(user)
                .build();
        final TweetTimelineListAdapter adapter = new TweetTimelineListAdapter.Builder(this)
                .setTimeline(userTimeline)
                .build();
        setListAdapter(adapter);
    }
}
