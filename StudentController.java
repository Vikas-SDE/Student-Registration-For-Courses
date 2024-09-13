package in.ashokit.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ch.qos.logback.classic.Logger;
import in.ashokit.entity.Student;
import in.ashokit.service.StudentService;
import jakarta.validation.Valid;

@Controller
public class StudentController {

	
	private static final int pageSize = 4;
	private Logger logger = (Logger) LoggerFactory.getLogger(StudentController.class);

	@Autowired
	private StudentService studentService;

	@GetMapping("/")
	public String showForm(Model model) {
		model.addAttribute("student", new Student());
		return "index";
	}

	@PostMapping("/saveStudent")
	public String saveStudent(@Valid @ModelAttribute("student") Student student, BindingResult rBinding, Model model) {
		if (rBinding.hasErrors()) {
			return "index";
		}
		boolean isSaved = studentService.saveStudent(student, model);
		if (isSaved) {
			model.addAttribute("msg", "Student saved successfully!");
		} else {
			model.addAttribute("msg1", "Some problem occurred");
		}
		return "index";
	}
	@GetMapping("/data")
	public String viewStudents(@RequestParam(defaultValue = "0") int page, 
	                           @RequestParam(defaultValue = "20") int size, 
	                           Model model) {
	    Pageable pageable = (Pageable) PageRequest.of(page, size);
	    Page<Student> studentsPage = studentService.getAllStudents(pageable);
	    
	    model.addAttribute("students", studentsPage.getContent());
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", studentsPage.getTotalPages());
	    model.addAttribute("pageSize", size);
	    
	    return "data";
	}


	@GetMapping("/delete")
	public String deleteStudent(@RequestParam Integer sid) {
		studentService.deleteStudent(sid);
		return "redirect:/data";
	}

	@GetMapping("/update")
	public String showUpdateForm(@RequestParam("sid") Integer sid, Model model) {
		 
		Student student = studentService.getStudentById(sid);
		model.addAttribute("student", student);
		return "Update";
	}

	@PostMapping("/update")
	public String updateStudent(@Valid @ModelAttribute("student") Student student, BindingResult rBinding,
			RedirectAttributes redirectAttributes) {

		if (rBinding.hasErrors()) {
			return "Update";
		}
		studentService.updateStudent(student);
		redirectAttributes.addFlashAttribute("msg", "Student updated successfully!");
		return "redirect:/data";
	}
}
