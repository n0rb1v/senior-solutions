package activitytracker;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class Area {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "area_cities", joinColumns = @JoinColumn(name = "area_id"), inverseJoinColumns = @JoinColumn(name = "city_id"))
    @MapKey(name = "name")
    private Map<String, City> cityMap = new HashMap<>();

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

    public Map<String, City> getCityMap() {
        return cityMap;
    }

    public void setCityMap(Map<String, City> cityMap) {
        this.cityMap = cityMap;
    }
}
