package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.Song;

import java.util.List;

public interface PlaylistRepository {
    List<Playlist> findAllActive();
    List<Playlist> findAllArchived();

    boolean save(Playlist playlist);
    boolean update(Playlist playlist);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Playlist> searchByName(String keyword);

    //Additional Methods

    // Fetches all active songs assigned to a given playlist via the playlist_songs junction table
    // (used when viewing a playlist or listing current songs before removing one).
    List<Song> findSongsByPlaylistId(int playlistId);

    // Fetches all active playlists owned by a specific user via their userId
    // (used to view/manage their playlist and enforce the one-playlist-per-user rule).
    List<Playlist> findByUserId(int userId);

    boolean addSongToPlaylist(int playlistId, int songId);
    boolean removeSongFromPlaylist(int playlistId, int songId);

}