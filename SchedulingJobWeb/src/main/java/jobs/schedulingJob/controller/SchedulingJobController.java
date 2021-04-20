package jobs.schedulingJob.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<List<Job>> ScheduleJobs(@RequestBody JobSchedulingParametersDto parameters) {
		
		List<List<Job>> scheduling =  jobSchedulerService.schedule(
				parameters.getExecutionWindowStart(), 
				parameters.getExecutionWindowEnd(), 
				parameters.getJobs());
		
		return scheduling;
	}

}
