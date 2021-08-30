package app;
import java.util.Scanner;
import java.io.IOException;
import java.util.List;

import dao.DAO;
import business.Aluno;
import business.Curso;
import business.Disciplina;
import business.Matricula;
import business.Oferta;
import business.Professor;

import dao.AlunoDAO;
import dao.CursoDAO;
import dao.DisciplinaDAO;
import dao.MatriculaDAO;
import dao.OfertaDAO;
import dao.ProfessorDAO;

public class Aplication {
	
	public static void main(String args[]) throws IOException {	
		DAO<Aluno, String> alunoDAO = new AlunoDAO("alunos.bin");
		DAO<Curso, String> cursoDAO = new CursoDAO("cursos.bin");
		DAO<Disciplina, String> disciplinaDAO = new DisciplinaDAO("disciplinas.bin");
		DAO<Matricula, String> matriculaDAO = new MatriculaDAO("matriculas.bin");
		DAO<Oferta, String> ofertaDAO = new OfertaDAO("ofertas.bin");
		DAO<Professor, String> professorDAO = new ProfessorDAO("professores.bin");
        
		Scanner sc = new Scanner(System.in);
		
		String nome, cpf, email, senha;
		Long codigo;
		
		int op = 0;
		menu(0);
		while (op != 100) {		
			
			op = sc.nextInt();
			switch (op) {
			case 1:
				menu(1);
				break;			
				
			case 2:
				menu(2);
				break;
			case 3:
				menu(3);
				break;	
			case 4:
				menu(4);
				break;
			case 5:
				menu(5);
				break;
			case 6:
				menu(6);
				break;
				
			case 7:
				System.out.print("Digite seu nome: ");
				sc.nextLine();
				nome = sc.nextLine();
				System.out.print("Digite seu cpf: ");
				cpf = sc.nextLine();
				System.out.print("Digite seu email: ");
				email = sc.nextLine();
				System.out.print("Digite sua senha: ");
				senha = sc.nextLine();
				System.out.print("Digite sua matrícula: ");
				Long matricula = (long) sc.nextInt();
				alunoDAO.add(new Aluno(nome, cpf, email, senha, matricula));
				
				menu(0);
				break;
			case 8:
				List<Aluno> alunos = alunoDAO.getAll();
				for (Aluno aluno: alunos) {
					System.out.println(aluno);
				}
				menu(0);
				break;
			case 9 :
				System.out.print("Digite seu nome: ");
				sc.nextLine();
				nome = sc.nextLine();
				System.out.print("Digite seu cpf: ");
				cpf = sc.nextLine();
				System.out.print("Digite seu email: ");
				email = sc.nextLine();
				System.out.print("Digite sua senha: ");
				senha = sc.nextLine();
				System.out.print("Digite sua codigo: ");
				Long cod = (long) sc.nextInt();
				professorDAO.add(new Professor(nome, cpf, email, senha, cod));
				
				menu(0);
				break;
			case 10 :
				List<Professor> professores = professorDAO.getAll();
				for (Professor professor: professores) {
					System.out.println(professor);
				}
				menu(0);
				break;
			case 11 :
				System.out.print("Digite o nome: ");
				sc.nextLine();
				nome = sc.nextLine();
				System.out.print("Digite o ID: ");
				Long id = (long) sc.nextInt();
				System.out.print("Digite a quantidade de creditos: ");
				int credito = sc.nextInt();
				cursoDAO.add(new Curso(id, credito, nome));
				
				menu(0);
				break;
			case 12 :
				List<Curso> cursos = cursoDAO.getAll();
				for (Curso curso: cursos) {
					System.out.println(curso);
				}
				menu(0);
				break;
			case 13 :
				System.out.print("Digite o nome: ");
				sc.nextLine();
				nome = sc.nextLine();
				System.out.print("Digite a carga horaria: ");
				String carga = sc.nextLine();
				System.out.print("Digite o código do curso: ");
				Long codigoCurso = (long) sc.nextInt();
				System.out.print("Digite o codigo: ");
				codigo = (long) sc.nextInt();
				disciplinaDAO.add(new Disciplina(codigo, codigoCurso, nome, carga));
				
				menu(0);
				break;
			case 14 :
				List<Disciplina> disciplinas = disciplinaDAO.getAll();
				for (Disciplina disciplina: disciplinas) {
					System.out.println(disciplina);
				}
				menu(0);
				break;
			case 15 :
				System.out.print("Digite o código da disciplina: ");
				Long codigoDisciplina = (long) sc.nextInt();
				System.out.print("Digite o código do professor: ");
				Long codigoProfessor = (long) sc.nextInt();
				System.out.print("Digite o codigo: ");
				codigo = (long) sc.nextInt();
				ofertaDAO.add(new Oferta(codigo, codigoDisciplina, codigoProfessor, true));
				
				menu(0);
				break;
			case 16 :
				List<Oferta> ofertas = ofertaDAO.getAll();
				for (Oferta oferta: ofertas) {
					System.out.println(oferta);
				}
				menu(0);
				break;
			default:
				System.out.println("Opção inválida");
				menu(0);
				break;
			case 100:
				break;
		
			}
		}
		
		
		
		
		System.out.println("Fim do programa");
		sc.close();		
		
	}
	
	public static void menu(int param) {
		if(param == 0) {
			System.out.println("Menu de Opções:");
			System.out.println("1 – Aluno");
			System.out.println("2 – Professor");
			System.out.println("3 – Curso");
			System.out.println("4 – Diciplina");
			System.out.println("5 – Oferta");
			System.out.println("6 – Matricula");
			
		}
		if(param == 1) {
			System.out.println("Menu de Opções do aluno:");
			System.out.println("7 - Cadastrar");
			System.out.println("8 - Listar");
		}
		if(param == 2) {
			System.out.println("Menu de Opções do Professor:");
			System.out.println("9 - Cadastrar");
			System.out.println("10 - Listar");
		}
		if(param == 3) {
			System.out.println("Menu de Opções do Curso:");
			System.out.println("11 - Cadastrar");
			System.out.println("12 - Listar");
		}
		if(param == 4) {
			System.out.println("Menu de Opções da Diciplina:");
			System.out.println("13 - Cadastrar");
			System.out.println("14 - Listar");
		}
		if(param == 5) {
			System.out.println("Menu de Opções da Oferta:");
			System.out.println("15 - Cadastrar");
			System.out.println("16 - Listar");
		}
		if(param == 6) {
			System.out.println("Menu de Opções da Matricula:");
			System.out.println("To Do");
		}
		System.out.println("100 - Sair");
	}
	
	
	
	
}