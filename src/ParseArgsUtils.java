package src;

import src.enums.CommandEnum;
import src.enums.ModeEnum;

public class ParseArgsUtils {

    public static ModeEnum argToEnumMode(String arg){
        ModeEnum mode = ModeEnum.LOCAL;
        String[] splited = arg.split("=");
        if(splited.length < 2){
            mode = ModeEnum.UNKNOW;
        } else if (splited[1].equals("local")){
            mode = ModeEnum.LOCAL;
        }

        return mode;
    }

    public static CommandEnum stringToCommandEnum(String str){
        return CommandEnum.ADD;
    }
}


