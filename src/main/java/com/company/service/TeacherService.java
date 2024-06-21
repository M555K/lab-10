package com.company.service;

import com.company.dto.TeacherDTO;

import java.util.List;

public interface TeacherService {

    List<TeacherDTO> findAll();

    TeacherDTO findByUsername(String username);

    TeacherDTO createTeacher(TeacherDTO teacherDTO);

}
