package com.CodeWithSharath.CertificationCourses.service.impl;

import com.CodeWithSharath.CertificationCourses.Entity.Courses;
import com.CodeWithSharath.CertificationCourses.dao.CoursesRepository;
import com.CodeWithSharath.CertificationCourses.dto.CoursesDTO;
import com.CodeWithSharath.CertificationCourses.mapper.CoursesMapper;
import com.CodeWithSharath.CertificationCourses.service.CoursesService;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CoursesServiceImpl implements CoursesService {
    private final CoursesRepository repository;

    public CoursesServiceImpl(CoursesRepository repository) {
        this.repository = repository;
    }

    @Override
    public Courses save(Courses entity) {
        return repository.save(entity);
    }

    @Override
    public List<Courses> save(List<Courses> entities) {
        return (List<Courses>) repository.saveAll(entities);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Courses> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Courses> findAll() {
        return (List<Courses>) repository.findAll();
    }

    @Override
    public Page<Courses> findAll(Pageable pageable) {
        Page<Courses> entityPage = repository.findAll(pageable);
        List<Courses> entities = entityPage.getContent();
        return new PageImpl<>(entities, pageable, entityPage.getTotalElements());
    }

    @Override
    public Courses update(Courses entity, Long id) {
        Optional<Courses> optional = findById(id) ;
        if (optional.isPresent()) {
            return save(entity);
        }
        return null;
    }
}