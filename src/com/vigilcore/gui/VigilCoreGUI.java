package com.vigilcore.gui;

import com.vigilcore.core.*;
import com.vigilcore.models.Threat;
import com.vigilcore.models.MalwareSignature;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.io.File;

public class VigilCoreGUI extends JFrame {
    private MalwareDatabase database;
    private FileScanner scanner;
    private ThreatAnalyzer analyzer;
    private MonitoringLog monitoringLog;
    
    private JTextArea threatDisplayArea;
    private JTextArea logDisplayArea;
    private JTextArea signatureDisplayArea;
    private JLabel statusLabel;
    private JLabel threatCountLabel;
    private JTextField filePathField;
    
    public VigilCoreGUI() {
        database = new MalwareDatabase();
        scanner = new FileScanner(database);
        analyzer = new ThreatAnalyzer();
        monitoringLog = new MonitoringLog();
        
        initializeGUI();
        updateSignatureDisplay();
    }
    
    private void initializeGUI() {
        setTitle("VIGIL CORE: Advanced Threat Operations Platform");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(1200, 800);
        
        JPanel topPanel = createTopPanel();
        add(topPanel, BorderLayout.NORTH);
        
        JPanel centerPanel = createCenterPanel();
        add(centerPanel, BorderLayout.CENTER);
        
        JPanel bottomPanel = createBottomPanel();
        add(bottomPanel, BorderLayout.SOUTH);
        
        JPanel leftPanel = createLeftPanel();
        add(leftPanel, BorderLayout.WEST);
        
        setLocationRelativeTo(null);
    }
    
    private JPanel createTopPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(new TitledBorder("File Scanner"));
        
        JPanel filePanel = new JPanel(new BorderLayout(5, 5));
        filePathField = new JTextField();
        filePathField.setEditable(false);
        
        JButton browseButton = new JButton("Browse File");
        browseButton.addActionListener(e -> browseFile());
        
        JButton scanButton = new JButton("Scan File");
        scanButton.addActionListener(e -> scanFile());
        
        filePanel.add(new JLabel("File Path:"), BorderLayout.WEST);
        filePanel.add(filePathField, BorderLayout.CENTER);
        filePanel.add(browseButton, BorderLayout.EAST);
        
        panel.add(filePanel, BorderLayout.CENTER);
        panel.add(scanButton, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2, 10, 10));
        
        JPanel threatPanel = new JPanel(new BorderLayout());
        threatPanel.setBorder(new TitledBorder("Detected Threats"));
        threatDisplayArea = new JTextArea();
        threatDisplayArea.setEditable(false);
        threatDisplayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane threatScroll = new JScrollPane(threatDisplayArea);
        threatPanel.add(threatScroll, BorderLayout.CENTER);
        
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(new TitledBorder("Recent Scans (Circular Queue - Last 50)"));
        logDisplayArea = new JTextArea();
        logDisplayArea.setEditable(false);
        logDisplayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
        JScrollPane logScroll = new JScrollPane(logDisplayArea);
        logPanel.add(logScroll, BorderLayout.CENTER);
        
        panel.add(threatPanel);
        panel.add(logPanel);
        
        return panel;
    }
    
    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Status: Ready");
        threatCountLabel = new JLabel("Threats Detected: 0");
        statusPanel.add(statusLabel);
        statusPanel.add(new JLabel(" | "));
        statusPanel.add(threatCountLabel);
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        
        JButton sortSeverityButton = new JButton("Sort by Severity (Merge Sort)");
        sortSeverityButton.addActionListener(e -> sortBySeverity());
        
        JButton sortSizeButton = new JButton("Sort by Size (Quick Sort)");
        sortSizeButton.addActionListener(e -> sortBySize());
        
        JButton clearButton = new JButton("Clear History");
        clearButton.addActionListener(e -> clearHistory());
        
        JButton addSignatureButton = new JButton("Add Signature");
        addSignatureButton.addActionListener(e -> addSignature());
        
        buttonPanel.add(sortSeverityButton);
        buttonPanel.add(sortSizeButton);
        buttonPanel.add(clearButton);
        buttonPanel.add(addSignatureButton);
        
        panel.add(statusPanel, BorderLayout.WEST);
        panel.add(buttonPanel, BorderLayout.EAST);
        
        return panel;
    }
    
    private JPanel createLeftPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new TitledBorder("Malware Database (Binary Search Tree)"));
        panel.setPreferredSize(new Dimension(300, 0));
        
        signatureDisplayArea = new JTextArea();
        signatureDisplayArea.setEditable(false);
        signatureDisplayArea.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 11));
        JScrollPane scroll = new JScrollPane(signatureDisplayArea);
        panel.add(scroll, BorderLayout.CENTER);
        
        return panel;
    }
    
    private void browseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select File to Scan");
        int result = fileChooser.showOpenDialog(this);
        
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            filePathField.setText(selectedFile.getAbsolutePath());
            statusLabel.setText("Status: File selected - Ready to scan");
        }
    }
    
    private void scanFile() {
        String filePath = filePathField.getText();
        if (filePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a file first!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        File file = new File(filePath);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, "File does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        statusLabel.setText("Status: Scanning...");
        SwingUtilities.invokeLater(() -> {
            Threat threat = scanner.scanFile(file);
            
            if (threat != null) {
                analyzer.addThreat(threat);
                monitoringLog.logFile(threat);
                statusLabel.setText("Status: THREAT DETECTED! - " + threat.getFileName());
                JOptionPane.showMessageDialog(this, 
                    "THREAT DETECTED!\n" + threat.toString(), 
                    "Alert", 
                    JOptionPane.WARNING_MESSAGE);
            } else {
                statusLabel.setText("Status: File is safe - No threats detected");
                JOptionPane.showMessageDialog(this, "File is safe. No threats detected.", "Scan Complete", JOptionPane.INFORMATION_MESSAGE);
            }
            
            updateDisplays();
        });
    }
    
    private void sortBySeverity() {
        Threat[] sorted = analyzer.sortBySeverity();
        updateThreatDisplay(sorted, "Sorted by Severity (Merge Sort - O(n log n))");
    }
    
    private void sortBySize() {
        Threat[] sorted = analyzer.sortBySize();
        updateThreatDisplay(sorted, "Sorted by File Size (Quick Sort - O(n log n))");
    }
    
    private void clearHistory() {
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to clear all threat history?", 
            "Confirm", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            analyzer.clearHistory();
            monitoringLog.clear();
            updateDisplays();
            statusLabel.setText("Status: History cleared");
        }
    }
    
    private void addSignature() {
        JDialog dialog = new JDialog(this, "Add Malware Signature", true);
        dialog.setLayout(new GridLayout(4, 2, 5, 5));
        dialog.setSize(400, 200);
        
        JTextField sigField = new JTextField();
        JTextField nameField = new JTextField();
        JSpinner severitySpinner = new JSpinner(new SpinnerNumberModel(5, 1, 10, 1));
        
        dialog.add(new JLabel("Signature:"));
        dialog.add(sigField);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Severity (1-10):"));
        dialog.add(severitySpinner);
        
        JButton addButton = new JButton("Add");
        JButton cancelButton = new JButton("Cancel");
        
        addButton.addActionListener(e -> {
            String signature = sigField.getText();
            String name = nameField.getText();
            int severity = (Integer) severitySpinner.getValue();
            
            if (!signature.isEmpty() && !name.isEmpty()) {
                database.addSignature(signature, name, severity);
                updateSignatureDisplay();
                dialog.dispose();
                statusLabel.setText("Status: Signature added successfully");
            } else {
                JOptionPane.showMessageDialog(dialog, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        
        cancelButton.addActionListener(e -> dialog.dispose());
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addButton);
        buttonPanel.add(cancelButton);
        dialog.add(new JLabel());
        dialog.add(buttonPanel);
        
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }
    
    private void updateDisplays() {
        updateThreatDisplay(analyzer.getAllThreats(), "All Detected Threats");
        updateLogDisplay();
        threatCountLabel.setText("Threats Detected: " + analyzer.getThreatCount());
    }
    
    private void updateThreatDisplay(Threat[] threats, String header) {
        StringBuilder sb = new StringBuilder();
        sb.append(header).append("\n");
        for (int i = 0; i < 80; i++) sb.append("=");
        sb.append("\n\n");
        
        if (threats.length == 0) {
            sb.append("No threats detected.\n");
        } else {
            for (int i = 0; i < threats.length; i++) {
                Threat t = threats[i];
                sb.append(String.format("%d. %s\n", i + 1, t.getFileName()));
                sb.append(String.format("   Path: %s\n", t.getFilePath()));
                sb.append(String.format("   Signature: %s\n", t.getSignature()));
                sb.append(String.format("   Severity: %d/10\n", t.getSeverity()));
                sb.append(String.format("   Size: %d bytes\n", t.getFileSize()));
                sb.append(String.format("   Detected: %s\n", t.getDetectionTime()));
                sb.append("\n");
            }
        }
        
        threatDisplayArea.setText(sb.toString());
    }
    
    private void updateLogDisplay() {
        Threat[] recentScans = monitoringLog.getRecentScans();
        StringBuilder sb = new StringBuilder();
        sb.append("Recent Scans (Last ").append(recentScans.length).append(" files)\n");
        for (int i = 0; i < 80; i++) sb.append("=");
        sb.append("\n\n");
        
        if (recentScans.length == 0) {
            sb.append("No files scanned yet.\n");
        } else {
            for (int i = recentScans.length - 1; i >= 0; i--) {
                Threat t = recentScans[i];
                String status = t != null ? "THREAT" : "SAFE";
                sb.append(String.format("%d. %s - %s\n", 
                    recentScans.length - i, 
                    t != null ? t.getFileName() : "Unknown",
                    status));
            }
        }
        
        logDisplayArea.setText(sb.toString());
    }
    
    private void updateSignatureDisplay() {
        MalwareSignature[] signatures = database.getAllSignatures();
        StringBuilder sb = new StringBuilder();
        sb.append("Malware Signatures in Database\n");
        for (int i = 0; i < 40; i++) sb.append("=");
        sb.append("\n\n");
        
        for (int i = 0; i < signatures.length; i++) {
            MalwareSignature sig = signatures[i];
            sb.append(String.format("%d. %s\n", i + 1, sig.getName()));
            sb.append(String.format("   Signature: %s\n", sig.getSignature()));
            sb.append(String.format("   Severity: %d/10\n\n", sig.getSeverity()));
        }
        
        signatureDisplayArea.setText(sb.toString());
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                String systemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
                UIManager.setLookAndFeel(systemLookAndFeel);
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            new VigilCoreGUI().setVisible(true);
        });
    }
}

