package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.Song;

import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllActivePlaylists();
    List<Playlist> getAllArchivedPlaylists();

    boolean createPlaylist(String name, int userId);
    boolean updatePlaylist(int id, String name, int userId);
    boolean deletePlaylist(int id);
    boolean archivePlaylist(int id);
    boolean restorePlaylist(int id);
    List<Playlist> searchPlaylists(String keyword);

    // Additional Methods
    List<Song> getSongsInPlaylist(int playlistId);
    List<Playlist> getPlaylistsByUser(int userId);
    boolean addSongToPlaylist(int playlistId, int songId);
    boolean removeSongFromPlaylist(int playlistId, int songId);

}