package com.vigilcore.core;

import com.vigilcore.dsa.CircularQueue;
import com.vigilcore.models.Threat;

public class MonitoringLog {
    private CircularQueue queue;
    private static final int MAX_LOG_SIZE = 50;
    
    public MonitoringLog() {
        this.queue = new CircularQueue(MAX_LOG_SIZE);
    }
    
    public void logFile(Threat threat) {
        queue.enqueue(threat);
    }
    
    public Threat[] getRecentScans() {
        return queue.getAllItems();
    }
    
    public int getLogSize() {
        return queue.getSize();
    }
    
    public void clear() {
        queue.clear();
    }
}



