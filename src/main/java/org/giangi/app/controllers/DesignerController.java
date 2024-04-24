package org.giangi.app.controllers;

import org.giangi.app.domain.Designer;
import org.giangi.app.domain.User;
import org.giangi.app.services.DesignerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

    @GetMapping("/designers/search/{name}")
    public List<Designer> getByName(@PathVariable String name){
        List<Designer> designers = new ArrayList<>();
        designerService.listAll().forEach(designers::add);
        List<Designer> toSend = new ArrayList<>();
        Iterator<Designer> iterator = designers.iterator();
        while(iterator.hasNext()){
            Designer temp = iterator.next();
            if(temp.getFirstName().equals(name)){
                toSend.add(temp);
            }
        }
        return toSend;
    }


    @PostMapping(value = "/designer/new", consumes = {"application/json"})
    public void add(@RequestBody Designer designer){
        designerService.save(designer);
    }
}
