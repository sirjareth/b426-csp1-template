package com.joysistvi.recordingapp;

import com.joysistvi.recordingapp.config.DbConnection;
import com.joysistvi.recordingapp.repository.ArtistRepository;
import com.joysistvi.recordingapp.repository.ArtistRepositoryImpl;
import com.joysistvi.recordingapp.service.ArtistService;
import com.joysistvi.recordingapp.service.ArtistServiceImpl;
import com.joysistvi.recordingapp.view.ArtistView;

import java.util.Scanner;

// Entry Point
public class App {

    public static void main(String[] args) {
        DbConnection dbConnection = new DbConnection();
        Scanner scanner = new Scanner(System.in);
        // Wiring
        ArtistRepository artistRepository = new ArtistRepositoryImpl(dbConnection);
        ArtistService artistService = new ArtistServiceImpl(artistRepository);
        ArtistView artistView = new ArtistView(artistService, scanner);


        artistView.displayAllArtist();
        artistView.promptCreateArtist();

    }
}




/*
// APPLICATION FLOW
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
 */
