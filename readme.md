这是一个三级联动的Demo，
也可以只有一个列表，
在手机上以滑轮的方式展示.
## 使用方法
1。自己的 adapter 继承 BaseAdapter，并实现

```
    1.下一个列表设置adapter的方法
    public abstract void setNextAdapter(WheelView nextwheelView);
   
    2.获取对于角标对象的方法
    public abstract T getItemsObj(int postion);
   
    3.列表上应显示的文字
    public abstract CharSequence getItemText(int index);

```

2。多级联动的对话框 Wheel3Popwindow 的使用方法
```java
       Wheel3Popwindow dialog = new Wheel3Popwindow(this);
    
       /**
       创建三个实现了 BaseAdapter 的adapter对象。
       */
       BaseAdapter pAdapter = new GoodsTypeAdapter<GoodsType>(this, goodsType);
       BaseAdapter cAdapter = new GoodsTypeAdapter<GoodsType>(this,
                           goodsType.get(0).children);
       BaseAdapter sunAdapter = new GoodsTypeAdapter<GoodsType>(this,
                           goodsType.get(0).children.get(0).children);

        /**
        * 给dialog设置以上三个adapter
        */
       dialog.setParentAdapter(pAdapter)
                    .setChildrenAdapter(cAdapter).setSunAdapter(sunAdapter);

```

最后对话框就可以弹出来了😊

这个是效果图

![这个是效果图，三个列表](https://upload-images.jianshu.io/upload_images/9093439-789b6d7ffe18057f.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


![这个是一个列表的效果图](https://upload-images.jianshu.io/upload_images/9093439-78c4f67df993e802.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


下面是demo的效果图
