package com.shoaib.ladders;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Author: Shoaib
 * Since: 12/9/2016
 */
public class WordlistReader {

    void readTextFile() {
        try(Stream<String> stream = Files.lines(Paths.get("wordList.txt"))){
            stream.forEach(WordDictionary::addWord);
        }catch (IOException ex){
            ex.printStackTrace();
        }
    }

    public void readObjectFile(){

    }

}
