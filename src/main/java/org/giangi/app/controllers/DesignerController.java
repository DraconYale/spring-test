package org.giangi.app.controllers;

import org.giangi.app.domain.Designer;
import org.giangi.app.services.DesignerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class DesignerController {

    private final DesignerService designerService;

    public DesignerController(DesignerService designerService) {
        this.designerService = designerService;
    }

    @GetMapping("/designers")
    public List<Designer> getDesigners(){
        List<Designer> designers = new ArrayList<>();
        designerService.listAll().forEach(designers::add);
        return designers;
    }

    @GetMapping("/designers/{id}")
    public ResponseEntity<Designer> getUser(@PathVariable Long id){
        try{
            return new ResponseEntity<Designer>(designerService.get(id), HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<Designer>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/designers/new", consumes = {"application/json"})
    public void addDesigner(@RequestBody Designer designer){
        designerService.save(designer);
    }

    @PutMapping(value = "designers/{id}")
    public ResponseEntity<?> updateDesigner(@RequestBody Designer designer, @PathVariable Long id){
        try {
            Designer toUp = designerService.get(id);
            designerService.save(designer);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        catch (NoSuchElementException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "designers/{id}")
    public void deleteDesignerById(@PathVariable Long id){
        designerService.delete(id);
    }
}
