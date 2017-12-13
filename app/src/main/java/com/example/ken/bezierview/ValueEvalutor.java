package com.example.ken.bezierview;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Ken on 2017/12/13.
 */

public class ValueEvalutor implements TypeEvaluator<PointF>{
    private PointF pointF1;
    private PointF pointF2;

    public ValueEvalutor(PointF pointF1, PointF pointF2) {
        this.pointF1 = pointF1;
        this.pointF2 = pointF2;
    }

    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        PointF result = new PointF();
        result.x = startValue.x*(1-t)*(1-t)*(1-t)+3*pointF1.x*t*(1-t)*(1-t)+3*pointF2.x*t*t*(1-t)+endValue.x*t*t*t;
        result.y = startValue.y*(1-t)*(1-t)*(1-t)+3*pointF1.y*t*(1-t)*(1-t)+3*pointF2.y*t*t*(1-t)+endValue.y*t*t*t;
        return result;
    }
}
