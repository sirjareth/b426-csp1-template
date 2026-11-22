package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.repository.PlaylistRepository;
import java.util.Collections;
import java.util.List;

public class PlaylistServiceImpl implements PlaylistService {
    private final PlaylistRepository playlistRepository;

    public PlaylistServiceImpl(PlaylistRepository playlistRepository) {
        this.playlistRepository = playlistRepository;
    }

    @Override
    public List<Playlist> getAllActivePlaylists() {
        return playlistRepository.findAllActive();
    }

    @Override
    public List<Playlist> getAllArchivedPlaylists() {
        return playlistRepository.findAllArchived();
    }

    @Override
    public List<Playlist> getPlaylistsByUser(int userId) {
        if (userId <= 0) {
            System.out.println("Validation Error: User ID must be positive.");
            return Collections.emptyList();
        }
        return playlistRepository.findByUserId(userId);
    }

    @Override
    public boolean createPlaylist(String name, int userId) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Playlist name cannot be blank.");
            return false;
        }
        if (userId <= 0) {
            System.out.println("Validation Error: User ID must be positive.");
            return false;
        }
        return playlistRepository.save(new Playlist(0, name.trim(), userId, null));
    }

    @Override
    public boolean updatePlaylist(int id, String name, int userId) {
        if (id <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return false;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Validation Error: Playlist name cannot be blank.");
            return false;
        }
        if (userId <= 0) {
            System.out.println("Validation Error: User ID must be positive.");
            return false;
        }
        return playlistRepository.update(new Playlist(id, name.trim(), userId, null));
    }

    @Override
    public boolean deletePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return false;
        }
        return playlistRepository.deleteById(id);
    }

    @Override
    public boolean archivePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return false;
        }
        return playlistRepository.archiveById(id);
    }

    @Override
    public boolean restorePlaylist(int id) {
        if (id <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return false;
        }
        return playlistRepository.restoreById(id);
    }

    @Override
    public List<Playlist> searchPlaylists(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("Validation Error: Search keyword cannot be blank.");
            return Collections.emptyList();
        }
        return playlistRepository.searchByName(keyword.trim());
    }
}