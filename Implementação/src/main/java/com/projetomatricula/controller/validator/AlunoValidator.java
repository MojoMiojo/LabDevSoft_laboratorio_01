package com.projetomatricula.controller.validator;

import com.projetomatricula.model.aluno.Aluno;
import com.projetomatricula.model.error.ApplicationError;
import com.projetomatricula.model.error.BusinessException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import java.util.concurrent.ExecutionException;

@Component
public class AlunoValidator implements Validator {

    @Autowired
    public AlunoValidator() {

    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Aluno.class.equals(aClass);
    }

    @SneakyThrows
    @Override
    public void validate(Object o, Errors errors) {
        Aluno aluno = (Aluno) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nome", "name is required");
    }
}