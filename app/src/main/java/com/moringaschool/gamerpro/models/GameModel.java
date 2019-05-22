package com.moringaschool.gamerpro.models;
import org.parceler.Parcel;

import java.util.ArrayList;
@Parcel
public class GameModel {

    private  String mDeck;
    private ArrayList<String> Platforms = new ArrayList<>();

    private  String mDateadded;
    private  String mOriginalrelease;
    private  String mAliases;
    private  String mName;
    private String mImages;

    public String getmName() {
        return mName;
    }

    public String getmDeck() {
        return mDeck;
    }

    public String getmDateadded() {
        return mDateadded;
    }

    public String getmAliases() {
        return mAliases;
    }

    public String getmImages() {
        return mImages;
    }

    public GameModel(String mDeck, ArrayList<String> platforms, String mDateadded, String mOriginalrelease, String mAliases, String mName, String mImages) {
        this.mDeck = mDeck;
        Platforms = platforms;
        this.mDateadded = mDateadded;
        this.mOriginalrelease = mOriginalrelease;
        this.mAliases = mAliases;
        this.mName = mName;
        this.mImages = mImages;
    }

    public void setmDeck(String mDeck) {
        this.mDeck = mDeck;
    }

    public ArrayList<String> getPlatforms() {
        return Platforms;
    }

    public void setPlatforms(ArrayList<String> platforms) {
        Platforms = platforms;
    }

    public void setmDateadded(String mDateadded) {
        this.mDateadded = mDateadded;
    }

    public String getmOriginalrelease() {
        return mOriginalrelease;
    }

    public void setmOriginalrelease(String mOriginalrelease) {
        this.mOriginalrelease = mOriginalrelease;
    }

    public void setmAliases(String mAliases) {
        this.mAliases = mAliases;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImages(String mImages) {
        this.mImages = mImages;
    }
}
