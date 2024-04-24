package org.giangi.app.services;

import org.giangi.app.domain.Publisher;
import org.giangi.app.repositories.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherService {

    @Autowired
    private PublisherRepository publisherRepository;

    public List<Publisher> listAll() {
        return (List<Publisher>) publisherRepository.findAll();
    }

    public void save(Publisher publisher) {
        publisherRepository.save(publisher);
    }

    public Publisher get(Long id) {
        return publisherRepository.findById(id).get();
    }

    public void delete(Long id) {
        publisherRepository.deleteById(id);
    }
}
