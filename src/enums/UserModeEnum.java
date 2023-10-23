package src.enums;

public enum UserModeEnum {
    LOCAL,
    LOCAL_LOGGED,
    REPLAY_LOG,
    REMOTE_SHARED_STACK,
    REMOTE_NOT_SHARED_STACK,
    UNKNOW;

    public static String valuesAsString() {
        UserModeEnum[] values = UserModeEnum.values();
        StringBuilder concatenatedValues = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            concatenatedValues.append(values[i].name());
            if (i < values.length - 1) {
                concatenatedValues.append(", ");
            }
        }
        return concatenatedValues.toString();
    }

    public static UserModeEnum parseArgToUserModeEnum(String[] args) {
        UserModeEnum initialMode = UserModeEnum.LOCAL;
        if (args != null) {
            for(String arg: args){
                if(arg.matches("^--user=.*")){
                    initialMode = argToUserModeEnum(arg);
                }
            }
        }

        if (initialMode == UserModeEnum.UNKNOW) {
            System.out.println("\n---- Invalid mode provided, get (" + initialMode + ").Please, set argument for mode as valid. exemple: --user=(" + UserModeEnum.valuesAsString() + ").");
        }
        return initialMode;
    }

    private static UserModeEnum argToUserModeEnum(String arg){
        UserModeEnum mode = UserModeEnum.UNKNOW;
        String[] splited = arg.split("=");

        for (UserModeEnum value : UserModeEnum.values()) {
            if(value.name().equals(splited[1].toUpperCase())) {
                mode = value;
            }
        }

        return mode;
    }
}

