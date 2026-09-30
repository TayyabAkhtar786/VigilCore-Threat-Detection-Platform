# VIGIL CORE: Advanced Threat Operations Platform

A desktop-based security application demonstrating the practical application of Data Structures and Algorithms (DSA) in cybersecurity threat detection.

## Project Overview

Vigil Core is an educational security platform that implements core DSA concepts to solve real-world cybersecurity problems:
- **Real-time file scanning** with pattern matching
- **Efficient threat lookup** using Binary Search Trees (O(log n))
- **Fixed-size monitoring logs** using Circular Queues (O(1))
- **Dynamic threat storage** using Linked Lists
- **Threat prioritization** using Merge Sort and Quick Sort (O(n log n))

## Team Members

1. Tayyab Akhtar (4986)
2. Muhammad Uzair (5004)
3. Abdullah Kashif (5008)

## Features

### 1. Malware Database (Binary Search Tree)
- O(log n) signature lookup
- Dynamic signature management
- Pre-loaded with common malware patterns

### 2. File Scanner
- Real-time file analysis
- Pattern matching against malware signatures
- File hash generation for identification

### 3. Threat Analyzer
- Merge Sort for severity-based sorting
- Quick Sort for size-based sorting
- Threat history management

### 4. Monitoring Log (Circular Queue)
- Fixed-size buffer (last 50 scans)
- O(1) enqueue/dequeue operations
- Memory-efficient logging

### 5. User Interface
- Java Swing GUI
- Real-time threat visualization
- Interactive signature management
- Sorting and analysis tools

## Technical Stack

- **Language**: Java (JDK 17+)
- **GUI Framework**: Java Swing
- **IDE**: Compatible with NetBeans, IntelliJ IDEA, Eclipse

## Project Structure

```
src/
├── com/vigilcore/
│   ├── models/
│   │   ├── Threat.java
│   │   └── MalwareSignature.java
│   ├── dsa/
│   │   ├── BinarySearchTree.java
│   │   ├── CircularQueue.java
│   │   ├── LinkedList.java
│   │   └── SortingAlgorithms.java
│   ├── core/
│   │   ├── MalwareDatabase.java
│   │   ├── FileScanner.java
│   │   ├── ThreatAnalyzer.java
│   │   └── MonitoringLog.java
│   └── gui/
│       └── VigilCoreGUI.java
```

## How to Run

1. **Compile the project:**
   ```bash
   javac -d bin src/com/vigilcore/**/*.java
   ```

2. **Run the application:**
   ```bash
   java -cp bin com.vigilcore.gui.VigilCoreGUI
   ```

   Or simply run `VigilCoreGUI.java` from your IDE.
## Screenshots
<img width="959" height="505" alt="Screenshot 2026-09-30 154515" src="https://github.com/user-attachments/assets/dc74a4db-f39b-4fc6-b866-ca231afc6a6d" />

<img width="959" height="502" alt="Screenshot 2026-09-30 154821" src="https://github.com/user-attachments/assets/b42a18ca-303e-4a12-9a6e-3c5ab2058847" />

<img width="957" height="503" alt="Screenshot 2026-09-30 154855" src="https://github.com/user-attachments/assets/304764ae-f192-4e14-b784-e7ae0689b993" />

<img width="960" height="503" alt="Screenshot 2026-09-30 154918" src="https://github.com/user-attachments/assets/bc07ac3e-9944-4dd5-884d-b93a8d957cae" />



## Usage

1. **Scan a File:**
   - Click "Browse File" to select a file
   - Click "Scan File" to analyze it
   - Results will appear in the threat display area

2. **View Signatures:**
   - All malware signatures are displayed in the left panel
   - Signatures are stored in a Binary Search Tree for efficient lookup

3. **Sort Threats:**
   - Click "Sort by Severity" to use Merge Sort
   - Click "Sort by Size" to use Quick Sort

4. **Add Custom Signatures:**
   - Click "Add Signature" to add new malware patterns
   - Specify signature string, name, and severity (1-10)

5. **View Monitoring Log:**
   - Recent scans are displayed in the right panel
   - Uses Circular Queue to maintain last 50 scans

## Algorithm Complexity

| Component | Data Structure | Complexity |
|-----------|---------------|------------|
| Signature Lookup | Binary Search Tree | O(log n) |
| Queue Operations | Circular Queue | O(1) |
| Threat Sorting | Merge Sort / Quick Sort | O(n log n) |
| Threat Storage | Linked List | O(1) insertion |

## Educational Value

This project demonstrates:
- How DSA concepts apply to real-world security problems
- The importance of algorithm efficiency in cybersecurity
- Transparent implementation without "black box" libraries
- Practical use of sorting, searching, and queue management

## Notes

- This is an educational project for demonstrating DSA concepts
- Malware detection uses simple pattern matching
- For production use, more sophisticated detection methods would be required
- All algorithms are implemented from scratch without external libraries





