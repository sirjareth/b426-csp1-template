package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.repository.*;
import com.joysistvi.recordingapp.service.*;
import com.joysistvi.recordingapp.view.AlbumView;
import com.joysistvi.recordingapp.view.ArtistView;
import com.joysistvi.recordingapp.view.UserView;

import java.util.Scanner;

// Entry Point
public class App {

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

        // Artist flow: Read, Create, Search, Update, Archive, Restore, Delete
//        artistView.displayAllArtists();
//        artistView.promptCreateArtist();
//        artistView.promptSearchArtists();
//        artistView.promptUpdateArtist();
//        artistView.promptArchiveArtist();
//        artistView.promptRestoreArtist();
//        artistView.promptDeleteArtist();
//
//        // Album flow: Read, Create, Search, Update, Archive, Restore, Delete
        albumView.displayAllAlbums();
//        albumView.promptCreateAlbum();
//        albumView.promptSearchAlbums();
//        albumView.promptUpdateAlbum();
//        albumView.promptArchiveAlbum();
//        albumView.promptRestoreAlbum();
//        albumView.promptDeleteAlbum();
//
//        // User flow: Register/Create, Login, Read, Search, Update, Archive, Restore, Delete
//        userView.promptCreateUser();
//        userView.promptLogin();
        userView.displayAllUsers();
//        userView.promptSearchUsers();
//        userView.promptUpdateUser();
//        userView.promptArchiveUser();
//        userView.promptRestoreUser();
//        userView.promptDeleteUser();

        scanner.close();

    }
}




/*
===============================================================================
                             Dependency Wiring
===============================================================================
    Repository
     │
     ▼
    Service
     │
     ▼
    View
     │
     ▼
    Main Application

    /*
===============================================================================
DATA FLOW
===============================================================================

REQUEST FLOW (Save / Insert / Update)

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
