package com.example.dwo;

public class Hero {
    private String name;
    private Specifications specifications;
    private String role;
    private String story;
    private String inventory;
    private double money;

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
                break;
            case 2 : this.role = "Mag";
                break;
            case 3 : this.role = "Rower";
                break;
            case 4 : this.role = "Thief";
                break;
            default: this.role = "Add hero";
                break;
        }
    }

    public Hero(String name, int role, Specifications specifications) {
        this.name = name;
        this.specifications = specifications;
        switch (role){
            case 1 : this.role = "Knight";
                break;
            case 2 : this.role = "Mag";
                break;
            case 3 : this.role = "Rower";
                break;
            case 4 : this.role = "Thief";
                break;
            default: this.role = "Add hero";
                break;
        }
        this.story = "";
        this.inventory = "";
        this.money = 0;
    }

    public Hero(String name, int role, Specifications specifications, String inventory, String story, double money) {
        this.name = name;
        this.specifications = specifications;
        switch (role){
            case 1 : this.role = "Knight";
                break;
            case 2 : this.role = "Mag";
                break;
            case 3 : this.role = "Rower";
                break;
            case 4 : this.role = "Thief";
                break;
            default: this.role = "Add hero";
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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
