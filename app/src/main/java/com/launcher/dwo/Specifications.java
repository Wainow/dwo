package com.launcher.dwo;

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
        isNull = false;
    }

    public int getAgility() {
        return Agility;
    }

    public void setAgility(int agility) {
        Agility = agility;
        isNull = false;
    }

    public int getIntelligence() {
        return Intelligence;
    }

    public void setIntelligence(int intelligence) {
        Intelligence = intelligence;
        isNull = false;
    }

    public int getCharisma() {
        return Charisma;
    }

    public void setCharisma(int charisma) {
        Charisma = charisma;
        isNull = false;
    }

    public int getStamina() {
        return Stamina;
    }

    public void setStamina(int stamina) {
        Stamina = stamina;
        isNull = false;
    }

    public int getHealth() {
        return Health;
    }

    public void setHealth(int health) {
        Health = health;
        isNull = false;
    }

    public boolean isNull() {
        return isNull;
    }

    public Specifications(int strength, int agility, int intelligence, int charisma, int stamina, int health) {
        if(strength != 0 && agility != 0 && intelligence != 0 && charisma != 0 && stamina != 0 && health != 0) {
            this.Strength = strength;
            this.Agility = agility;
            this.Intelligence = intelligence;
            this.Charisma = charisma;
            this.Stamina = stamina;
            this.Health = health;
        } else
            this.isNull = true;
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
