package com.projetomatricula.model.professor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "alunos")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Professor {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long matricula;

    @Column(nullable = false)
    private String nome;
}