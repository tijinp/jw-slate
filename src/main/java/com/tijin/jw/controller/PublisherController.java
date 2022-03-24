package com.tijin.jw.controller;

import com.tijin.jw.exception.ResourceNotFoundException;
import com.tijin.jw.model.entity.Publisher;
import com.tijin.jw.model.repository.PublisherRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class PublisherController {

    private final PublisherRepository publisherRepository;

    @GetMapping("/publishers")
    public List<Publisher> getAllPublishers() {
        log.info("Getting all Publishers");
        return publisherRepository.findAll();
    }

    @GetMapping("/publishers/{id}")
    @SneakyThrows
    public ResponseEntity< Publisher > getEmployeeById(@PathVariable(value = "id") Long publisherId) {
        Publisher publisher = publisherRepository.findById(publisherId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + publisherId));
        return ResponseEntity.ok().body(publisher);
    }

    @PostMapping("/publishers")
    public Publisher createEmployee(@Valid @RequestBody Publisher publisher) {
        log.info("Creating Publisher record {}", publisher);
        return publisherRepository.save(publisher);
    }
}
