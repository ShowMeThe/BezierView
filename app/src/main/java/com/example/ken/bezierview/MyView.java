package com.example.ken.bezierview;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by Ken 2017/11/29.
 */
public class MyView extends RelativeLayout {
    private ImageView imageView = new ImageView(getContext());
    private Drawable[] drawables = new Drawable[3];
    private Random random = new Random();
    private LayoutParams params;
    private int mWidth,mHeight,dWidth,dHeight;
    private Paint paint;
    private Path path;
    PointF pointF = new PointF();
    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    public void onMeasure(int Width,int Height){
        super.onMeasure(Width,Height);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
    }
    private void init(){
        drawables[0] = getResources().getDrawable(R.drawable.radius_img_blue,null);
        drawables[1] = getResources().getDrawable(R.drawable.radius_img_green,null);
        drawables[2] = getResources().getDrawable(R.drawable.radius_img_yellow,null);
        dWidth = drawables[0].getIntrinsicWidth();
        dHeight = drawables[0].getIntrinsicHeight();
         if(path==null || paint == null){
             paint = new Paint();
             path = new Path();
         }

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event){
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            removeView(imageView);

            pointF.x = event.getX();
            pointF.y = event.getY();
            addDrawable(pointF);
        }
        return true;
    }
    private void addDrawable(PointF pointF){
        params = new LayoutParams(dWidth,dHeight);
        params.width = params.height = 50;
        params.leftMargin = (int) ((pointF.x - params.width/2));
        params.topMargin = (int) ((pointF.y - params.width/2));
        imageView.setImageDrawable(drawables[random.nextInt(3)]);
        AnimatorSet set = getObjectAnimator(imageView);
        addView(imageView,params);
        set.start();

    }
    private AnimatorSet getObjectAnimator(ImageView imageView){
        ObjectAnimator animX = ObjectAnimator.ofFloat(imageView,"translationX",0.0f,1.0f);
        ObjectAnimator  animY = ObjectAnimator.ofFloat(imageView,"translationY",0.0f,1.0f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView,"alpha",0.0f,1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animX,animY,alpha);
        ValueAnimator animator = getValeAnimation(imageView,pointF);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animatorSet,animator);
        set.setTarget(imageView);
        return set;
    }

    private ValueAnimator getValeAnimation(final ImageView imageView, final PointF pointF){
        PointF pointF0 = new PointF();
        pointF0.x = pointF.x - params.width/2;
        pointF0.y = pointF.y - params.width/2;

        PointF pointFc1 = new PointF();
        pointFc1.x = random.nextInt(mWidth);
        pointFc1.y = random.nextInt(mHeight);


        PointF pointFc2 = new PointF();
        pointFc2.x = random.nextInt(mWidth);
        pointFc2.y = random.nextInt(mHeight);

        PointF pointFe;
        if(pointF0.y < mHeight/2){
            pointFe = new PointF(random.nextInt(mWidth),mHeight - 100);

        }else {
            pointFe = new PointF(random.nextInt(mWidth),0);
        }

        ValueEvalutor evalutor = new ValueEvalutor(pointFc1,pointFc2);
        ValueAnimator animator = ValueAnimator.ofObject(evalutor,pointF0,pointFe);
        animator.setDuration(3000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF1 = (PointF) animation.getAnimatedValue();
                imageView.setX(pointF1.x);
                imageView.setY(pointF1.y);
            }
        });

        return animator;
    }
}
