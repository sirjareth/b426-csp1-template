package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.service.ArtistService;

import java.util.List;

public class ArtistView {

    private final ArtistService artistService;

    public ArtistView(ArtistService artistService) {
        this.artistService = artistService;
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
}
