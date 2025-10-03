import java.io.File;

public class AudioFile {
    private File file;
    private String filePath;

    public AudioFile(File file, String filePath) {
        this.file = file;
        this.filePath = filePath;
    }

    public File getFile() {
        return file;
    }
    public String getFilePath() {
        return filePath;
    }
}
