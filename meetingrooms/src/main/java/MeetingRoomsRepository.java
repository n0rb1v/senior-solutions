import java.util.List;

public interface MeetingRoomsRepository {

    void save(String name, int width, int lenght);

    List<MeetingRoom> findAll();

    void deleteAll();
}
