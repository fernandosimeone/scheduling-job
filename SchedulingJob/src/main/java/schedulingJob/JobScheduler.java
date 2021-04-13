package schedulingJob;

import java.time.LocalDateTime;
import java.util.List;

public class JobScheduler {

	private static final int GROUP_MAX_EXECUTION_TIME_IN_MINUTES = 480;
	
	public List<List<Job>> schedule(LocalDateTime executionWindowStart, 
			LocalDateTime executionWindowEnd, List<Job> jobs) {
		
		return null;
	}
}
