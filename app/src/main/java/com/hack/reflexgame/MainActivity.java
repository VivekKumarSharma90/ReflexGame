package com.hack.reflexgame;

import java.io.IOException;
import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.app.Activity;
import android.content.Context;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
    /*Display idisplay = getWindowManager().getDefaultDisplay();

    public int displayWidth = idisplay.getWidth();
    public int displayHeight = idisplay.getHeight();*/
    public int displayWidth;
    public int displayHeight;
    private int score;
    TextView timerField, s, level, time_up, again, textView;
    MediaPlayer mediaPlayer_swipe_sound, mediaPlayer_aww, mediaPlayer_person_cheering;
    Typeface typeface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerField = (TextView) findViewById(R.id.timer);
        level = (TextView) findViewById(R.id.level);
        s = (TextView) findViewById(R.id.score);
        time_up = (TextView) findViewById(R.id.time_up);
        again = (TextView) findViewById(R.id.again);

        mediaPlayer_swipe_sound = MediaPlayer.create(MainActivity.this, R.raw.swipe_sound);
        mediaPlayer_aww = MediaPlayer.create(MainActivity.this, R.raw.aww2);
        mediaPlayer_person_cheering = MediaPlayer.create(MainActivity.this, R.raw.person_cheering);
        try {
            mediaPlayer_swipe_sound.prepare();
            mediaPlayer_aww.prepare();
            mediaPlayer_person_cheering.prepare();
        } catch (Exception e) {
            e.printStackTrace();
        }

        typeface = Typeface.createFromAsset(getAssets(), "comics.ttf");
        timerField.setTypeface(typeface);
        level.setTypeface(typeface);
        s.setTypeface(typeface);
        time_up.setTypeface(typeface);
    }

    public void onStartClick(View view) {

        Canvas canvas = new Canvas();
        Paint paint = new Paint();
        canvas.drawPaint(paint);
        paint.setColor(Color.BLUE);
        paint.setTextSize(16);
        canvas.drawText("My Text", 500, 500, paint);

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawPaint(paint);

        paint.setColor(Color.WHITE);
        paint.setTextSize(20);
        canvas.drawText("Some Text", 10, 25, paint);

        Animation animation = AnimationUtils.loadAnimation(MainActivity.this, R.anim.text_animation);
        textView = (TextView) findViewById(R.id.anim_text);
        textView.startAnimation(animation);
        textView.setVisibility(View.VISIBLE);

        timerField.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        Display display = getWindowManager().getDefaultDisplay();
        displayWidth = display.getWidth();
        displayHeight = display.getHeight();


        View t = findViewById(R.id.hitme);
        //t.setVisibility(View.VISIBLE);

        CountDownTimer timer = new CountDownTimer(30000, 1000) {

            View h = findViewById(R.id.hitme);
            View a = findViewById(R.id.again);

            public void onTick(long millisUntilFinished) {
                timerField.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {

                //timerField.setText("Time's Up Bro!");
                time_up.setVisibility(View.VISIBLE);
                timerField.setTextColor(getResources().getColor(android.R.color.holo_purple));
                h.setVisibility(View.GONE);
                a.setVisibility(View.VISIBLE);

                if (score < 30) {
                    mediaPlayer_aww.start();
                    time_up.setText("Level failed");
                    time_up.setVisibility(View.VISIBLE);
                    again.setText("Play again??!");
                } else {
                    mediaPlayer_person_cheering.start();
                    time_up.setText("level completed");
                    again.setText("Play next level??!");
                }
            }
        }.start();

        View s = findViewById(R.id.start);
        s.setVisibility(View.GONE);

        Random r = new Random();

        int x = r.nextInt(displayWidth);
        int y = r.nextInt(displayHeight);

        System.out.println("width and height " + displayWidth + "  " + displayHeight + "\n" + x + "  " + y);
        System.out.println("x and y " + x + "  " + y);
        /*if (y < 100) {
            //y += 100;
            y = y + 100;
        }
        if (x < 100) {
            //x += 100;
            x = x + 100;
        }
        if (x > (displayWidth - 250)) {
            //x -= 250;
            x = x - 250;
        }
        if (y > (displayHeight - 250)) {
            //y -= 250;
            y = y - 250;
        }*/

        System.out.println("after manupulation " + x + "  " + y);
        t.setX(x / 4);
        t.setY(y / 4);
        t.setVisibility(View.VISIBLE);
    }

    public void onAgainClick(View view) {

        time_up.setVisibility(View.GONE);

        timerField.setTextColor(getResources().getColor(android.R.color.holo_red_light));
        Display display = getWindowManager().getDefaultDisplay();
        displayWidth = display.getWidth();
        displayHeight = display.getHeight();
        score = 0;

        //s = (TextView) (findViewById(R.id.score));
        s.setText("Score: " + score);

        View t = findViewById(R.id.hitme);
        t.setVisibility(View.VISIBLE);

        CountDownTimer timer = new CountDownTimer(30000, 1000) {

            View h = findViewById(R.id.hitme);
            View a = findViewById(R.id.again);

            public void onTick(long millisUntilFinished) {
                timerField.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                //timerField.setText("Time's Up Bro!");
                time_up.setVisibility(View.VISIBLE);
                timerField.setTextColor(getResources().getColor(android.R.color.holo_purple));
                h.setVisibility(View.GONE);
                a.setVisibility(View.VISIBLE);

                System.out.println("score" + score);
                if (score < 30) {
                    mediaPlayer_aww.start();
                    time_up.setText("Level faild!");
                    time_up.setVisibility(View.VISIBLE);
                    again.setText("Play again??!");
                } else {
                    mediaPlayer_person_cheering.start();
                    again.setText("Next level");
                    time_up.setText("Level completed");
                    time_up.setVisibility(View.VISIBLE);
                }
            }
        }.start();

        View a = findViewById(R.id.again);
        a.setVisibility(View.GONE);

        Random r = new Random();

        int x = r.nextInt(displayWidth);
        int y = r.nextInt(displayHeight);
        System.out.println("" + x + ", " + y);
        if (y < 100) {
            y += 100;
        }
        if (x < 100) {
            x += 100;
        }
        if (x > (displayWidth - 250)) {
            x -= 250;
        }
        if (y > (displayHeight - 250)) {
            y -= 250;
        }
        t.setX(x);
        t.setY(y);
        System.out.println("kdkssld" + x + "////" + y);
    }


    public void onTapClick(View view) {

        mediaPlayer_swipe_sound.start();
        View t = findViewById(R.id.hitme);

        score++;
        //s = (TextView) (findViewById(R.id.score));
        s.setText("Score: " + score);

        Random r = new Random();

        int x = r.nextInt(displayWidth);
        int y = r.nextInt(displayHeight);

        System.out.println("" + x + ", " + y);
        if (y < 100) {
            y += 100;
        }
        if (x < 100) {
            x += 100;
        }
        if (x > (displayWidth - 250)) {
            x -= 250;
        }
        if (y > (displayHeight - 250)) {
            y -= 250;
        }
        t.setX(x);
        t.setY(y);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }


}
