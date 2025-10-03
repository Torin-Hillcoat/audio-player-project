import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.io.FileNotFoundException;

public class Queue {
    private AudioFile currentAudio;
    private List<AudioFile> queue;

    // constructor
    // searches through audio directory and converts all files to AudioFile, then stores them in queue
    public Queue() throws FileNotFoundException {
        this.queue = new ArrayList<AudioFile>();
        File dir = new File("audio");
        File[] directoryListing = dir.listFiles();
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

    public void play() {
        assert true;
    }

    public void  pause() {
        assert true;
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
}
