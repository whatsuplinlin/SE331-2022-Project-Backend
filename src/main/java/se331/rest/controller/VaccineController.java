package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import se331.rest.entity.Comment;
import se331.rest.entity.People;
import se331.rest.entity.Vaccine;
import se331.rest.service.PeopleService;
import se331.rest.service.VaccineService;
import se331.rest.util.LabMapper;

@Controller
public class VaccineController {
    @Autowired
    PeopleService peopleService;

    @Autowired
    VaccineService vaccineServicec;

    @PostMapping("/vaccine/people/{id}")
    ResponseEntity<?> addComment(@PathVariable("id") Long id, @RequestBody Vaccine vaccine){
        People output = peopleService.getPeople(id);
        output.getVaccineList().add(vaccine);
        Vaccine output2 = vaccineServicec.save(vaccine);
        return ResponseEntity.ok(LabMapper.INSTANCE.getVaccineDto(output2));
    }
}
