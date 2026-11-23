package com.joysistvi.recordingapp.view;

import com.joysistvi.recordingapp.model.Album;
import com.joysistvi.recordingapp.service.AlbumService;
import java.util.List;
import java.util.Scanner;

public class AlbumView {
    private final AlbumService albumService;
    private final Scanner scanner;

    public AlbumView(AlbumService albumService, Scanner scanner) {
        this.albumService = albumService;
        this.scanner = scanner;
    }

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== MANAGE ALBUMS ===");
            System.out.println("1. View All Albums");
            System.out.println("2. Create Album");
            System.out.println("3. Update Album");
            System.out.println("4. Search Album");
            System.out.println("5. Archive Album");
            System.out.println("6. Restore Album");
            System.out.println("7. Delete Album");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1 -> displayAllAlbums();
                case 2 -> promptCreateAlbum();
                case 3 -> promptUpdateAlbum();
                case 4 -> promptSearchAlbums();
                case 5 -> promptArchiveAlbum();
                case 6 -> promptRestoreAlbum();
                case 7 -> promptDeleteAlbum();
                case 0 -> { return; }
                default -> System.out.println("Invalid option. Please try again.");

            }
        }
    }

    public void displayAllAlbums() {
        printAlbumTable("\n----- Active Albums -----", albumService.getAllActiveAlbums(), "No active albums found.");
    }

    public void promptCreateAlbum() {
        System.out.println("\n=== CREATE Album ===");
        System.out.print("Enter album name: ");
        String name = scanner.nextLine();
        int year = promptForInt("Enter release year (e.g. 2024): ");
        int artistId = promptForInt("Enter artist ID: ");

        boolean created = albumService.createAlbum(name, year, artistId);
        if (created) {
            System.out.println("Album '" + name.trim() + "' created successfully!");
        } else {
            System.out.println("Failed to create album.");
        }
    }

    public void promptUpdateAlbum() {
        System.out.println("\n=== UPDATE Album ===");
        displayAllAlbums();
        int id = promptForInt("Enter album ID to update: ");
        System.out.print("Enter new album name: ");
        String name = scanner.nextLine();
        int year = promptForInt("Enter new release year: ");
        int artistId = promptForInt("Enter artist ID: ");

        boolean updated = albumService.updateAlbum(id, name, year, artistId);
        if (updated) {
            System.out.println("Album ID " + id + " updated successfully!");
        } else {
            System.out.println("No album updated (check ID or input).");
        }
    }

    public void promptDeleteAlbum() {
        System.out.println("\n=== DELETE Album ===");
        displayAllAlbums();
        int id = promptForInt("Enter album ID to delete: ");

        boolean deleted = albumService.deleteAlbum(id);
        if (deleted) {
            System.out.println("Album ID " + id + " deleted successfully!");
        } else {
            System.out.println("No album deleted (check ID or input).");
        }
    }

    public void promptArchiveAlbum() {
        System.out.println("\n=== ARCHIVE Album ===");
        displayAllAlbums();
        int id = promptForInt("Enter album ID to archive: ");

        boolean archived = albumService.archiveAlbum(id);
        if (archived) {
            System.out.println("Album ID " + id + " archived successfully!");
        } else {
            System.out.println("No album archived (check ID or input).");
        }
    }

    public void promptRestoreAlbum() {
        System.out.println("\n=== RESTORE Album ===");
        List<Album> archivedAlbums = albumService.getAllArchivedAlbums();
        printAlbumTable("\n----- Archived Albums -----", archivedAlbums, "No archived albums found.");

        if (archivedAlbums == null || archivedAlbums.isEmpty()) {
            return;
        }

        int id = promptForInt("Enter album ID to restore: ");
        boolean restored = albumService.restoreAlbum(id);
        if (restored) {
            System.out.println("Album ID " + id + " restored successfully!");
        } else {
            System.out.println("No archived album restored (check ID or input).");
        }
    }

    public void promptSearchAlbums() {
        System.out.println("\n=== SEARCH Albums ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Album> matches = albumService.searchAlbums(keyword);
        printAlbumTable("\n----- Search Albums (Keyword: '" + keyword.trim() + "') -----",
                matches, "No matching active albums found.");
    }

    private void printAlbumTable(String header, List<Album> albums, String emptyMessage) {
        String border = "+------+---------------------------+";

        System.out.println("\n" + header);
        System.out.println(border);
        System.out.printf("| %-4s | %-25s |%n", "ID", "Album Name");
        System.out.println(border);

        if (albums == null || albums.isEmpty()) {
            String message = (emptyMessage != null && !emptyMessage.isEmpty())
                    ? emptyMessage
                    : "No artists found.";
            System.out.printf("| %-32s |%n", truncate(message, 32));
            System.out.println(border);
            return;
        }

        for (Album album : albums) {
            String name = album.getName() != null ? album.getName() : "";
            System.out.printf("| %-4d | %-25s |%n", album.getId(), truncate(name, 25));
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