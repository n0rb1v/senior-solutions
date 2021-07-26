package meetingrooms;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MeetingRoomsDao extends JpaRepository<MeetingRoom, Long> {

    List<MeetingRoom> findAllByNameLike(String s);
}
