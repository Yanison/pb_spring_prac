package com.pb.starter.component.util;

public class CommonUtil {

    public static boolean isStartWith(String str, String[] startWith) {
        for(String s : startWith){
            if(str.startsWith(s)){ return true; }
        }
        return false;
    }


}
