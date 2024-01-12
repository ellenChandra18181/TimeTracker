public class Task {
    private String taskName;
    private int duration; // in minutes

    public Task(String taskName, int duration) {
        this.taskName = taskName;
        this.duration = duration;
    }

    public String getTaskName() {
        return taskName;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String displayTask() {
        return "Task: " + taskName + ", Duration: " + duration + " minutes";
    }
}
