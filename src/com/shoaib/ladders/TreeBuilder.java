package com.shoaib.ladders;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Shoaib
 * Since: 12/9/2016
 */
class TreeBuilder {

    Tree build(String input, String output){
        List<String> edgeOperations = getEdgeOperationsList(input, output);
        Tree tree = new Tree();
        tree.initializeTree(input, output, edgeOperations);
        return tree;
    }

    private List<String> getEdgeOperationsList(String input, String output){
        char[] inCharArr = input.toCharArray();
        char[] outCharArr = output.toCharArray();
        for(int i = 0 ; i < inCharArr.length; i++){
            for(int j = 0 ; j < outCharArr.length; j++){
                if(inCharArr[i] == outCharArr[j]){
                    inCharArr[i] = '?';
                    outCharArr[j] = '?';
                }
            }
        }
        List<String> edgeList = new ArrayList<>();
        for(char chr : inCharArr){
            if(chr != '?'){
                edgeList.add("-".concat(String.valueOf(chr)));
            }
        }
        for(char chr : outCharArr){
            if(chr != '?'){
                edgeList.add("+".concat(String.valueOf(chr)));
            }
        }
        return edgeList;
    }
}
