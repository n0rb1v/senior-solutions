package meetingrooms;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Data
@ConfigurationProperties(prefix = "hello")
@Validated
public class HelloProperties {
    @NotBlank
    private String message;

}
