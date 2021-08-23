package com.projetomatricula.controller.impl;

import com.projetomatricula.controller.BaseController;
import com.projetomatricula.controller.validator.AlunoValidator;
import com.projetomatricula.model.aluno.Aluno;
import com.projetomatricula.model.error.ApplicationError;
import com.projetomatricula.service.impl.aluno.AlunoService;
import com.sun.istack.Nullable;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/aluno")
public class AlunoController extends BaseController<Aluno> {

    private final AlunoValidator alunoValidator;

    @Autowired
    public AlunoController(AlunoService service, AlunoValidator alunoValidator) {
        super(service);
        this.alunoValidator = alunoValidator;
    }

    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<Object> save(@RequestBody @NonNull Aluno body, @Nullable BindingResult result) {
        alunoValidator.validate(body, result);
        Optional<ApplicationError> erros = ApplicationError.verify(result);

        if(erros.isPresent()){
            return ResponseEntity.badRequest().body(erros);
        }

        Optional<Aluno> entity = service.save(body);

        return entity
                .map((x) -> ResponseEntity.ok((Object) x))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping(path = "", produces = "application/json")
    public ResponseEntity<Object> list() {

        return  ResponseEntity.ok(service.findAll());
    }
}
