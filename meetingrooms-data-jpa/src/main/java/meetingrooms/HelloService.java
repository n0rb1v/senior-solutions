package meetingrooms;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@EnableConfigurationProperties(HelloProperties.class)
@Slf4j
public class HelloService {

    //private static final Logger log = LoggerFactory.getLogger(HelloService.class);

    private HelloProperties helloProperties;
    private String h2;

    public HelloService(HelloProperties helloProperties, @Value("${spring.h2.console.enabled}")String h2) {
        this.helloProperties = helloProperties;
        this.h2 = h2;
    }

    public String sayHello() {
        log.info("Message printed");
        log.debug("Message printed: {}",helloProperties.getMessage());
        return helloProperties.getMessage()+" "+ LocalDateTime.now();
    }
    public String sayH2() {
        return h2;
    }

}
