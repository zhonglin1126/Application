package com.lin.commonadapter.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * 作者:  C001
 * 创建日期:2017/3/1510:22
 * 版权所有:深圳市捷乘网络有限公司
 */

public abstract class MultiItemTypeAdapter<T> extends BaseAdapter {
    private LayoutInflater layoutInflater;
    private Context context;
    private List<T> datalist;
    private int layoutId;

    public MultiItemTypeAdapter(Context context, List<T> datalist, int layoutId) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.datalist = datalist;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return datalist == null ? 0 : datalist.size();
    }

    @Override
    public Object getItem(int position) {
        return datalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(convertView, parent, layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConverView();
    }

    protected abstract void convert(ViewHolder viewHolder, Object item);

    private ViewHolder getViewHolder(View convertView, ViewGroup parent, int layoutId, int position) {
        return ViewHolder.get(context, convertView, parent, layoutId, position);
    }
}
