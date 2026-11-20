package com.joysistvi.recordingapp.service;

import com.joysistvi.recordingapp.model.Artist;
import com.joysistvi.recordingapp.repository.ArtistRepository;

import java.util.List;


// Application Tier: Business Rule / Validation
public class ArtistServiceImpl implements ArtistService{

    private final ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Artist> getAllActiveArtists() {
        return artistRepository.findAllActive();
    }

    @Override
    public boolean createArtist(String name){
        // Guard Clause
        if (name == null || name.trim().isEmpty()) {
            System.out.println("\nValidation Error: Artist name is required.");
            return false;
        }

        Artist artist = new Artist(name);
        return artistRepository.save(artist);
    }

}
