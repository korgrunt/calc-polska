package src.utils;

import src.enums.ModeEnum;

public class ParseArgsUtils {

    public static ModeEnum argToEnumMode(String arg){
        ModeEnum mode = ModeEnum.UNKNOW;
        String[] splited = arg.split("=");

        if(splited.length < 2){
            mode = ModeEnum.UNKNOW;
        } else{
            for (ModeEnum value : ModeEnum.values()) {
                if(value.name().equals(splited[1].toUpperCase())) {
                    mode = value;
                }
            }
        }
        return mode;
    }
}


