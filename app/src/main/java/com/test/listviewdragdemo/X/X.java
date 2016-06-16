package com.test.listviewdragdemo.X;

import android.view.View;

import org.xutils.view.annotation.ViewInject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by 13798 on 2016/6/14.
 */
public class X {
    public static void inject(Object object, View view) {
        Class obejctClass = object.getClass();
        Class viewClass = view.getClass();
        // 获取object的所有成员变量
        Field[] fields = obejctClass.getDeclaredFields();
        try {
            // 通过反射拿到View的findViewById
            Method findViewById = viewClass.getMethod("findViewById", int.class);
            for (Field field : fields) {
                ViewInject annotation = field.getAnnotation(ViewInject.class);
                if (annotation!=null){
                    // 拿到View的子类和对应的id值
//                    Log.d("X", field.getName() + "-----" + annotation.value());
                    field.setAccessible(true);
                    // 相当于view.findViewById(R.id.xxx);
                    Object invoke = findViewById.invoke(view, annotation.value());
                    // 相当于 TextView tv = (TextView)view.findViewById();
                    field.set(object,invoke);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
