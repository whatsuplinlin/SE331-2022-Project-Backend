package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.People;
import se331.rest.service.PeopleService;
import se331.rest.util.LabMapper;

@Controller
public class PeopleController {
    @Autowired
    PeopleService peopleService;

    @GetMapping("/people")
    public ResponseEntity<?> getPeople(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page,@RequestParam(value = "name" , required = false) String name) {
        perPage = perPage == null ? 4 : perPage;
        page = page == null ? 1 : page;
        Page<People> pageOutput;
        if(name==null){
            pageOutput = peopleService.getPeople(perPage,page);
        }else{
            pageOutput = peopleService.getPeople(name, PageRequest.of(page-1,perPage));
        }
//        pageOutput = peopleService.getPeople(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(LabMapper.INSTANCE.getPeopleDto(pageOutput.getContent()), responseHeader, HttpStatus.OK);
    }
    @GetMapping("people/{id}")
    public ResponseEntity<?> get(@PathVariable("id") Long id) {
        People output = peopleService.getPeople(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getPeopleDto(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
    @GetMapping("people/{id}/comment")
    public ResponseEntity<?> getPeopleComment(@PathVariable("id") Long id) {
        People output = peopleService.getPeople(id);
        if (output != null) {
            return ResponseEntity.ok(LabMapper.INSTANCE.getCommentDto(output.getCommentList()));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

}
