package com.digifactbeta;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;



public class Swch extends Button {


    public Boolean isTurnedOn = false;

    public void setIsTurnedOn (Boolean swchStatus){
        isTurnedOn = swchStatus;
        changeColor();
    }

    public Swch(Context context) {
        super(context);
        this.setTextColor(Color.BLUE);
        this.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                isTurnedOn = !isTurnedOn;
                changeColor();
            }
        });
        changeColor();





    }

    private void changeColor() {
        if (isTurnedOn) {
            setBackgroundColor(Color.YELLOW);
            setText("I am ON");
        } else {
            setBackgroundColor(Color.GRAY);
            setText("I am OFF");
        }
    }

    public Swch(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public Swch(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
}