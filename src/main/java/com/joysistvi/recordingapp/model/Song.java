package com.joysistvi.recordingapp.model;

public class Song {
    private int id;
    private String title;
<<<<<<< HEAD
    private int durationSeconds;
=======
    private String genre;
    private String length;
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
    private int albumId;
    private String albumName;
    private boolean archived;

    public Song() {
    }

<<<<<<< HEAD
    public Song(int id, String title, int durationSeconds, int albumId, String albumName) {
        this.id = id;
        this.title = title;
        this.durationSeconds = durationSeconds;
=======
    public Song(int id, String title, String genre, String length, int albumId, String albumName) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
        this.albumId = albumId;
        this.albumName = albumName;
    }

<<<<<<< HEAD
    public Song(int id, String title, int durationSeconds, int albumId, String albumName, boolean archived) {
        this.id = id;
        this.title = title;
        this.durationSeconds = durationSeconds;
=======
    public Song(int id, String title, String genre, String length, int albumId, String albumName, boolean archived) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.length = length;
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
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

<<<<<<< HEAD
    public int getDurationSeconds() {
        return durationSeconds;
    }

    public void setDurationSeconds(int durationSeconds) {
        this.durationSeconds = durationSeconds;
=======
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
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
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