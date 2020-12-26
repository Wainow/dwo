package com.launcher.dwo.Hero;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import com.launcher.dwo.R;
import com.launcher.dwo.Specifications;

public class Hero implements Parcelable {
    private String name;
    private Specifications specifications;
    private String role;
    private String story;
    private String inventory;
    private int money;
    private Integer resID;
    private String uriResID;
    private boolean isEvil = false;
    private boolean isDownloaded = false;
    private int roleInt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Specifications getSpecifications() {
        return specifications;
    }

    public void setSpecifications(Specifications specifications) {
        this.specifications = specifications;
    }

    public String getRole() {
        return role;
    }

    public int getRoleInt(){
        return roleInt;
    }

    public void setRole(int role) {
        this.roleInt = role;
        switch (role){
            case 1 : this.role = "Knight";
                resID = R.drawable.mini_knight;
                isEvil = false;
                break;
            case 2 : this.role = "Mag";
                resID = R.drawable.mini_mag;
                isEvil = false;
                break;
            case 3 : this.role = "Rower";
                resID = R.drawable.mini_row;
                isEvil = false;
                break;
            case 4 : this.role = "Thief";
                resID = R.drawable.mini_thief;
                isEvil = false;
                break;
            case 5 : this.role = "Evil1";
                resID = R.drawable.mini_evil1;
                isEvil = true;
                break;
            case 6 : this.role = "Evil2";
                resID = R.drawable.mini_evil2;
                isEvil = true;
                break;
            case 7 : this.role = "Evil3";
                resID = R.drawable.mini_evil3;
                isEvil = true;
                break;
            case 8 : this.role = "Evil4";
                resID = R.drawable.mini_evil4;
                isEvil = true;
                break;
            case 9 : this.role = "Evil6";
                resID = R.drawable.mini_evil6;
                isEvil = true;
                break;
            case 10 : this.role = "Evil_download";
                resID = R.drawable.mini_download;
                isEvil = true;
                break;
            default: this.role = "Add hero";
                resID = R.drawable.mini_q;
                isEvil = true;
                break;
        }
    }

    public Hero(String name, int role, Specifications specifications) {
        this.name = name;
        this.specifications = specifications;
        this.roleInt = role;
        switch (role){
            case 1 : this.role = "Knight";
                resID = R.drawable.mini_knight;
                break;
            case 2 : this.role = "Mag";
                resID = R.drawable.mini_mag;
                break;
            case 3 : this.role = "Rower";
                resID = R.drawable.mini_row;
                break;
            case 4 : this.role = "Thief";
                resID = R.drawable.mini_thief;
                break;
            case 5 : this.role = "Evil1";
                resID = R.drawable.mini_evil1;
                isEvil = true;
                break;
            case 6 : this.role = "Evil2";
                resID = R.drawable.mini_evil2;
                isEvil = true;
                break;
            case 7 : this.role = "Evil3";
                resID = R.drawable.mini_evil3;
                isEvil = true;
                break;
            case 8 : this.role = "Evil4";
                resID = R.drawable.mini_evil4;
                isEvil = true;
                break;
            case 9 : this.role = "Evil6";
                resID = R.drawable.mini_evil6;
                isEvil = true;
                break;
            case 10 : this.role = "Evil_download";
                resID = R.drawable.mini_download;
                isEvil = true;
                break;
            default: this.role = "Add hero";
                resID = R.drawable.mini_q;
                break;
        }
        this.story = "";
        this.inventory = "";
        this.money = 0;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isDownloaded() {
        return isDownloaded;
    }

    public Integer getResID() {
        return resID;
    }

    public void setResID(Integer resID) {
        this.resID = resID;
    }

    public Hero(String name, String role, Specifications specifications, String inventory, String story, int money) {
        this.name = name;
        this.specifications = specifications;
        this.inventory = inventory;
        this.role = role;
        this.story = story;
        this.money = money;
        switch (role){
            case "Knight" : this.roleInt = 1;
                resID = R.drawable.mini_knight;
                isEvil = false;
                break;
            case "Mag" : this.roleInt = 2;
                resID = R.drawable.mini_mag;
                isEvil = false;
                break;
            case "Rower" : this.roleInt = 3;
                resID = R.drawable.mini_row;
                isEvil = false;
                break;
            case "Thief" : this.roleInt = 4;
                resID = R.drawable.mini_thief;
                isEvil = false;
                break;
            case "Evil1" :
                this.roleInt = 5;
                resID = R.drawable.mini_evil1;
                isEvil = true;
                break;
            case "Evil2" :
                resID = R.drawable.mini_evil2;
                this.roleInt = 6;
                isEvil = true;
                break;
            case "Evil3" :
                resID = R.drawable.mini_evil3;
                this.roleInt = 7;
                isEvil = true;
                break;
            case "Evil4" :
                resID = R.drawable.mini_evil4;
                this.roleInt = 8;
                isEvil = true;
                break;
            case "Evil6" :
                resID = R.drawable.mini_evil6;
                this.roleInt = 9;
                isEvil = true;
                break;
            case "Evil_download" :
                resID = R.drawable.mini_download;
                this.roleInt = 10;
                isEvil = true;
                break;
            default:
                this.roleInt = 0;
                break;
        }
    }

    public boolean isEvil() {
        return isEvil;
    }

    public Uri getUriResID() {
        return Uri.parse(uriResID);
    }

    public String getStringUriResID(){
        return uriResID;
    }

    public Hero(String name, Specifications specifications, String inventory, String story, int money, Uri resID) {
        this.name = name;
        this.specifications = specifications;
        this.roleInt = 0;
        this.role = "Evil_download";
        this.uriResID = resID.toString();
        //this.uriResID = resID.getPath();
        this.inventory = inventory;
        this.story = story;
        this.money = money;
        this.isDownloaded = true;
        this.isEvil = true;
    }

    public Hero(String name, int role, Specifications specifications, String inventory, String story, int money) {
        this.name = name;
        this.specifications = specifications;
        this.roleInt = role;
        switch (role){
            case 1 : this.role = "Knight";
                resID = R.drawable.mini_knight;
                break;
            case 2 : this.role = "Mag";
                resID = R.drawable.mini_mag;
                break;
            case 3 : this.role = "Rower";
                resID = R.drawable.mini_row;
                break;
            case 4 : this.role = "Thief";
                resID = R.drawable.mini_thief;
                break;
            case 5 : this.role = "Evil1";
                resID = R.drawable.mini_evil1;
                isEvil = true;
                break;
            case 6 : this.role = "Evil2";
                resID = R.drawable.mini_evil2;
                isEvil = true;
                break;
            case 7 : this.role = "Evil3";
                resID = R.drawable.mini_evil3;
                isEvil = true;
                break;
            case 8 : this.role = "Evil4";
                resID = R.drawable.mini_evil4;
                isEvil = true;
                break;
            case 9 : this.role = "Evil6";
                resID = R.drawable.mini_evil6;
                isEvil = true;
                break;
            case 10 : this.role = "Evil_download";
                resID = R.drawable.mini_download;
                isEvil = true;
                break;
            default: this.role = "Add hero";
                resID = R.drawable.mini_q;
                break;
        }
        this.inventory = inventory;
        this.story = story;
        this.money = money;
    }

    public Hero(){
        this.name = "";
        this.specifications = new Specifications();
        this.role = "Add hero";
        this.inventory = "";
        this.story = "...";
        this.money = 0;
        this.roleInt = 0;
        this.resID = R.drawable.mini_q;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", specifications=" + specifications +
                ", role='" + role + '\'' +
                ", story='" + story + '\'' +
                ", inventory='" + inventory + '\'' +
                ", money=" + money +
                '}';
    }

    public String getStory() {
        return story;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(role);
        dest.writeString(story);
        dest.writeString(inventory);
        dest.writeInt(money);

        dest.writeInt(specifications.getAgility());
        dest.writeInt(specifications.getCharisma());
        dest.writeInt(specifications.getHealth());
        dest.writeInt(specifications.getIntelligence());
        dest.writeInt(specifications.getStrength());
        dest.writeInt(specifications.getStamina());
    }

    public static final Parcelable.Creator<Hero> CREATOR = new Parcelable.Creator<Hero>(){
        @Override
        public Hero createFromParcel(Parcel source) {
            String name = source.readString();
            String role = source.readString();
            String story = source.readString();
            String inventory = source.readString();
            int money = source.readInt();

            int agility = source.readInt();
            int charisma = source.readInt();
            int health = source.readInt();
            int intelligence = source.readInt();
            int strength = source.readInt();
            int stamina = source.readInt();

            return new Hero(
                    name,
                    role,
                    new Specifications(strength, agility, intelligence, charisma, stamina, health),
                    inventory,
                    story,
                    money);
        }

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
