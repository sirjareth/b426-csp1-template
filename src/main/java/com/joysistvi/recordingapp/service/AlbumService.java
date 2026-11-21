package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Album;
import java.util.List;

public interface AlbumService {
    List<Album> getAllActiveAlbums();
    List<Album> getAllArchivedAlbums();
    boolean createAlbum(String name, int year, int artistId);
    boolean updateAlbum(int id, String name, int year, int artistId);
    boolean deleteAlbum(int id);
    boolean archiveAlbum(int id);
    boolean restoreAlbum(int id);
    List<Album> searchAlbums(String keyword);
}