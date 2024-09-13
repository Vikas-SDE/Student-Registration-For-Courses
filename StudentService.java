package in.ashokit.service;

import java.io.File;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import in.ashokit.controller.EmployeeDeatails;
import in.ashokit.entity.Student;
import in.ashokit.repo.StudentRepo;

@Service
public class StudentService {

	@Autowired
	private StudentRepo studentRepository;

	@Autowired
	private EmailSenderService emailSenderService;

	public boolean saveStudent(Student student, Model m) {
		// Check if the email is already used
		if (studentRepository.findByEmail(student.getEmail()).isPresent()) {
			m.addAttribute("email", "Email already in use");
			return false;
		}

		// Save the student
		Student savedStudent = studentRepository.save(student);

		// Define the file to attach
		File interviewQuestions = new File("C:\\Users\\vikas vilas chavan\\Downloads\\JavaInterViewQuestion.pdf");

		// Send the email with attachment
		emailSenderService.sendSimpleMessage(student.getEmail(), "Welcome!",
				"Hello " + student.getName()
						+ ", welcome to the Java world! Attached are some interview questions practice this.",
				interviewQuestions);

		return savedStudent.getSid() != null;
	}

	public Page<Student> getAllStudents(Pageable pageable) {
		return studentRepository.findAll(pageable);

	}

	public void deleteStudent(Integer sid) {
		studentRepository.deleteById(sid);
	}

	public void updateStudent(Student student) {
		studentRepository.save(student); // This will update the student if the ID already exists
	}

	public Student getStudentById(Integer sid) {
		Optional<Student> studentOptional = studentRepository.findById(sid);
		if (studentOptional.isPresent()) {
			return studentOptional.get();
		} else {
			throw new RuntimeException("Student not found for ID: " + sid);
		}
	}

}
