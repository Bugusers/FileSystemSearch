# File System Search

This project provides a file system search tool that allows you to search for files within a specified directory and its subdirectories. It includes two implementations: one using traditional threads and another using a ThreadPoolExecutor for parallel file searching.

## Table of Contents

- [Introduction](#introduction)
- [How to Use](#how-to-use)
    - [File System Search](#file-system-search)
    - [ThreadPoolExecutor Implementation](#threadpoolexecutor-implementation)
- [System Requirements](#system-requirements)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The project consists of three main classes:
1. `FileSystemSearch`: A class that performs file searching using traditional threads. It provides options for limiting the number of threads and setting a maximum recursion depth.
2. `FileSystemSearchWithThreadPool`: A class that uses a ThreadPoolExecutor for parallel file searching. It offers similar options for controlling the number of threads and recursion depth.
3. `SystemSearch` (Interface): An interface defining the contract for file system search operations.

## How to Use

### File System Search

The `FileSystemSearch` class allows you to perform file searches using traditional threads. Here's how to use it:

```java
SystemSearch fileSystemSearch = new FileSystemSearch();
List<String> results = fileSystemSearch.search("/path/to/directory", "file-name-to-search-for");