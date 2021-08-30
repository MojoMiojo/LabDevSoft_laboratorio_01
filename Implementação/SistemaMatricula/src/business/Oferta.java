package business;

public class Oferta {
	private Long codigo;
	private Long codigoDisciplina;
	private Long codigoProfessor;
	private boolean ativa;
	
	
	public Oferta(Long codigo, Long codigoDisciplina, Long codigoProfessor, boolean ativa) {
		setCodigo(codigo);
		setCodigoDisciplina(codigoDisciplina);
		setCodigoProfessor(codigoProfessor);
		setAtiva(ativa);
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public Long getCodigoDisciplina() {
		return codigoDisciplina;
	}
	
	public void setCodigoDisciplina(Long codigoDisciplina) {
		this.codigoDisciplina = codigoDisciplina;
	}
	
	public Long getCodigoProfessor() {
		return codigoProfessor;
	}
	
	public void setCodigoProfessor(Long codigoProfessor) {
		this.codigoProfessor = codigoProfessor;
	}
	
	public boolean getAtiva() {
		return ativa;
	}
	
	public void setAtiva(boolean ativa) {
		this.ativa = ativa;
	}
	
	
	@Override
	public String toString() {
		return "Código: " + codigo + 
				"  	Código Disciplina: " + codigoDisciplina + 
				"   Nome: " + codigoProfessor +
				"   Ativa:" + (ativa ? "sim" : "não");
	}
}
