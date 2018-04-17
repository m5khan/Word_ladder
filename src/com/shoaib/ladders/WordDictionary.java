package com.shoaib.ladders;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Author: Shoaib
 * Since: 12/9/2016
 */
class WordDictionary {
    private static Map<Integer, List<String>> wordMap = new HashMap<>();

    static void addWord(String word){
        List<String> wordList = getWordList(word.length());
        if(wordList == null){
            List<String> newList = new ArrayList<>();
            newList.add(word);
            wordMap.put(word.length(), newList);
        }else{
            wordList.add(word);
        }
    }

    static List<String> getWordList(int wordLength){
        return wordMap.get(wordLength);
    }
}
