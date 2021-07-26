package meetingrooms;

import org.junit.jupiter.api.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomRepoIT {

    static EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu");
    static MeetingRoomRepo repo = new MeetingRoomRepo(factory);

    @BeforeEach
    void init(){
        repo.deleteAll();
        repo.save("Jupiter",20,20);
        repo.save("Neptune",15,15);
        repo.save("Mars",20,20);
        repo.save("Uranusz",15,15);
    }

    @AfterAll
    static void close() {
        factory.close();
    }

    @Test
    @DisplayName("Save 2 meetingroom then query all")
    void save() {
        repo.deleteAll();
        repo.save("Jupiter",20,20);
        repo.save("Neptune",15,15);
        List<String> result = repo.getMeetingroomsOrderedByName();
        assertEquals(List.of("Jupiter","Neptune"),result);
    }

    @Test
    void list2nd() {
        List<String> result = repo.getEverySecondMeetingRoom();
        assertEquals(List.of("Jupiter","Mars"),result);
    }

    @Test
    void listrooms() {
        List<MeetingRoom> result = repo.getMeetingRooms();
        assertEquals(4,result.size());
        System.out.println(result);
    }
    @Test
    void listroomsname() {
        List<MeetingRoom> result = repo.getExactMeetingRoomByName("Mars");
        assertEquals(1,result.size());
        System.out.println(result);
    }
    @Test
    void listroomsprefix() {
        List<MeetingRoom> result = repo.getMeetingRoomsByPrefix("Ju");
        assertEquals(1,result.size());
        System.out.println(result);
    }
}