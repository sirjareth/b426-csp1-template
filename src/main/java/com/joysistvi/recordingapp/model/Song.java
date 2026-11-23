package com.joysistvi.recordingapp.model;

public class Song {
    private int id;
    private String title;
    private String genre;
    private String length;
    private int albumId;
    private String albumName;
    private boolean archived;

    public Song() {
    }

    public Song(int id, String title, String genre, String length, int albumId, String albumName) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public Song(int id, String title, String genre, String length, int albumId, String albumName, boolean archived) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
        this.albumId = albumId;
        this.albumName = albumName;
        this.archived = archived;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public boolean isArchived() {
        return archived;
    }

    public void setArchived(boolean archived) {
        this.archived = archived;
    }
}