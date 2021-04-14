package schedulingJob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class JobScheduler {

	private static final int GROUP_MAX_EXECUTION_TIME_IN_MINUTES = 480;
	
	public List<List<Job>> schedule(LocalDateTime executionWindowStart, 
			LocalDateTime executionWindowEnd, List<Job> jobs) {
		
		// TODO Validar parametros
		
		jobs = new ArrayList<>(jobs);
		
		jobs.sort((j1, j2) -> j1.getMaxConclusionDateTime().compareTo(j2.getMaxConclusionDateTime()));;
		
		List<List<Job>> groups = new ArrayList<>();
		List<Job> currentGroup = new ArrayList<>();
		groups.add(currentGroup);
		int groupExecutionTime = 0;
		
		for (Job job : jobs) {
			
			if (groupExecutionTime + job.getEstimatedExecutionTimeInMinutes() > GROUP_MAX_EXECUTION_TIME_IN_MINUTES) {
				currentGroup = new ArrayList<>();
				groups.add(currentGroup);
				groupExecutionTime = 0;
			}
			
			currentGroup.add(job);
			groupExecutionTime += job.getEstimatedExecutionTimeInMinutes();
		}
		
		return groups;
	}
}
