package com.moringaschool.gamerpro.models;
import org.parceler.Parcel;


import java.util.ArrayList;
import java.util.List;

@Parcel
public class GameModel {

    String deck;
    List<String> platforms = new ArrayList<>();
    String dateAdded;
    String originalRelease;
    String aliases;
    String name;
    String images;
    private String pushId;



    public  GameModel(){}

    public GameModel(String deck, List<String> platforms, String dateAdded, String originalRelease, String aliases, String name, String images) {
        this.deck = deck;
        this.platforms = platforms;
        this.dateAdded = dateAdded;
        this.originalRelease = originalRelease;
        this.aliases = aliases;
        this.name = name;
        this.images = images;
        this.pushId ="";
    }
    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getDeck() {
        return deck;
    }

    public void setDeck(String deck) {
        this.deck = deck;
    }

    public List<String> getPlatforms() {
        return platforms;
    }

    public void setPlatforms(List<String> platforms) {
        this.platforms = platforms;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getOriginalRelease() {
        return originalRelease;
    }

    public void setOriginalRelease(String originalRelease) {
        this.originalRelease = originalRelease;
    }

    public String getAliases() {
        return aliases;
    }

    public void setAliases(String aliases) {
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
    //    public String getLargeImageUrl(String imageUrl) {
//        String largeImageUrl = imageUrl.substring(0, imageUrl.length() - 6).concat("o.jpg");
//        return largeImageUrl;
//    }
}