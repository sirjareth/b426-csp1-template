package com.joysistvi.recordingapp.main;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class Main {

    static String url = "jdbc:mysql://localhost:3306/recording_studio_db";
    static String username = "root";
    static String password = "";

    public static void main(String[] args) {

        

        // ==========================================
        // SAMPLE TEST CALLS (VALID & INVALID INPUTS)
        // ==========================================

        // Artists validation & CRUD tests
        createArtist("   ");                     // Validation Error: blank name
        createArtist("Cup of Joe");
        updateArtist(0, "Cup of Joe (OPM)");     // Validation Error: id <= 0
        updateArtist(6, "Cup of Joe (OPM)");
        searchArtist("  ");                      // Validation Error: blank keyword
        searchArtist("Cup");
        archiveArtist(-1);                       // Validation Error: id <= 0
        archiveArtist(6);
        restoreArtist(6);
        deleteArtist(6);

        // Albums validation & CRUD tests
        createAlbum("", 2021, 1);                // Validation Error: blank name
        createAlbum("Pebble House", 1800, 1);    // Validation Error: invalid year
        createAlbum("Pebble House", 2021, 0);    // Validation Error: invalid artistId
        createAlbum("Pebble House", 2021, 1);
        updateAlbum(6, "Coco", 1990, 1);
        searchAlbum("   ");                      // Validation Error: blank keyword
        searchAlbum("Coco");
        archiveAlbum(6);
        restoreAlbum(6);
        deleteAlbum(6);

        // Users validation & auth tests
        createUser("", "yuno123", "yuno@gmail.com");
        createUser("yuno", "123", "yuno@gmail.com");
        createUser("yuno", "yuno123", "invalid-email");
        // createUser("yuno", "yuno123", "yuno@gmail.com");
        loginUser("", "yuno123");
        loginUser("yuno", "");
        loginUser("yuno", "yuno123");
    }

    // ducking exception
    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    // ==========================================
    // ALBUMS CRUD OPERATIONS
    // ==========================================

    // Read Albums
    public static void viewAllAlbums() {
        String query = "SELECT albums.id, albums.name, albums.year, artists.name AS artist_name " +
                "FROM albums " +
                "JOIN artists " +
                "ON albums.artist_id = artists.id " +
                "WHERE albums.is_archived = 0";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n----- Albums -----");
            System.out.printf("%-4s %-20s %-8s %-20s%n", "ID", "Name", "Year", "Artist");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int year = resultSet.getInt("year");
                String artistName = resultSet.getString("artist_name");
                System.out.printf("%-4d %-20s %-8d %-20s%n", id, name, year, artistName);
            }
        } catch (SQLException e) {
            System.out.println("Read Albums Error: " + e.getMessage());
        }
    }

    // Create Album
    public static void createAlbum(String name, int year, int artistId) {
        // Guard Clauses
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Album name is required.");
            return;
        }
        if (year < 1900 || year > 2100) {
            System.out.println("\nValidation Error: Year must be between 1900 and 2100.");
            return;
        }
        if (artistId <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "INSERT INTO albums (name, year, artist_id) VALUES (?, ?, ?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, artistId);

            preparedStatement.executeUpdate();

            System.out.println("\nAlbum: " + name.trim() + " created successfully!");
            viewAllAlbums();
        } catch (SQLException e) {
            System.out.println("\nCreate Album Error: " + e.getMessage());
        }
    }

    // Update Album
    public static void updateAlbum(int id, String name, int year, int artistId) {
        // Guard Clauses
        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Album name is required.");
            return;
        }
        if (year < 1900 || year > 2100) {
            System.out.println("\nValidation Error: Year must be between 1900 and 2100.");
            return;
        }
        if (artistId <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "UPDATE albums SET name = ?, year = ?, artist_id = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, artistId);
            preparedStatement.setInt(4, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " updated successfully!");
                viewAllAlbums();
            } else {
                System.out.println("\nNo album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nUpdate Album Error: " + e.getMessage());
        }
    }

    // Delete Album
    public static void deleteAlbum(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        String query = "DELETE FROM albums WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " deleted successfully!");
                viewAllAlbums();
            } else {
                System.out.println("\nNo album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nDelete Album Error: " + e.getMessage());
        }
    }

    // Search Album
    public static void searchAlbum(String keyword) {
        // Guard Clause
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("\nValidation Error: Search keyword is required.");
            return;
        }

        String query = "SELECT albums.id, albums.name, albums.year, artists.name AS artist_name " +
                "FROM albums " +
                "JOIN artists ON albums.artist_id = artists.id " +
                "WHERE albums.is_archived = 0 AND albums.name LIKE ? " +
                "ORDER BY albums.id ASC";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            String cleanKeyword = keyword.trim();
            prep.setString(1, "%" + cleanKeyword + "%");

            try (ResultSet res = prep.executeQuery()) {
                System.out.println("\n----- Search Albums (Keyword: '" + cleanKeyword + "') -----");
                System.out.printf("%-4s %-22s %-8s %-20s%n", "ID", "Name", "Year", "Artist");

                boolean found = false;
                while (res.next()) {
                    found = true;
                    System.out.printf("%-4d %-22s %-8d %-20s%n",
                            res.getInt("id"),
                            res.getString("name"),
                            res.getInt("year"),
                            res.getString("artist_name"));
                }

                if (!found) {
                    System.out.println("No matching active albums found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSearch Album Error: " + e.getMessage());
        }
    }

    // Archive Album (Soft Delete)
    public static void archiveAlbum(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        String query = "UPDATE albums SET is_archived = 1 WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " archived successfully!");
                viewAllAlbums();
            } else {
                System.out.println("\nNo album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nArchive Album Error: " + e.getMessage());
        }
    }

    // Restore Album
    public static void restoreAlbum(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Album ID must be greater than 0.");
            return;
        }

        String query = "UPDATE albums SET is_archived = 0 WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setInt(1, id);

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nAlbum ID " + id + " restored successfully!");
                viewAllAlbums();
            } else {
                System.out.println("\nNo album found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nRestore Album Error: " + e.getMessage());
        }
    }

    // ==========================================
    // ARTISTS CRUD OPERATIONS
    // ==========================================

    // Read Artists
    public static void viewAllArtists() {
        String query = "SELECT * FROM artists WHERE is_archived = 0";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            System.out.println("\n----- Artists -----");
            System.out.printf("%-4s %-20s%n", "ID", "Artist Name");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.printf("%-4d %-20s%n", id, name);
            }
        } catch (SQLException e) {
            System.out.println("Read Artists Error: " + e.getMessage());
        }
    }

    // Create Artist
    public static void createArtist(String name) {
        // Guard Clause
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return;
        }

        String query = "INSERT INTO artists (name) VALUES (?)";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());

            preparedStatement.executeUpdate();

            System.out.println("\nArtist: " + name.trim() + " created successfully!");
            viewAllArtists();
        } catch (SQLException e) {
            System.out.println("\nCreate Artist Error: " + e.getMessage());
        }
    }

    // Update Artist
    public static void updateArtist(int id, String name) {
        // Guard Clauses
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return;
        }

        String query = "UPDATE artists SET name = ? WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, name.trim());
            preparedStatement.setInt(2, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " updated successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nUpdate Artist Error: " + e.getMessage());
        }
    }

    // Delete Artist
    public static void deleteArtist(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "DELETE FROM artists WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " deleted successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nDelete Artist Error: " + e.getMessage());
        }
    }

    // Archive Artist (Soft Delete)
    public static void archiveArtist(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "UPDATE artists SET is_archived = 1 WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " archived successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nArchive Artist Error: " + e.getMessage());
        }
    }

    // Restore Artist
    public static void restoreArtist(int id) {
        // Guard Clause
        if (id <= 0) {
            System.out.println("\nValidation Error: Artist ID must be greater than 0.");
            return;
        }

        String query = "UPDATE artists SET is_archived = 0 WHERE id = ?";

        try (Connection connection = connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("\nArtist ID " + id + " restored successfully!");
                viewAllArtists();
            } else {
                System.out.println("\nNo artist found with ID " + id + ".");
            }
        } catch (SQLException e) {
            System.out.println("\nRestore Artist Error: " + e.getMessage());
        }
    }

    // Search Artist
    public static void searchArtist(String keyword) {
        // Guard Clause
        if (keyword == null || keyword.trim().isEmpty()) {
            System.out.println("\nValidation Error: Search keyword is required.");
            return;
        }

        String query = "SELECT id, name FROM artists " +
                "WHERE is_archived = 0 AND name LIKE ? " +
                "ORDER BY id ASC";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            String cleanKeyword = keyword.trim();
            prep.setString(1, "%" + cleanKeyword + "%");

            try (ResultSet res = prep.executeQuery()) {
                System.out.println("\n----- Search Artists (Keyword: '" + cleanKeyword + "') -----");
                System.out.printf("%-4s %-25s%n", "ID", "Artist Name");

                boolean found = false;
                while (res.next()) {
                    found = true;
                    System.out.printf("%-4d %-25s%n",
                            res.getInt("id"),
                            res.getString("name"));
                }

                if (!found) {
                    System.out.println("No matching active artists found.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nSearch Artist Error: " + e.getMessage());
        }
    }

    // ==========================================
    // USER AUTHENTICATION (BCRYPT + GUARD CLAUSES)
    // ==========================================

    public static void createUser(String uname, String rawPassword, String email) {
        // Guard Clauses
        if (uname == null || uname.trim().isEmpty()) {
            System.out.println("\nValidation Error: Username is required.");
            return;
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@") || !email.contains(".")) {
            System.out.println("\nValidation Error: Valid email is required.");
            return;
        }
        if (rawPassword == null || rawPassword.trim().length() < 6) {
            System.out.println("\nValidation Error: Password must be at least 6 characters.");
            return;
        }

        String hashedPassword = BCrypt.hashpw(rawPassword.trim(), BCrypt.gensalt());

        String query = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, 'USER')";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, uname.trim());
            prep.setString(2, hashedPassword);
            prep.setString(3, email.trim());

            int rowsAffected = prep.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("\nUser '" + uname.trim() + "' created successfully!");
            }
        } catch (SQLException e) {
            System.out.println("\nCreate User Error: " + e.getMessage());
        }
    }

    public static void loginUser(String uname, String candidatePassword) {
        // Guard Clauses
        if (uname == null || uname.trim().isEmpty()) {
            System.out.println("\nValidation Error: Username is required to log in.");
            return;
        }
        if (candidatePassword == null || candidatePassword.isEmpty()) {
            System.out.println("\nValidation Error: Password is required to log in.");
            return;
        }

        String query = "SELECT username, password, role FROM users WHERE username = ?";

        try (Connection conn = connect();
             PreparedStatement prep = conn.prepareStatement(query)) {

            prep.setString(1, uname.trim());

            try (ResultSet res = prep.executeQuery()) {
                if (res.next()) {
                    String storedHash = res.getString("password");

                    if (BCrypt.checkpw(candidatePassword, storedHash)) {
                        String username = res.getString("username");
                        String role = res.getString("role");
                        System.out.println("\nLogin successful! Welcome, " + username + " (Role: " + role + ")");
                    } else {
                        System.out.println("\nLogin failed: Invalid username or password.");
                    }
                } else {
                    System.out.println("\nLogin failed: Invalid username or password.");
                }
            }
        } catch (SQLException e) {
            System.out.println("\nLogin Error: " + e.getMessage());
        }
    }
}

/*
===============================================================================
                         3-TIER ARCHITECTURE OVERVIEW
===============================================================================

┌───────────────────────────┬────────────────┬──────────────────────────────────────────────┬──────────────────────────────────────────────┐
│ ARCHITECTURAL TIER        │ PACKAGE        │ RESPONSIBILITY                               │ EXAMPLE CLASSES                              │
├───────────────────────────┼────────────────┼──────────────────────────────────────────────┼──────────────────────────────────────────────┤
│ Presentation Tier         │ view           │ Console menus, Scanner inputs, and           │ ArtistView                                   │
│ (User Interface)          │                │ formatted output.                            │ AlbumView                                    │
│                           │                │                                              │ UserView                                     │
├───────────────────────────┼────────────────┼──────────────────────────────────────────────┼──────────────────────────────────────────────┤
│ Application Tier          │ service        │ Business rules, validation, guard clauses,   │ ArtistService                                │
│ (Business Logic Layer)    │                │ and BCrypt password hashing.                 │ ArtistServiceImpl                            │
│                           │                │                                              │ UserService                                  │
│                           │                │                                              │ UserServiceImpl                              │
├───────────────────────────┼────────────────┼──────────────────────────────────────────────┼──────────────────────────────────────────────┤
│ Data Tier                 │ repository     │ Database access, SQL queries, and            │ ArtistRepository                             │
│ (Persistence Layer)       │                │ ResultSet-to-Model mapping only.             │ ArtistRepositoryImpl                         │
│                           │                │                                              │ UserRepository                               │
│                           │                │                                              │ UserRepositoryImpl                           │
└───────────────────────────┴────────────────┴──────────────────────────────────────────────┴──────────────────────────────────────────────┘


===============================================================================
                         SUPPORTING PACKAGES
===============================================================================

┌─────────────────────┬──────────────┬────────────────────────────────────────┬─────────────────────┐
│ ROLE                │ PACKAGE      │ RESPONSIBILITY                         │ EXAMPLE CLASS       │
├─────────────────────┼──────────────┼────────────────────────────────────────┼─────────────────────┤
│ Database Config     │ config       │ JDBC URL, credentials, and             │ DbConnection        │
│                     │              │ Connection management.                 │                     │
├─────────────────────┼──────────────┼────────────────────────────────────────┼─────────────────────┤
│ Domain Models       │ model        │ POJOs representing database entities.  │ Artist              │
│                     │              │ Represent database data                │ Album               │
│                     │              │                                        │ User                │
└─────────────────────┴──────────────┴────────────────────────────────────────┴─────────────────────┘

*/