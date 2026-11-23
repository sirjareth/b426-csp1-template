package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.PlaylistService;
import com.joysistvi.recordingapp.service.SongService;
import java.util.List;
import java.util.Scanner;

public class PlaylistView {
    private final PlaylistService playlistService;
    private final SongService songService;
    private final Scanner scanner;
    private int currentUserId;

    public PlaylistView(PlaylistService playlistService, SongService songService, Scanner scanner) {
        this.playlistService = playlistService;
        this.songService = songService;
        this.scanner = scanner;
    }

    public void handleMenu(int userId) {
        this.currentUserId = userId;
        while (true) {
            System.out.println("\n=== MANAGE PLAYLIST ===");
            System.out.println("1. View Playlist");
            System.out.println("2. Create Playlist");
            System.out.println("3. Delete Playlist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
                    handleViewUserPlaylists(currentUserId);
                    break;
                case 2:
                    promptCreatePlaylist(currentUserId);
                    break;
                case 3:
                    promptDeleteUserPlaylist(currentUserId);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public void promptCreatePlaylist(int userId) {
        System.out.println("\n--- Create Playlist ---");
        if (!playlistService.getPlaylistsByUser(userId).isEmpty()) {
            System.out.println("You already have a playlist. Only one playlist is allowed per user.");
            return;
        }

        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();

        if (playlistService.createPlaylist(name, userId)) {
            System.out.println("Playlist created successfully.");
        } else {
            System.out.println("Failed to create playlist.");
        }
    }

    public void handleViewUserPlaylists(int userId) {
        List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
            System.out.println("\nNo playlist found.");
            return;
        }

        for (Playlist playlist : userPlaylists) {
            System.out.println("\nPlaylist: " + playlist.getName());
            List<Song> songs = playlistService.getSongsInPlaylist(playlist.getId());
            printSongListTable("----- Current Songs in Playlist -----", songs);
        }
    }

    public void promptDeleteUserPlaylist(int userId) {
        System.out.println("\n--- Delete Playlist ---");
        List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
            System.out.println("No playlist found to delete.");
            return;
        }

        Playlist playlist = userPlaylists.get(0);
        if (playlistService.deletePlaylist(playlist.getId())) {
            System.out.println("Playlist \"" + playlist.getName() + "\" deleted successfully.");
        } else {
            System.out.println("Failed to delete playlist.");
        }
    }

    // Additional Methods
    public void handleManageSongsInPlaylist(int userId) {
        List<Playlist> userPlaylists = playlistService.getPlaylistsByUser(userId);
        if (userPlaylists.isEmpty()) {
            System.out.println("\nNo playlist found for this user. Please create a playlist first.");
            return;
        }

        Playlist playlist = userPlaylists.getFirst();

        while (true) {
            System.out.println("\n=== MANAGE SONGS (" + playlist.getName() + ") ===");
            System.out.println("1. Add Song to Playlist");
            System.out.println("2. Remove Song from Playlist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
                    printSongListTable("----- Available Songs -----", songService.getAllActiveSongs());
                    int addSongId = promptForInt("Enter song ID to add: ");
                    if (playlistService.addSongToPlaylist(playlist.getId(), addSongId)) {
                        System.out.println("Song added to playlist.");
                    } else {
                        System.out.println("Failed to add song to playlist.");
                    }
                    break;
                case 2:
                    printSongListTable("----- Current Songs in Playlist -----", playlistService.getSongsInPlaylist(playlist.getId()));
                    int removeSongId = promptForInt("Enter song ID to remove: ");
                    if (playlistService.removeSongFromPlaylist(playlist.getId(), removeSongId)) {
                        System.out.println("Song removed from playlist.");
                    } else {
                        System.out.println("Failed to remove song from playlist.");
                    }
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }



    private void printSongListTable(String header, List<Song> songs) {
        String border = "+-------+------------------------+-----------------+----------+----------------------+";

        System.out.println("\n" + header);
        System.out.println(border);
        System.out.printf("| %-5s | %-22s | %-15s | %-8s | %-20s |%n",
                "ID", "Title", "Genre", "Length", "Album");
        System.out.println(border);

        if (songs == null || songs.isEmpty()) {
            System.out.printf("| %-84s |%n", truncate("No songs found.", 84));
            System.out.println(border);
            return;
        }

        for (Song s : songs) {
            String title = s.getTitle() != null ? s.getTitle() : "";
            String genre = s.getGenre() != null ? s.getGenre() : "";
            String album = s.getAlbumName() != null ? s.getAlbumName() : "";

            System.out.printf("| %-5d | %-22s | %-15s | %-8s | %-20s |%n",
                    s.getId(),
                    truncate(title, 22),
                    truncate(genre, 15),
                    s.getLength(),
                    truncate(album, 20));
        }

        System.out.println(border);
    }

    private String truncate(String text, int maxLength) {
        if (text == null) {
            return "";
        }
        if (text.length() <= maxLength) {
            return text;
        }
        if (maxLength <= 3) {
            return text.substring(0, maxLength);
        }
        return text.substring(0, maxLength - 3) + "...";
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