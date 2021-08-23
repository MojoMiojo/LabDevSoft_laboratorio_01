package com.projetomatricula.controller.validator;

import com.projetomatricula.model.aluno.Aluno;
import com.projetomatricula.model.professor.Professor;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ProfessorValidator implements Validator {

    @Autowired
    public ProfessorValidator() {

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Aluno.class.equals(aClass);
    }

    @SneakyThrows
    @Override
    public void validate(Object o, Errors errors) {
        Professor aluno = (Professor) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "name is required");
    }
}