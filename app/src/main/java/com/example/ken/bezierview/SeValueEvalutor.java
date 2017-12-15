package com.example.ken.bezierview;

import android.animation.TypeEvaluator;
import android.graphics.PointF;

/**
 * Created by Ken on 2017/12/15.
 */

public class SeValueEvalutor implements TypeEvaluator<PointF> {
    private PointF pointF1;

    public SeValueEvalutor(PointF pointF1) {
        this.pointF1 = pointF1;
    }

    @Override
    public PointF evaluate(float t, PointF startValue, PointF endValue) {
        PointF result = new PointF();
        result.x = startValue.x*(1-t)*(1-t) + 2*pointF1.x*(1-t)*t + t*t*endValue.x;
        result.y = startValue.y*(1-t)*(1-t) + 2*pointF1.y*(1-t)*t + t*t*endValue.y;
        return result;
    }
}
