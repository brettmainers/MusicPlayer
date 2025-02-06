public class SongInfo {

    // method that allows the user to see what song is currently playing
    public static String toString (int n) {

        switch(n) {
            case 0: return "Now playing: 'Peaked my Interest' - Nathan Moore (2:03)";
            case 1: return "Now playing: 'The Princess' - The Evening Telecast (2:24)";
            case 2: return "Now playing: 'Dogtown' - National Sweetheart (2:37)";
            case 3: return "Now playing: 'Never Play' - Jeremy Blake (4:11)";
            case 4: return "Now playing: 'Fading Into Obscurity' - Evening Telecast (2:18)";
            case 5: return "Now playing: 'The Color of the Barrel' - Nathan Moore (2:48)";
            case 6: return "Now playing: '29 Palms' - National Sweetheart (3:12)";
            default: return "Invalid song number";
        }
    }
}