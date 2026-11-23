package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Song;
import com.joysistvi.recordingapp.service.SongService;
import java.util.List;
import java.util.Scanner;

public class SongView {
    private final SongService songService;
    private final Scanner scanner;

    public SongView(SongService songService, Scanner scanner) {
        this.songService = songService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== SONG MANAGEMENT ===");
            System.out.println("1. View All Active Songs");
            System.out.println("2. Create Song");
            System.out.println("3. Update Song");
            System.out.println("4. Search Songs");
            System.out.println("5. Archive Song");
            System.out.println("6. Restore Song");
            System.out.println("7. Delete Song");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
                    displayAllSongs();
                    break;
                case 2:
                    promptCreateSong();
                    break;
                case 3:
                    promptUpdateSong();
                    break;
                case 4:
                    promptSearchSongs();
                    break;
                case 5:
                    promptArchiveSong();
                    break;
                case 6:
                    promptRestoreSong();
                    break;
                case 7:
                    promptDeleteSong();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public void displayAllSongs() {
        printSongTable("----- Active Songs -----", songService.getAllActiveSongs());
    }

    public void promptCreateSong() {
        System.out.println("\n--- Create Song ---");
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter length (e.g. 3:45): ");
        String length = scanner.nextLine();
        int albumId = promptForInt("Enter album ID: ");

        if (songService.createSong(title, genre, length, albumId)) {
            System.out.println("Song created successfully.");
        } else {
            System.out.println("Failed to create song.");
        }
    }

    public void promptUpdateSong() {
        System.out.println("\n--- Update Song ---");
        int id = promptForInt("Enter song ID to update: ");
        System.out.print("Enter new song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter new genre: ");
        String genre = scanner.nextLine();
        System.out.print("Enter new length (e.g. 3:45): ");
        String length = scanner.nextLine();
        int albumId = promptForInt("Enter new album ID: ");

        if (songService.updateSong(id, title, genre, length, albumId)) {
            System.out.println("Song updated successfully.");
        } else {
            System.out.println("Failed to update song.");
        }
    }

    public void promptSearchSongs() {
        System.out.println("\n--- Search Songs ---");
        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine();
        printSongTable("----- Search Results -----", songService.searchSongs(keyword));
    }

    public void promptArchiveSong() {
        System.out.println("\n--- Archive Song ---");
        int id = promptForInt("Enter song ID to archive: ");
        if (songService.archiveSong(id)) {
            System.out.println("Song archived successfully.");
        } else {
            System.out.println("Failed to archive song.");
        }
    }

    public void promptRestoreSong() {
        System.out.println("\n--- Restore Song ---");
        List<Song> archived = songService.getAllArchivedSongs();
        if (archived.isEmpty()) {
            System.out.println("No archived songs found.");
            return;
        }
        printSongTable("----- Archived Songs -----", archived);
        int id = promptForInt("Enter song ID to restore: ");
        if (songService.restoreSong(id)) {
            System.out.println("Song restored successfully.");
        } else {
            System.out.println("Failed to restore song.");
        }
    }

    public void promptDeleteSong() {
        System.out.println("\n--- Delete Song ---");
        int id = promptForInt("Enter song ID to delete permanently: ");
        if (songService.deleteSong(id)) {
            System.out.println("Song deleted successfully.");
        } else {
            System.out.println("Failed to delete song.");
        }
    }

    private void printSongTable(String header, List<Song> songs, String emptyMessage) {
        String border = "+------+---------------------------+------------+----------+";

        System.out.println("\n" + header);
        System.out.println(border);
        System.out.printf("| %-4s | %-25s | %-10s | %-8s |%n", "ID", "Song Title", "Length", "Album ID");
        System.out.println(border);

        if (songs == null || songs.isEmpty()) {
            String message = (emptyMessage != null && !emptyMessage.isEmpty())
                    ? emptyMessage
                    : "No songs found.";
            System.out.printf("| %-56s |%n", truncate(message, 56));
            System.out.println(border);
            return;
        }

        for (Song song : songs) {
            String title = song.getTitle() != null ? song.getTitle() : "";
            System.out.printf("| %-4d | %-25s | %-10d | %-8d |%n",
                    song.getId(),
                    truncate(title, 25),
                    song.getLength(),
                    song.getAlbumId());
        }

        System.out.println(border);
    }

    private void printSongTable(String header, List<Song> songs) {
        printSongTable(header, songs, "No songs found.");
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