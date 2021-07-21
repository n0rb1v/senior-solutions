package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityTrackerMain {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
        EntityManager em = factory.createEntityManager();

        Activity a1 =new Activity(LocalDateTime.of(2020,1,5,15,21,0),"test1",ActivityType.BIKING);
        Activity a2 =new Activity(LocalDateTime.of(2020,2,3,12,22,0),"test2",ActivityType.HIKING);
        Activity a3 =new Activity(LocalDateTime.of(2020,4,8,13,23,0),"test3",ActivityType.RUNNING);

        ActivityTrackerMain atm = new ActivityTrackerMain();
        atm.save(factory,a1);
        atm.save(factory,a2);
        atm.save(factory,a3);

        System.out.println(atm.listAll(factory));
        System.out.println(atm.findById(factory,1L));

    }

    private void save(EntityManagerFactory factory, Activity a) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }
    private List<Activity> listAll(EntityManagerFactory factory) {
        EntityManager em = factory.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a order by a.id", Activity.class)
                .getResultList();
        em.close();
        return activities;
    }
    public Activity findById(EntityManagerFactory factory,Long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }
}
