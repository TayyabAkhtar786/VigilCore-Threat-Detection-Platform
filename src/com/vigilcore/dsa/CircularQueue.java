package com.vigilcore.dsa;

import com.vigilcore.models.Threat;

public class CircularQueue {
    private Threat[] queue;
    private int front;
    private int rear;
    private int size;
    private int capacity;
    
    public CircularQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Threat[capacity];
        this.front = -1;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(Threat threat) {
        if (isFull()) {
            front = (front + 1) % capacity;
        } else {
            size++;
        }
        
        rear = (rear + 1) % capacity;
        queue[rear] = threat;
        
        if (front == -1) {
            front = 0;
        }
    }
    
    public Threat dequeue() {
        if (isEmpty()) {
            return null;
        }
        
        Threat threat = queue[front];
        if (front == rear) {
            front = rear = -1;
        } else {
            front = (front + 1) % capacity;
        }
        size--;
        return threat;
    }
    
    public boolean isEmpty() {
        return front == -1;
    }
    
    public boolean isFull() {
        return size == capacity;
    }
    
    public int getSize() {
        return size;
    }
    
    public Threat[] getAllItems() {
        Threat[] items = new Threat[size];
        if (isEmpty()) {
            return items;
        }
        
        int index = 0;
        int current = front;
        do {
            items[index++] = queue[current];
            current = (current + 1) % capacity;
        } while (current != (rear + 1) % capacity && index < size);
        
        return items;
    }
    
    public void clear() {
        front = rear = -1;
        size = 0;
    }
}



