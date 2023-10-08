package com.g12.faunalencyclopedia.Search;

public class AVLNode {
    String data;
    int height;
    AVLNode left;
    AVLNode right;

    AVLNode(String data){
        this.data = data;
        height =1;
    }
}
