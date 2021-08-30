package business;

import java.io.Serializable;

public class Aluno extends Usuario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long matricula;
	
	public Aluno(String nome, String cpf, String email, String senha, Long matricula) {
		setNome(nome);
		setCpf(cpf);
		setEmail(email);
		setSenha(senha);
		setMatricula(matricula);
	}

	public long getMatricula() {
		return matricula;
	}

	public void setMatricula(Long matricula) {
		this.matricula = matricula;
	}
	
	@Override
	public String toString() {
		return "Nome: " + getNome() + 
				"  	CPF: " + getCpf() + 
				"   Email: " + getEmail() + 
				"   Matricula: " + matricula ;
	}

}
