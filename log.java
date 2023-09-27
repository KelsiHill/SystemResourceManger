	import java.io.FileWriter;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.util.ArrayList;
	import java.util.Date;
	import java.util.List;

	public class log {
		//creates 2 array lists to store cpu and mem data for graphing later
	    public static List<Double> cpuData = new ArrayList<>();
	    public static List<Double> MemUseData = new ArrayList<>();


	    public static void main(String[] args) {
	        // creates a timer and a scheduled action to retrieve requested info every second
	        java.util.Timer timer = new java.util.Timer();
	        timer.scheduleAtFixedRate(new java.util.TimerTask() {
	            public void run() {
	            	//pulls data from resource calls 
	                double cpu = ResourceCalls.cpu();
	                double useMem = ResourceCalls.memory();

	                // sends all the data to the log
	                logData(cpu, useMem);
	            }
	        }, 0, 1000);
	    }

	    public static void logData(double cpu, double usemem) {
	        // creates a log file using print writer and filewriter
	        try (PrintWriter writer = new PrintWriter(new FileWriter("system_data.log", true))) {
	            // timestamps the data points for archiving purpose
	            Date timestamp = new Date();

	            // these lines copy the information into the log file
	            writer.println("Timestamp: " + timestamp);
	            writer.println("CPU Usage: " + cpu + "%");
	            writer.println("Memory Useage: " + usemem + "%");

	            writer.println();

	            // adds the data points to the array lists
	            cpuData.add(cpu);
	            MemUseData.add(usemem);

	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
}
