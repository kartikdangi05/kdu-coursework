package com.kdu.smarthome.validators;

public class Validator {

    private Validator(){}
    public static boolean isParsable(String string){
        try{
            Long.parseLong(string);
            return true;
        }catch (NumberFormatException ex){
            return false;
        }
    }
}
