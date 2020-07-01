package com.adentis.purchase.controller;

import java.time.temporal.ValueRange;

public class ValidateRange {

    private static final ValueRange onetoTree = ValueRange.of(0,3);
    private static final ValueRange forToSix = ValueRange.of(4,6);
    private static final ValueRange sevenToTwelve = ValueRange.of(7,12);

    public static String getRange(int value){
        if(onetoTree.isValidValue(value)){
            return "1-3";
        } else if(forToSix.isValidValue(value)){
            return "4-6";
        } else if(sevenToTwelve.isValidValue(value)){
            return "7-12";
        }else{
            return ">12";
        }
    }

}
