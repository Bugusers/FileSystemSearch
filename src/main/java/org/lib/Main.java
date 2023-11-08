package org.lib;

import org.lib.filesystemsearch.FileSystemSearch;
import org.lib.filesystemsearch.FileSystemSearchWithTreadPool;

import java.net.URL;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        FileSystemSearch searcher = new FileSystemSearch();
        String directory = Main.class.getClassLoader().getResource("").getPath();
        String fileName = "test";

        List<String> results1 = searcher.search(directory, fileName);
        printResult(results1);

        FileSystemSearchWithTreadPool searcher2 = new FileSystemSearchWithTreadPool(4, 4);
        List<String> results2 = searcher2.search(directory, fileName);
        printResult(results2);

        System.out.println(results1.size());
    }

    public static void printResult(List<String> results) {
        if (!results.isEmpty()) {
            System.out.println("Found files:");
            for (String result : results) {
                System.out.println(result);
            }
        } else {
            System.out.println("Files not found");
        }
    }

}