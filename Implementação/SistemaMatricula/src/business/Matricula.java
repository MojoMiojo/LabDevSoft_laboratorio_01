package business;

public class Matricula {
	private Long codigo;
	private Long codigoOferta;
	private Long matriculaAluno;
	
	public Matricula(Long codigoOferta, Long matriculaAluno) {
		setCodigoOferta(codigoOferta);
		setMatriculaAluno(matriculaAluno);
		setCodigo((codigoOferta+matriculaAluno));
	}
	
	public Long getCodigoOferta() {
		return codigoOferta;
	}
	
	public void setCodigoOferta(Long codigoOferta) {
		this.codigoOferta = codigoOferta;
	}
	
	public Long getMatriculaAluno() {
		return matriculaAluno;
	}
	
	public void setMatriculaAluno(Long matriculaAluno) {
		this.matriculaAluno = matriculaAluno;
	}
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	@Override
	public String toString() {
		return "Código Oferta: " + codigoOferta + 
				"  	Matricula Aluno: " + matriculaAluno;
	}

}
