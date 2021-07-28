package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
//@Sql(statements = "delete from meetingrooms")
class MeetingRoomsDaoIT {

    @Autowired
    MeetingRoomsDao meetingRoomsDao;

    @BeforeEach
    void init() {
        meetingRoomsDao.save(new MeetingRoom("Uranusz", 20, 20));
        meetingRoomsDao.save(new MeetingRoom("Neptune", 15, 15));
        meetingRoomsDao.save(new MeetingRoom("Mars", 20, 20));
        meetingRoomsDao.save(new MeetingRoom("Jupiter", 15, 15));
    }

    @Test
    void testPersist() {
        meetingRoomsDao.deleteAll();
        MeetingRoom meetingRoom = new MeetingRoom("turu",7,7);
        meetingRoomsDao.save(meetingRoom);
        List<MeetingRoom> meetingRooms = meetingRoomsDao.findAll();
        assertThat(meetingRooms)
                .hasSize(1)
                .extracting(MeetingRoom::getName)
                .containsExactly("turu");
    }
    @Commit
    @Test
    void testPersistExt() {
        //meetingRoomsDao.deleteAll();
        meetingRoomsDao.save(new MeetingRoom("turu",7,7));
        meetingRoomsDao.save(new ExtMeetingRoom("dudu",6,6,10_000));
        meetingRoomsDao.save(new CompMeetingRoom("nunu",5,5,20));

        List<MeetingRoom> result1 = meetingRoomsDao.findAllByName("turu");
        assertThat(result1)
                .hasSize(1)
                .extracting(MeetingRoom::getName)
                .containsExactly("turu");
        List<MeetingRoom> result2 = meetingRoomsDao.findAllByName("dudu");
        assertThat(result2)
                .hasSize(1)
                .extracting(MeetingRoom::getName)
                .containsExactly("dudu");
        List<MeetingRoom> result3 = meetingRoomsDao.findAllByName("nunu");
        assertThat(result3)
                .hasSize(1)
                .extracting(MeetingRoom::getName)
                .containsExactly("nunu");

    }
    @Test
    void findAllByNameLike() {
        List<MeetingRoom> meetingRooms = meetingRoomsDao.findAllByNameLike("Ju%");
        assertThat(meetingRooms)
                .hasSize(1)
                .extracting(MeetingRoom::getName)
                .containsExactly("Jupiter");
    }

    @Test
    void findByOrderByNameAsc() {
        List<MeetingRoom> meetingRooms = meetingRoomsDao.findByOrderByNameAsc();
        assertThat(meetingRooms)
                .hasSize(4)
                .extracting(MeetingRoom::getName)
                .startsWith("Jupiter");
    }

}