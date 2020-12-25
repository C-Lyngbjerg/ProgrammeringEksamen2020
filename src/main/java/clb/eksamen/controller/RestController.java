package clb.eksamen.controller;

import clb.eksamen.model.Student;
import clb.eksamen.model.Supervisor;
import clb.eksamen.repository.StudentRepository;
import clb.eksamen.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@org.springframework.web.bind.annotation.RestController
public class RestController {
//    Autowired RestController til repositories ved dependency injection
//    i stedet for at bruge Constructor injection, fordi det er det vi er mest vant til fra 2. semester
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SupervisorRepository supervisorRepository;

//    HTTP Get for at få alle students
    @GetMapping("/student")
    public Iterable<Student> findAll(){
        return studentRepository.findAll();
    }

//        HTTP PUT, ie. update
    @PutMapping("/student/{id}")
    public ResponseEntity<String> update(@PathVariable("id") Long id, @RequestBody Student s){
//        get studentById
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (!optionalStudent.isPresent()){
//            student id findes ikke
            return ResponseEntity.status(404).body("{'msg':'Not found'");
        }
//        gem student
        studentRepository.save(s);
        return ResponseEntity.status(204).body("{'msg':'Updated'}");
    }

    // HTTP Post, ie. create
    @CrossOrigin(origins = "*", exposedHeaders = "Location")
    @PostMapping(value="/student", consumes={"application/json"})
    public ResponseEntity<String> create(@RequestBody Student s){
        Student tempStudent = new Student(s.getId(), s.getName(), s.getEmail(), s.getSupervisor());
        studentRepository.save(tempStudent);
        return ResponseEntity.status(201).header("Location", "/student/" + s.getId()).body("{'Msg': 'post created'}");
    }

    // HTTPDelete
    @DeleteMapping("/student/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        Optional<Student> student = studentRepository.findById(id);
        //check at studenten findes
        if(!student.isPresent()){
            return ResponseEntity.status(404).body("{'msg':'Not found'"); // Not found
        }
        studentRepository.deleteById(id);

        return ResponseEntity.status(200).body("{'msg':'Deleted'}");
    }

}
