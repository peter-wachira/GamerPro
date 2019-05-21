package com.moringaschool.gamerpro;

public class GameModel {

    private  String mDescription;
    private  String mPlatforms;
    private  String mDateadded;

    public String getmDescription() {
        return mDescription;
    }

    public String getmPlatforms() {
        return mPlatforms;
    }

    public String getmDateadded() {
        return mDateadded;
    }

    public String getmExpectedrelease() {
        return mExpectedrelease;
    }

    public String getmAliases() {
        return mAliases;
    }

    public String getmName() {
        return mName;
    }

    private  String mExpectedrelease;
    private  String mAliases;
    private  String mName;

    public GameModel(String description, String platforms, String dateadded, String expectedrelease, String aliases, String name) {

    this.mAliases = aliases;
    this.mDateadded =dateadded;
    this.mDescription =description;
    this.mExpectedrelease = expectedrelease;
    this.mName = name;
    this.mPlatforms =platforms;

    }
}
