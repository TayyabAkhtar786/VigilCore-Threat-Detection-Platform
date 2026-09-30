package com.vigilcore.models;

public class Threat {
    private String fileName;
    private String filePath;
    private String signature;
    private int severity;
    private long fileSize;
    private String detectionTime;
    
    public Threat(String fileName, String filePath, String signature, int severity, long fileSize, String detectionTime) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.signature = signature;
        this.severity = severity;
        this.fileSize = fileSize;
        this.detectionTime = detectionTime;
    }
    
    public String getFileName() { return fileName; }
    public String getFilePath() { return filePath; }
    public String getSignature() { return signature; }
    public int getSeverity() { return severity; }
    public long getFileSize() { return fileSize; }
    public String getDetectionTime() { return detectionTime; }
    
    public void setSeverity(int severity) { this.severity = severity; }
    
    @Override
    public String toString() {
        return String.format("%s [Severity: %d, Size: %d bytes]", fileName, severity, fileSize);
    }
}

