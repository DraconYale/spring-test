package org.giangi.app.services;

import org.giangi.app.domain.Designer;
import org.giangi.app.repositories.DesignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignerService {

    @Autowired
    private DesignerRepository designerRepository;

    public List<Designer> listAll() {
        return (List<Designer>) designerRepository.findAll();
    }

    public void save(Designer designer) {
        designerRepository.save(designer);
    }

    public Designer get(Long id) {
        return designerRepository.findById(id).get();
    }

    public void delete(Long id) {
        designerRepository.deleteById(id);
    }

}
