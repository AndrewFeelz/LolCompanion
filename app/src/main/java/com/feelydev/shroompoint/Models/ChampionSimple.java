package com.feelydev.shroompoint.Models;

import com.google.gson.annotations.SerializedName;

public class ChampionSimple {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("alias")
    private String alias;

    @SerializedName("squarePortraitPath")
    private String thumbnailPath;

    public ChampionSimple() {
    }

    public ChampionSimple(int id, String name, String alias, String thumbnailPath) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.thumbnailPath = thumbnailPath;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAlias() {
        return alias;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }
}
