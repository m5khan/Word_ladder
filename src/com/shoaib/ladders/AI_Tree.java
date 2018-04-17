package com.shoaib.ladders;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.ListIterator;

/**
 * Author: Shoaib
 * Since: 12/9/2016
 */
public class AI_Tree {
    public static void main(String[] args) {
        String input = "apple";
        String target = "baritone";
        //String input = args[0];
        //String target = args[1];
        WordlistReader wlReader = new WordlistReader();
        // populate com.shoaib.ladders.WordDictionary
        wlReader.readTextFile();
        TreeBuilder tBuilder = new TreeBuilder();
        Tree myTree = tBuilder.build(input, target);
        List<String> wordpath = myTree.find(target);
        ListIterator<String> iterator = wordpath.listIterator(wordpath.size());
        //while (iterator.hasPrevious()) System.out.println(iterator.previous());

        try (Writer writer = new FileWriter("output.txt")) {
            while (iterator.hasPrevious()) {
                writer.write(iterator.previous() + "\r\n");
            }
            writer.flush();
            writer.close();
        }catch (IOException ex){
            ex.printStackTrace();
        }

    }
}
