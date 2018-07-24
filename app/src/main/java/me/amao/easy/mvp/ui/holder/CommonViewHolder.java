package me.amao.easy.mvp.ui.holder;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.AlphaAnimation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import me.amao.easy.mvp.ui.widget.CircleView;


/**
 * Created by tanghuan on 2018/3/17.
 * Supported By 甜瓜移动.
 * Official Website: www.melonmobile.cn.
 *
 * @author tanghuan
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> mViews;
    private View mConvertView;
    private Context mContext;

    public CommonViewHolder(Context context, View itemView, ViewGroup parent) {
        super(itemView);
        mContext = context;
        mConvertView = itemView;
        mViews = new SparseArray<View>();
    }

    public static CommonViewHolder get(Context context, ViewGroup parent, int layoutId) {

        View itemView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        CommonViewHolder holder = new CommonViewHolder(context, itemView, parent);
        return holder;
    }

    /**
     * 通过viewId获取控件
     *
     * @param viewId
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T) view;
    }

    public void updatePosition(int position) {

    }

    public CommonViewHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    public CommonViewHolder setDrawableLever(int viewId, int maxLever/*, int drawbleId*/) {
        View v = getView(viewId);
//        Drawable drawable = ContextCompat.getDrawable(mContext,drawbleId);
//        drawable.setLevel(maxLever);
//        v.setBackground(drawable);
        v.getBackground().setLevel(maxLever);
        return this;
    }


    public CommonViewHolder setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public CommonViewHolder setImageUrl(int viewId, String url) {
        ImageView view = getView(viewId);
        Glide.with(mContext).load(url).into(view);
        return this;
    }



    public CommonViewHolder setOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public CommonViewHolder setOnItemClickListener(View.OnClickListener listener) {
        mConvertView.setOnClickListener(listener);
        return this;
    }
    public CommonViewHolder setTextCorlor(int viewId, int colorId) {
        TextView view = getView(viewId);
        view.setTextColor(ContextCompat.getColor(mContext,colorId));
        return this;
    }

    public CommonViewHolder setOnItemTouchListener( View.OnTouchListener onTouchListener) {
        itemView.setOnTouchListener(onTouchListener);
        return this;
    }
    public CommonViewHolder setBackground(int colorId) {
        itemView.setBackgroundColor(ContextCompat.getColor(mContext,colorId));
        return this;
    }

    public CommonViewHolder setAlpha(float alpha) {
        itemView.setAlpha(alpha);
        return this;
    }


    public void viewDoAnim(int viewId){
        View view = getView(viewId);
        AnimationSet set = new AnimationSet(true);
        AlphaAnimation alphaAnimation = new AlphaAnimation(0,1);
        TranslateAnimation translateAnimation = new TranslateAnimation(0,0,0,12);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0,1,0,1);
        set.addAnimation(scaleAnimation);
        set.addAnimation(alphaAnimation);
        set.addAnimation(translateAnimation);
        set.setDuration(200);
        view.startAnimation(set);
    }

    public void doRoate(int viewId,boolean way){
        View view = getView(viewId);
        RotateAnimation animation = new RotateAnimation(way?0f:180f,way?180f:0f,0.5f,0.5f);
        animation.setDuration(50);
        view.startAnimation(animation);
    }
    public CommonViewHolder setVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public CommonViewHolder setItemClickble(boolean clickble) {
        itemView.setEnabled(clickble);
        return this;
    }


    public CommonViewHolder setCircleViewClolor(int viewId, int i) {
        CircleView circleView = getView(viewId);
        circleView.setColor(i);
        return this;

    }

    public CommonViewHolder setOnLongClickListener(View.OnLongClickListener longClickListener) {
        itemView.setOnLongClickListener(longClickListener);
        return this;
    }

    public CommonViewHolder setTextViewGravity(int tvId) {
        final TextView tv = getView(tvId);
        tv.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                tv.setGravity(tv.getLineCount()>1?Gravity.LEFT:Gravity.RIGHT);
                return true;
            }
        });
        return this;

    }
}

