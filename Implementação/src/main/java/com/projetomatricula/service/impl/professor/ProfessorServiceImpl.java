package com.projetomatricula.service.impl.professor;

import com.projetomatricula.model.professor.Professor;
import com.projetomatricula.repository.impl.ProfessorRepository;
import com.projetomatricula.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl extends BaseService<Professor> implements ProfessorService {

    @Autowired
    public ProfessorServiceImpl(ProfessorRepository repository) {
        super(repository);
    }
}
