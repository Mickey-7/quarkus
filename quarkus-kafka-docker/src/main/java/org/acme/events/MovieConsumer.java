package org.acme.events;

import io.smallrye.reactive.messaging.kafka.Record;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class MovieConsumer {
    private final Logger logger = LoggerFactory.getLogger(MovieConsumer.class);

    @Incoming("movies-in")
    public void receive(Record<Integer,String> record){
        logger.info(String.format("got a movie: %d - %s",record.key(), record.value()));
    }
}
