package com.company.controller;

import com.company.dto.ResponseWrapper;
import com.company.dto.TeacherDTO;
import com.company.service.TeacherService;
import com.company.service.impl.TeacherServiceImpl;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    private final TeacherServiceImpl teacherService;

    public TeacherController(TeacherServiceImpl teacherService) {
        this.teacherService = teacherService;
    }

    /*
           Endpoint: /api/v1/teacher
           JSON Response Body:
           <teachers data>
        */
    @GetMapping
    public ResponseEntity<ResponseWrapper> getAllTeachers() {
        ResponseWrapper response = ResponseWrapper.builder()
                .data(teacherService.findAll()).build();
        return ResponseEntity.ok(response);
    }

        /*
           Endpoint: /api/v1/teacher/{username}
           HTTP Status Code: 200

           JSON Response Body:
           "success": true
           "message": "Teacher is successfully retrieved."
           "code":200
           "data":<teacher data>
        */
        @GetMapping("/{username}")
        public ResponseEntity<ResponseWrapper> getTeacherByUsername(@PathVariable("username") String username) {
            ResponseWrapper responseWrapper = ResponseWrapper.builder()
                    .success(true)
                    .message("Teacher is successfully retrieved.")
                    .code(HttpStatus.OK.value())
                    .data(teacherService.findByUsername(username)).build();
            return ResponseEntity.ok(responseWrapper);
        }
       /*
           Endpoint: /api/v1/teacher
           HTTP Status Code: 201
           Custom Response Header: "teacherUsername", <created username>

           JSON Response Body:
           "success": true
           "message": "Teacher is successfully created."
           "code":201
           "data":<created teacher data>
     */
    @PostMapping()
    public ResponseEntity<ResponseWrapper> createTeacher( @RequestBody TeacherDTO teacherDTO){
        ResponseWrapper responseWrapper = ResponseWrapper.builder()
                .success(true)
                .message("Teacher is successfully retrieved.")
                .code(HttpStatus.CREATED.value())
                .data(teacherDTO).build();
        return ResponseEntity.status(HttpStatus.CREATED)
                .header("teacherUsername",teacherDTO.getUsername()).body(responseWrapper);
    }

}
