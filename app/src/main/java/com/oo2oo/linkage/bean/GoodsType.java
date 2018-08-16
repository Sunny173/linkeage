package com.oo2oo.linkage.bean;

import java.util.List;

/**
 * Created by work on 2018/4/20.
 * 商品分类
 */

public class GoodsType {
    public String parentId;
    public int divLevel;
    public String id;
    public String label;//分类名字
    public List<GoodsType> children;

}
