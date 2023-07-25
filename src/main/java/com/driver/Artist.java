package com.driver;

import java.util.List;

public class Artist {
    private String name;
    private int likes;

    public Artist(){

    }

    public Artist(String name){
        this.name = name;
        this.likes = 0;
    }
    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o!=null && this.getClass()!=o.getClass()){
            return false;
        }
        Artist artist = (Artist) o;
        if(this.getName().equals(artist.getName())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = name.hashCode();
        return result;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
