package com.CodeWithSharath.CertificationCourses.controller.impl;

import com.CodeWithSharath.CertificationCourses.Entity.Courses;
import com.CodeWithSharath.CertificationCourses.controller.CoursesController;
import com.CodeWithSharath.CertificationCourses.dto.CoursesDTO;
import com.CodeWithSharath.CertificationCourses.mapper.CoursesMapper;
import com.CodeWithSharath.CertificationCourses.service.CoursesService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequestMapping("/api/courses")
@RestController
public class CoursesControllerImpl implements CoursesController {
    private final CoursesService coursesService;
    private final CoursesMapper coursesMapper;

    public CoursesControllerImpl(CoursesService coursesService, CoursesMapper coursesMapper) {
        this.coursesService = coursesService;
        this.coursesMapper = coursesMapper;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CoursesDTO save(@RequestBody CoursesDTO coursesDTO) {
        Courses courses = coursesMapper.asEntity(coursesDTO);
        return coursesMapper.asDTO(coursesService.save(courses));
    }

    @Override
    @GetMapping("/{id}")
    public CoursesDTO findById(@PathVariable("id") Long id) {
        Courses courses = coursesService.findById(id).orElse(null);
        return coursesMapper.asDTO(courses);
    }

    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        coursesService.deleteById(id);
    }

    @Override
    @GetMapping
    public List<CoursesDTO> list() {
        return coursesMapper.asDTOList(coursesService.findAll());
    }

    @Override
    @GetMapping("/page-query")
    public Page<CoursesDTO> pageQuery(Pageable pageable) {
        Page<Courses> coursesPage = coursesService.findAll(pageable);
        List<CoursesDTO> dtoList = coursesPage
                .stream()
                .map(coursesMapper::asDTO).collect(Collectors.toList());
        return new PageImpl<>(dtoList, pageable, coursesPage.getTotalElements());
    }

    @Override
    @PutMapping("/{id}")
    public CoursesDTO update(@RequestBody CoursesDTO coursesDTO, @PathVariable("id") Long id) {
        Courses courses = coursesMapper.asEntity(coursesDTO);
        return coursesMapper.asDTO(coursesService.update(courses, id));
    }
}