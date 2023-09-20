import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;

public class home {
	
	static com.sun.management.OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	public static void main(String[] args) {
		long lastcall = 0;
		//do while statement, purposefully meant to run the whole time program is running
		do {
			//if more than 1 second has passed, enter the statement
			if(System.currentTimeMillis() - lastcall > 1000) {
				System.out.println("CPU usage: " + cpu() + "%" + "Memory usage: " + memory() + "%");
				lastcall = System.currentTimeMillis();
			}
		//while statement to keep the loop running
		}while(System.currentTimeMillis() > 0);
		
		
	}
	
	public static double cpu() {
		
		//set cpu = to the cpu load pulled from osBean * 100 to make it a percent
		double cpu = osBean.getCpuLoad() * 100;
		//multiply by 100 and round it
		cpu = Math.round(cpu * 100);
		//divide by 100 to get the accurate percentage to 2 decimal places
		cpu = cpu/100;
		//print the result
		return cpu;
	}
	
	public static double memory() {
		double totalmem = osBean.getTotalMemorySize();
		return totalmem; 
	}
}
