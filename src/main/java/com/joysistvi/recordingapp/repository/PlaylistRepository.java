package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.model.Playlist;
import java.util.List;

public interface PlaylistRepository {
    List<Playlist> findAllActive();
    List<Playlist> findAllArchived();
    List<Playlist> findByUserId(int userId);
    boolean save(Playlist playlist);
    boolean update(Playlist playlist);
    boolean deleteById(int id);
    boolean archiveById(int id);
    boolean restoreById(int id);
    List<Playlist> searchByName(String keyword);
}