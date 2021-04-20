package jobs.schedulingJob.model;

public class SchedulingException  extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public SchedulingException() {
		
	}
	
	public SchedulingException(String message) {
		super(message);
	}
}
