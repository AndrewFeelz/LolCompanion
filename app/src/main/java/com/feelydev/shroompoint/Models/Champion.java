package com.feelydev.shroompoint.Models;

import com.google.gson.annotations.SerializedName;

public class Champion {

    @SerializedName("id")
    private int id;

    @SerializedName("name")
    private String name;

    @SerializedName("alias")
    private String alias;

    @SerializedName("title")
    private String title;

    @SerializedName("shortBio")
    private String bio;

    @SerializedName("tacticalInfo")
    private Tactility tacticalInfo;

    @SerializedName("playstyleInfo")
    private Playstyle playstyleInfo;

    @SerializedName("squarePortraitPath")
    private String thumbnailPath;

    @SerializedName("roles")
    private String[] roles;

    @SerializedName("passive")
    private Passive passive;

    @SerializedName("spells")
    private Spell[] spells;

    public Champion() {
    }

    public Champion(int id, String name, String alias, String title, String bio, Tactility tacticalInfo, Playstyle playstyleInfo, String thumbnailPath, String[] roles, Passive passive, Spell[] spells) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.title = title;
        this.bio = bio;
        this.tacticalInfo = tacticalInfo;
        this.playstyleInfo = playstyleInfo;
        this.thumbnailPath = thumbnailPath;
        this.roles = roles;
        this.passive = passive;
        this.spells = spells;
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

    public String getTitle() {
        return title;
    }

    public String getBio() {
        return bio;
    }

    public Tactility getTacticalInfo() {
        return tacticalInfo;
    }

    public Playstyle getPlaystyleInfo() {
        return playstyleInfo;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public String[] getRoles() {
        return roles;
    }

    public Passive getPassive() {
        return passive;
    }

    public Spell[] getSpells() {
        return spells;
    }
}
