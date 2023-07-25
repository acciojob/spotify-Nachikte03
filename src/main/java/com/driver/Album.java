package com.driver;

import java.util.Date;
import java.util.List;

public class Album {
    private String title;
    private Date releaseDate;

    public Album(){

    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o!=null && this.getClass()!=o.getClass()){
            return false;
        }
        Album album = (Album) o;
        if(this.getTitle().equals(album.getTitle())){
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
    public Album(String title){
        this.title = title;
        this.releaseDate = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }
}
