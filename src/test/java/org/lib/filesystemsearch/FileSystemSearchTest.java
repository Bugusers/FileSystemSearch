package org.lib.filesystemsearch;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSystemSearchTest {

    @org.junit.jupiter.api.Test
    void searchFileSystemSearch() {
        // Create an instance of your FileSystemSearch or FileSystemSearchWithThreadPool
        SystemSearch fileSystemSearch = new FileSystemSearch();

        // Define the directory and file name you want to search for
        URL resourceUrl = getClass().getClassLoader().getResource("");
        String directory = resourceUrl != null ? resourceUrl.getPath() : null;
        String fileName = "test.txt";

        System.out.println(directory);

        // Perform the search
        List<String> results = fileSystemSearch.search(directory, fileName);


        // Assuming you expect one matching file
        assertEquals(1, results.size());
        // Verify that the found file has the expected name
        assertEquals(fileName, new File(results.get(0)).getName());
    }

    @org.junit.jupiter.api.Test
    void searchFileSystemSearchWithTreadPool() {
        // Create an instance of your FileSystemSearch or FileSystemSearchWithThreadPool
        SystemSearch fileSystemSearch = new FileSystemSearchWithTreadPool(4, 4);

        // Define the directory and file name you want to search for
        URL resourceUrl = getClass().getClassLoader().getResource("");
        String directory = resourceUrl != null ? resourceUrl.getPath() : null;
        String fileName = "test.txt";

        System.out.println(directory);

        // Perform the search
        List<String> results = fileSystemSearch.search(directory, fileName);


        // Assuming you expect one matching file
        assertEquals(1, results.size());
        // Verify that the found file has the expected name
        assertEquals(fileName, new File(results.get(0)).getName());
    }
}