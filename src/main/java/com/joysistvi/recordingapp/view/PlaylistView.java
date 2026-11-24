package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Playlist;
<<<<<<< HEAD
import com.joysistvi.recordingapp.service.PlaylistService;
=======
import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.PlaylistService;
import com.joysistvi.recordingapp.service.SongService;
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
import java.util.List;
import java.util.Scanner;

public class PlaylistView {
    private final PlaylistService playlistService;
<<<<<<< HEAD
    private final Scanner scanner;

    public PlaylistView(PlaylistService playlistService, Scanner scanner) {
        this.playlistService = playlistService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== PLAYLIST MANAGEMENT ===");
            System.out.println("1. View All Active Playlists");
            System.out.println("2. View Playlists by User");
            System.out.println("3. Create Playlist");
            System.out.println("4. Update Playlist");
            System.out.println("5. Search Playlists");
            System.out.println("6. Archive Playlist");
            System.out.println("7. Restore Playlist");
            System.out.println("8. Delete Playlist");
=======
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
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
<<<<<<< HEAD
                    displayAllPlaylists();
                    break;
                case 2:
                    promptViewPlaylistsByUser();
                    break;
                case 3:
                    promptCreatePlaylist();
                    break;
                case 4:
                    promptUpdatePlaylist();
                    break;
                case 5:
                    promptSearchPlaylists();
                    break;
                case 6:
                    promptArchivePlaylist();
                    break;
                case 7:
                    promptRestorePlaylist();
                    break;
                case 8:
                    promptDeletePlaylist();
=======
                    handleViewUserPlaylists(currentUserId);
                    break;
                case 2:
                    promptCreatePlaylist(currentUserId);
                    break;
                case 3:
                    promptDeleteUserPlaylist(currentUserId);
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

<<<<<<< HEAD
    public void displayAllPlaylists() {
        printPlaylistTable("----- Active Playlists -----", playlistService.getAllActivePlaylists());
    }

    public void promptViewPlaylistsByUser() {
        System.out.println("\n--- View Playlists by User ---");
        int userId = promptForInt("Enter user ID: ");
        printPlaylistTable("----- User Playlists -----", playlistService.getPlaylistsByUser(userId));
    }

    public void promptCreatePlaylist() {
        System.out.println("\n--- Create Playlist ---");
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        int userId = promptForInt("Enter owner user ID: ");
=======
    public void promptCreatePlaylist(int userId) {
        System.out.println("\n--- Create Playlist ---");
        if (!playlistService.getPlaylistsByUser(userId).isEmpty()) {
            System.out.println("You already have a playlist. Only one playlist is allowed per user.");
            return;
        }

        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445

        if (playlistService.createPlaylist(name, userId)) {
            System.out.println("Playlist created successfully.");
        } else {
            System.out.println("Failed to create playlist.");
        }
    }

<<<<<<< HEAD
    public void promptUpdatePlaylist() {
        System.out.println("\n--- Update Playlist ---");
        int id = promptForInt("Enter playlist ID to update: ");
        System.out.print("Enter new playlist name: ");
        String name = scanner.nextLine();
        int userId = promptForInt("Enter new owner user ID: ");

        if (playlistService.updatePlaylist(id, name, userId)) {
            System.out.println("Playlist updated successfully.");
        } else {
            System.out.println("Failed to update playlist.");
        }
    }

    public void promptSearchPlaylists() {
        System.out.println("\n--- Search Playlists ---");
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();
        printPlaylistTable("----- Search Results -----", playlistService.searchPlaylists(keyword));
    }

    public void promptArchivePlaylist() {
        System.out.println("\n--- Archive Playlist ---");
        int id = promptForInt("Enter playlist ID to archive: ");
        if (playlistService.archivePlaylist(id)) {
            System.out.println("Playlist archived successfully.");
        } else {
            System.out.println("Failed to archive playlist.");
        }
    }

    public void promptRestorePlaylist() {
        System.out.println("\n--- Restore Playlist ---");
        List<Playlist> archived = playlistService.getAllArchivedPlaylists();
        if (archived.isEmpty()) {
            System.out.println("No archived playlists found.");
            return;
        }
        printPlaylistTable("----- Archived Playlists -----", archived);
        int id = promptForInt("Enter playlist ID to restore: ");
        if (playlistService.restorePlaylist(id)) {
            System.out.println("Playlist restored successfully.");
        } else {
            System.out.println("Failed to restore playlist.");
        }
    }

    public void promptDeletePlaylist() {
        System.out.println("\n--- Delete Playlist ---");
        int id = promptForInt("Enter playlist ID to delete permanently: ");
        if (playlistService.deletePlaylist(id)) {
            System.out.println("Playlist deleted successfully.");
        } else {
            System.out.println("Failed to delete playlist.");
        }
    }

    private void printPlaylistTable(String header, List<Playlist> playlists) {
        System.out.println("\n" + header);
        System.out.printf("%-5s %-25s %-18s%n", "ID", "Playlist Name", "Owner");
        if (playlists == null || playlists.isEmpty()) {
            System.out.println("No playlists found.");
            return;
        }
        for (Playlist p : playlists) {
            System.out.printf("%-5d %-25s %-18s%n",
                    p.getId(), p.getName(), p.getUsername());
        }
    }

=======
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


>>>>>>> 0458865eb445e3545f62d4d3cb7aad9d8e7eb445
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