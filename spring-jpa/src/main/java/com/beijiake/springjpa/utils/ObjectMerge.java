package com.beijiake.springjpa.utils;

import java.lang.reflect.Method;

public class ObjectMerge {

    public static void merge(Object target, Object source) {
        merge(target, source, false);
    }


    public static void merge(Object target, Object source, boolean copyNullValue) {
        if(!target.getClass().isAssignableFrom(source.getClass())){
            return;
        }

        Method[] methods = target.getClass().getMethods();

        for(Method fromMethod: methods){
            if(fromMethod.getDeclaringClass().equals(target.getClass())
                    && fromMethod.getName().startsWith("get")){

                String fromName = fromMethod.getName();
                String toName = fromName.replace("get", "set");

                try {
                    Method toMethod = target.getClass().getMethod(toName, fromMethod.getReturnType());
                    Object value = fromMethod.invoke(source, (Object[])null);
                    if(value != null || copyNullValue){
                        toMethod.invoke(target, value);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }


    }

}
