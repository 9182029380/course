package com.CodeWithSharath.CertificationCourses.mapper;

import com.CodeWithSharath.CertificationCourses.Entity.Courses;
import com.CodeWithSharath.CertificationCourses.dto.CoursesDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CoursesMapper extends GenericMapper<Courses, CoursesDTO> {
    @Override
    @Mapping(target = "id", ignore = false)
    Courses asEntity(CoursesDTO dto);
}