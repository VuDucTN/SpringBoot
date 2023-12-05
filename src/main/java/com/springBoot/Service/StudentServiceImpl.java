package com.springBoot.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springBoot.Model.Student;
import com.springBoot.Repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	private StudentRepository studentRepository;

	@Override
	@Transactional
	public Student saveStudent(Student student) { 
		return  studentRepository.save(student);
	}

}
