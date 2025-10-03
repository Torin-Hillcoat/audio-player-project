import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Queue {
    private AudioFile currentAudio;
    private List<AudioFile> queue;
    private Long currentFrame;
    private Clip clip;
    private String status;
    private AudioInputStream audioInputStream;

    // constructor
    // searches through audio directory and converts all files to AudioFile, then stores them in queue
    public Queue() throws FileNotFoundException {
        this.queue = new ArrayList<AudioFile>();
        this.status = "invalid";

        File dir = new File("audio");
        File[] directoryListing = dir.listFiles();
        // check that the audio directory exists
        if (directoryListing != null) {
            for (File child : directoryListing) {
                // create AudioFile and add to queue
                AudioFile audio = new AudioFile(child, child.toString());
                this.queue.add(audio);
            }
        } else {
            throw new FileNotFoundException("Default \"audio\" directory not found");
        }
    }

    public void play() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        // use first audio in queue if no current audio
        if (currentAudio == null) {
            this.currentAudio = this.queue.getFirst();
        }

        // create audio clip if none exists
        if (this.status == "invalid") {
            this.audioInputStream = AudioSystem.getAudioInputStream(currentAudio.getFile());
            this.clip = AudioSystem.getClip();
            this.clip.open(audioInputStream);
            this.clip.loop(Clip.LOOP_CONTINUOUSLY);
        } else if (this.status == "paused") {
            this.clip.close();
            resetStream();
            this.clip.setMicrosecondPosition(this.currentFrame);
        }

        // play audio clip
        this.clip.start();
        this.status = "playing";
    }

    public void pause() {
        if (this.status == "paused") {
            System.out.println("Audio already paused");
        } else if (this.status == "invalid") {
            System.out.println("No audio is playing");
        } else {
            this.currentFrame = this.clip.getMicrosecondPosition();
            this.clip.stop();
            this.status = "paused";
        }
    }

    public void skipForward() {
        assert true;
    }

    public void skipBackward() {
        assert true;
    }

    public void shuffle() {
        assert true;
    }

    // audio stopper, to be used when exiting
    public void stop() {
        this.currentFrame = 0L;
        this.clip.stop();
        this.clip.close();
    }

    public void resetStream() throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        this.audioInputStream = AudioSystem.getAudioInputStream(currentAudio.getFile());
        this.clip.open(this.audioInputStream);
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
