package activitytracker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    private List<Activity> activities = new ArrayList<>();

    public Area() {
    }

    public Area(String name) {
        this.name = name;
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.getAreas().add(this);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}
