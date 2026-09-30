package com.vigilcore.dsa;

import com.vigilcore.models.Threat;

public class SortingAlgorithms {
    
    public static void mergeSortBySeverity(Threat[] threats) {
        if (threats.length <= 1) {
            return;
        }
        mergeSortBySeverity(threats, 0, threats.length - 1);
    }
    
    private static void mergeSortBySeverity(Threat[] threats, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSortBySeverity(threats, left, mid);
            mergeSortBySeverity(threats, mid + 1, right);
            mergeBySeverity(threats, left, mid, right);
        }
    }
    
    private static void mergeBySeverity(Threat[] threats, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        Threat[] leftArray = new Threat[n1];
        Threat[] rightArray = new Threat[n2];
        
        System.arraycopy(threats, left, leftArray, 0, n1);
        System.arraycopy(threats, mid + 1, rightArray, 0, n2);
        
        int i = 0;
        int j = 0;
        int k = left;
        
        while (i < n1 && j < n2) {
            if (leftArray[i].getSeverity() >= rightArray[j].getSeverity()) {
                threats[k] = leftArray[i];
                i++;
            } else {
                threats[k] = rightArray[j];
                j++;
            }
            k++;
        }
        
        while (i < n1) {
            threats[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            threats[k] = rightArray[j];
            j++;
            k++;
        }
    }
    
    public static void quickSortBySize(Threat[] threats) {
        if (threats.length <= 1) {
            return;
        }
        quickSortBySize(threats, 0, threats.length - 1);
    }
    
    private static void quickSortBySize(Threat[] threats, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionBySize(threats, low, high);
            quickSortBySize(threats, low, pivotIndex - 1);
            quickSortBySize(threats, pivotIndex + 1, high);
        }
    }
    
    private static int partitionBySize(Threat[] threats, int low, int high) {
        long pivot = threats[high].getFileSize();
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            if (threats[j].getFileSize() <= pivot) {
                i++;
                Threat temp = threats[i];
                threats[i] = threats[j];
                threats[j] = temp;
            }
        }
        
        Threat temp = threats[i + 1];
        threats[i + 1] = threats[high];
        threats[high] = temp;
        
        return i + 1; 
    }
}



