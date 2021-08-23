package com.projetomatricula.repository.impl;

import com.projetomatricula.model.aluno.Aluno;
import com.projetomatricula.model.professor.Professor;
import com.projetomatricula.repository.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends BaseRepository<Professor> {
}
