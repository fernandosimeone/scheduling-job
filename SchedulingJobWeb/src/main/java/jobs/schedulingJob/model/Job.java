package jobs.schedulingJob.model;

import java.time.LocalDateTime;

public class Job {

	private int id;
	private String description;
	private LocalDateTime maxConclusionDateTime;
	private int estimatedExecutionTimeInMinutes;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public LocalDateTime getMaxConclusionDateTime() {
		return maxConclusionDateTime;
	}
	
	public void setMaxConclusionDateTime(LocalDateTime maxConclusionDateTime) {
		this.maxConclusionDateTime = maxConclusionDateTime;
	}
	
	public int getEstimatedExecutionTimeInMinutes() {
		return estimatedExecutionTimeInMinutes;
	}
	
	public void setEstimatedExecutionTimeInMinutes(int estimatedExecutionTimeInMinutes) {
		this.estimatedExecutionTimeInMinutes = estimatedExecutionTimeInMinutes;
	}
}