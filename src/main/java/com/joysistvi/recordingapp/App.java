package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;
import com.joysistvi.recordingapp.view.*;

import java.sql.SQLOutput;
import java.util.Scanner;

// Role-Based Access Control (RBAC)  ADMIN vs. USER

// Entry Point
public class App {

    private final Scanner scanner;
    private final UserView userView;
    private final ArtistView artistView;
    private final AlbumView albumView;
    private final SongView songView;
    private final PlaylistView playlistView;

    public App(Scanner scanner, UserView userView, ArtistView artistView, AlbumView albumView, SongView songView, PlaylistView playlistView) {
        this.scanner = scanner;
        this.userView = userView;
        this.artistView = artistView;
        this.albumView = albumView;
        this.songView = songView;
        this.playlistView = playlistView;
    }

    SongRepository songRepository = new SongRepositoryImpl(dbConnection);
    SongService songService = new SongServiceImpl(songRepository);
    SongView songView = new SongView(songService, scanner);

    PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
    PlaylistService playlistService = new PlaylistServiceImpl(playlistRepository);
    PlaylistView playlistView = new PlaylistView(playlistService, scanner);

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Scanner scanner = new Scanner(System.in);
        // Wiring
        // 1. Wire Artist (Repository -> Service -> View)
        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistView artistView = new ArtistView(artistService, scanner);

        // 2. Wire Album (Repository -> Service -> View)
        AlbumRepository albumRepository = new AlbumRepositoryImpl(dbConnection);
        AlbumService albumService = new AlbumServiceImpl(albumRepository);
        AlbumView albumView = new AlbumView(albumService, scanner);

        // 3. Wire User (Repository -> Service -> View)
        UserRepository userRepository = new UserRepositoryImpl(dbConnection);
        UserService userService = new UserServiceImpl(userRepository);
        UserView userView = new UserView(userService, scanner);

        SongRepository songRepository = new SongRepositoryImpl(dbConnection);
        SongService songService = new SongServiceImpl(songRepository);
        SongView songView = new SongView(songService, scanner);

        PlaylistRepository playlistRepository = new PlaylistRepositoryImpl(dbConnection);
        PlaylistService playlistService = new PlaylistServiceImpl(playlistRepository);
        PlaylistView playlistView = new PlaylistView(playlistService, songService, scanner);


        App app = new App(scanner, userView, artistView, albumView, songView, playlistView);
        app.run();

        scanner.close();

    }

    public void run() {

        while (true) {
            User currentUser = showAuthMenuAndAuthenticate();
            if (currentUser == null) {
                System.out.println("\nExiting Recording Studio App. Goodbye!");
                break;
            }

            // Any logged-in user enter the album menu
            System.out.println("\nWelcome, " + currentUser.getUsername() + " (" + currentUser.getRole() + ")!");
<<<<<<< HEAD
            dashboard();
=======

            if ("ADMIN".equalsIgnoreCase(currentUser.getRole())) {
                adminDashboard();
            } else {
                userDashboard(currentUser);
            }

>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
            System.out.println("\nLogged out " + currentUser.getUsername() + ".");
        }
    }

    // Authorization vs Authentication
    private void adminDashboard(){
        System.out.println("\n=== ADMIN DASHBOARD ===");
        System.out.println("1. Song Management");
        System.out.println("2. Artist Management");
        System.out.println("3. Album Management");
        System.out.println("4. Users Management");

        System.out.println("0. Back");

        int choice = promptForInt("Choice: ");
        switch (choice) {
<<<<<<< HEAD
             case 1 -> songView.handleMenu();
             case 2 -> artistView.handleMenu();
             case 3 -> albumView.handleAlbumMenu();
             case 4 -> playlistView.handleMenu();
             case 0 -> {return;}
=======
            case 1 -> songView.handleMenu();
            case 2 -> artistView.handleMenu();
            case 3 -> albumView.handleMenu();
            case 4 -> userView.handleMenu();
            case 0 -> {return;}
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
        }
    }

    private void userDashboard(User currentUser) {
        while (true) {
            System.out.println("\n=== USER DASHBOARD ===");
            System.out.println("1. Browse Artists, Albums, Songs");
            System.out.println("2. Search Songs");
            System.out.println("3. Manage Playlist");
            System.out.println("4. Add / Remove Songs in Playlist");
            System.out.println("0. Logout");

            int choice = promptForInt("Choice: ");

            switch (choice) {
                case 1 -> browseCatalog();
                case 2 -> songView.promptSearchSongs();
                case 3 -> playlistView.handleMenu(currentUser.getId());
                case 4 -> playlistView.handleManageSongsInPlaylist(currentUser.getId());
                case 0 -> { return; }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void browseCatalog() {
        System.out.println("\n=== BROWSE CATALOG ===");
        System.out.println("1. View Artists");
        System.out.println("2. View Albums");
        System.out.println("3. View Songs");
        System.out.println("0. Back");

        int choice = promptForInt("Choice: ");

        switch (choice) {
            case 1 -> artistView.displayAllArtists();
            case 2 -> albumView.displayAllAlbums();
            case 3 -> songView.displayAllSongs();
            case 0 -> { return; }
            default -> System.out.println("Invalid Choice: Please try again.");
        }
    }


    private User showAuthMenuAndAuthenticate() {
        clearScreen();
        while (true) {
            System.out.println("\n=== RECORDING STUDIO APP ===");
            System.out.println("1. Login");
            System.out.println("2. Register Account");
            System.out.println("0. Exit");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
                    User loggedInUser = userView.promptLogin();

                    if (loggedInUser != null ) {
                        return loggedInUser;
                    }
                    break;

                case 2:
                    userView.promptCreateUser();
                    break;
                case 0:
                    return null;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }


    private int promptForInt(String promptMessage) {
        while (true) {
            System.out.print(promptMessage);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    // Clears the console using ANSI escape codes. Works in real terminals and in
    // IntelliJ's Run console IF "Emulate terminal in output console" is enabled.
    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}




/*
===============================================================================
                             Build &  Wiring Order (Bottom-Up)
===============================================================================

    Model
    │
    ▼
    Repository
     │
     ▼
    Service
     │
     ▼
    View
     │
     ▼
    Main Application (App)

    /*
===============================================================================
                    DATA FLOW (Top-Down)
===============================================================================

REQUEST FLOW (Save / Insert / Update / Delete)

User/App
  ↓
View
  ↓
Service
  ↓
Repository (DAO)
  ↓
Database


RESPONSE FLOW (Retrieve / Search)

Database
  ↓
Repository (DAO)
  ↓
Service
  ↓
View
  ↓
User




Dependency Wiring:
Repository --> Service --> View --> main()

Example:
ArtistRepositoryImpl
        │
        ▼
ArtistServiceImpl
        │
        ▼
ArtistView
        │
        ▼
App.main()

Dependency Flow
View → Service → Repository → Database

Data Flow
User → View → Service → Repository → Database
Database → Repository → Service → View → User
 */
