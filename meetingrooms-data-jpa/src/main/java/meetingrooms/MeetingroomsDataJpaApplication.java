package meetingrooms;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
@AllArgsConstructor
public class MeetingroomsDataJpaApplication implements CommandLineRunner {

//    private final MeetingRoomService service;
//    private final HelloService helloService;

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
    }
}
