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

    public void handleMenu() {
        while (true) {
            System.out.println("\n=== ARTIST MANAGEMENT ===");
            System.out.println("1. View All Active Artists");
            System.out.println("2. Create Artist");
            System.out.println("3. Update Artist");
            System.out.println("4. Search Artists");
            System.out.println("5. Archive Artist");
            System.out.println("6. Restore Artist");
            System.out.println("7. Delete Artist");
            System.out.println("0. Back");

            int choice = promptForInt("Choose an option: ");
            switch (choice) {
                case 1:
                    displayAllArtists();
                    break;
                case 2:
                    promptCreateArtist();
                    break;
                case 3:
                    promptUpdateArtist();
                    break;
                case 4:
                    promptSearchArtists();
                    break;
                case 5:
                    promptArchiveArtist();
                    break;
                case 6:
                    promptRestoreArtist();
                    break;
                case 7:
                    promptDeleteArtist();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    public void displayAllArtists() {
        printArtistTable("\n----- Active Artists -----", artistService.getAllActiveArtists(), "No active artists found.");
    }

    public void promptCreateArtist() {
        System.out.println("\n=== CREATE Artist ===");
        System.out.print("Enter artist name: ");
        String name = scanner.nextLine();

        boolean created = artistService.createArtist(name);
        if (created) {
            System.out.println("Artist '" + name.trim() + "' created successfully!");
        } else {
            System.out.println("Failed to create artist.");
        }
    }

    public void promptUpdateArtist() {
        System.out.println("\n=== UPDATE Artist ===");
        displayAllArtists();
        int id = promptForInt("Enter artist ID to update: "); // 16

        Artist current = artistService.getArtistById(id);

        if (current == null) {
            System.out.println("Artist not found or invalid ID.");
            return;
        }


        System.out.print("Enter new artist name (press Enter to keep current [" + current.getName() + "]):");
        String name = scanner.nextLine();

        if (name.trim().isEmpty()) {
            name = current.getName();
        } else {
            name = name.trim();
        }

        boolean updated = artistService.updateArtist(id, name);
        if (updated) {
            System.out.println("Artist updated successfully!");
        } else {
            System.out.println("No artist updated (check ID or input).");
        }

        if (updated) {
            System.out.println();
            displayAllArtists();
        }
    }

    public void promptDeleteArtist() {
        System.out.println("\n=== DELETE Artist ===");
        displayAllArtists();
        int id = promptForInt("Enter artist ID to delete: ");

        boolean deleted = artistService.deleteArtist(id);
        if (deleted) {
            System.out.println("Artist ID " + id + " deleted successfully!");
        } else {
            System.out.println("No artist deleted (check ID or input).");
        }
    }

    public void promptArchiveArtist() {
        System.out.println("\n=== ARCHIVE Artist ===");
        displayAllArtists();
        int id = promptForInt("Enter artist ID to archive: ");

        boolean archived = artistService.archiveArtist(id);
        if (archived) {
            System.out.println("Artist ID " + id + " archived successfully!");
        } else {
            System.out.println("No artist archived (check ID or input).");
        }
    }

    public void promptRestoreArtist() {
        System.out.println("\n=== RESTORE Artist ===");
        List<Artist> archivedArtists = artistService.getAllArchivedArtists();
        printArtistTable("\n----- Archived Artists -----", archivedArtists, "No archived artists found.");

        if (archivedArtists == null || archivedArtists.isEmpty()) {
            return;
        }

        int id = promptForInt("Enter artist ID to restore: ");
        boolean restored = artistService.restoreArtist(id);
        if (restored) {
            System.out.println("Artist ID " + id + " restored successfully!");
        } else {
            System.out.println("No archived artist restored (check ID or input).");
        }
    }

    public void promptSearchArtists() {
        System.out.println("\n=== SEARCH Artists ===");
        System.out.print("Enter search keyword: ");
        String keyword = scanner.nextLine();

        List<Artist> matches = artistService.searchArtists(keyword);
        printArtistTable("\n----- Search Results (Keyword: '" + keyword.trim() + "') -----",
                matches, "No matching active artists found.");
    }



    private void printArtistTable(String header, List<Artist> artists, String emptyMessage) {
        String border = "+------+---------------------------+";

        System.out.println("\n" + header);
        System.out.println(border);
        System.out.printf("| %-4s | %-25s |%n", "ID", "Artist Name");
        System.out.println(border);

        if (artists == null || artists.isEmpty()) {
            String message = (emptyMessage != null && !emptyMessage.isEmpty())
                    ? emptyMessage
                    : "No artists found.";
            System.out.printf("| %-32s |%n", truncate(message, 32));
            System.out.println(border);
            return;
        }

        for (Artist artist : artists) {
            String name = artist.getName() != null ? artist.getName() : "";
            System.out.printf("| %-4d | %-25s |%n", artist.getId(), truncate(name, 25));
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