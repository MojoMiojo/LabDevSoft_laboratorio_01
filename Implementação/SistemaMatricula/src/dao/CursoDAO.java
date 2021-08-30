package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import business.Curso;

public  class CursoDAO implements DAO<Curso, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	

	public CursoDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Curso get(String chave) {
		Curso curso = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				curso = (Curso) inputFile.readObject();

				Long id = curso.getId();
				if (chave.equals(String.valueOf(id))) {
					return curso;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o curso '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Curso curso) {
		try {
			outputFile.writeObject(curso);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o curso '" + curso.getNome() + "' no disco!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Curso curso) {
		List<Curso> cursos = getAll();
		int index = cursos.indexOf(curso);
		if (index != -1) {
			cursos.set(index, curso);
		}
		saveToFile(cursos);
		
	}

	@Override
	public void remove(Curso curso) {
		List<Curso> cursos = getAll();
		int index = cursos.indexOf(curso);
		if (index != -1) {
			cursos.remove(index);
		}
		saveToFile(cursos);
	}

	@Override
	public List<Curso> getAll() {
		List<Curso> cursos = new ArrayList<Curso>();
		Curso curso = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				curso = (Curso) inputFile.readObject();
				cursos.add(curso);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar curso no disco!");
			e.printStackTrace();
		}
		return cursos;
	}

	private void saveToFile(List<Curso> cursos) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Curso curso : cursos) {
				outputFile.writeObject(curso);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar curso no disco!");
			e.printStackTrace();
		}
	}
	
	private void close() throws IOException {
		outputFile.close();
		fos.close();
	}

	@Override
	protected void finalize() throws Throwable {
		this.close();
	}
}
