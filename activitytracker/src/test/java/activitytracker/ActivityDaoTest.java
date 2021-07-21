package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class ActivityDaoTest {

    private ActivityDao dao;

    @BeforeEach
    void init() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
        dao = new ActivityDao(emf);
    }

    @Test
    void saveActivity() {
        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        dao.saveActivity(a1);

        Long id = a1.getId();

        Activity another = dao.findActivityById(id);
        assertEquals("test1",another.getDescription());
    }

    @Test
    void listActivities() {
        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        Activity a2 =new Activity(LocalDateTime.of(2020,2,3,12,22,0),"test2",ActivityType.HIKING);
        Activity a3 =new Activity(LocalDateTime.of(2020,4,8,13,23,0),"test3",ActivityType.RUNNING);
        dao.saveActivity(a1);
        dao.saveActivity(a2);
        dao.saveActivity(a3);

        List<Activity> activities = dao.listActivities();
        assertEquals(List.of("test1","test2","test3"),activities.stream().map(Activity::getDescription).collect(Collectors.toList()));
    }

    @Test
    void deleteActivity() {
        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        dao.saveActivity(a1);

        Long id = a1.getId();

        dao.deleteActivity(id);
        List<Activity> activities = dao.listActivities();
        assertEquals(0,activities.size());
    }

    @Test
    void updateActivity() {
        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        dao.saveActivity(a1);

        Long id = a1.getId();
        dao.updateActivity(id,"test8");
        Activity modified = dao.findActivityById(id);

        assertEquals("test8",modified.getDescription());
    }

    @Test
    void findActivityByIdWithLabels() {
        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        a1.setLabels(List.of("abc","bgh","hji"));
        Activity a2 =new Activity(LocalDateTime.of(2020,2,3,12,22,0),"test2",ActivityType.HIKING);
        a2.setLabels(List.of("xxx","yyy","zzz"));
        dao.saveActivity(a1);
        dao.saveActivity(a2);

        Long id = a1.getId();

        Activity another = dao.findActivityByIdWithLabels(id);
        assertEquals(List.of("abc","bgh","hji"),another.getLabels());
    }

}