package com.joysistvi.recordingapp.model;

public class Song {
    private int id;
    private String title;
    private int durationSeconds;
    private int albumId;
    private String albumName;
    private boolean archived;

    public Song() {
    }

    public Song(int id, String title, int durationSeconds, int albumId, String albumName) {
        this.id = id;
        this.title = title;
        this.durationSeconds = durationSeconds;
        this.albumId = albumId;
        this.albumName = albumName;
    }

    public Song(int id, String title, int durationSeconds, int albumId, String albumName, boolean archived) {
        this.id = id;
        this.title = title;
        this.durationSeconds = durationSeconds;
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

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
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