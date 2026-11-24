package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Playlist;
<<<<<<< HEAD
=======
import com.joysistvi.recordingapp.model.Song;

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
import java.util.List;

public interface PlaylistRepository {
    List<Playlist> findAllActive();
    List<Playlist> findAllArchived();
<<<<<<< HEAD
    List<Playlist> findByUserId(int userId);
=======

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
    boolean save(Playlist playlist);
    boolean update(Playlist playlist);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Playlist> searchByName(String keyword);
<<<<<<< HEAD
=======

    //Additional Methods

    // Fetches all active songs assigned to a given playlist via the playlist_songs junction table
    // (used when viewing a playlist or listing current songs before removing one).
    List<Song> findSongsByPlaylistId(int playlistId);

    // Fetches all active playlists owned by a specific user via their userId
    // (used to view/manage their playlist and enforce the one-playlist-per-user rule).
    List<Playlist> findByUserId(int userId);

    boolean addSongToPlaylist(int playlistId, int songId);
    boolean removeSongFromPlaylist(int playlistId, int songId);

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
}