package com.example.dwo;

public class Hero {
    private String name;
    private Specifications specifications;
    private String role;
    private String story;
    private String inventory;
    private int money;
    private Integer resID;

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
}
