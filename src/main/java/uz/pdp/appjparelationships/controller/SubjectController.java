package uz.pdp.appjparelationships.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appjparelationships.entity.Address;
import uz.pdp.appjparelationships.entity.Subject;
import uz.pdp.appjparelationships.entity.University;
import uz.pdp.appjparelationships.payload.SubjectDto;
import uz.pdp.appjparelationships.payload.UniversityDto;
import uz.pdp.appjparelationships.repository.SubjectRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/subject")
public class SubjectController {
    @Autowired
    SubjectRepository subjectRepository;

    //Create
    @RequestMapping(method = RequestMethod.POST)
    public String addSubject(@RequestBody SubjectDto subjectDto) {
        boolean existsByName = subjectRepository.existsByName(subjectDto.getName());
        if (existsByName)
            return "this subject alread exist";
        Subject subject=new Subject();
        subject.setName(subjectDto.getName());
        subjectRepository.save(subject);
        return "Subject added";
    }

    //READ
//    @RequestMapping(method = RequestMethod.GET)
    @GetMapping
    public List<Subject> getSubjects() {
        List<Subject> subjectList = subjectRepository.findAll();
        return subjectList;
    }
    //Update
    @PutMapping("/{id}")
    public String editSubject(@PathVariable Integer id, @RequestBody SubjectDto subjectDto){
        Optional<Subject> optionalSubject = subjectRepository.findById(id);
        if (optionalSubject.isPresent()){
            Subject subject=optionalSubject.get();
            subject.setName(subjectDto.getName());

            subjectRepository.save(subject);
            return "Subject edited";
        }
        return "Subject not found";

    }
    //Delete
    @DeleteMapping("/{subjectId}")
    public String deleteSubject(@PathVariable("subjectId") Integer id){
        subjectRepository.deleteById(id);
        return "Subject delete";
    }

}
