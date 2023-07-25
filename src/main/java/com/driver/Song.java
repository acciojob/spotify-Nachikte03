package com.driver;

public class Song {
    private String title;
    private int length;
    private int likes;

    public Song(){

    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(o!=null && this.getClass()!=o.getClass()){
            return false;
        }
        Song song = (Song) o;
        if(this.getTitle().equals(song.getTitle())){
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

    public Song(String title, int length){
        this.title = title;
        this.length = length;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
