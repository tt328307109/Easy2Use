package me.amao.easy.mvp.ui.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

import me.amao.easy.R;


/**
 * Created by tanghuan on 2018/3/16.
 * Supported By 甜瓜移动.
 * Official Website: www.melonmobile.cn.
 *
 * @author tanghuan
 */
public class CircleView extends View {
    private int color;
    private float radii;

    public CircleView(Context context) {
        super(context,null);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.Circle);
        color = ta.getColor(R.styleable.Circle_circle_color, ContextCompat.getColor(getContext(),android.R.color.holo_red_dark));
        radii = ta.getDimension(R.styleable.Circle_circle_radii,3);
        ta.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(1);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,radii,paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension((int)radii*2+1,(int)radii*2+1);
    }

    public void setColor(int colorId) {
        this.color = ContextCompat.getColor(getContext(),colorId);
        invalidate();
    }

    public void setRadii(float radii) {
        this.radii = radii;
        invalidate();
    }
}
