package meetingrooms;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class MeetingRoomService {

    private MeetingRoomsDao meetingRoomsDao;

    public MeetingRoom save(MeetingRoom meetingRoom) {
        return meetingRoomsDao.save(meetingRoom);
    }

    public List<String> getMeetingroomsOrderedByName() {
        return meetingRoomsDao.findByOrderByNameAsc().stream()
                .map(meetingRoom -> meetingRoom.getName())
                .collect(Collectors.toList());
    }

    public List<String> getEverySecondMeetingRoom() {
        List<MeetingRoom> sec = meetingRoomsDao.findAll();
        return IntStream.range(0, sec.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(sec::get)
                .map(MeetingRoom::getName)
                .toList();
    }

    public List<MeetingRoom> getMeetingRooms() {
        return meetingRoomsDao.findAll();
    }

    public List<MeetingRoom> getExactMeetingRoomByName(String name) {
        return meetingRoomsDao.findAllByName(name);
    }

    public List<MeetingRoom> getMeetingRoomsByPrefix(String nameOrPrefix){
        return meetingRoomsDao.findAllByNameLike(nameOrPrefix);
    }

    public void deleteAll() {
        meetingRoomsDao.deleteAll();
    }
}
