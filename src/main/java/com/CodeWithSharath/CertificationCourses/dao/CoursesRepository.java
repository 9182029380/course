package com.CodeWithSharath.CertificationCourses.dao;

import com.CodeWithSharath.CertificationCourses.Entity.Courses;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends PagingAndSortingRepository<Courses, Long> {
}