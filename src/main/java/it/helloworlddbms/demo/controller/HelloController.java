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
        HelloMessage message = this.helloMessageRepository.findById(language)
                .orElse(null);

        return message != null ? ResponseEntity.ok(message) : ResponseEntity.notFound().build();
    }

    @PostMapping("/hello-world")
    private ResponseEntity<?> postHelloWorldMessage(@RequestBody HelloMessage helloMessage) {
        log.info("POST REQUEST");

        if (this.helloMessageRepository.findById(helloMessage.getLanguage()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Language " + helloMessage.getLanguage() + " already configured");
        }

        return ResponseEntity.ok(helloMessageRepository.save(helloMessage));
    }
}
