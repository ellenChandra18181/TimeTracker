public class StudyTask extends Task {
    private String subject;

    public StudyTask(String taskName, int duration, String subject) {
        super(taskName, duration);
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public String displayTask() {
        return super.displayTask() + ", Subject: " + subject;
    }
}
