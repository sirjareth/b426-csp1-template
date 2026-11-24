package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
<<<<<<< HEAD
=======
import com.joysistvi.recordingapp.model.Song;

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllActivePlaylists();
    List<Playlist> getAllArchivedPlaylists();
<<<<<<< HEAD
    List<Playlist> getPlaylistsByUser(int userId);
=======

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
    boolean createPlaylist(String name, int userId);
    boolean updatePlaylist(int id, String name, int userId);
    boolean deletePlaylist(int id);
    boolean archivePlaylist(int id);
    boolean restorePlaylist(int id);
    List<Playlist> searchPlaylists(String keyword);
<<<<<<< HEAD
=======

    // Additional Methods
    List<Song> getSongsInPlaylist(int playlistId);
    List<Playlist> getPlaylistsByUser(int userId);
    boolean addSongToPlaylist(int playlistId, int songId);
    boolean removeSongFromPlaylist(int playlistId, int songId);

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
}