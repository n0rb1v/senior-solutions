package meetingrooms;

import lombok.AllArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.stream.IntStream;

@AllArgsConstructor
public class MeetingRoomRepo {

    private EntityManagerFactory emf;

    public MeetingRoom save(String name, int width, int length) {
        EntityManager em = emf.createEntityManager();
        MeetingRoom meetingRoom = new MeetingRoom(name, width, length);
        em.getTransaction().begin();
        em.persist(meetingRoom);
        em.getTransaction().commit();
        em.close();
        return meetingRoom;
    }

    public List<String> getMeetingroomsOrderedByName() {
        EntityManager em = emf.createEntityManager();
        List<String> names = em.createQuery("select m.name from MeetingRoom m order by m.name",String.class)
                .getResultList();
        em.close();
        return names;
    }

    public List<String> getEverySecondMeetingRoom() {
        EntityManager em = emf.createEntityManager();
        List<String> names = em.createQuery("select m.name from MeetingRoom m order by m.id",String.class)
                .getResultList();
        List<String> result = IntStream.range(0, names.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(names::get)
                .toList();
        em.close();
        return result;
    }

    public List<MeetingRoom> getMeetingRooms() {
        EntityManager em = emf.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m order by m.id",MeetingRoom.class)
                .getResultList();
        em.close();
        return meetingRooms;
    }

    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        EntityManager em = emf.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m where m.name = :name",MeetingRoom.class)
                .setParameter("name",name)
                .getResultList();
        em.close();
        return meetingRooms;
    }

    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix) {
        EntityManager em = emf.createEntityManager();
        List<MeetingRoom> meetingRooms = em.createQuery("select m from MeetingRoom m where m.name like :name",MeetingRoom.class)
                .setParameter("name",nameOrPrefix+"%")
                .getResultList();
        em.close();
        return meetingRooms;
    }

      public void deleteAll(){
          EntityManager em = emf.createEntityManager();
          em.getTransaction().begin();
          em.createQuery("delete from MeetingRoom m")
                  .executeUpdate();
          em.getTransaction().commit();
          em.close();
      }
}
