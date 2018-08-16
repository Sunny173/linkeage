package com.oo2oo.linkage.bean;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sun on 2018/7/31.
 */

public class TestData {
    public static boolean isTest = true;
    static Gson gson = new Gson();

    public static <T> List<T> getList(Context applicationContext, int merchant_data, Class<T> clazz) {
        if (isTest) {
            try {
                String str = readDataFromRaw(applicationContext, merchant_data);
                Type type = new ParameterizedTypeImpl(clazz);
                ArrayList<T> shopFoods = gson.fromJson(str, type);
                return shopFoods;
            } catch (JsonSyntaxException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType {
        Class clazz;

        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }


    public static <T> T getObj(Context applicationContext, int merchant_data, Class<T> tClass) {
        if (isTest) {
            String str = readDataFromRaw(applicationContext, merchant_data);
            T t = gson.fromJson(str, tClass);
            return t;
        }
        return null;
    }

    public static String readDataFromRaw(Context activity, int id) {
        String res = "";
        //关闭
        try {
            //得到资源中的Raw数据流
            InputStream in = activity.getResources().openRawResource(id);

            //得到数据的大小
            int length = in.available();

            byte[] buffer = new byte[length];

            //读取数据
            in.read(buffer);

            //依test.txt的编码类型选择合适的编码，如果不调整会乱码
            res = new String(buffer, "utf-8");
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }
}
