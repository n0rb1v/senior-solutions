package meetingrooms;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class MeetingroomsDataJpaApplication implements CommandLineRunner {

    private final MeetingRoomService service;
    private final HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(MeetingroomsDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        System.out.println(helloService.sayHello());
//        System.out.println(helloService.sayH2());
//
//        service.save(new MeetingRoom("Jupiter",20,20));
//        service.save(new MeetingRoom("Neptune",15,15));
//        service.save(new MeetingRoom("Mars",20,20));
//        service.save(new MeetingRoom("Uranusz",15,15));
//
//        System.out.println(service.getMeetingroomsOrderedByName());
//        System.out.println(service.getEverySecondMeetingRoom());
//        System.out.println(service.getMeetingRooms());
//        System.out.println(service.getExactMeetingRoomByName("Mars"));
//        System.out.println(service.getMeetingRoomsByPrefix("Ju%"));
//        service.deleteAll();
//        System.out.println(service.getMeetingRooms());
        service.save(new MeetingRoom("turu",7,7));
        service.save(new ExtMeetingRoom("dudu",6,6,10_000));
        service.save(new CompMeetingRoom("nunu",5,5,20));
        System.out.println(service.getMeetingRooms());
        System.out.println((ExtMeetingRoom)service.getExactMeetingRoomByName("dudu").get(0));
        System.out.println((CompMeetingRoom)service.getExactMeetingRoomByName("nunu").get(0));

    }
}
