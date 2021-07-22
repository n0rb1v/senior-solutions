package activitytracker;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class AreaDaoTest {

    private ActivityDao activityDao;
    private AreaDao areaDao;

    @BeforeEach
    void init() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        activityDao = new ActivityDao(factory);
        areaDao = new AreaDao(factory);
    }
    @Test
    void saveArea() {
        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        Activity a2 =new Activity(LocalDateTime.of(2020,2,3,12,22,0),"test2",ActivityType.HIKING);
        Activity a3 =new Activity(LocalDateTime.of(2020,4,8,13,23,0),"test3",ActivityType.RUNNING);

        activityDao.saveActivity(a1);
        activityDao.saveActivity(a2);
        activityDao.saveActivity(a3);

        Area area1 = new Area("Budapest");
        Area area2 = new Area("Eger");
        Area area3 = new Area("Debrecen");

        area1.addActivity(a1);
        area1.addActivity(a2);

        area2.addActivity(a1);
        area2.addActivity(a3);

        area3.addActivity(a3);

        areaDao.saveArea(area1);
        areaDao.saveArea(area2);
        areaDao.saveArea(area3);

        Area area = areaDao.findAreaByName("Eger");
        assertEquals(List.of("test1","test3"),area.getActivities().stream().map(Activity::getDescription).collect(Collectors.toList()));
    }
}