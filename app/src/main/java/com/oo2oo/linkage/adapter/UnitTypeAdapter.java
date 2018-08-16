package com.oo2oo.linkage.adapter;

import android.content.Context;

import com.oo2oo.linkage.bean.UnitType;
import com.oo2oo.linkage.wheelview.WheelView;

import java.util.List;

/**
 * Created by sun on 2018/8/16.
 */

public class UnitTypeAdapter <T> extends BaseAdapter {
    List<UnitType> units;

    public UnitTypeAdapter(Context activity, List<UnitType> units) {
        super(activity);
        this.units = units;
    }

    @Override
    public void setNextAdapter(WheelView wv_sel2) {

    }

    @Override
    public UnitType getItemsObj(int postion) {
        return units.get(postion);
    }

    @Override
    public CharSequence getItemText(int index) {
        return units.get(index).name;
    }

    @Override
    public int getItemsCount() {
        return units.size();
    }
}