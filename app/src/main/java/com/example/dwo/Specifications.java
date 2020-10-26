package com.example.dwo;

public class Specifications {
    private int Strength;
    private int Agility;
    private int Intelligence;
    private int Charisma;
    private int Stamina;
    private int Health;
    private boolean isNull = false;

    public int getStrength() {
        return Strength;
    }

    public void setStrength(int strength) {
        Strength = strength;
    }

    public int getAgility() {
        return Agility;
    }

    public void setAgility(int agility) {
        Agility = agility;
    }

    public int getIntelligence() {
        return Intelligence;
    }

    public void setIntelligence(int intelligence) {
        Intelligence = intelligence;
    }

    public int getCharisma() {
        return Charisma;
    }

    public void setCharisma(int charisma) {
        Charisma = charisma;
    }

    public int getStamina() {
        return Stamina;
    }

    public void setStamina(int stamina) {
        Stamina = stamina;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
    }

    public Specifications(int strength, int agility, int intelligence, int charisma, int stamina, int health) {
        this.Strength = strength;
        this.Agility = agility;
        this.Intelligence = intelligence;
        this.Charisma = charisma;
        this.Stamina = stamina;
        this.Health = health;
    }

    public Specifications(){
        this.isNull = true;
    }

    @Override
    public String toString() {
        if(!isNull){
            return "Specifications{" +
                    "Strength=" + Strength +
                    ", Agility=" + Agility +
                    ", Intelligence=" + Intelligence +
                    ", Charisma=" + Charisma +
                    ", Stamina=" + Stamina +
                    ", Health=" + Health +
                    '}';
        } else{
            return "  ...  ";
        }
    }
}
