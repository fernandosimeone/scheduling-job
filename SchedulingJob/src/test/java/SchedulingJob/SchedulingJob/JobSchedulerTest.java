package SchedulingJob.SchedulingJob;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import schedulingJob.Job;
import schedulingJob.JobScheduler;

public class JobSchedulerTest {

	private JobScheduler scheduler;
	
	@Before
	public void initialize() {
		scheduler = new JobScheduler();
	}
	
	@Test
	public void shouldScheduleJobs() {
		List<Job> jobs = Arrays.asList( 
			createJob(1, "Importação de arquivos de fundos", "2019-11-10T12:00:00", 120),
			createJob(2, "Importação de dados da Base Legada", "2019-11-11T12:00:00", 240),
			createJob(3, "Importação de dados de integração", "2019-11-11T08:00:00", 360)
		);
		
		List<List<Job>> result = scheduler
			.schedule(LocalDateTime.parse("2019-11-10T09:00:00"), LocalDateTime.parse("2019-11-11T12:00:00"), jobs);
		
		Assert.assertNotNull(result);
		Assert.assertEquals(2, result.size());
		
		Assert.assertEquals(2, result.get(0).size());
		Assert.assertEquals(1, result.get(0).get(0).getId());
		Assert.assertEquals(3, result.get(0).get(1).getId());
	
		Assert.assertEquals(1, result.get(1).size());
		Assert.assertEquals(2, result.get(1).get(0).getId());
	}
	
	
	// Métodos auxiliares
	
	private Job createJob(int id, String description, String maxConclusionDateTime, int excecutionTime) {
    	
		Job job = new Job();
    	job.setId(id);
    	job.setDescription(description);
    	job.setEstimatedExecutionTimeInMinutes(excecutionTime);
    	job.setMaxConclusionDateTime(LocalDateTime.parse(maxConclusionDateTime));
	    	
    	return job;
	}
}
