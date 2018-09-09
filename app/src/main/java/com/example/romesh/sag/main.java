package com.example.romesh.sag;

import android.graphics.Point;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class main extends AppCompatActivity {


    private TextView scoreLabel;
    private TextView startLabel;
    private ImageView box;
    private ImageView orange;
    private ImageView pink;
    private ImageView black;


    //Size
    private int frameHeight;
    private int boxSize;
    private int screenWidth;
    private int screenHeight;

    //Position
    private int boxY;
    private int orangeX;
    private int orangeY;
    private int pinkX;
    private int pinkY;
    private int blackX;
    private int blackY;



    //Initialize Class
    private Handler handler = new Handler();
    private Timer timer = new Timer();


    //status check
    private boolean action_flg = false;
    private boolean start_flg = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        startLabel = (TextView) findViewById(R.id.startLabel);
        box = (ImageView) findViewById(R.id.box);
        orange = (ImageView) findViewById(R.id.orange);
        pink = (ImageView) findViewById(R.id.pink);
        black = (ImageView) findViewById(R.id.black);

        //Get the screen size
        WindowManager wn = getWindowManager();
        Display disp  = wn.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        screenWidth = size.x;
        screenWidth = size.y;




        // Move to out of screen.
        orange.setX(-80);
        orange.setY(-80);
        pink.setX(-80);
        pink.setY(-80);
        black.setX(-80);
        black.setY(-80);






    }





    public void changePos() {




        //orange
        orangeX -= 12;
        if(orangeX < 0) {
            orangeX = screenWidth + 20;
            orangeY = (int) Math.floor(Math.random() * (frameHeight - orange.getHeight()));
        }
        orange.setX(orangeX);
        orange.setY(orangeY);

        //Black
        blackX -= 16;

        if(blackX<0){
            blackX = screenWidth+10;
            blackY = (int ) Math.floor(Math.random() * (frameHeight - black.getHeight()));

        }
        black.setX(blackX);
        black.setY(blackY);

        //Pink
        pinkX -= 20;
        if (pinkX < 0) {
            pinkX = screenWidth + 5000;
            pinkY = (int) Math.floor(Math.random() * (frameHeight - pink.getHeight()));
        }
        pink.setX(pinkX);
        pink.setY(pinkY);

        //Move BOx
        if (action_flg == true){
            //Touching
            boxY -= 20;
        }else{
            //Releasing
            boxY += 20;
        }
            //Check box positions
        if(boxY <0) boxY = 0;
        if(boxY > frameHeight - boxSize) boxY = frameHeight - boxSize;
        box.setY(boxY);
    }


    public boolean onTouchEvent(MotionEvent me){


        if(start_flg == false){


            start_flg = true;

            //Why get frame height and box height here?
            //Because the UI has been set on the screen in onCreate()!!

            FrameLayout frame_= (FrameLayout) findViewById(R.id.frame);
            frameHeight = frame_.getHeight();

            boxY = (int)box.getY();

            //The box is a square
            boxSize = box.getHeight();
            startLabel.setVisibility(View.GONE);

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run(){
                            changePos();
                        }
                    });
                }
            },0,20);




        }else{
            if(me.getAction() == MotionEvent.ACTION_DOWN){

                action_flg = true;


            }else if (me.getAction() == MotionEvent.ACTION_UP){
                action_flg = false;
            }
        }






        return true;

    }



}



