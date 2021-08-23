package com.projetomatricula.model.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.Optional;

import static org.springframework.http.ResponseEntity.badRequest;

@AllArgsConstructor
@Getter
public class ApplicationError {
    private String mensagem;

    public static Optional<ApplicationError> verify(BindingResult result) {
        if (result.hasErrors()) {
            ObjectError validationError = result.getAllErrors().get(0);
            ApplicationError error = new ApplicationError(validationError.getCode());

            return Optional.ofNullable(error);
        }
        return Optional.empty();
    }
}
