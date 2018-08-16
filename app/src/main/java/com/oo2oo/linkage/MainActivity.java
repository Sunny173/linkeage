package com.oo2oo.linkage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.oo2oo.linkage.adapter.GoodsTypeAdapter;
import com.oo2oo.linkage.adapter.BaseAdapter;
import com.oo2oo.linkage.adapter.UnitTypeAdapter;
import com.oo2oo.linkage.bean.GoodsType;
import com.oo2oo.linkage.bean.TestData;
import com.oo2oo.linkage.bean.UnitType;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // Content View Elements

    private TextView tv_three_pop;
    private TextView tv_one_pop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_three_pop = (TextView) findViewById(R.id.tv_three_pop);
        tv_one_pop = (TextView) findViewById(R.id.tv_one_pop);
        findViewById(R.id.tv_three_pop).setOnClickListener(this);
        findViewById(R.id.tv_one_pop).setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_three_pop) {

            final List<GoodsType> goodsType = TestData.getList(this, R.raw.goods_classifiy, GoodsType.class);

            Wheel3Popwindow dialog = new Wheel3Popwindow(this);

            BaseAdapter pAdapter = new GoodsTypeAdapter<GoodsType>(this, goodsType);
            BaseAdapter cAdapter = new GoodsTypeAdapter<GoodsType>(this,
                    goodsType.get(0).children);
            BaseAdapter sunAdapter = new GoodsTypeAdapter<GoodsType>(this,
                    goodsType.get(0).children.get(0).children);

            dialog.setParentAdapter(pAdapter)
                    .setChildrenAdapter(cAdapter).setSunAdapter(sunAdapter);

            dialog.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            dialog.setBirthdayListener(new Wheel3Popwindow.OnBirthListener<GoodsType, GoodsType, GoodsType>() {

                @Override
                public void onClick(GoodsType parent, GoodsType child, GoodsType sun) {
                    String str = tv_three_pop.getText() + "       " + parent.label;
                    if (child != null) {
                        str = str + "->" + child.label;
                    }
                    if (sun != null) {
                        str = str + "->" + sun.label;
                    }
                    tv_three_pop.setText(str);
                }

            });

        } else if (view.getId() == R.id.tv_one_pop) {
            final List<UnitType> units = TestData.getList(this, R.raw.goods_type, UnitType.class);
            Wheel3Popwindow mChangeBirthDialog2 = new Wheel3Popwindow(this);
            BaseAdapter parentAdapter = new UnitTypeAdapter<UnitType>(this, units);

            mChangeBirthDialog2.setParentAdapter(parentAdapter);
            mChangeBirthDialog2.showAtLocation(view, Gravity.BOTTOM, 0, 0);
            mChangeBirthDialog2.setBirthdayListener(new Wheel3Popwindow.OnBirthListener<UnitType, UnitType, UnitType>() {
                @Override
                public void onClick(UnitType parent, UnitType child, UnitType sun) {
                    String str = tv_one_pop.getText() + "       " + parent.name;
                    tv_one_pop.setText(str);
                }
            });
        }
    }


}
