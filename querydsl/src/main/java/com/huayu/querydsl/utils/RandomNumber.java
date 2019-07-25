package com.huayu.querydsl.utils;

import java.util.Random;

public class RandomNumber {

    public static Long SC = 400L;


    public static Long random() {
        return random(900000L, 999999L);

    }

    public static Long random(Long min, Long max) {
        Random random = new Random();
        return random.longs(min, max).findFirst().getAsLong();
    }

}
