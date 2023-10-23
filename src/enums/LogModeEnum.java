package src.enums;

import static src.constants.MessageConstants.INVALID_LOG_MODE;

public enum LogModeEnum {
    LOG,
    REPLAY,
    NO_LOG,
    UNKNOW;

    public static String valuesAsString(){
        LogModeEnum[] values = LogModeEnum.values();
        StringBuilder concatenatedValues = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            concatenatedValues.append(values[i].name());
            if(i < values.length - 1) {
                concatenatedValues.append(", ");
            }
        }
        return concatenatedValues.toString();
    }


    public static LogModeEnum parseArgToLogModeEnum(String[] args) {
        LogModeEnum initialMode = LogModeEnum.NO_LOG;
        if (args != null) {
            for(String arg: args){
                if(arg.matches("^--log=.*")){
                    initialMode = argToLogModeEnum(arg);
                }
            }
        }

        if (initialMode == LogModeEnum.UNKNOW) {
            System.out.println(String.format(INVALID_LOG_MODE, initialMode, LogModeEnum.valuesAsString()));
        }
        return initialMode;
    }

    private static LogModeEnum argToLogModeEnum(String arg){
        LogModeEnum logMode = LogModeEnum.UNKNOW;
        String[] splited = arg.split("=");

        for (LogModeEnum value : LogModeEnum.values()) {
            if(value.name().equals(splited[1].toUpperCase())) {
                logMode = value;
            }
        }

        return logMode;
    }
}

