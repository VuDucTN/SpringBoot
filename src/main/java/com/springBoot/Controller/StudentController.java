package com.springBoot.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springBoot.Model.Student;
import com.springBoot.Service.StudentServiceImpl;

@Controller
@RequestMapping("/student")
public class StudentController {
	@Autowired
	private StudentServiceImpl studentServiceImpl;
	
	@GetMapping("/addStudent")
	public String getFormAdd(@ModelAttribute("student") Student student) {
		return "AddFormStudent";
	}
	
	@PostMapping("/addStudent")
	public String saveStudent(@ModelAttribute("student") Student student, Model model) {
		studentServiceImpl.saveStudent(student);
		model.addAttribute("message", "Add Student Successfully");
		return "AddFormStudent";
	}
}
