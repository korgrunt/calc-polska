package src;

import src.enums.ModeEnum;
import src.utils.ParseArgsUtils;

public class Application {

    public static void main(String[] args)  {

        // Parse and use argument on program launch
        ModeEnum initialMode;
        if(args == null || args.length < 1){
            initialMode = ModeEnum.LOCAL;
        } else {

            initialMode = args[0].matches("^--user=.*") ? ParseArgsUtils.argToEnumMode(args[0]) : ModeEnum.UNKNOW;
            if(initialMode == ModeEnum.UNKNOW) {
                System.out.println("\n---- Invalid mode provided, get ("+initialMode+").Please, set argument for mode as valid. exemple: --user=(" + ModeEnum.valuesAsString() + ").");
                return;
            }
        }

        // Create engine
        CalcEngine engine = new CalcEngine(initialMode);

        // Create IO and provide engine, local and remote user now allowed to interact
        new CalcIO(engine);

    }
}
