package src;

import src.dtos.StackRPL;
import src.enums.ModeEnum;
import src.io.StreamManager;

public class CalcEngine {


    private final StackRPL globalRplStack = new StackRPL(50);

    private ModeEnum mode;

    public CalcEngine(ModeEnum initialMode) {
        this.mode = initialMode;
    }

    /*
    GETTERS AND SETTERS
     */
    public ModeEnum getMode() {
        return mode;
    }

    public void setMode(ModeEnum mode) {
        this.mode = mode;
    }

    public StackRPL getGlobalRplStack() {
        return globalRplStack;
    }

}
