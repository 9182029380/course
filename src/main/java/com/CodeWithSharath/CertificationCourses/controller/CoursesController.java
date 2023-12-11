package com.CodeWithSharath.CertificationCourses.controller;

import com.CodeWithSharath.CertificationCourses.dto.CoursesDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Api(tags = "Courses API")
public interface CoursesController {
    @ApiOperation("Add new data")
    public CoursesDTO save(@RequestBody CoursesDTO courses);

    @ApiOperation("Find by Id")
    public CoursesDTO findById(@PathVariable("id") Long id);

    @ApiOperation("Delete based on primary key")
    public void delete(@PathVariable("id") Long id);

    @ApiOperation("Find all data")
    public List<CoursesDTO> list();

    @ApiOperation("Pagination request")
    public Page<CoursesDTO> pageQuery(Pageable pageable);

    @ApiOperation("Update one data")
    public CoursesDTO update(@RequestBody CoursesDTO dto, @PathVariable("id") Long id);
}