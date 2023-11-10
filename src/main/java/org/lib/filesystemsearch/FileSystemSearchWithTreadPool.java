package org.lib.filesystemsearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * A file system search implementation that uses a ThreadPoolExecutor for parallel file searching.
 */
public class FileSystemSearchWithTreadPool implements SystemSearch {
    private final ThreadPoolExecutor executorService;
    private final int maxRecursionDepth;

    /**
     * Creates a new instance of FileSystemSearchWithThreadPool with the specified parameters.
     *
     * @param maxPoolSize       The maximum number of threads in the thread pool.
     * @param maxRecursionDepth The maximum recursion depth for directory traversal.
     */
    public FileSystemSearchWithTreadPool(int maxPoolSize, int maxRecursionDepth) {
        this.maxRecursionDepth = maxRecursionDepth;
        this.executorService = new ThreadPoolExecutor(maxPoolSize, maxPoolSize, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10));
    }

    @Override
    public List<String> search(String directory, String fileName) {
        long start = System.currentTimeMillis();

        File dir = new File(directory);
        List<String> results = new ArrayList<>();

        if(dir.isDirectory() && dir.exists()) {
            searchFiles(dir, fileName, results, 0);
        }

        executorService.shutdown();

        long end = System.currentTimeMillis();
        System.out.println("Searching time: " + (end - start));

        return results;
    }

    private void searchFiles(File directory, String fileName, List<String> results, int depth) {
        if (depth >= maxRecursionDepth) {
            return;
        }

        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    searchFiles(file, fileName, results, depth + 1);
                } else if (file.getName().contains(fileName)) {
                    executorService.execute(() -> {
                        results.add(file.getAbsolutePath());
                    });
                }
            }
        }
    }
}