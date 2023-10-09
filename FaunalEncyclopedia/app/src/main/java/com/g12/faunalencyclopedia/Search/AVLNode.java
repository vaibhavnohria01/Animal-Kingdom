package com.g12.faunalencyclopedia.Search;

public class AVLNode<T> {
    T data;
    int height;
    AVLNode<T> left;
    AVLNode<T> right;

    AVLNode(T data){
        this.data = data;
        height =1;
    }
}
