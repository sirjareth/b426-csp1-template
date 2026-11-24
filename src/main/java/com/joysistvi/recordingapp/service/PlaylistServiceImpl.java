package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Playlist;
<<<<<<< HEAD
import com.joysistvi.recordingapp.repository.PlaylistRepository;
=======
import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.repository.PlaylistRepository;

import java.util.ArrayList;
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
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

<<<<<<< HEAD
=======

    // Use findByUserId
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
    @Override
    public List<Playlist> getPlaylistsByUser(int userId) {
        if (userId <= 0) {
            System.out.println("Validation Error: User ID must be positive.");
            return Collections.emptyList();
        }
        return playlistRepository.findByUserId(userId);
    }

<<<<<<< HEAD
=======
    // Used findByUserId
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
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
<<<<<<< HEAD
=======

        if (!playlistRepository.findByUserId(userId).isEmpty()) {
            System.out.println("Validation Error: User already has a playlist.");
            return false;
        }
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
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
<<<<<<< HEAD
=======



    // Used findSongsByPlaylistId
    @Override
    public List<Song> getSongsInPlaylist(int playlistId) {
        if (playlistId <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return new ArrayList<>(); // to avoid NullPointerException
            // empty list
        }


        List<Song> results = playlistRepository.findSongsByPlaylistId(playlistId);
        if (results != null) {
            return results;
        } else {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean addSongToPlaylist(int playlistId, int songId) {
        if (playlistId <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return false;
        }
        if (songId <= 0) {
            System.out.println("Validation Error: Song ID must be positive.");
            return false;
        }
        return playlistRepository.addSongToPlaylist(playlistId, songId);
    }

    @Override
    public boolean removeSongFromPlaylist(int playlistId, int songId) {
        if (playlistId <= 0) {
            System.out.println("Validation Error: Playlist ID must be positive.");
            return false;
        }
        if (songId <= 0) {
            System.out.println("Validation Error: Song ID must be positive.");
            return false;
        }
        return playlistRepository.removeSongFromPlaylist(playlistId, songId);
    }
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
}