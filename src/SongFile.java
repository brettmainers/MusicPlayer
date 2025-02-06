import java.io.File;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Scanner;

public class SongFile {

    // creating audio files for each song
    public static File peakedMyInterest = new File("resources/Peaked My Interest - Nathan Moore.wav");
    public static File thePrincess = new File("resources/The Princess - Evening Telecast.wav");
    public static File dogtown = new File("resources/Dogtown - National Sweetheart.wav");
    public static File neverPlay = new File("resources/Never Play - Jeremy Blake.wav");
    public static File fadingIntoObscurity = new File("resources/Fading Into Obscurity - Evening Telecast.wav");
    public static File theColorOfTheBarrel = new File("resources/The Color of the Barrel - Nathan Moore.wav");
    public static File palms = new File("resources/29 Palms - National Sweetheart.wav");

    // creating an array of Clips that will be used to store the audio clips for playlist behavior
    public static Clip[] clips = new Clip[7];

    public static void start() {

       try {
        // creating an array of AudioInputStreams and adding the inputStream files for each song to it
        AudioInputStream[] inputStream = new AudioInputStream[7];
        inputStream[0] = AudioSystem.getAudioInputStream(peakedMyInterest);
        inputStream[1] = AudioSystem.getAudioInputStream(thePrincess);
        inputStream[2] = AudioSystem.getAudioInputStream(dogtown);
        inputStream[3] = AudioSystem.getAudioInputStream(neverPlay);
        inputStream[4] = AudioSystem.getAudioInputStream(fadingIntoObscurity);
        inputStream[5] = AudioSystem.getAudioInputStream(theColorOfTheBarrel);
        inputStream[6] = AudioSystem.getAudioInputStream(palms);
        
        // adding each song clip to the Clips[] array
        clips[0] = AudioSystem.getClip(); // Peaked My Interest
        clips[1] = AudioSystem.getClip(); // The Princess
        clips[2] = AudioSystem.getClip(); // Dogtown
        clips[3] = AudioSystem.getClip(); // Never Play
        clips[4] = AudioSystem.getClip(); // Fading Into Obscurity
        clips[5] = AudioSystem.getClip(); // The Color of the Barrel
        clips[6] = AudioSystem.getClip(); // 29 Palms

        Scanner input = new Scanner(System.in);

        // initial messaging and prompts:
        System.out.println("Welcome to my Music Player!");
        System.out.println("There are 7 songs included in this playlist: \n");
        System.out.println("1. 'Peaked my Interest' - Nathan Moore");
        System.out.println("2. 'The Princess' - The Evening Telecast");
        System.out.println("3. 'Dogtown' - National Sweetheart");
        System.out.println("4. 'Never Play' - Jeremy Blake");
        System.out.println("5. 'Fading Into Obscurity' - Evening Telecast");
        System.out.println("6. 'The Color of the Barrel' - Nathan Moore");
        System.out.println("7. '29 Palms' - National Sweetheart \n");
        System.out.println("Listed below are 7 different commands that can be used on this playlist: \n");

        // creating a variable that will be used for playlist behavior
        int n = 0;

        // opening each song clip
        for (int i = 0; i < clips.length; i++) {
            clips[i].open(inputStream[i]);
        }
        
        String response = " ";

        while (!response.equalsIgnoreCase("Q")) {
            // different prompts for user input
            System.out.println("Press 'P' to play / resume");
            System.out.println("Press 'S' to pause song");
            System.out.println("Press 'R' to restart current song");
            System.out.println("Press 'N' to play next song");
            System.out.println("Press 'B' to go play previous song");
            System.out.println("Press 'V' to restart the playlist from the beginning");
            System.out.println("Press 'Q' to quit the program");
            System.out.print("Enter your choice: ");
            response = input.nextLine().toUpperCase();

            // switch statement that handles each possible user input
            switch (response) {

                case "P": // playing or resuming song
                    clips[n].start();
                    System.out.println("--------------------------------------------------------");
                    System.out.println(SongInfo.toString(n));
                    System.out.println("--------------------------------------------------------");
                    break;
                case "S": // pausing song
                    clips[n].stop();
                    break;
                case "R": // restarting song from beginning
                    clips[n].setMicrosecondPosition(0);
                    clips[n].start();
                    break;
                case "N": // playing next song
                    if (n + 1 >= clips.length) {
                        System.out.println("--------------------------------------------------------");
                        System.out.println("* No more songs available *");
                        System.out.println("Enter any key to return to the button menu");
                        System.out.println("--------------------------------------------------------");
                        response = input.nextLine().toUpperCase();
                        break;
                    }
                    clips[n].stop();
                    clips[n + 1].setMicrosecondPosition(0);
                    clips[n + 1].start();
                    n++;
                    System.out.println("--------------------------------------------------------");
                    System.out.println(SongInfo.toString(n));
                    System.out.println("--------------------------------------------------------");
                    break;
                case "B": // playing previous song
                    if ((n - 1) < 0) {
                        System.out.println("--------------------------------------------------------");
                        System.out.println("* You are at the start of the playlist. *");
                        System.out.println("Enter any key to return to the button menu.");
                        System.out.println("--------------------------------------------------------");
                        response = input.nextLine().toUpperCase();
                        break;
                    }
                    clips[n].stop();
                    clips[n - 1].setMicrosecondPosition(0);
                    clips[n - 1].start();
                    n--;
                    System.out.println("--------------------------------------------------------");
                    System.out.println(SongInfo.toString(n));
                    System.out.println("--------------------------------------------------------");
                    break;
                case "V": // restarting playlist from beginning
                    clips[n].stop();
                    n = 0;
                    clips[n].start();
                    System.out.println("--------------------------------------------------------");
                    System.out.println(SongInfo.toString(n));
                    System.out.println("--------------------------------------------------------");
                    break;
                case "Q": // ending program
                    clips[n].stop();
                    break;
                default:
                    System.out.println("--------------------------------------------------------");
                    System.out.println("* Invalid input, please try again *");
                    System.out.println("--------------------------------------------------------");
            }
        }
        System.out.println("Thank you for using my Music Player today!");
        System.out.println("Goodbye");
        input.close();

        // catching any exception that might be thrown
       } catch (UnsupportedAudioFileException e) {
        System.out.println("Unsupported audio file, please use .wav, .au, or .aiff files");
       } catch (IOException e) {
        System.out.println("Whoops, something went wrong. Please try again.");
       } catch (LineUnavailableException e) {
        System.out.println("Audio could not be opened, please try again.");
       }
    }
}
