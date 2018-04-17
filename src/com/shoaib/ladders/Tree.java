package com.shoaib.ladders;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Author: Shoaib
 * Since: 12/10/2016
 */
class Tree {
    private Node rootNode = null;
    private String inputWord, targetWord;
    private Queue<Node> queue = new LinkedList<>();

    List<String> find(String targetStr){
        List<String> wordPath = new ArrayList<>();
        Node reqNode = null;
        List<Node> childNodes = rootNode.getChildrens();
        if(childNodes != null){
            childNodes.forEach(queue::add);
        }
        while (queue.peek() != null){
            Node node = queue.remove();
            if(node.getWord().equals(targetStr)){
                reqNode = node;
                break;
            }else if(node.getChildrens() != null) {
                node.getChildrens().forEach(queue::add);
            }
        }
        while (reqNode != null){
            wordPath.add(reqNode.getWord());
            reqNode = reqNode.getParent();
        }
        return wordPath;
    }

    void initializeTree(String inWord, String outWord, List<String> edgeList){
        this.inputWord = inWord;
        this.targetWord = outWord;
        rootNode = new Node();
        rootNode.setParent(null);
        rootNode.setDepth(0);
        rootNode.setEdges(edgeList);
        rootNode.setWord(inWord);
        rootNode.setEdgeOpPerformed(null);
        this.queue.add(rootNode);
        generateNodes();
    }

    private void generateNodes(){
        while(queue.peek() != null){
            Node node = queue.remove();
            if(node.getWord()!= null){
                List<String> childWords = getChildWords(node);  // childwords are not proper words, they are just a string of characters that are supposed to be in a child nodes and may form a proper word after organizing
                processAndAddChildWords(childWords, node);
            }
        }
    }

    private void processAndAddChildWords(List<String> childWords, Node node) {
        for(int x = 0 ; x < childWords.size(); x++){
            List<String> wordList = WordDictionary.getWordList(childWords.get(x).length());
            String matchedWord = null;
            if(wordList != null)
            for(String word : wordList){
                if(charsMatch(childWords.get(x), word)){
                    if(node.getEdges().size() > 1){
                        matchedWord = word;
                        break;
                    }else if(word.equals(targetWord)){          // if this is a last edge operation then word must match target word aswell
                        matchedWord = word;
                        break;
                    }
                }
            }
            if(matchedWord != null){
                Node childNode = prepareNode(matchedWord, node, node.getEdges(), node.getEdges().get(x));
                node.addChild(childNode);
                queue.add(childNode);
            }
        }
        //System.out.print("done");
    }

    private List<String> getChildWords(Node node){
        List<String> edges = node.getEdges();
        String currentWord = node.getWord();
        return edges.stream().map(x-> x.charAt(0) == '+' ? currentWord + x.charAt(1) : currentWord.replaceFirst(Character.toString(x.charAt(1)), "")).collect(Collectors.toList());
    }

    private boolean charsMatch(String str1, String str2){
        //System.out.println(str1 + " : " + str2);
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        boolean matched = true;
        for(int x = 0 ; x < chars1.length ; x++){
            if(chars1[x] != chars2[x]){
                matched = false;
                break;
            }
        }
        return matched;
    }

    private Node prepareNode(String word, Node parentNode, List<String> edgeList, String opPerformed){
        Node node = new Node();
        node.setParent(parentNode);
        node.setDepth(parentNode.getDepth() + 1);
        node.setWord(word);
        List<String> childEdgeList = new ArrayList<>();
        boolean once = false;
        for(String edge : edgeList){
            if(!edge.equals(opPerformed) || once){
                childEdgeList.add(edge);
            }else once = true;
        }
        node.setEdges(childEdgeList);
        node.setEdgeOpPerformed(opPerformed);
        return node;
    }

}
