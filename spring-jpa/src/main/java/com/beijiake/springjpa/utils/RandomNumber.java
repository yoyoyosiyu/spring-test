package com.beijiake.springjpa.utils;

import java.util.Random;

public class RandomNumber {

    public static Long SC = 400L;


    public static Long random() {
        return random(90000000000L, 99999999999L);

    }

    public static Long random(Long min, Long max) {
        Random random = new Random();
        return random.longs(min, max).findFirst().getAsLong();
    }

}
