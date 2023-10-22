package src.enums;

public enum ModeEnum {
    LOCAL,
    LOCAL_LOGGED,
    REPLAY_LOG,
    REMOTE_SHARED_STACK,
    REMOTE_NOT_SHARED_STACK,
    REMOTE_SHARED_STACK_LOG,
    REMOTE_NOT_SHARED_STACK_LOG,
    UNKNOW;

    public static String valuesAsString(){
        ModeEnum[] values = ModeEnum.values();
        StringBuilder concatenatedValues = new StringBuilder();

        for (int i = 0; i < values.length; i++) {
            concatenatedValues.append(values[i].name());
            if(i < values.length - 1) {
                concatenatedValues.append(", ");
            }
        }
        return concatenatedValues.toString();
    }
}

