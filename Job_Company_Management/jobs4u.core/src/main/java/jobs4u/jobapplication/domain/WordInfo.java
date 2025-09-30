package jobs4u.jobapplication.domain;

import java.util.HashSet;
import java.util.Set;

public class WordInfo {
    private final String word;
    private int count;
    private final Set<String> files;

    public WordInfo(String word) {
        this.word = word;
        this.count = 0;
        this.files = new HashSet<>();
    }

    public void incrementCount() {
        this.count++;
    }

    public void addFile(String fileName) {
        this.files.add(fileName);
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public Set<String> getFiles() {
        return files;
    }
}

