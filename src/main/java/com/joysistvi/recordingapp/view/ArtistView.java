package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.service.ArtistService;

import java.util.List;
import java.util.Scanner;

public class ArtistView {

    private final ArtistService artistService;
    private final Scanner scanner;

    public ArtistView(ArtistService artistService, Scanner scanner) {
        this.artistService = artistService;
        this.scanner = scanner;
    }

    public void displayAllArtist() {
        List<Artist> artists = artistService.getAllActiveArtists();


        System.out.println("\n----- Artists -----");
        System.out.printf("%-4s %-20s%n", "ID", "Artist Name");

        if (artists == null || artists.isEmpty()) {
            System.out.println("No active artists found.");
            return;
        }

        for (Artist artist : artists) {
            System.out.printf("%-4d %-20s%n",
                    artist.getId(),
                    artist.getName());
        }

    }

    public void promptCreateArtist() {
        System.out.println("\n----- Create New Artist -----");
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        boolean created = artistService.createArtist(name);

        if (created) {
            System.out.println("Artist " + name.trim() + " created successfully" );
        } else {
            System.out.println("Failed to create artist.");
        }
    }
}
