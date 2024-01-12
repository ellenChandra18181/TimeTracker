public class WorkTask extends Task {
    private String project;

    public WorkTask(String taskName, int duration, String project) {
        super(taskName, duration);
        this.project = project;
    }

    public String getProject() {
        return project;
    }

    @Override
    public String displayTask() {
        return super.displayTask() + ", Project: " + project;
    }
}
