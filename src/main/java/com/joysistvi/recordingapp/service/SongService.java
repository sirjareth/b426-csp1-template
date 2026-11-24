package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Song;
import java.util.List;

public interface SongService {
    List<Song> getAllActiveSongs();
    List<Song> getAllArchivedSongs();
<<<<<<< HEAD
    boolean createSong(String title, int durationSeconds, int albumId);
    boolean updateSong(int id, String title, int durationSeconds, int albumId);
=======
    boolean createSong(String title, String genre, String length, int albumId);
    boolean updateSong(int id, String title, String genre, String length, int albumId);
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
    boolean deleteSong(int id);
    boolean archiveSong(int id);
    boolean restoreSong(int id);
    List<Song> searchSongs(String keyword);
}