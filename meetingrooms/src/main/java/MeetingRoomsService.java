import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MeetingRoomsService {
    private MeetingRoomsRepository meetingRoomsRepository;

    public MeetingRoomsService(MeetingRoomsRepository meetingRoomsRepository) {
        this.meetingRoomsRepository = meetingRoomsRepository;
    }

    public void save(String name, int width, int lenght) {
        meetingRoomsRepository.save(name, width, lenght);
    }

    public List<MeetingRoom> listEmployees() {
        return meetingRoomsRepository.findAll();
    }

    public List<MeetingRoom> revListEmployees() {
        List<MeetingRoom> rev = meetingRoomsRepository.findAll();
        Collections.reverse(rev);
        return rev;
    }

    public List<MeetingRoom> evenListEmployees() {
        List<MeetingRoom> even = meetingRoomsRepository.findAll();
        return IntStream.range(0, even.size())
                .filter(n -> n % 2 == 0)
                .mapToObj(even::get)
                .collect(Collectors.toList());
    }


    public void deleteAll() {
        meetingRoomsRepository.deleteAll();
    }
}
