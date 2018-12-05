package hello;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.cloud.stream.annotation.EnableBinding;

@EnableBinding(Streams.class)
public class StreamsConfig {

}
