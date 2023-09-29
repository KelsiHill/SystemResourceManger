
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graph {
    private static JFrame frame;
    private static JPanel graphPanel;
    private static JProgressBar cpuProgressBar;
    private static JProgressBar resourceProgressBar;
    private static JProgressBar memoryProgressBar;

    public static void main(String[] args) {
        // Create main frame
        frame = new JFrame("Resource Usage Graph");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 400);

        // Create panel for the graph
        graphPanel = new JPanel(new GridLayout(3, 1));

        // Create progress bars for CPU, Resource, and Memory usage
        cpuProgressBar = new JProgressBar(0, 100);
        cpuProgressBar.setStringPainted(true);
        resourceProgressBar = new JProgressBar(0, 100);
        resourceProgressBar.setStringPainted(true);
        memoryProgressBar = new JProgressBar(0, 100);
        memoryProgressBar.setStringPainted(true);

        // Add progress bars to the graph panel
        graphPanel.add(cpuProgressBar);
        graphPanel.add(resourceProgressBar);
        graphPanel.add(memoryProgressBar);

        // Create timer to update the values
        Timer timer = new Timer(1000, new ActionListener() {
       
            public void actionPerformed(ActionEvent e) {
                updateValues();
            }
        });
        timer.start();

        // Add graph panel to the frame
        frame.add(graphPanel);

        // Make frame visible
        frame.setVisible(true);
    }

    // Update progress bars with current values
    private static void updateValues() {
        // Get CPU, Resource, and Memory values from ResourceCalls
        double cpuUsage = ResourceCalls.cpu();
        double resourceUsage = ResourceCalls.storage();
        double memoryUsage = ResourceCalls.memory();

        // Update progress bars
        cpuProgressBar.setValue((int) cpuUsage);
        cpuProgressBar.setString("CPU: " + cpuUsage + "%");

        resourceProgressBar.setValue((int) resourceUsage);
        resourceProgressBar.setString("Resource: " + resourceUsage + "%");

        memoryProgressBar.setValue((int) memoryUsage);
        memoryProgressBar.setString("Memory: " + memoryUsage + "%");
    }
}
