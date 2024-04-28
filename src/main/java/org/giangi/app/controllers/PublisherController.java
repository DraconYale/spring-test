package org.giangi.app.controllers;

import org.giangi.app.domain.Publisher;
import org.giangi.app.services.PublisherService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class PublisherController {

    private final PublisherService publisherService;

    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/publishers")
    public List<Publisher> getPublishers(){
        List<Publisher> publishers = new ArrayList<Publisher>();
        publisherService.listAll().forEach(publishers::add);
        return publishers;
    }

    @PostMapping("/publishers/new")
    public void addPublisher(@RequestBody Publisher publisher){
        publisherService.save(publisher);
    }

    @PutMapping("/publishers/{id}")
    public ResponseEntity<?> updatePublisher(@RequestBody Publisher publisher, @PathVariable Long id){
        try {
            Publisher toUp = publisherService.get(id);
            publisherService.save(publisher);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("publishers/{id}")
    public void deletePublisher(@PathVariable Long id){
        publisherService.delete(id);
    }
}
