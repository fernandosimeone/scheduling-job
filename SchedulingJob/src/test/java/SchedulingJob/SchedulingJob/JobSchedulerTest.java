package SchedulingJob.SchedulingJob;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import schedulingJob.Job;
import schedulingJob.JobScheduler;
import schedulingJob.SchedulingException;

public class JobSchedulerTest {

	private JobScheduler scheduler;
	private LocalDateTime windowStart, windowEnd;
	private List<Job> jobs;
	
	@Before
	public void initialize() {
		scheduler = new JobScheduler();
		windowStart = LocalDateTime.of(2019, 11, 10, 9, 00);
		windowEnd = LocalDateTime.of(2019, 11, 11, 12, 00);
		
		jobs = new ArrayList<>(); 
		jobs.add(createJob(1, "Job 1", LocalDateTime.of(2019, 11, 10, 12, 00), 120));
		jobs.add(createJob(2, "Job 2", LocalDateTime.of(2019, 11, 11, 12, 00), 240));
		jobs.add(createJob(3, "Job 3", LocalDateTime.of(2019, 11, 11,  8, 00), 360));
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
	
	@Test(expected = SchedulingException.class)
	public void shouldThrowErrorWhenCannotAttendToMaxConclusionDateTime() {
		
		jobs.add(createJob(4, "Job 4", LocalDateTime.of(2019, 11, 10, 13, 00), 480));
		scheduler.schedule(windowStart, windowEnd, jobs);
	}
	
	@Test(expected = SchedulingException.class)
	public void shouldThrowErrorWhenExecutionWindowIsNotEnough() {
		
		jobs.add(createJob(4, "Job 4", LocalDateTime.of(2021, 01, 01, 13, 00), 60 * 48));
		scheduler.schedule(windowStart, windowEnd, jobs);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowErrorWithNullListOfJobs() {
		
		scheduler.schedule(windowStart, windowEnd, null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowErrorWithEmptyListOfJobs() {
		
		scheduler.schedule(windowStart, windowEnd, new ArrayList<Job>());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowErrorWithoutWindowStart() {
		
		scheduler.schedule(null, windowEnd, jobs);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowErrorWithoutWindowEnd() {
		
		scheduler.schedule(windowStart, null, jobs);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowErrorWithInvalidWindow() {
		windowEnd = LocalDateTime.of(2019, 01, 01, 9, 00);
		scheduler.schedule(windowStart, windowEnd, jobs);
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
