package com.example.dwo;

import java.util.Arrays;

public class Room {
    private String room_name;
    private Hero[] heroes;
    private int number_of_players;

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public Hero[] getHeroes() {
        return heroes;
    }

    public void setHeroes(Hero[] heroes) {
        this.heroes = heroes;
    }

    public int getNumber_of_players() {
        return number_of_players;
    }

    public void setNumber_of_players(int number_of_players) {
        this.number_of_players = number_of_players;
    }

    public Room(String room_name, Hero[] heroes){
        this.heroes = heroes;
        this.room_name = room_name;
        this.number_of_players = heroes.length;
    }

    public Room(String room){

    }

    @Override
    public String toString() {
        return "Room{" +
                "room_name='" + room_name + '\'' +
                ", heroes=" + Arrays.toString(heroes) +
                ", number_of_players=" + number_of_players +
                '}';
    }
}
