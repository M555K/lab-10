package com.company.controller;

import com.company.dto.ResponseWrapper;
import com.company.dto.StudentDTO;
import com.company.service.impl.StudentServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")//"/students"
public class StudentController {
    private final StudentServiceImpl studentService;

    public StudentController(StudentServiceImpl studentService) {
        this.studentService = studentService;
    }
    /*
           Endpoint: /api/v1/student
           HTTP Status Code: 200
           JSON Response Body:
           "success": true
           "message": "Students are successfully retrieved."
           "code":200
           "data":<students data>
     */
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllStudents() {
        ResponseWrapper response = ResponseWrapper.builder()
                .success(true)
                .message("Students are successfully retrieved.")
                .code(HttpStatus.OK.value())
                .data(studentService.findAll()).build();
        return ResponseEntity.ok(response);
    }
    /*
          Endpoint: /api/v1/student
          HTTP Status Code: 201

          JSON Response Body:
          "success": true
          "message": "Student is successfully created."
          "code":201
          "data":<created student data>
    */
    @PostMapping
    private ResponseEntity<ResponseWrapper> createStudent(@RequestBody StudentDTO studentDTO){
       StudentDTO createdStudent= studentService.createStudent(studentDTO);
        ResponseWrapper student =  ResponseWrapper.builder()
                .message("Student is successfully created.")
                .code(HttpStatus.CREATED.value())
                .data(createdStudent).build();
        return ResponseEntity.ok(student);
    }

}
