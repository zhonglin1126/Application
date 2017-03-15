package com.lin.commonadapter.listview;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 作者:  C001
 * 创建日期:2017/3/1510:23
 * 版权所有:深圳市捷乘网络有限公司
 */

public class ViewHolder {
    private SparseArray<View> viewSparseArray;
    private View converView;
    private int position;

    public ViewHolder(Context context, ViewGroup parent, int layoutId, int position) {
        this.viewSparseArray = new SparseArray<>();
        this.position = position;
        converView = LayoutInflater.from(context).inflate(layoutId, parent, false);
        converView.setTag(this);
    }

    /***
     *获取viewholder
     * @param context
     * @param converView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View converView, ViewGroup parent, int layoutId, int position) {
        if (converView == null) {
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder) converView.getTag();
    }

    /***
     * 根据ViewID获取view
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId) {
        View view = viewSparseArray.get(viewId);
        if (view == null) {
            view = converView.findViewById(viewId);
            viewSparseArray.put(viewId, view);
        }
        return (T) view;
    }

    public ViewHolder setText(int viewId, String text) {
        TextView textview = getView(viewId);
        textview.setText(text);
        return this;
    }

    public ViewHolder setText(int viewId, int stringid) {
        TextView textview = getView(viewId);
        textview.setText(stringid);
        return this;
    }

    public ViewHolder setImageResource(int viewId, int drawableId) {
        ImageView imageview = getView(viewId);
        imageview.setImageResource(drawableId);
        return this;
    }

    public ViewHolder setImageBitmap(int viewId, Bitmap bm) {
        ImageView imageview = getView(viewId);
        imageview.setImageBitmap(bm);
        return this;
    }

    public ViewHolder setVisible(int viewId,int viewsble) {
        View view=getView(viewId);
        view.setVisibility(viewsble);
        return this;
    }

    public View getConverView() {
        return converView;
    }

    public int getPosition() {
        return position;
    }

}
