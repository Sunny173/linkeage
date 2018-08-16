package com.oo2oo.linkage.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.oo2oo.linkage.R;
import com.oo2oo.linkage.wheelview.WheelView;
import com.oo2oo.linkage.wheelview.adapter.AbstractWheelAdapter;

/**
 * Created by sun on 2018/8/14.
 */

public abstract class BaseAdapter<T> extends AbstractWheelAdapter {
    private int maxTextSize = 20;
    private int minTextSize = 20;
    public int postion;

    public Context context;

    protected BaseAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getItem(int index, View convertView, ViewGroup parent) {
        MyHolder myHolder = new MyHolder();
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_wheel, null);
            myHolder.textView = convertView.findViewById(R.id.tempValue);
            convertView.setTag(myHolder);
        } else {
            myHolder = (MyHolder) convertView.getTag();
        }
        myHolder.textView.setText(getItemText(index));
        if (postion == index) {
            myHolder.textView.setTextSize(maxTextSize);
            myHolder.textView.setTextColor(Color.parseColor("#e53017"));
        } else {
            myHolder.textView.setTextSize(minTextSize);
            myHolder.textView.setTextColor(Color.parseColor("#000000"));
        }
        myHolder.textView.setGravity(Gravity.CENTER);

        return convertView;
    }

    /**
     * 给下一个列表设置adapter数据
     *
     * @param nextwheelView 表示下一个列表
     */
    public abstract void setNextAdapter(WheelView nextwheelView);

    /**
     * 根据角标获取列表的对象，
     * 1。可以得到下一个列表的数据
     * 2。记录当前选择时，用到。
     *
     * @param postion
     * @return
     */

    public abstract T getItemsObj(int postion);

    /**
     * 列表上要显示的文字
     *
     * @param index
     * @return
     */
    public abstract CharSequence getItemText(int index);

    public void setSelectIndex(int index) {
        postion = index;
        notifyDataChangedEvent();
    }


    public class MyHolder {
        public TextView textView;
    }

}
