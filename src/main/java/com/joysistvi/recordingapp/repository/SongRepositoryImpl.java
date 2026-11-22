package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Song;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongRepositoryImpl implements SongRepository {
    private final DbConnection dbConnection;

    public SongRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Song> findAllActive() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT s.id, s.title, s.duration_seconds, s.album_id, al.name AS album_name " +
                "FROM songs s " +
                "JOIN albums al ON s.album_id = al.id " +
                "WHERE s.is_archived = 0 " +
                "ORDER BY s.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        rs.getInt("album_id"),
                        rs.getString("album_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllActive): " + e.getMessage());
        }
        return songs;
    }

    @Override
    public List<Song> findAllArchived() {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT s.id, s.title, s.duration_seconds, s.album_id, al.name AS album_name " +
                "FROM songs s " +
                "JOIN albums al ON s.album_id = al.id " +
                "WHERE s.is_archived = 1 " +
                "ORDER BY s.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql);
             ResultSet rs = prep.executeQuery()) {

            while (rs.next()) {
                songs.add(new Song(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getInt("duration_seconds"),
                        rs.getInt("album_id"),
                        rs.getString("album_name")
                ));
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (findAllArchived): " + e.getMessage());
        }
        return songs;
    }

    @Override
    public boolean save(Song song) {
        String sql = "INSERT INTO songs (title, duration_seconds, album_id) VALUES (?, ?, ?)";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, song.getTitle());
            prep.setInt(2, song.getDurationSeconds());
            prep.setInt(3, song.getAlbumId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (save): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Song song) {
        String sql = "UPDATE songs SET title = ?, duration_seconds = ?, album_id = ? WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, song.getTitle());
            prep.setInt(2, song.getDurationSeconds());
            prep.setInt(3, song.getAlbumId());
            prep.setInt(4, song.getId());
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (update): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteById(int id) {
        String sql = "DELETE FROM songs WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (deleteById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean archiveById(int id) {
        String sql = "UPDATE songs SET is_archived = 1 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (archiveById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean restoreById(int id) {
        String sql = "UPDATE songs SET is_archived = 0 WHERE id = ?";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setInt(1, id);
            return prep.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Repository Error (restoreById): " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Song> searchByTitle(String keyword) {
        List<Song> songs = new ArrayList<>();
        String sql = "SELECT s.id, s.title, s.duration_seconds, s.album_id, al.name AS album_name " +
                "FROM songs s " +
                "JOIN albums al ON s.album_id = al.id " +
                "WHERE s.is_archived = 0 AND s.title LIKE ? " +
                "ORDER BY s.id ASC";

        try (Connection conn = dbConnection.connect();
             PreparedStatement prep = conn.prepareStatement(sql)) {

            prep.setString(1, "%" + keyword + "%");
            try (ResultSet rs = prep.executeQuery()) {
                while (rs.next()) {
                    songs.add(new Song(
                            rs.getInt("id"),
                            rs.getString("title"),
                            rs.getInt("duration_seconds"),
                            rs.getInt("album_id"),
                            rs.getString("album_name")
                    ));
                }
            }
        } catch (SQLException e) {
            System.out.println("Repository Error (searchByTitle): " + e.getMessage());
        }
        return songs;
    }
}