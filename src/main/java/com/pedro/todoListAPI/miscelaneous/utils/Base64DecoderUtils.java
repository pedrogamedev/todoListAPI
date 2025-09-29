package com.pedro.todoListAPI.miscelaneous.utils;

import java.util.Base64;

public class Base64DecoderUtils {
    public static boolean isValidBase64Input(String input){
        try{
            Base64.getDecoder().decode(input);
            return true;
        }
        catch (IllegalArgumentException e){
            return false;
        }
    }
}
