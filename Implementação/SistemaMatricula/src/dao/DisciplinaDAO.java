package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import business.Disciplina;

public  class DisciplinaDAO implements DAO<Disciplina, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	

	public DisciplinaDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Disciplina get(String chave) {
		Disciplina disciplina = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				disciplina = (Disciplina) inputFile.readObject();

				Long codigo = disciplina.getCodigo();
				if (chave.equals(String.valueOf(codigo))) {
					return disciplina;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o disciplina '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Disciplina disciplina) {
		try {
			outputFile.writeObject(disciplina);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o disciplina '" + disciplina.getNome() + "' no disco!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Disciplina disciplina) {
		List<Disciplina> disciplinas = getAll();
		int index = disciplinas.indexOf(disciplina);
		if (index != -1) {
			disciplinas.set(index, disciplina);
		}
		saveToFile(disciplinas);
		
	}

	@Override
	public void remove(Disciplina disciplina) {
		List<Disciplina> disciplinas = getAll();
		int index = disciplinas.indexOf(disciplina);
		if (index != -1) {
			disciplinas.remove(index);
		}
		saveToFile(disciplinas);
	}

	@Override
	public List<Disciplina> getAll() {
		List<Disciplina> disciplinas = new ArrayList<Disciplina>();
		Disciplina disciplina = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				disciplina = (Disciplina) inputFile.readObject();
				disciplinas.add(disciplina);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar disciplina no disco!");
			e.printStackTrace();
		}
		return disciplinas;
	}

	private void saveToFile(List<Disciplina> disciplinas) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Disciplina disciplina : disciplinas) {
				outputFile.writeObject(disciplina);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar disciplina no disco!");
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
