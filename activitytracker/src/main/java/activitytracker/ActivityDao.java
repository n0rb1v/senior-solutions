package activitytracker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDateTime;
import java.util.List;

public class ActivityDao {
    private EntityManagerFactory factory;

    public ActivityDao(EntityManagerFactory factory) {
        this.factory = factory;
    }

    public void saveActivity(Activity a) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        a.setCreatedAt(LocalDateTime.now());
        em.persist(a);
        em.getTransaction().commit();
        em.close();
    }

    public List<Activity> listActivities() {
        EntityManager em = factory.createEntityManager();
        List<Activity> activities = em.createQuery("select a from Activity a order by a.id", Activity.class)
                .getResultList();
        em.close();
        return activities;
    }

    public Activity findActivityById(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em.find(Activity.class, id);
        em.close();
        return activity;
    }

    public void deleteActivity(long id) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.getReference(Activity.class, id);
        em.remove(activity);
        em.getTransaction().commit();
        em.close();
    }

    public void updateActivity(long id, String s) {
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        Activity activity = em.find(Activity.class, id);
        activity.setDescription(s);
        activity.setUpdatedAt(LocalDateTime.now());
        em.getTransaction().commit();
        em.close();
    }

    public Activity findActivityByIdWithLabels(long id) {
        EntityManager em = factory.createEntityManager();
        Activity activity = em
                .createQuery("select a from Activity a join fetch a.labels where id = :id", Activity.class)
                .setParameter("id", id)
                .getSingleResult();
        em.close();
        return activity;
    }
}
