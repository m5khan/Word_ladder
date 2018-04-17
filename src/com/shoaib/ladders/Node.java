package com.shoaib.ladders;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Shoaib
 * Since: 12/10/2016
 */
public class Node {
    private int depth;
    private Node parent = null;
    private List<Node> childrens = null;
    private String word;
    private List<String> edges;
    private String edgeOpPerformed;

    void setEdges(List<String> edges){
        this.edges = edges;
    }

    void setParent(Node parent){
        this.parent = parent;
    }

    void setChildrens(List<Node> childrens){
        this.childrens = childrens;
    }

    void addChild(Node node){
        if(this.childrens == null) this.childrens = new ArrayList<>();
        this.childrens.add(node);
    }

    void setDepth(int depth){
        this.depth = depth;
    }

    void setWord(String word){
        this.word = word;
    }

    void setEdgeOpPerformed(String edgeOp){
        this.edgeOpPerformed = edgeOp;
    }

    List<String> getEdges(){
        return this.edges;
    }

    String getWord(){
        return this.word;
    }

    String getEdgeOpPerformed(){
        return edgeOpPerformed;
    }

    int getDepth(){
        return this.depth;
    }

    List<Node> getChildrens(){
        return this.childrens;
    }

    Node getParent(){
        return this.parent;
    }
}
