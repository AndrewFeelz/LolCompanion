package com.feelydev.shroompoint.models;

import com.google.gson.annotations.SerializedName;

public class Tactility {

    @SerializedName("style")
    private int style;

    @SerializedName("difficulty")
    private int difficulty;

    @SerializedName("damageType")
    private String damageType;

    public Tactility() {
    }

    public Tactility(int style, int difficulty, String damageType) {
        this.style = style;
        this.difficulty = difficulty;
        this.damageType = damageType;
    }

    public int getStyle() {
        return style;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getDamageType() {
        return damageType;
    }
}
