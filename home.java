	/*3. System Resource Monitor : Identify current available and 
	 * utilized resources of the system e.g., CPU, memory, I/O, and bandwidth.
	 * Your program should be able to log historical system resources and 
	 * capable to show resource utilization graph.
	 */
import java.lang.management.ManagementFactory;
import java.io.*;

public class home {
	//sets up osBean object to perform system pulls for cpu and memory
	static com.sun.management.OperatingSystemMXBean osBean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
	
	public static void main(String[] args) {
		long lastcall = 0;
		//do while statement, purposefully meant to run the whole time program is running
		do {
			//if more than 1 second has passed, enter the statement
			if(System.currentTimeMillis() - lastcall > 1000) {
				//prints information
				System.out.println("Storage: " + storage() + " GB free of " + totalstore() + " GB");
				System.out.println("CPU Usage: " + cpu() + "%" + "  Memory Usage: " + memory() + "%" + "  ");
				System.out.println("CPU Free: " + cpufree() + "%" + "  Memory Free: " + memoryfree() + "%");
				System.out.println("Network Speed?: " );
				System.out.println("--------------------------------------------------");
				//sets lastcall = to current time
				lastcall = System.currentTimeMillis();
			}
		//while statement to keep the loop running
		}while(System.currentTimeMillis() > 0);
		
	}
	
	//returns used cpu space
	public static double cpu() {
		//set cpu = to the cpu load pulled from osBean * 100 to make it a percent
		double cpu = osBean.getCpuLoad() * 100;
		//this is the java virtual machine cpu usage to account for running this program
		double JVcpu = osBean.getProcessCpuLoad()*100;
		//when java environment is not running it will just set this to 0 and add to cpu
		if(JVcpu <= 0) {
			JVcpu = 0;
		}
		//add both usages together
		cpu = cpu + JVcpu;
		//multiply by 100 and round it
		cpu = Math.round(cpu * 100);
		//divide by 100 to get the accurate percentage to 2 decimal places
		cpu = cpu/100;
		//if there is no data, it will return 0
		if(cpu <= -100) {
			return 0;
		}
		else {
		//returns cpu
		return cpu;
		}
	}
	
	//returns free cpu space
	public static double cpufree() {
		//set cpu = to the cpu load pulled from osBean * 100 to make it a percent
		double cpu = osBean.getCpuLoad() * 100;
		//this is the java virtual machine cpu usage to account for running this program
		double JVcpu = osBean.getProcessCpuLoad()*100;
		//when java environment is not running it will just set this to 0 and add to cpu
		if(JVcpu <= 0) {
			JVcpu = 0;
		}
		//add both usages together
		cpu = cpu + JVcpu;
		//multiply by 100 and round it
		cpu = Math.round(cpu * 100);
		//divide by 100 to get the accurate percentage to 2 decimal places
		cpu = cpu/100;
		//if there is no data, it will return 0
		if(cpu <= -100) {
			return 0;
		}
		else {
		//returns free cpu processing
		return 100 - cpu;
		}
	}
	
	//returns ram usage
	public static double memory() {
		//sets totalmem = to total ram space
		double totalmem = osBean.getTotalMemorySize();
		//set freemem = to amount of freemem
		double freemem = osBean.getFreeMemorySize();
		//sets memused = to difference of total and free
		double memused = totalmem - freemem;
		//multiplying by 100 to get a %
		double mem = (memused / totalmem) * 100;
		//multiply again to get accurate 2 digit round
		mem = Math.round(mem *100);
		//divide by 100 to get a %
		mem = mem / 100;
		//return mem
		return mem;
	}
	
	//returns free ram
	public static double memoryfree() {
		//sets totalmem = to total ram space
		double totalmem = osBean.getTotalMemorySize();
		//set freemem = to amount of freemem
		double freemem = osBean.getFreeMemorySize();
		//multiplying by 100 to get a %
		double mem = (freemem / totalmem) * 100;
		//multiply again to get accurate 2 digit round
		mem = Math.round(mem *100);
		//divide by 100 to get a %
		mem = mem / 100;
		//return mem
		return mem;
	}
	
	//pulls the free ssd or hdd space
	public static double storage() {
		//creates a new file object
        File file = new File("C:\\");
        //set var = to free space in gigs * 100 to get accurate rounding
        double space = (file.getFreeSpace() / (1024.0 * 1024 * 1024)) * 100;
        //round it
        space = Math.round(space);
        //divide by 100 to get back into gigs
        space = space / 100;
        return space;
	}
	
	//pulls total space of drive
	public static double totalstore() {
		//sets var = to total space of drive in gigs
        double totspace = new File("C:\\").getTotalSpace() / (1024.0 * 1024 * 1024) * 100;
        //rounds it 
        totspace = Math.round(totspace);
        //divides by 100 to get back to gigs
        totspace = totspace / 100;
        return totspace;
	}
}
