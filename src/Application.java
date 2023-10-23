package src;

import src.enums.LogModeEnum;
import src.enums.UserModeEnum;

public class Application {

    public static void main(String[] args)  {

        // Parse argument on program launch
        final UserModeEnum initialUserMode = UserModeEnum.parseArgToUserModeEnum(args);
        final LogModeEnum initialLogMode = LogModeEnum.parseArgToLogModeEnum(args);

        System.out.println(initialUserMode);

        // Create engine
        final CalcEngine engine = new CalcEngine(initialUserMode, initialLogMode);

        // Create IO and provide engine, local and remote user now allowed to interact
        new CalcIO(engine);
    }
}
