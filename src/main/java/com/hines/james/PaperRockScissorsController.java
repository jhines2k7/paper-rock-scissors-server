package com.hines.james;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.rsocket.RSocketRequester;
import org.springframework.messaging.rsocket.annotation.ConnectMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PreDestroy;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Controller
public class PaperRockScissorsController {
    private final List<RSocketRequester> CLIENTS = new ArrayList<>();

    @PreDestroy
    void shutdown() {
        log.info("Detaching all remaining clients...");
        CLIENTS.stream().forEach(requester -> requester.rsocket().dispose());
        log.info("Shutting down.");
    }

    @ConnectMapping("shell-client")
    void connectShellClientAndAskForTelemetry(RSocketRequester requester,
                                              @Payload String client) {

        requester.rsocket()
                .onClose()
                .doFirst(() -> {
                    // Add all new clients to a client list
                    log.info("Client: {} CONNECTED.", client);
                    CLIENTS.add(requester);
                })
                .doOnError(error -> {
                    // Warn when channels are closed by clients
                    log.warn("Channel to client {} CLOSED", client);
                })
                .doFinally(consumer -> {
                    // Remove disconnected clients from the client list
                    CLIENTS.remove(requester);
                    log.info("Client {} DISCONNECTED", client);
                })
                .subscribe();

        // Callback to client, confirming connection
        requester.route("client-status")
                .data("OPEN")
                .retrieveFlux(String.class)
                .doOnNext(s -> log.info("Client: {} Free Memory: {}.", client, s))
                .subscribe();
    }

    @MessageMapping("challenger-stream")
    Flux<Challenger> challengerStream(final Player player) {
        log.info("Received challenge request: {}", player);

        Challenger avi = new Challenger("avi", 10, 2, 14, PlayerStatus.WAITING);
        Challenger riv = new Challenger("riv", 2, 2, 8, PlayerStatus.WAITING);
        Challenger casey = new Challenger("casey", 20, 2, 14, PlayerStatus.WAITING);
        Challenger tab = new Challenger("tab", 20, 2, 14, PlayerStatus.WAITING);

        List<Challenger> challengers = new ArrayList<>(Arrays.asList(avi, riv, casey, tab));

        return Flux
                .interval(Duration.ofSeconds(5))
                .map(index -> challengers.get((int)(index % 4)));
    }

    @MessageMapping("new-challenger")
    Mono<Challenger> newChallenger(final Challenger challenger) {
        log.info("Adding a new challenger to the challenger stream: {}", challenger);

        return Mono.just(new Challenger("", 1, 1, 1, PlayerStatus.ACCEPTED));
    }
}
