package com.oo2oo.linkage.adapter;

import android.content.Context;

import com.oo2oo.linkage.bean.GoodsType;
import com.oo2oo.linkage.wheelview.WheelView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2018/8/16.
 */

public class GoodsTypeAdapter <T> extends BaseAdapter {
    List<GoodsType> goodsType;

    public GoodsTypeAdapter(Context context, List<GoodsType> goodsType) {
        super(context);
        this.goodsType = goodsType;
    }


    @Override
    public int getItemsCount() {
        return goodsType.size();
    }

    @Override
    public void setNextAdapter(WheelView wheelView) {
        List<GoodsType> list = getItemsObj(postion).children;
        BaseAdapter strAdapter;
        if (list == null || list.size() == 0) {
            strAdapter = new GoodsTypeAdapter<GoodsType>(context, new ArrayList<GoodsType>());
        } else {
            strAdapter = new GoodsTypeAdapter<GoodsType>(context, list);

        }
        wheelView.setViewAdapter(strAdapter);
        wheelView.setCurrentItem(0);
    }

    @Override
    public GoodsType getItemsObj(int postion) {
        return goodsType.get(postion);
    }

    @Override
    public CharSequence getItemText(int index) {
        if (index < getItemsCount()) {
            return goodsType.get(index).label;

        }
        return "";
    }
}