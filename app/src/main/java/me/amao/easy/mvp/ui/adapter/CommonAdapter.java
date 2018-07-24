package me.amao.easy.mvp.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import me.amao.easy.mvp.ui.holder.CommonViewHolder;

/**
 * Created by tanghuan on 2018/3/17.
 * Supported By 甜瓜移动.
 * Official Website: www.melonmobile.cn.
 *
 * @author tanghuan
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    public CommonAdapter(Context context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position),position);
    }

    public abstract void convert(CommonViewHolder holder, T t,int position);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void appendListData(List<T> mDatas){
        if(mDatas != null && mDatas.size()>0){
            this.mDatas.addAll(mDatas);
        }
        notifyDataSetChanged();
    }
    public void removeItem(int position){
        mDatas.remove(position);
        notifyItemRemoved(position);
        if (position!= mDatas.size()&& mDatas.size()!=0){
            notifyItemRangeChanged(position,mDatas.size()-position);
        }
    }
}
