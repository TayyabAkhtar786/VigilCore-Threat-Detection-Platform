package com.vigilcore.dsa;

import com.vigilcore.models.MalwareSignature;

public class BinarySearchTree {
    private Node root;
    
    private class Node {
        MalwareSignature data;
        Node left, right;
        
        Node(MalwareSignature data) {
            this.data = data;
            left = right = null;
        }
    }
    
    public void insert(MalwareSignature signature) {
        root = insertRec(root, signature);
    }
    
    private Node insertRec(Node root, MalwareSignature signature) {
        if (root == null) {
            root = new Node(signature);
            return root;
        }
        
        if (signature.compareTo(root.data) < 0) {
            root.left = insertRec(root.left, signature);
        } else if (signature.compareTo(root.data) > 0) {
            root.right = insertRec(root.right, signature);
        }
        
        return root;
    }
    
    public MalwareSignature search(String signature) {
        return searchRec(root, signature);
    }
    
    private MalwareSignature searchRec(Node root, String signature) {
        if (root == null) {
            return null;
        }
        
        int comparison = signature.compareTo(root.data.getSignature());
        if (comparison == 0) {
            return root.data;
        } else if (comparison < 0) {
            return searchRec(root.left, signature);
        } else {
            return searchRec(root.right, signature);
        }
    }
    
    public MalwareSignature[] getAllSignatures() {
        java.util.List<MalwareSignature> list = new java.util.ArrayList<>();
        inOrderTraversal(root, list);
        return list.toArray(new MalwareSignature[0]);
    }
    
    private void inOrderTraversal(Node root, java.util.List<MalwareSignature> list) {
        if (root != null) {
            inOrderTraversal(root.left, list);
            list.add(root.data);
            inOrderTraversal(root.right, list);
        }
    }
    
    public boolean isEmpty() {
        return root == null;
    }
}

