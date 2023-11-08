package org.lib.filesystemsearch;

import java.util.List;

/**
 * Interface for searching files in the file system.
 */
public interface SystemSearch {
    /**
     * Search for files in the specified directory by the given file name.
     *
     * @param directory The path to the directory in which to search for files.
     * @param fileName  The full or partial name of the file to search for.
     * @return A list of paths to files that match the given name.
     */
    List<String> search(String directory, String fileName);
}
