package SchedulingJob.SchedulingJob;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import schedulingJob.Job;
import schedulingJob.JobScheduler;

public class JobSchedulerTest {

	private JobScheduler scheduler;
	private LocalDateTime windowStart, windowEnd;
	private List<Job> jobs;
	
	@Before
	public void initialize() {
		scheduler = new JobScheduler();
		windowStart = LocalDateTime.of(2019, 11, 10, 9, 00);
		windowEnd = LocalDateTime.of(2019, 11, 11, 12, 00);
		
		jobs = Arrays.asList( 
			createJob(1, "Job 1", LocalDateTime.of(2019, 11, 10, 12, 00), 120),
			createJob(2, "Job 2", LocalDateTime.of(2019, 11, 11, 12, 00), 240),
			createJob(3, "Job 3", LocalDateTime.of(2019, 11, 11,  8, 00), 360)
		);
	}
	
	@Test
	public void shouldScheduleJobs() {
		
		List<List<Job>> result = scheduler.schedule(windowStart, windowEnd, jobs);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		
		Assert.assertEquals(2, result.get(0).size());
		Assert.assertEquals(1, result.get(0).get(0).getId());
		Assert.assertEquals(3, result.get(0).get(1).getId());
	
		Assert.assertEquals(1, result.get(1).size());
		Assert.assertEquals(2, result.get(1).get(0).getId());
	}
	
	
	// Métodos auxiliares
	
	private Job createJob(int id, String description, LocalDateTime maxConclusionDateTime, int excecutionTime) {
    	
		Job job = new Job();
    	job.setId(id);
    	job.setDescription(description);
    	job.setEstimatedExecutionTimeInMinutes(excecutionTime);
    	job.setMaxConclusionDateTime(maxConclusionDateTime);
	    	
    	return job;
	}
}
