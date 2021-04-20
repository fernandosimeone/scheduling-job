package schedulingJob;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class JobScheduler {
	
	private static final int GROUP_MAX_EXECUTION_TIME_IN_MINUTES = 480;
	
	public List<List<Job>> schedule(LocalDateTime executionWindowStart, 
			LocalDateTime executionWindowEnd, List<Job> jobs) {
		
		if (jobs == null || jobs.isEmpty())
			throw new IllegalArgumentException("Nenhum job foi informado.");
		
		validateExecutionWindow(executionWindowStart, executionWindowEnd);
		
		jobs = new ArrayList<>(jobs);
		jobs.sort((j1, j2) -> j1.getMaxConclusionDateTime().compareTo(j2.getMaxConclusionDateTime()));;
		
		return groupJobs(executionWindowStart, executionWindowEnd, jobs);
	}
	
	private List<List<Job>> groupJobs(LocalDateTime executionWindowStart, 
			LocalDateTime executionWindowEnd, List<Job> jobs) {
		
		List<List<Job>> groups = new ArrayList<>();
		List<Job> currentGroup = new ArrayList<>();
		groups.add(currentGroup);
		
		LocalDateTime executionEnd = executionWindowStart;
		int groupExecutionTime = 0;
		
		for (Job job : jobs) {
			
			if (groupExecutionTime + job.getEstimatedExecutionTimeInMinutes() > GROUP_MAX_EXECUTION_TIME_IN_MINUTES) {
				currentGroup = new ArrayList<>();
				groups.add(currentGroup);
				groupExecutionTime = 0;
			}
			
			currentGroup.add(job);
			groupExecutionTime += job.getEstimatedExecutionTimeInMinutes();
			executionEnd = executionEnd.plus(job.getEstimatedExecutionTimeInMinutes(), ChronoUnit.MINUTES);
			
			if (executionEnd.isAfter(executionWindowEnd))
				throw new SchedulingException("Não foi possível agendar a execução dos jobs dentro da janela de tempo informada");
			
			if (executionEnd.isAfter(job.getMaxConclusionDateTime()))
				throw new SchedulingException("Não foi possível agendar a execução do job " + job.getId() + " antes de sua data/hora limite." );
		}
		
		return groups;
	}
	
	private void validateExecutionWindow(LocalDateTime executionWindowStart, LocalDateTime executionWindowEnd) {
		
		if (executionWindowStart == null || executionWindowEnd == null || 
				executionWindowStart.compareTo(executionWindowEnd) != -1)
			throw new IllegalArgumentException();
	}
}
