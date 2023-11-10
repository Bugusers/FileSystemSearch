package org.lib.filesystemsearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for searching files in the file system.
 */
public class FileSystemSearch implements SystemSearch {
    @Override
    public List<String> search(String directory, String fileName) {
        long start = System.currentTimeMillis();

        File dir = new File(directory);
           List<String> results = new ArrayList<>();

        if(dir.isDirectory() && dir.exists()) {
            searchFiles(dir, fileName, results);
        }

        long end = System.currentTimeMillis();
        System.out.println("Searching time: " + (end - start));


        return results;
    }

    private void searchFiles(File directory, String fileName, List<String> results) {
        try {
            File[] files = directory.listFiles();

            if (files != null) {
                List<Thread> threads = new ArrayList<>();

                for (File file : files) {
                    if (file.isDirectory()) {
                        searchFiles(file, fileName, results);
                    } else if (file.getName().contains(fileName)){
                        Thread thread = new Thread(() -> {
                            results.add(file.getAbsolutePath());
                        });

                        threads.add(thread);
                        thread.start();
                    }
                }

                for (Thread th: threads) {
                    th.join();
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
