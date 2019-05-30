package cn.iocoder.springboot.labs.lab06.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class Controller {

    @GetMapping("/hello")
    public Mono<String> hello() {
        return Mono.just("world");
    }

    @GetMapping("/sleep_direct")
    public Mono<String> sleepDirect() throws InterruptedException {
        Thread.sleep(100L);
        return Mono.just("world");
    }

    @GetMapping("/sleep")
    public Mono<String> sleep() {
//        System.out.println(Thread.currentThread().getName());
//        return Mono.just("world").delayElement(Duration.ofMillis(100));
        return Mono.defer(() -> {
//            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(100L);
            } catch (InterruptedException ignored) {
            }
            return Mono.just("world");
        }).subscribeOn(Schedulers.parallel());
    }

}
