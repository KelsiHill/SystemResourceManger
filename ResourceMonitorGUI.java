import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ResourceMonitorGUI {
    private JFrame frame;
    private DefaultTableModel tableModel;
    private JTable processTable;
    private JTable Jtable2;
    private Timer timer;
    private DefaultTableModel table2;
    
    public ResourceMonitorGUI() throws IOException, InterruptedException {
    	//just basic jframe junk
        frame = new JFrame("System Resource Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        //creating a table with columns
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Resources: ");
        tableModel.addColumn("CPU: ");
        tableModel.addColumn("Memory: ");
        //allows for resizing of the table
        processTable = new JTable(tableModel);
        processTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        frame.add(new JScrollPane(processTable));
        
        table2 = new DefaultTableModel();
        table2.addColumn("Storage: " + ResourceCalls.storage() + " GB free out of " + ResourceCalls.totalstore() + " GB");
        if(ResourceCalls.netconnection() == 0) {
        	table2.addColumn("Internet Connection Status: Connected");
        }
        else {
        	table2.addColumn("Internet Connection Status: Disconnected");
        }
        Jtable2 = new JTable(table2);
        Jtable2.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        
        JPanel home = new JPanel(new BorderLayout());
        home.add(new JScrollPane(processTable), BorderLayout.CENTER);
        home.add(new JScrollPane(Jtable2), BorderLayout.SOUTH);
        
        frame.add(home);
        //this creates a timer set to 1 millisecond and calls the update method every second
        //* edit, changed this to 1 millisecond, one second was waaaaay to long, seems to have solved the issue for now
        // gui is a teeny bit glitchy looking but should be fine
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
    }
    //starts the timer and sets jframe to visible
    public void start() {
        frame.setVisible(true);
        timer.start();
    }
    
    //where the magic happens
    private void update() {
        // clears the rows
        while (tableModel.getRowCount() > 0) {
            tableModel.removeRow(0);
        }

        // adds rows with relevant info
        tableModel.addRow(new Object[]{"Usage (%)", ResourceCalls.cpu(), ResourceCalls.memory()});
        tableModel.addRow(new Object[] {"Free (%)", ResourceCalls.cpufree(), ResourceCalls.memoryfree()});
    }
    //boring main method that starts the gui
    public static void main(String[] args) throws IOException, InterruptedException {
    	
    	ResourceMonitorGUI gui = new ResourceMonitorGUI();
        gui.start();
            
        
    }
}
