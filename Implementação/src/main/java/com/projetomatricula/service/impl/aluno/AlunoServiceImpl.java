package com.projetomatricula.service.impl.aluno;

import com.projetomatricula.model.aluno.Aluno;
import com.projetomatricula.repository.impl.AlunoRepository;
import com.projetomatricula.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoServiceImpl extends BaseService<Aluno> implements AlunoService {

    @Autowired
    public AlunoServiceImpl(AlunoRepository repository) {
        super(repository);
    }
}
