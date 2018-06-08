package amoghjapps.com.stopwatch1;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static java.lang.Math.abs;
import static java.lang.Math.multiplyExact;

public class MainActivity extends AppCompatActivity {
    public long[] avglist = new long[10];
    long millisectime,timebuff, starttime,updatetime=0L;
    public int count=0,tapcount=0;
    int secs,mins,millsecs;
    int currentsecs,currentmins,currentmillis;
    public Handler myHandler;
    ConstraintLayout layout;
    MediaPlayer mplayer;
    TextView timer,timersave;
    int intcount=0;
    Button avg;
    int count2=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        mplayer=MediaPlayer.create(this,R.raw.hurry);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         timer = findViewById(R.id.timer);
         timer.setText("Ready?");
         timersave=findViewById(R.id.timersave);
        myHandler = new Handler();
        avg= findViewById(R.id.button);
        layout=findViewById(R.id.layout);
        layout.setBackgroundColor(Color.GREEN);
        avg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,average.class);
                startActivity(intent);
            }
        });
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tapcount==0){
                    starttime = SystemClock.uptimeMillis();
                    myHandler.post(runnable);
                    tapcount++;
                    count2++;
                }else if(tapcount==1){
                    if(secs<15){
                        currentsecs=secs;
                        currentmillis=millsecs;

                        timersave.setText("" + (15-currentsecs) + ":"
                                + String.format("%02d", (1000-currentmillis)));
                        starttime = SystemClock.uptimeMillis();

                        count=2;

                    myHandler.post(runnable);
                    tapcount++;
                    }
                    if(secs>=15){
                        starttime = SystemClock.uptimeMillis();

                        count=1;
                    }

                }else if(tapcount==2){
                    if(count2<=10){
                        avglist[count2-1]=millisectime;
                    }
                    myHandler.removeCallbacks(runnable);
                    tapcount++;
                    layout.setBackgroundColor(Color.GREEN);millisectime = 0L;
                    starttime = 0L;
                    timebuff=0L;
                    updatetime = 0L;
                    secs = 0;
                    mins = 0;
                    millsecs = 0;
                    count = 0;

                }else if(tapcount==3){
                    timer.setText("00:00:00");
                    timersave.setText("00:00");
                    tapcount=0;
                }

            }

        });

        };









    final Runnable runnable = new Runnable() {

        public void run() {
            if(count==0){
            millisectime= SystemClock.uptimeMillis()-starttime;
            updatetime=timebuff+millisectime;
            secs=(int)(updatetime/1000);
            secs=abs(14-secs%60);
            millsecs = (int) (1000-updatetime % 1000);
            timer.setText(String.format("%02d", secs) + ":"
                    + String.format("%03d", millsecs));
            myHandler.postDelayed(this,0);
                if(secs==3){
                    mplayer.start();
                  }
                }

            if(secs==0&&count==0){
            count=1;
            tapcount++;
            }
            if(count==1){
                if(intcount==0){
                    starttime = SystemClock.uptimeMillis();
                intcount++;
                }
                timersave.setText("00:00");

                millisectime= SystemClock.uptimeMillis()-starttime;
                updatetime=timebuff+millisectime;
                secs=(int)(updatetime/1000);
                mins=secs/60;
                secs=secs%60;
                millsecs = (int) (updatetime % 1000);
                timer.setText("" + mins + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%03d", millsecs));
                myHandler.postDelayed(this,0);
                layout.setBackgroundColor(Color.RED);
            }
            if(count==2){
                millisectime= SystemClock.uptimeMillis()-starttime;
                updatetime=timebuff+millisectime;
                secs=(int)(updatetime/1000);
                mins=secs/60;
                secs=secs%60;
                millsecs = (int)(updatetime % 1000);
                timer.setText("" + mins + ":"
                        + String.format("%02d", secs) + ":"
                        + String.format("%03d", millsecs));
                myHandler.postDelayed(this,0);
                layout.setBackgroundColor(Color.RED);
            }

            }



        };


    }







