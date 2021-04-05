package it.helloworlddbms.demo.controller;

import it.helloworlddbms.demo.dao.HelloMessageRepository;
import it.helloworlddbms.demo.entity.HelloMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RestController
public class HelloController {

    private final HelloMessageRepository helloMessageRepository;

    public HelloController(HelloMessageRepository helloMessageRepository) {
        this.helloMessageRepository = helloMessageRepository;
    }

    @GetMapping("/hello-world")
    private ResponseEntity<?> getHelloWorldMessage(@RequestParam(name = "language", required = true) String language) {
        log.info("GET REQUEST");
        return ResponseEntity.ok(this.helloMessageRepository.findById(language)
                .orElseThrow(() -> new RuntimeException("language" + language + " not supported")));
    }

    @PostMapping("/hello-world")
    private ResponseEntity<?> postHelloWorldMessage(@RequestBody HelloMessage helloMessage) {
        log.info("POST REQUEST");

        this.helloMessageRepository.findById(helloMessage.getLanguage())
                .ifPresent((p) -> {
                    throw new RuntimeException("language " + helloMessage.getLanguage() + " already configured");
                });

        return ResponseEntity.ok(helloMessageRepository.save(helloMessage));
    }
}
