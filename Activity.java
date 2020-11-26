public class Activity implements Comparable<Activity>{
    private String name;
    private int priority;

    public Activity(String name, int priority) {
        this.name = name;
        this.priority = priority;
    }
    
    public int compareTo(Activity other) {
        return 0 - (this.priority - other.priority);
    }

    public String toString() {
        return "Task: " + name + "\nPriority: " + priority;
    }
}
