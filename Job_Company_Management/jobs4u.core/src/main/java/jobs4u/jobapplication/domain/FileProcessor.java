package jobs4u.jobapplication.domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FileProcessor implements Runnable {
    private File file = null;
    private Map<String, WordInfo> wordMap = new HashMap<>();
    private Object lock = null;

    public FileProcessor(File file, Map<String, WordInfo> wordMap, Object lock) {
        this.file = file;
        this.wordMap = wordMap;
        this.lock = lock;
    }

    public FileProcessor() {

    }

    @Override
    public void run() {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\W+");
                for (String word : words) {
                    if (!word.isEmpty()) {
                        synchronized (lock) {
                            WordInfo wordInfo = wordMap.getOrDefault(word, new WordInfo(word));
                            wordInfo.incrementCount();
                            wordInfo.addFile(file.getName());
                            wordMap.put(word, wordInfo);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public List<WordInfo> processFiles(JobApplication ja){
        File[] files = new File(ja.candidateFilesPath()).listFiles();
        if (files == null) {
            System.out.println("No files found in the specified directory.");
            return null;
        }

        Map<String, WordInfo> wordMap = new HashMap<>();
        Object lock = new Object();
        List<Thread> threads = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                Thread thread = new Thread(new FileProcessor(file, wordMap, lock));
                threads.add(thread);
                thread.start();
            }
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        List<WordInfo> wordInfos = new ArrayList<>(wordMap.values());
        wordInfos.sort(Comparator.comparingInt(WordInfo::getCount).reversed());
        return wordInfos;
    }
}
