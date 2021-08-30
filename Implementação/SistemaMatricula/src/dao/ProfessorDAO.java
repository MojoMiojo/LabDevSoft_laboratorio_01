package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import business.Professor;

public  class ProfessorDAO implements DAO<Professor, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	

	public ProfessorDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Professor get(String chave) {
		Professor professor = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				professor = (Professor) inputFile.readObject();

				Long cod = professor.getCod();
				if (chave.equals(String.valueOf(cod))) {
					return professor;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o professor '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Professor professor) {
		try {
			outputFile.writeObject(professor);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o professor '" + professor.getNome() + "' no disco!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Professor professor) {
		List<Professor> professores = getAll();
		int index = professores.indexOf(professor);
		if (index != -1) {
			professores.set(index, professor);
		}
		saveToFile(professores);
		
	}

	@Override
	public void remove(Professor professor) {
		List<Professor> professores = getAll();
		int index = professores.indexOf(professor);
		if (index != -1) {
			professores.remove(index);
		}
		saveToFile(professores);
	}

	@Override
	public List<Professor> getAll() {
		List<Professor> professores = new ArrayList<Professor>();
		Professor professor = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				professor = (Professor) inputFile.readObject();
				professores.add(professor);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar professor no disco!");
			e.printStackTrace();
		}
		return professores;
	}

	private void saveToFile(List<Professor> professores) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Professor professor : professores) {
				outputFile.writeObject(professor);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar professor no disco!");
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
