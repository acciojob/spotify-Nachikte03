package com.driver;

import java.util.*;

import org.springframework.stereotype.Repository;

@Repository
public class SpotifyRepository {
    public HashMap<Artist, List<Album>> artistAlbumMap;
    public HashMap<Album, List<Song>> albumSongMap;
    public HashMap<Playlist, List<Song>> playlistSongMap;
    public HashMap<Playlist, List<User>> playlistListenerMap;
    public HashMap<User, Playlist> creatorPlaylistMap;
    public HashMap<User, List<Playlist>> userPlaylistMap;
    public HashMap<Song, List<User>> songLikeMap;

    public List<User> users;
    public List<Song> songs;
    public List<Playlist> playlists;
    public List<Album> albums;
    public List<Artist> artists;

    public SpotifyRepository(){
        //To avoid hitting apis multiple times, initialize all the hashmaps here with some dummy data
        artistAlbumMap = new HashMap<>();
        albumSongMap = new HashMap<>();
        playlistSongMap = new HashMap<>();
        playlistListenerMap = new HashMap<>();
        creatorPlaylistMap = new HashMap<>();
        userPlaylistMap = new HashMap<>();
        songLikeMap = new HashMap<>();

        users = new ArrayList<>();
        songs = new ArrayList<>();
        playlists = new ArrayList<>();
        albums = new ArrayList<>();
        artists = new ArrayList<>();
    }

    public User createUser(String name, String mobile) {
        User user = new User(name,mobile);
        users.add(user);
        return user;
    }

    public Artist createArtist(String name) {
        Artist artist = new Artist(name);
        artist.setLikes(0);
        artists.add(artist);
        return artist;
    }

    public Album createAlbum(String title, String artistName) {
        Album album = new Album(title);
        album.setReleaseDate(new Date());
        albums.add(album);
        Artist artist = null;
        for(Artist k:artists){
            if(k.getName().equals(artistName)){
                artist = k;
            }
        }
        if(artist == null){
            artist = createArtist(artistName);
        }
        if(artistAlbumMap.containsKey(artist)){
            if(!artistAlbumMap.get(artist).contains(album)){
                artistAlbumMap.get(artist).add(album);
                artist.setLikes(artist.getLikes()+1);
            }
        }
        else{
            artistAlbumMap.put(artist,new ArrayList<>(){{add(album);}});
            artist.setLikes(1);
        }
        return album;
    }

    public Song createSong(String title, String albumName, int length) throws Exception{
        Song song = new Song(title,length);
        song.setLikes(0);
        Album album = null;
        songs.add(song);
        for(Album k:albums){
            if(k.getTitle().equals(albumName)){
                album = k;
            }
        }
        if(album == null){
            throw new Exception("Album does not exist");
        }
        if(albumSongMap.containsKey(album)){
            albumSongMap.get(album).add(song);
        }
        else{
            albumSongMap.put(album,new ArrayList<>(){{add(song);}});
        }
        return song;
    }

    public Playlist createPlaylistOnLength(String mobile, String title, int length) throws Exception {
        Playlist playlist = new Playlist(title);
        playlists.add(playlist);
        User user = null;
        for(User p:users){
            if(p.getMobile().equals(mobile)){
                user = p;
            }
        }
        if(user == null){
            throw new Exception("User does not exist");
        }
        if(playlistListenerMap.containsKey(playlist)){
            playlistListenerMap.get(playlist).add(user);
        }
        else{
            User finalUser = user;
            playlistListenerMap.put(playlist,new ArrayList<>(){{add(finalUser);}});
        }
        if(userPlaylistMap.containsKey(user)){
            userPlaylistMap.get(user).add(playlist);
        }
        else{
            userPlaylistMap.put(user,new ArrayList<>(){{add(playlist);}});
        }
        creatorPlaylistMap.put(user,playlist);
        List<Song> list = new ArrayList<>();
        for(Song p:songs){
            if(p.getLength()==length){
                list.add(p);
            }
        }
        playlistSongMap.put(playlist,list);




        return playlist;
    }

    public Playlist createPlaylistOnName(String mobile, String title, List<String> songTitles) throws Exception {
        Playlist playlist = new Playlist(title);
        playlists.add(playlist);
        User user = null;
        for(User p:users){
            if(p.getMobile().equals(mobile)){
                user = p;
            }
        }
        if(user == null){
            throw new Exception("User does not exist");
        }
        if(playlistListenerMap.containsKey(playlist)){
            playlistListenerMap.get(playlist).add(user);
        }
        else{
            User finalUser = user;
            playlistListenerMap.put(playlist,new ArrayList<>(){{add(finalUser);}});
        }
        if(userPlaylistMap.containsKey(user)){
            userPlaylistMap.get(user).add(playlist);
        }
        else{
            userPlaylistMap.put(user,new ArrayList<>(){{add(playlist);}});
        }
        creatorPlaylistMap.put(user,playlist);
        List<Song> list = new ArrayList<>();
        for(Song p:songs){
            if(songTitles.contains(p.getTitle())){
                list.add(p);
            }
        }
        playlistSongMap.put(playlist,list);




        return playlist;

    }

    public Playlist findPlaylist(String mobile, String playlistTitle) throws Exception {
        User user = null;
        Playlist playlist = null;
        for(User p:users){
            if(p.getMobile().equals(mobile)){
                user = p;
            }
        }
        if(user == null){
            throw new Exception("User not found");
        }
        for(Playlist p:playlists){
            if(p.getTitle().equals(playlistTitle)){
                playlist = p;
            }
        }
        if(playlist==null){
            throw new Exception("Playlist does not exist");
        }
        if(!playlistListenerMap.get(playlist).contains(user)){
            playlistListenerMap.get(playlist).add(user);
            if(!userPlaylistMap.get(user).contains(playlist)){
                userPlaylistMap.get(user).add(playlist);
            }
        }
        return playlist;
    }

    public Song likeSong(String mobile, String songTitle) throws Exception {
        Song song = null;
        User user = null;
        for(User p:users){
            if(p.getMobile().equals(mobile)){
                user = p;
            }
        }
        if(user == null){
            throw new Exception("User not found");
        }
        for(Song p:songs){
            if(p.getTitle().equals(songTitle)){
                song = p;
            }
        }
        if(song == null){
            throw new Exception("Song not found");
        }
        song.setLikes(song.getLikes()+1);
        if(songLikeMap.containsKey(song)){
            songLikeMap.get(song).add(user);
        }
        else{
            List<User> list = new ArrayList<>();
            list.add(user);
            songLikeMap.put(song,list);
        }
        return song;
    }

    public String mostPopularArtist() {
        if(artists.size()==0){
            return "";
        }
        Artist artist  = null;
        for(Artist p:artists){
            assert artist != null;
            if(p.getLikes()>artist.getLikes()){
                artist = p;
            }
        }
        return artist.getName();
    }

    public String mostPopularSong() {
        if(songs.size()==0){
            return "";
        }
        Song song = songs.get(0);
        for(Song p:songs){
            if(song.getLikes()<p.getLikes()){
                song = p;
            }
        }
        return song.getTitle();
    }
}
