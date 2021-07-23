package activitytracker;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "activitytracker")
@NamedQuery(name = "findTrackPointCoordinatesByDate",query = "select new activitytracker.Coordinate(t.lat,t.lon) from Activity a join a.trackpoints t where a.startTime > :afterThis")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Act_name")
    @TableGenerator(name = "Act_name", table = "act_id_gen", pkColumnName = "id_gen", valueColumnName = "id_val")
    private Long id;

    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 20, nullable = false)
    private ActivityType type;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @ElementCollection
    @CollectionTable(name = "labels", joinColumns = @JoinColumn(name = "act_id"))
    @Column(name = "label")
    private List<String> labels;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.REMOVE},mappedBy = "activity")
    @OrderBy("time")
    private List<Trackpoint> trackpoints;

    @ManyToMany(mappedBy = "activities")
    private List<Area> areas = new ArrayList<>();

    public Activity() {
    }

    public Activity(LocalDateTime startTime, String description, ActivityType type) {
        this.startTime = startTime;
        this.description = description;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ActivityType getType() {
        return type;
    }

    public void setType(ActivityType type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Trackpoint> getTrackpoints() {
        return trackpoints;
    }

    public void setTrackpoints(List<Trackpoint> trackpoints) {
        this.trackpoints = trackpoints;
    }

    public void addTrackPoint(Trackpoint t) {
        if (trackpoints == null) {
            trackpoints = new ArrayList<>();
        }
        trackpoints.add(t);
        t.setActivity(this);
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }
}
