package com.projetomatricula.model.aluno;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "alunos")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Aluno{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long matricula;

    @Column(nullable = false)
    private String nome;
}