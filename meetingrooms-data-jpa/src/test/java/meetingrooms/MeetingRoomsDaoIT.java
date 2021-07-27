package meetingrooms;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Sql(statements = "delete from meetingrooms")
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