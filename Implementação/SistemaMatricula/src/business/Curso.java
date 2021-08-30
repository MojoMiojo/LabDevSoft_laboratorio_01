package business;

public class Curso {
	private Long id;
	private int creditos;
	private String nome;
	
	public Curso(Long id, int creditos, String nome) {
		setId(id);
		setCreditos(creditos);
		setNome(nome);
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public int getCreditos() {
		return creditos;
	}
	
	public void setCreditos(int creditos) {
		this.creditos = creditos;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	@Override
	public String toString() {
		return "ID: " + id + 
				"  	Creditos: " + creditos + 
				"   Nome: " + nome;
	}
	
}
