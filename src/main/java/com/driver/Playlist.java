package com.driver;

import java.util.List;

public class Playlist {
    private String title;

    public Playlist(){

    }


    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o!=null && this.getClass()!=o.getClass()){
            return false;
        }
        Playlist playlist = (Playlist) o;
        if(this.getTitle().equals(playlist.getTitle())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode()
    {
        int result = title.hashCode();
        return result;
    }

    public Playlist(String title){
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
