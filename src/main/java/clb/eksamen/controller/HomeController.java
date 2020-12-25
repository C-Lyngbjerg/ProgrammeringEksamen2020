package clb.eksamen.controller;

import clb.eksamen.model.Student;
import clb.eksamen.repository.StudentRepository;
import clb.eksamen.repository.SupervisorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SupervisorRepository supervisorRepository;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("students", studentRepository.findAll());
        return "index";
    }

    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("supervisors", supervisorRepository.findAll());
        return "create";
    }
    @PostMapping("/create")
    public String create(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/";
    }

    @GetMapping("/delete/{student_id}")
    public String delete(@PathVariable("student_id") long id){
        studentRepository.deleteById(id);
            return "redirect:/";
    }
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model, Model modelsuper){
        model.addAttribute("student", studentRepository.findById(id));
        modelsuper.addAttribute("supervisors", supervisorRepository.findAll());
        return "update";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute Student student){
        studentRepository.save(student);
        return "redirect:/";
    }
}
