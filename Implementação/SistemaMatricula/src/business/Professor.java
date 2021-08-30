package business;

public class Professor extends Usuario {
	private Long cod;
	
	public Professor(String nome, String cpf, String email, String senha, Long cod) {
		setNome(nome);
		setCpf(cpf);
		setEmail(email);
		setSenha(senha);
		setCod(cod);
	}

	public long getCod() {
		return cod;
	}

	public void setCod(Long cod) {
		this.cod = cod;
	}
	
	@Override
	public String toString() {
		return "Nome: " + getNome() + 
				"  	CPF: " + getCpf() + 
				"   Email: " + getEmail() + 
				"   Codigo: " + cod ;
	}
}
