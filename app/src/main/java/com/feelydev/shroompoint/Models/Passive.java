package com.feelydev.shroompoint.Models;

import com.google.gson.annotations.SerializedName;

public class Passive {

    @SerializedName("name")
    private String name;

    @SerializedName("abilityIconPath")
    private String thumbnailPath;

    @SerializedName("description")
    private String desc;

    public Passive() {
    }

    public Passive(String name, String thumbnailPath, String desc) {
        this.name = name;
        this.thumbnailPath = thumbnailPath;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public String getDesc() {
        return desc;
    }
}
