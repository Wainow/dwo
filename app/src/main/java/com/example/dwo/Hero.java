package com.example.dwo;

import android.os.Parcel;
import android.os.Parcelable;

public class Hero implements Parcelable {
    private String name;
    private Specifications specifications;
    private String role;
    private String story;
    private String inventory;
    private int money;
    private Integer resID;
    private boolean isEvil = false;

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

    public void setRole(int role) {
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
                break;
            case 6 : this.role = "Evil2";
                resID = R.drawable.mini_evil2;
                break;
            case 7 : this.role = "Evil3";
                resID = R.drawable.mini_evil3;
                break;
            case 8 : this.role = "Evil4";
                resID = R.drawable.mini_evil4;
                break;
            case 9 : this.role = "Evil6";
                resID = R.drawable.mini_evil6;
                break;
            case 10 : this.role = "Evil_download";
                resID = R.drawable.mini_download;
                break;
            default: this.role = "Add hero";
                resID = R.drawable.mini_q;
                break;
        }
    }

    public Hero(String name, int role, Specifications specifications) {
        this.name = name;
        this.specifications = specifications;
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
    }

    public boolean isEvil() {
        return isEvil;
    }

    public Hero(String name, int role, Specifications specifications, String inventory, String story, int money) {
        this.name = name;
        this.specifications = specifications;
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

    /*
    * private String name;
    private Specifications specifications;
    private String role;
    private String story;
    private String inventory;
    private int money;
    private Integer resID;
    *
    *
    * private int Strength;
    private int Agility;
    private int Intelligence;
    private int Charisma;
    private int Stamina;
    private int Health;
    private boolean isNull = false;
    *
    *
    * public Hero(String name, String role, Specifications specifications, String inventory, String story, int money) {*/

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

        /*
        * public Specifications(int strength, int agility, int intelligence, int charisma, int stamina, int health) {
        * */

        @Override
        public Hero[] newArray(int size) {
            return new Hero[size];
        }
    };
}
