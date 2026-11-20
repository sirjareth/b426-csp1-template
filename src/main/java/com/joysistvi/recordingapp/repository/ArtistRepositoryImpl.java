package com.joysistvi.recordingapp.repository;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Artist;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository{

    // Constructor Injection
    // Composition -> has a relationship
    // Using Composition Artist Repository is loosely coupled to DbConnection
    private final DbConnection dbConnection;

    public ArtistRepositoryImpl(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }

    @Override
    public List<Artist> findAllActive() {
        List<Artist> artists = new ArrayList<>();
        String query = "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection connection = dbConnection.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                artists.add(new Artist(id, name));


            }

        } catch (SQLException e) {
            System.out.println("Read Artists Error: " + e.getMessage());
        }

        return artists;
    }

    @Override
    public boolean save(Artist artists) {

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = dbConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, artists.getName().trim());

            return preparedStatement.executeUpdate() > 0;

            //System.out.println("\nArtist: " + artists.getName().trim() + " created successfully!");

        } catch (SQLException e) {
            System.out.println("\nCreate Artist Error: " + e.getMessage());
            return false;
        }
    }

}
