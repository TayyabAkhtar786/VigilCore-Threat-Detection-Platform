package com.vigilcore.dsa;

import com.vigilcore.models.Threat;

public class LinkedList {
    private Node head;
    private int size;
    
    private class Node {
        Threat data;
        Node next;
        
        Node(Threat data) {
            this.data = data;
            this.next = null;
        }
    }
    
    public void add(Threat threat) {
        Node newNode = new Node(threat);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }
    
    public Threat get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            return;
        }
        
        if (index == 0) {
            head = head.next;
        } else {
            Node current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            current.next = current.next.next;
        }
        size--;
    }
    
    public int size() {
        return size;
    }
    
    public boolean isEmpty() {
        return head == null;
    }

    public Threat[] toArray() {
        Threat[] array = new Threat[size];
        Node current = head;
        int index = 0;
        while (current != null) {
            array[index++] = current.data;
            current = current.next;
        }
        return array;
    }
    
    public void clear() {
        head = null;
        size = 0;
    }
}



