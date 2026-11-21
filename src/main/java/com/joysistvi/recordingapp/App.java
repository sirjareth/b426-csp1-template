package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.model.User;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;
import com.joysistvi.recordingapp.view.AlbumView;
import com.joysistvi.recordingapp.view.ArtistView;
import com.joysistvi.recordingapp.view.UserView;

import java.util.Scanner;

// Entry Point
public class App {

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

    public static void main(String[] args) {
        App app = new App();
        app.run();

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
            dashBoard();
            System.out.println("\nLogged out " + currentUser.getUsername() + ".");
        }
    }

    // Authorization vs Authentication
    private void dashboard(){
        System.out.println("1. Song Management");
        System.out.println("2. Artist Management");
        System.out.println("3. Album Management");
        System.out.println("4. Users Management");
        System.out.println("0. Back");

        int choice = promptForInt("Choice: ");
        switch (choice) {
             //case 1 -> handleSongMenu();
             //case 2 -> handleArtistMenu();
             case 3 -> albumView.handleAlbumMenu();
             //case 4 -> handleUserMenu();
             case 0 -> {return;}
        }
    }

    private User showAuthMenuAndAuthenticate() {

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
