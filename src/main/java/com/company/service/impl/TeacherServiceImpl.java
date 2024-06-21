package com.company.service.impl;

import com.company.dto.TeacherDTO;
import com.company.entity.Teacher;
import com.company.exception.AlreadyExistsException;
import com.company.exception.NotFoundException;
import com.company.repository.AddressRepository;
import com.company.repository.TeacherRepository;
import com.company.service.TeacherService;
import com.company.util.MapperUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final AddressRepository addressRepository;
    private final MapperUtil mapperUtil;

    public TeacherServiceImpl(TeacherRepository teacherRepository,
                              AddressRepository addressRepository,
                              MapperUtil mapperUtil) {
        this.teacherRepository = teacherRepository;
        this.addressRepository = addressRepository;
        this.mapperUtil = mapperUtil;
    }

    @Override
    public List<TeacherDTO> findAll() {
        return teacherRepository
                .findAll()
                .stream()
                .map(teacher -> mapperUtil.convert(teacher, new TeacherDTO()))
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDTO findByUsername(String username) {
        Teacher foundTeacher = teacherRepository.findByUsername(username)
                .orElseThrow(() -> new NotFoundException("Teacher not found!"));
        return mapperUtil.convert(foundTeacher, new TeacherDTO());
    }

    @Override
    public TeacherDTO createTeacher(TeacherDTO teacherDTO) {

        Optional<Teacher> foundTeacher = teacherRepository.findByUsername(teacherDTO.getUsername());

        if (foundTeacher.isPresent()) {
            throw new AlreadyExistsException("Teacher already exists!");
        }

        addressRepository.findByAddressNo(teacherDTO.getAddressNo())
                .orElseThrow(() -> new NotFoundException("Address not found!"));

        Teacher teacherToSave = mapperUtil.convert(teacherDTO, new Teacher());
        Teacher savedTeacher = teacherRepository.save(teacherToSave);

        return mapperUtil.convert(savedTeacher, new TeacherDTO());

    }

}
