package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import java.util.List;

public interface PlaylistService {
    List<Playlist> getAllActivePlaylists();
    List<Playlist> getAllArchivedPlaylists();
    List<Playlist> getPlaylistsByUser(int userId);
    boolean createPlaylist(String name, int userId);
    boolean updatePlaylist(int id, String name, int userId);
    boolean deletePlaylist(int id);
    boolean archivePlaylist(int id);
    boolean restorePlaylist(int id);
    List<Playlist> searchPlaylists(String keyword);
}