package schedulingJob;

import java.util.Date;

public class Job {

	private int id;
	private String description;
	private Date maxConclusionDateTime;
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
	
	public Date getMaxConclusionDateTime() {
		return maxConclusionDateTime;
	}
	
	public void setMaxConclusionDateTime(Date maxConclusionDateTime) {
		this.maxConclusionDateTime = maxConclusionDateTime;
	}
	
	public int getEstimatedExecutionTimeInMinutes() {
		return estimatedExecutionTimeInMinutes;
	}
	
	public void setEstimatedExecutionTimeInMinutes(int estimatedExecutionTimeInMinutes) {
		this.estimatedExecutionTimeInMinutes = estimatedExecutionTimeInMinutes;
	}
}
