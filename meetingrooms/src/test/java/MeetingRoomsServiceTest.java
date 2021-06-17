import meetingrooms.MariaDbMeetingRoomsRepository;
import meetingrooms.MeetingRoom;
import meetingrooms.MeetingRoomsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MeetingRoomsServiceTest {
    MeetingRoomsService meetingRoomsService = new MeetingRoomsService(new MariaDbMeetingRoomsRepository());

    @BeforeEach
    void init() {
        meetingRoomsService.deleteAll();
        meetingRoomsService.save("arabia",3,7);
        meetingRoomsService.save("manhattan",6,6);
        meetingRoomsService.save("raktar",5,7);
        meetingRoomsService.save("targyalo",8,5);

    }

    @Test
    void sorrend() {
        List<MeetingRoom> meetingRooms = meetingRoomsService.listMeetingRooms();
        assertEquals(4, meetingRooms.size());
        assertEquals("arabia", meetingRooms.get(0).getName());
        assertEquals("targyalo", meetingRooms.get(3).getName());
    }

    @Test
    void revsorrend() {
        List<MeetingRoom> meetingRooms = meetingRoomsService.revListMeetingRooms();
        assertEquals(4, meetingRooms.size());
        assertEquals("targyalo", meetingRooms.get(0).getName());
        assertEquals("arabia", meetingRooms.get(3).getName());
    }
    @Test
    void masodik() {
        List<MeetingRoom> meetingRooms = meetingRoomsService.evenListMeetingRooms();
        assertEquals(2, meetingRooms.size());
        assertEquals("arabia", meetingRooms.get(0).getName());
        assertEquals("raktar", meetingRooms.get(1).getName());
    }
    @Test
    void teruletek() {
        List<MeetingRoom> meetingRooms = meetingRoomsService.areaListMeetingRooms();
        assertEquals(4, meetingRooms.size());
        assertEquals(40, meetingRooms.get(0).getArea());
        assertEquals(21, meetingRooms.get(3).getArea());
    }
    @Test
    void kereses() {
        assertEquals("raktar", meetingRoomsService.findByName("raktar").get().getName());
    }
    @Test
    void keresesreszlet() {
        assertEquals("raktar", meetingRoomsService.findByPartname("rak").get().getName());
    }
    @Test
    void keresesteruletek() {
        List<MeetingRoom> meetingRooms = meetingRoomsService.findByArea(25);
        assertEquals(3, meetingRooms.size());
        assertEquals(36, meetingRooms.get(0).getArea());
        assertEquals(40, meetingRooms.get(2).getArea());
    }

}