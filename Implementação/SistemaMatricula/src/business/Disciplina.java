package business;

public class Disciplina {
	private Long codigo;
	private Long codigoCurso;
	private String nome;
	private String cargaHoraria;
	
	
	public Disciplina(Long codigo, Long codigoCurso, String nome, String cargaHoraria) {
		this.codigo = codigo;
		this.codigoCurso = codigoCurso;
		this.nome = nome;
		this.cargaHoraria = cargaHoraria;
	}

	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(String cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Long getCodigoCurso() {
		return codigoCurso;
	}

	public void setCodigoCurso(Long codigoCurso) {
		this.codigoCurso = codigoCurso;
	}
	
	@Override
	public String toString() {
		return "Código: " + codigo + 
				"  	Codigo Curso: " + codigoCurso + 
				"   Nome: " + nome +
				"   Carga Horaria:" + cargaHoraria;
	}

}
