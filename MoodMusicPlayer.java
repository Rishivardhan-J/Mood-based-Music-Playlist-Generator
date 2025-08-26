// File: MoodMusicPlayer.java

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * A simple class to represent a song.
 * It holds the title, artist, and the mood it's associated with.
 */
class Song {
    private String title;
    private String artist;
    private String mood;

    public Song(String title, String artist, String mood) {
        this.title = title;
        this.artist = artist;
        this.mood = mood;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getMood() {
        return mood;
    }

    @Override
    public String toString() {
        // Custom string representation for easy printing.
        return "  - '" + title + "' by " + artist;
    }
}

/**
 * The main application class for the Mood Based Music Playlist Generator.
 */
public class MoodMusicPlayer {

    public static void main(String[] args) {
        // 1. Initialize our music library
        List<Song> musicLibrary = initializeLibrary();
        Scanner scanner = new Scanner(System.in);

        System.out.println("🎵 Welcome to the Mood Music Playlist Generator! 🎵");

        // Main application loop
        while (true) {
            // 2. Display available moods and get user input
            System.out.println("\nAvailable Moods: Happy, Sad, Calm, Energetic");
            System.out.print("Enter your current mood (or type 'quit' to exit): ");
            String userMood = scanner.nextLine().trim();

            // Check if the user wants to exit
            if (userMood.equalsIgnoreCase("quit")) {
                break;
            }

            // 3. Generate the playlist based on the mood
            List<Song> playlist = generatePlaylist(musicLibrary, userMood);

            // 4. Display the generated playlist
            if (playlist.isEmpty()) {
                System.out.println("\nSorry, we couldn't find any songs for that mood. Please try another one.");
            } else {
                System.out.println("\n✨ Here is your " + userMood + " playlist: ✨");
                for (Song song : playlist) {
                    System.out.println(song);
                }
            }
        }

        System.out.println("\nThank you for using the Mood Music Generator. Goodbye! 👋");
        scanner.close(); // Close the scanner to prevent resource leaks
    }

    /**
     * Creates and returns a predefined list of songs.
     * This acts as our "database".
     * @return A List of Song objects.
     */
    private static List<Song> initializeLibrary() {
        List<Song> songs = new ArrayList<>();
        
        // Happy Songs
        songs.add(new Song("Happy", "Pharrell Williams", "Happy"));
        songs.add(new Song("Don't Stop Me Now", "Queen", "Happy"));
        songs.add(new Song("Walking on Sunshine", "Katrina & The Waves", "Happy"));

        // Sad Songs
        songs.add(new Song("Someone Like You", "Adele", "Sad"));
        songs.add(new Song("Hallelujah", "Leonard Cohen", "Sad"));
        songs.add(new Song("Fix You", "Coldplay", "Sad"));

        // Calm Songs
        songs.add(new Song("Weightless", "Marconi Union", "Calm"));
        songs.add(new Song("Clair de Lune", "Claude Debussy", "Calm"));
        songs.add(new Song("Orinoco Flow", "Enya", "Calm"));

        // Energetic Songs
        songs.add(new Song("Eye of the Tiger", "Survivor", "Energetic"));
        songs.add(new Song("Uptown Funk", "Mark Ronson ft. Bruno Mars", "Energetic"));
        songs.add(new Song("Thunderstruck", "AC/DC", "Energetic"));
        
        return songs;
    }

    /**
     * Filters the music library to find songs matching a specific mood.
     * This method is case-insensitive.
     * @param library The full list of songs.
     * @param mood The mood to filter by.
     * @return A new List of songs that match the mood.
     */
    private static List<Song> generatePlaylist(List<Song> library, String mood) {
        // Using Java Streams to filter the list. It's a modern and concise way.
        return library.stream()
                      .filter(song -> song.getMood().equalsIgnoreCase(mood))
                      .collect(Collectors.toList());
    }
}