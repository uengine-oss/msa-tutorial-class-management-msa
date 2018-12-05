package hello;

import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Profile;

@EnableBinding(Streams.class)
@Profile("kafka")
public class StreamsConfig {
}
