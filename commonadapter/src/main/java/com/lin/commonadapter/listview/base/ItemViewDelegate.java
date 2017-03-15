package com.lin.commonadapter.listview.base;

import com.lin.commonadapter.listview.ViewHolder;

/**
 * 作者:  C001
 * 创建日期:2017/3/1511:42
 * 版权所有:深圳市捷乘网络有限公司
 */

public interface ItemViewDelegate<T> {
    public abstract int getItemViewLayoutId();

    public abstract boolean isForViewType(T item, int position);

    public abstract void convert(ViewHolder viewHolder, T t, int position);
}
