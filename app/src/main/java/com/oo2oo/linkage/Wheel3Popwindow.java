package com.oo2oo.linkage;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.oo2oo.linkage.adapter.BaseAdapter;
import com.oo2oo.linkage.wheelview.WheelView;
import com.oo2oo.linkage.wheelview.listener.OnWheelChangedListener;
import com.oo2oo.linkage.wheelview.listener.OnWheelScrollListener;

/**

 * Description:三级联动，商品选择
 */
public class Wheel3Popwindow extends PopupWindow implements View.OnClickListener {

    private TextView btnSure;
    private TextView btnCancel;
    private TextView tv_show_all;

    private OnBirthListener onBirthListener;
    private WheelView wv_sel1;
    private WheelView wv_sel2;
    private WheelView wv_sel3;
    private BaseAdapter parentAdapter;
    private BaseAdapter childrenAdapter;
    private BaseAdapter sunAdapter;

    private int parentIndex;
    private int childrenIndex;
    private int sunIndex;


    public Wheel3Popwindow(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.popwindow_wheel_level, null);
        btnSure = (TextView) view.findViewById(R.id.btn_myinfo_sure);
        btnCancel = (TextView) view.findViewById(R.id.btn_myinfo_cancel);
        tv_show_all = (TextView) view.findViewById(R.id.tv_show_all);

        wv_sel1 = (WheelView) view.findViewById(R.id.wv_sel1);
        wv_sel2 = (WheelView) view.findViewById(R.id.wv_sel2);
        wv_sel3 = (WheelView) view.findViewById(R.id.wv_sel3);


        //设置SelectPicPopupWindow的View
        this.setContentView(view);
        //设置SelectPicPopupWindow弹出窗体的宽
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体的高
        this.setHeight(ViewGroup.LayoutParams.MATCH_PARENT);
        //设置SelectPicPopupWindow弹出窗体可点击
        this.setFocusable(true);
        //设置SelectPicPopupWindow弹出窗体动画效果
//		this.setAnimationStyle(R.style.AnimBottom);
        //实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        //设置SelectPicPopupWindow弹出窗体的背景
        this.setBackgroundDrawable(dw);

        btnSure.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }


    public Wheel3Popwindow setParentAdapter(final BaseAdapter parentAdapter) {
        parentIndex = 0;
        wv_sel2.setVisibility(View.GONE);
        wv_sel3.setVisibility(View.GONE);
        this.parentAdapter = parentAdapter;
        wv_sel1.setVisibleItems(5);
        wv_sel1.setViewAdapter(parentAdapter);
        wv_sel1.setCurrentItem(parentIndex);

        wv_sel1.addChangingListener(new OnWheelChangedListener() {
            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                parentIndex = wheel.getCurrentItem();
                parentAdapter.setSelectIndex(parentIndex);

                if (wv_sel2.getVisibility() == View.VISIBLE) {
                    parentAdapter.setNextAdapter(wv_sel2);
                    //设置第三个列表
                    childrenAdapter = (BaseAdapter) wv_sel2.getViewAdapter();
                    childrenIndex = 0;
                    if (childrenAdapter.getItemsCount() > 0) {
                        childrenAdapter.setSelectIndex(childrenIndex);
                        childrenAdapter.setNextAdapter(wv_sel3);

                        sunAdapter = (BaseAdapter) wv_sel3.getViewAdapter();
                        sunIndex = wv_sel3.getCurrentItem();
                    }
                }
                changeText();

            }
        });

        wv_sel1.addScrollingListener(new OnWheelScrollListener() {
            @Override
            public void onScrollingStarted(WheelView wheel) {

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                parentIndex = wheel.getCurrentItem();
                parentAdapter.setSelectIndex(parentIndex);

                if (wv_sel2.getVisibility() == View.VISIBLE) {
                    parentAdapter.setNextAdapter(wv_sel2);
                    //设置第三个列表
                    childrenAdapter = (BaseAdapter) wv_sel2.getViewAdapter();
                    childrenIndex = 0;
                    if (childrenAdapter.getItemsCount() > 0) {
                        childrenAdapter.setSelectIndex(childrenIndex);
                        childrenAdapter.setNextAdapter(wv_sel3);

                        sunAdapter = (BaseAdapter) wv_sel3.getViewAdapter();
                        sunIndex = wv_sel3.getCurrentItem();
                    }
                }
                changeText();
            }
        });
        return this;
    }


    public Wheel3Popwindow setChildrenAdapter(BaseAdapter _childrenAdapter) {
        if (_childrenAdapter == null)
            return this;
        wv_sel2.setVisibility(View.VISIBLE);
        childrenIndex = 0;
        this.childrenAdapter = _childrenAdapter;
        wv_sel2.setVisibleItems(5);
        wv_sel2.setViewAdapter(childrenAdapter);
        wv_sel2.setCurrentItem(childrenIndex);


        wv_sel2.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                childrenIndex = wheel.getCurrentItem();
                childrenAdapter = (BaseAdapter) wv_sel2.getViewAdapter();
                if (childrenAdapter == null)
                    return;
                childrenAdapter.setSelectIndex(childrenIndex);
                childrenAdapter.setNextAdapter(wv_sel3);

                sunAdapter = (BaseAdapter) wv_sel3.getViewAdapter();
                sunIndex = wv_sel3.getCurrentItem();
                changeText();

            }
        });

        wv_sel2.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                childrenIndex = wheel.getCurrentItem();
                childrenAdapter = (BaseAdapter) wv_sel2.getViewAdapter();
                if (childrenAdapter == null)
                    return;
                childrenAdapter.setSelectIndex(childrenIndex);
                childrenAdapter.setNextAdapter(wv_sel3);

                sunAdapter = (BaseAdapter) wv_sel3.getViewAdapter();
                sunIndex = wv_sel3.getCurrentItem();
                changeText();

            }
        });
        return this;

    }

    public Wheel3Popwindow setSunAdapter(BaseAdapter _sunAdapter) {
        if (_sunAdapter == null)
            return this;
        wv_sel3.setVisibility(View.VISIBLE);
        sunIndex = 0;
        this.sunAdapter = _sunAdapter;
        wv_sel3.setVisibleItems(5);
        wv_sel3.setViewAdapter(sunAdapter);
        wv_sel3.setCurrentItem(sunIndex);

        wv_sel3.addChangingListener(new OnWheelChangedListener() {

            @Override
            public void onChanged(WheelView wheel, int oldValue, int newValue) {
                sunIndex = wheel.getCurrentItem();
                sunAdapter = (BaseAdapter) wv_sel3.getViewAdapter();
                if (sunAdapter == null)
                    return;
                sunAdapter.setSelectIndex(sunIndex);
                changeText();

            }
        });

        wv_sel3.addScrollingListener(new OnWheelScrollListener() {

            @Override
            public void onScrollingStarted(WheelView wheel) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScrollingFinished(WheelView wheel) {
                sunIndex = wheel.getCurrentItem();
                sunAdapter = (BaseAdapter) wv_sel3.getViewAdapter();
                if (sunAdapter == null)
                    return;
                sunAdapter.setSelectIndex(sunIndex);
                changeText();
            }
        });
        return this;
    }


    private void changeText() {
        CharSequence str = parentAdapter.getItemText(parentIndex);
        if (childrenAdapter != null) {
            str = str + "->" + childrenAdapter.getItemText(childrenIndex);
        }
        if (sunAdapter != null) {
            str = str + "->" + sunAdapter.getItemText(sunIndex);
        }
        tv_show_all.setText(str);
    }


    public void setBirthdayListener(OnBirthListener onBirthListener) {
        this.onBirthListener = onBirthListener;
    }

    @Override
    public void onClick(View v) {

        if (v == btnSure) {
            if (onBirthListener != null) {
                if (parentAdapter != null && childrenAdapter != null && sunAdapter != null) {
                    onBirthListener.onClick(parentAdapter.getItemsObj(parentIndex),
                            childrenAdapter.getItemsObj(childrenIndex), parentAdapter.getItemsObj(sunIndex));
                } else if (parentAdapter != null && childrenAdapter == null && sunAdapter == null) {
                    onBirthListener.onClick(parentAdapter.getItemsObj(parentIndex),
                            null, null);
                }
                Log.d("cy", tv_show_all.getText().toString());
            }
        } else {
            dismiss();
        }
        dismiss();

    }

    public interface OnBirthListener<P, C, T> {
        public void onClick(P parent, C child, T sun);
    }


}