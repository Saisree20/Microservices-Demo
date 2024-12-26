package com.example.springboot_rest_api.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.springboot_rest_api.bean.Student;

@RestController
@RequestMapping("students")
public class StudentController {

	@GetMapping("student")
	public ResponseEntity<Student> getStudent() {
		Student student=new Student(1,"Mary","John");
//		return new ResponseEntity<>(student, HttpStatus.OK);
//		return ResponseEntity.ok(student);
		return ResponseEntity.ok().header("custom-header","Harry").body(student);
	}
	
	@GetMapping("/students-list")
	public ResponseEntity<List<Student>> getListOfSudents(){
		List<Student> studentsList=new ArrayList<>();
		studentsList.add(new Student(1,"Mary","Comm"));
		studentsList.add(new Student(2,"Levis","Lee"));
		studentsList.add(new Student(3,"Maw","Lie"));
		studentsList.add(new Student(4,"Carvy","Joe"));
		studentsList.add(new Student(5,"Peter","Harry"));
		return ResponseEntity.ok(studentsList);
	}
	
//	Spring boot REST API with Path Variable
	
//	{id} -> URI template variable
   /**	@PathVariable annotation used on a method argument to
     *  bind it to the value of a URI template variable 
     */
	/**
	 * @PathVariable:- to bind the URI template variable with the method arguments
	 * @RequestParan:- to retrieve the query parameters from the URI
	 * */
	@GetMapping("/student/{id}/{first-name}/{last-name}")
	public ResponseEntity<Student> getStudentData(@PathVariable("id") int studentId,
			@PathVariable("first-name") String firstName,@PathVariable("last-name") String lastName) {
		Student stu= new Student(studentId,firstName,lastName);
		return ResponseEntity.ok(stu);
	}
	
//	Spring boot REST API with Request Param

	@GetMapping("/student/query")
	public ResponseEntity<Student> studentRequestVariable(@RequestParam int id,@RequestParam String firstName,@RequestParam String lastName) {
		Student stu= new Student(id,firstName,lastName);
		return ResponseEntity.ok(stu);
	}
	
//	Spring boot REST API that handles HTTP POST Request
//	PostMapping and @RequestBody
	
	/**
	 * @PostMapping:- It is used for mapping HTTP POST request onto specific handler method
	 * @RequestBody:- It is responsible for retrieving the HTTP request body and automatically converting it to the java object.
	 * It internally uses Spring provided HttpMessageConverter to convert JSON into java object
	 */
	
	@PostMapping("/student/create")
//	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Student> createStudent(@RequestBody Student student) {
		System.out.println("Id: "+student.getId());
		System.out.println("First Name: "+student.getFirstName());
		System.out.println("Last Name: "+student.getLastName());
		return new ResponseEntity<>(student,HttpStatus.CREATED);
	}
	
	@PutMapping("/student/{id}/update")
	public ResponseEntity<Student> updateStudent(@PathVariable int id,@RequestBody Student student) {
		student.setFirstName("new First Name");
		student.setId(id);
		System.out.println("Id: "+student.getId());
		System.out.println(" New First Name: "+student.getFirstName());
		System.out.println("Last Name: "+student.getLastName());
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	@DeleteMapping("/student/delete/{id}")
	public ResponseEntity<String> deleteStudent(@PathVariable("id") int studentId) {
		return ResponseEntity.ok("Student with student id: "+studentId+" deleted Successfully!");
	}
	
	/**
	 * 1. ResponseEntity represents the whole HTTP response: status code, headers, and body. As a result, we can use it to fully configure the HTTP response.
	 * 2. If we want to use it, we have to return it from the endpoint, spring takes case of the rest.
	 * 3. ResponseEntity is a generic type. Consequently, we can use any type as the response body.
	 */
	
}
