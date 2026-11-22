package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Playlist;
import com.joysistvi.recordingapp.service.PlaylistService;
import java.util.List;
import java.util.Scanner;

public class PlaylistView {
    private final PlaylistService playlistService;
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
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
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
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

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

        if (playlistService.createPlaylist(name, userId)) {
            System.out.println("Playlist created successfully.");
        } else {
            System.out.println("Failed to create playlist.");
        }
    }

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