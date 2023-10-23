package src;

import src.enums.LogModeEnum;
import src.enums.UserModeEnum;

public class Application {

    public static void main(String[] args)  {

        // Parse argument on program launch
        UserModeEnum initialUserMode = UserModeEnum.parseArgToUserModeEnum(args);
        LogModeEnum initialLogMode = LogModeEnum.parseArgToLogModeEnum(args);

        // Create engine
        CalcEngine engine = new CalcEngine(initialUserMode, initialLogMode);

        // Create IO and provide engine, local and remote user now allowed to interact
        new CalcIO(engine);

    }
}
