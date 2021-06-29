package com.example.testexample;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        View view = findViewById(R.id.draw_view);
//
//        ObjectAnimator topFlip = ObjectAnimator.ofFloat(view,"topFlip",0f,- 60f);
//        topFlip.setStartDelay(1000);
//        topFlip.setDuration(1000);
//
//        ObjectAnimator bottomFlip = ObjectAnimator.ofFloat(view,"bottomFlip",0f,60f);
//        bottomFlip.setStartDelay(200);
//        bottomFlip.setDuration(1000);
////
//        ObjectAnimator flioRotation = ObjectAnimator.ofFloat(view,"flipRotation",0f,270f);
//        flioRotation.setStartDelay(200);
//        flioRotation.setDuration(1000);
//
//        AnimatorSet set = new AnimatorSet();
//        set.playSequentially(bottomFlip,flioRotation,topFlip);
//        set.start();



      /*  findViewById(R.id.touch_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"dianji",Toast.LENGTH_SHORT).show();
            }
        });*/
    }
}