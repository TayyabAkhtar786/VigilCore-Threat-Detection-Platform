package com.vigilcore.core;

import com.vigilcore.dsa.LinkedList;
import com.vigilcore.dsa.SortingAlgorithms;
import com.vigilcore.models.Threat;

public class ThreatAnalyzer {
    private LinkedList threatHistory;
    
    public ThreatAnalyzer() {
        this.threatHistory = new LinkedList();
    }
    
    public void addThreat(Threat threat) {
        threatHistory.add(threat);
    }
    
    public Threat[] getAllThreats() {
        return threatHistory.toArray();
    }
    
    public Threat[] sortBySeverity() {
        Threat[] threats = threatHistory.toArray();
        SortingAlgorithms.mergeSortBySeverity(threats);
        return threats;
    }
    
    public Threat[] sortBySize() {
        Threat[] threats = threatHistory.toArray();
        SortingAlgorithms.quickSortBySize(threats);
        return threats;
    }
    
    public int getThreatCount() {
        return threatHistory.size();
    }
    
    public void clearHistory() {
        threatHistory.clear();
    }
    
    public Threat[] getHighSeverityThreats() {
        Threat[] allThreats = threatHistory.toArray();
        
        int count = 0;
        for (int i = 0; i < allThreats.length; i++) {
            if (allThreats[i].getSeverity() >= 7) {
                count++;
            }
        }
        
        Threat[] highSeverity = new Threat[count];
        int index = 0;
        
        for (int i = 0; i < allThreats.length; i++) {
            if (allThreats[i].getSeverity() >= 7) {
                highSeverity[index] = allThreats[i];
                index++;
            }
        }
        
        return highSeverity;
    }
}



