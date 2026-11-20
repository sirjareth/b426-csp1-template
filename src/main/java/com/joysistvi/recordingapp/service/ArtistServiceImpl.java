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

    public List<Artist> getAllActiveArtists() {
        return  artistRepository.findAllActive();
    }

}
