package com.midknight.juicebar.block.state;

import net.minecraft.util.IStringSerializable;

import javax.annotation.Nonnull;

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
        return this.getSerializedName();
    }

    @Override @Nonnull
    public String getSerializedName() {
        return this.isLifted;
    }


}
