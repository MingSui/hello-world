package com.example.lesson10;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    MyAdapter myAdapter;

    DividerItemDecoration dividerItemDecoration;
    String[] mDataSet = {"醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间",
            "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间",
            "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间",
            "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间", "醉翁之意不在酒，在乎山水之间",};

    ImageView iv;
    AnimationDrawable animationDrawable;
    DisplayMetrics dm;
    boolean isLeft = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv = (ImageView) findViewById(R.id.iv);
        iv.setBackgroundResource(R.drawable.pig_right);
//        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
//        initRecyclerView();


        dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);


}

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        Log.e("TAG",iv.getWidth()+"Width");
        final int value = dm.widthPixels - iv.getWidth();
        Log.e("TAG","value之前等于"+value);
        ObjectAnimator anim = ObjectAnimator.ofFloat(iv, "translationX", 0, value);
        anim.setRepeatMode(ValueAnimator.REVERSE);
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setDuration(1000);

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.e("TAG","anim已经开始");
                iv.setBackgroundResource(R.drawable.pig_right);
                animationDrawable = (AnimationDrawable) iv.getBackground();
                animationDrawable.start();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.e("TAG","anim已经结束");
                iv.setBackgroundResource(R.drawable.pig_left);
                animationDrawable = (AnimationDrawable) iv.getBackground();
                animationDrawable.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.e("TAG","当重复时"+iv.getBackground());
                if(isLeft) {
                    iv.setBackgroundResource(R.drawable.pig_right);
                }else {
                    iv.setBackgroundResource(R.drawable.pig_left);
                }
                isLeft = !isLeft;
                animationDrawable = (AnimationDrawable) iv.getBackground();
                animationDrawable.start();
            }
        });

        anim.start();
    }


    private void initRecyclerView() {
        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        dividerItemDecoration = new DividerItemDecoration(mRecyclerView.getContext(), LinearLayoutManager.VERTICAL);
        mRecyclerView.addItemDecoration(dividerItemDecoration);

        myAdapter = new MyAdapter(this, mDataSet);
        mRecyclerView.setAdapter(myAdapter);
    }
}
