package com.pb.starter.component;

import java.util.UUID;

public class CommonUtil {

    public static boolean isStartWith(String str, String[] startWith) {
        for(String s : startWith){
            if(str.startsWith(s)){ return true; }
        }
        return false;
    }

    public static String uuidGenerator(String title, String content) {
        // Combine title and content with a delimiter
        String input = title + ":" + content;

        // Generate a UUID based on the hash of the input string
        UUID uuid = UUID.nameUUIDFromBytes(input.getBytes());

        // Return the UUID as a string
        return uuid.toString();
    }
}
