package com.projetomatricula.controller.impl;

import com.projetomatricula.controller.BaseController;
import com.projetomatricula.controller.validator.AlunoValidator;
import com.projetomatricula.controller.validator.ProfessorValidator;
import com.projetomatricula.model.aluno.Aluno;
import com.projetomatricula.model.error.ApplicationError;
import com.projetomatricula.model.professor.Professor;
import com.projetomatricula.service.impl.professor.ProfessorService;
import com.sun.istack.Nullable;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/professor")
public class ProfessorController extends BaseController<Professor> {

    private final ProfessorValidator professorValidator;

    @Autowired
    public ProfessorController(ProfessorService service, ProfessorValidator professorValidator) {
        super(service);
        this.professorValidator = professorValidator;
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody @NonNull Professor body, @Nullable BindingResult result) {
        professorValidator.validate(body, result);
        Optional<ApplicationError> erros = ApplicationError.verify(result);

        if(erros.isPresent()){
            return ResponseEntity.badRequest().body(erros);
        }

        Optional<Professor> entity = service.save(body);

        return entity
                .map((x) -> ResponseEntity.ok((Object) x))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Object> list() {

        return  ResponseEntity.ok(service.findAll());
    }
}
