package com.vigilcore.core;

import com.vigilcore.models.Threat;
import com.vigilcore.models.MalwareSignature;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileScanner {
    private MalwareDatabase database;
    
    public FileScanner(MalwareDatabase database) {
        this.database = database;
    }
    
    public Threat scanFile(File file) {
        if (!file.exists() || !file.isFile()) {
            return null;
        }
        
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            MalwareSignature detected = database.detectMalware(content);
            
            if (detected != null) {
                Date now = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String timestamp = formatter.format(now);
                
                Threat threat = new Threat(
                    file.getName(),
                    file.getAbsolutePath(),
                    detected.getSignature(),
                    detected.getSeverity(),
                    file.length(),
                    timestamp
                );
                return threat;
            }
            
            return null;
        } catch (IOException e) {
            System.err.println("Error reading file: " + file.getName());
            e.printStackTrace();
            return null;
        }
    }
    
    public String generateFileHash(File file) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(file.getAbsolutePath())));
            return simpleHash(content);
        } catch (IOException e) {
            return "ERROR";
        }
    }
    
    private String simpleHash(String content) {
        int hash = 0;
        for (int i = 0; i < content.length(); i++) {
            char c = content.charAt(i);
            hash = ((hash << 5) - hash) + c;
            hash = hash & hash;
        }
        return Integer.toHexString(Math.abs(hash));
    }
}



