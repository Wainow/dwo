package com.example.dwo;

public class Hero {
    private String name;
    private Specifications specifications;
    private String role;

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

    public void setRole(String role) {
        this.role = role;
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
    }

    @Override
    public String toString() {
        return "Hero{" +
                "name='" + name + '\'' +
                ", specifications=" + specifications +
                ", role='" + role + '\'' +
                '}';
    }
}
