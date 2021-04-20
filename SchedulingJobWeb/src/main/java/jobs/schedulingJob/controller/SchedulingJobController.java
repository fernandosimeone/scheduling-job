package jobs.schedulingJob.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jobs.schedulingJob.dto.JobSchedulingParametersDto;
import jobs.schedulingJob.model.Job;
import jobs.schedulingJob.model.JobSchedulerService;

@RestController
public class SchedulingJobController {

	private JobSchedulerService jobSchedulerService;
	
	public SchedulingJobController(@Autowired JobSchedulerService jobSchedulerService) {
		this.jobSchedulerService = jobSchedulerService;
	}
	
	@PostMapping("/jobs/scheduling")
	public int[][] ScheduleJobs(@RequestBody JobSchedulingParametersDto parameters) {
		
		List<List<Job>> scheduling =  jobSchedulerService.schedule(
				parameters.getExecutionWindowStart(), 
				parameters.getExecutionWindowEnd(), 
				parameters.getJobs());
		
		return formatSchedulingResult(scheduling);
	}
	
	private int [][] formatSchedulingResult(List<List<Job>> scheduling) {
		
		int [][] result = new int[scheduling.size()][];
		
		for (int i = 0; i < scheduling.size(); i++) {
			result[i] = scheduling.get(i).stream().mapToInt(Job::getId).toArray(); 
		}
		
		return result;
	}
}
