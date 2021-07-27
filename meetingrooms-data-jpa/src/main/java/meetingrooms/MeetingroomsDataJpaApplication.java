package meetingrooms;

import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@AllArgsConstructor
public class MeetingroomsDataJpaApplication implements CommandLineRunner {

    private final MeetingRoomsDao dao;
    private final HelloService helloService;

    public static void main(String[] args) {
        SpringApplication.run(MeetingroomsDataJpaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println(helloService.sayHello());
        System.out.println(helloService.sayH2());

        dao.save(new MeetingRoom("Jupiter",20,20));
        dao.save(new MeetingRoom("Neptune",15,15));
        dao.save(new MeetingRoom("Mars",20,20));
        dao.save(new MeetingRoom("Uranusz",15,15));

        System.out.println(dao.findAllByNameLike("Ju%"));
    }
}
