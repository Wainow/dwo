package com.launcher.dwo;

import com.launcher.dwo.Hero.Hero;

import java.util.ArrayList;
import java.util.Arrays;

public class Room {
    private String room_name;
    private ArrayList<Hero> heroes;
    private int number_of_players;
    private Integer room_image_src;
    private int RoomID;

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    private String Description = "Some information";

    public Integer getRoom_image_src() {
        return room_image_src;
    }

    public void setRoom_image_src(Integer room_image_src) {
        this.room_image_src = room_image_src;
    }

    public Room(String room_name, ArrayList<Hero> heroes, Integer room_image_src) {
        this.room_name = room_name;
        this.heroes = heroes;
        this.number_of_players = heroes.size();
        this.room_image_src = room_image_src;
    }

    public int getRoomID() {
        return RoomID;
    }

    public Room(String room_name, ArrayList<Hero> heroes, Integer room_image_src, int RoomID) {
        this.room_name = room_name;
        this.heroes = heroes;
        this.number_of_players = heroes.size();
        this.room_image_src = room_image_src;
        this.RoomID = RoomID;
    }

    public Room(String room_name) {
        this.room_name = room_name;
    }

    public String getRoom_name() {
        return room_name;
    }

    public void setRoom_name(String room_name) {
        this.room_name = room_name;
    }

    public ArrayList<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(Hero[] heroes) {
        this.heroes = (ArrayList<Hero>) Arrays.asList(heroes);
    }

    public void setHeroes(ArrayList<Hero> heroes) {
        this.heroes = heroes;
    }

    public int getNumber_of_players() {
        return number_of_players;
    }

    public void setNumber_of_players(int number_of_players) {
        this.number_of_players = number_of_players;
    }

    public Room(String room_name, Hero[] heroes){
        this.heroes = new ArrayList<>(Arrays.asList(heroes));
        this.room_name = room_name;
        this.number_of_players = heroes.length;
    }

    public Room(String room_name, ArrayList<Hero> heroes){
        this.heroes = heroes;
        this.room_name = room_name;
        this.number_of_players = heroes.size();
    }

    @Override
    public String toString() {
        return "Room{" +
                "room_name='" + room_name + '\'' +
                ", heroes=" + heroes.toString() +
                ", number_of_players=" + number_of_players +
                '}';
    }
}
