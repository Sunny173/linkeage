package com.oo2oo.linkage.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.oo2oo.linkage.MainActivity;
import com.oo2oo.linkage.bean.UnitType;

import java.util.List;

/**
 * Created by sun on 2018/8/31.
 */

public class RecyclerAdapter extends RecyclerView.Adapter {
    Context context;
    List<UnitType> units;

    public RecyclerAdapter(Context context, List<UnitType> units) {
        this.context = context;
        this.units = units;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new TextView(context ));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder _holder, int position) {
        ViewHolder holder = (ViewHolder) _holder;
        holder.textView.setText(units.get(position).name);

    }

    @Override
    public int getItemCount() {
        return units.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        /*
         * 在 ViewHolder内部类里初始化布局中的view
         * */
        ViewHolder(View itemView) {
            super(itemView);
            //注意这里的view和Activity不一样，通过参数itemView来find
            textView = (TextView) itemView;
            textView.setGravity(Gravity.CENTER);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            textView.setLayoutParams(layoutParams);

        }
    }
}
