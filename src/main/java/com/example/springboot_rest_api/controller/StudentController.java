package com.example.springboot_rest_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_rest_api.bean.Student;

@RestController
public class StudentController {

	@GetMapping("student")
	public Student getStudent() {
		Student student=new Student(1,"Mary","John");
		return student;
	}
	
	@GetMapping("/students-list")
	public List<Student> getListOfSudents(){
		List<Student> studentsList=new ArrayList<>();
		studentsList.add(new Student(1,"Mary","Comm"));
		studentsList.add(new Student(1,"Levis","Lee"));
		studentsList.add(new Student(1,"Maw","Lie"));
		studentsList.add(new Student(1,"Carvy","Joe"));
		studentsList.add(new Student(1,"Peter","Harry"));
		return studentsList;
	}
}
