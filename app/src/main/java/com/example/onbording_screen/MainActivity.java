package com.example.onbording_screen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


     ViewPager nSliderViewPager;
     LinearLayout nDotlayout;
     Button Backbtn,Nextbtn, Skipbtn;

     TextView[] dots;
     ViewPageAdapter viewPageAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Backbtn =findViewById(R.id.backbtn);
        Nextbtn =findViewById(R.id.nextbtn);
        Skipbtn =findViewById(R.id.SkipButton);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) > 0){
                    nSliderViewPager.setCurrentItem(getitem(-1),true);
                }
            }
        });

        Nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getitem(0) <3)
                    nSliderViewPager.setCurrentItem(getitem(1),true);
                else {
                    Intent i=new Intent(MainActivity.this,mainscreen.class);
                    startActivity(i);
                    finish();

                }
            }
        });


        Skipbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(MainActivity.this,mainscreen.class);
                startActivity(i);
                finish();


            }
        });

        nSliderViewPager =(ViewPager) findViewById(R.id.slideViewPage);
        nDotlayout =(LinearLayout) findViewById(R.id.indicator_layout);

        viewPageAdapter =new ViewPageAdapter(this);

        nSliderViewPager.setAdapter(viewPageAdapter);

        setUpindicator(0);
        nSliderViewPager.addOnPageChangeListener(viewListener);
    }

    public void setUpindicator(int position){
        dots =new TextView[4];
        nDotlayout.removeAllViews();

        for (int i =0; i< dots.length; i++){
            dots[i]=new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                dots[i].setTextColor(getResources().getColor(R.color.inactive,getApplicationContext().getTheme()));
            }
            nDotlayout.addView(dots[i]);


        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            dots[position].setTextColor(getResources().getColor(R.color.active,getApplicationContext().getTheme()));
        }
    }

    ViewPager.OnPageChangeListener viewListener =new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            setUpindicator(position);

            if (position >0){
                Backbtn.setVisibility(View.VISIBLE);
            }else {
                Backbtn.setVisibility(View.INVISIBLE);
            }

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    private int getitem(int i){

        return nSliderViewPager.getCurrentItem() + i;
    }
}