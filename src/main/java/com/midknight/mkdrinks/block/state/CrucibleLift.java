package com.midknight.mkdrinks.block.state;

import net.minecraft.util.IStringSerializable;

public enum CrucibleLift implements IStringSerializable {

    NONE("none"),
    LIFTED("lifted")
    ;

    private final String isLifted;

    CrucibleLift(String liftState) {
        this.isLifted = liftState;
    }

    @Override
    public String toString() {
        return this.getString();
    }

    @Override
    public String getString() {
        return this.isLifted;
    }


}
