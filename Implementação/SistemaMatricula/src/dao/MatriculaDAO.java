package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import business.Matricula;

public  class MatriculaDAO implements DAO<Matricula, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	

	public MatriculaDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Matricula get(String chave) {
		Matricula matricula = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				matricula = (Matricula) inputFile.readObject();

				Long codigo = matricula.getCodigo();
				if (chave.equals(String.valueOf(codigo))) {
					return matricula;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o matricula '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Matricula matricula) {
		try {
			outputFile.writeObject(matricula);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o matricula '" + matricula.getCodigo() + "' no disco!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Matricula matricula) {
		List<Matricula> matriculas = getAll();
		int index = matriculas.indexOf(matricula);
		if (index != -1) {
			matriculas.set(index, matricula);
		}
		saveToFile(matriculas);
		
	}

	@Override
	public void remove(Matricula matricula) {
		List<Matricula> matriculas = getAll();
		int index = matriculas.indexOf(matricula);
		if (index != -1) {
			matriculas.remove(index);
		}
		saveToFile(matriculas);
	}

	@Override
	public List<Matricula> getAll() {
		List<Matricula> matriculas = new ArrayList<Matricula>();
		Matricula matricula = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				matricula = (Matricula) inputFile.readObject();
				matriculas.add(matricula);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar matricula no disco!");
			e.printStackTrace();
		}
		return matriculas;
	}

	private void saveToFile(List<Matricula> matriculas) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Matricula matricula : matriculas) {
				outputFile.writeObject(matricula);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar matricula no disco!");
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
