package jobs.schedulingJob.dto;

import java.time.LocalDateTime;
import java.util.List;

import jobs.schedulingJob.model.Job;

public class JobSchedulingParametersDto {

	private LocalDateTime executionWindowStart;
	private LocalDateTime executionWindowEnd;
	private List<Job> jobs;
	
	public LocalDateTime getExecutionWindowStart() {
		return executionWindowStart;
	}
	
	public void setExecutionWindowStart(LocalDateTime executionWindowStart) {
		this.executionWindowStart = executionWindowStart;
	}
	
	public LocalDateTime getExecutionWindowEnd() {
		return executionWindowEnd;
	}
	
	public void setExecutionWindowEnd(LocalDateTime executionWindowEnd) {
		this.executionWindowEnd = executionWindowEnd;
	}
	
	public List<Job> getJobs() {
		return jobs;
	}
	
	public void setJobs(List<Job> jobs) {
		this.jobs = jobs;
	}
}
