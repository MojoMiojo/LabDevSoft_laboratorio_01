package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import business.Aluno;

public  class AlunoDAO implements DAO<Aluno, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	

	public AlunoDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Aluno get(String chave) {
		Aluno aluno = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				aluno = (Aluno) inputFile.readObject();
				Long matricula = aluno.getMatricula();
				if (chave.equals(String.valueOf(matricula))) {
					return aluno;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o aluno '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Aluno aluno) {
		try {
			outputFile.writeObject(aluno);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o aluno '" + aluno.getNome() + "' no disco!"+e);
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Aluno aluno) {
		List<Aluno> alunos = getAll();
		int index = alunos.indexOf(aluno);
		if (index != -1) {
			alunos.set(index, aluno);
		}
		saveToFile(alunos);
		
	}

	@Override
	public void remove(Aluno aluno) {
		List<Aluno> alunos = getAll();
		int index = alunos.indexOf(aluno);
		if (index != -1) {
			alunos.remove(index);
		}
		saveToFile(alunos);
	}

	@Override
	public List<Aluno> getAll() {
		List<Aluno> alunos = new ArrayList<Aluno>();
		Aluno aluno = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				aluno = (Aluno) inputFile.readObject();
				alunos.add(aluno);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar aluno no disco!");
			e.printStackTrace();
		}
		return alunos;
	}

	private void saveToFile(List<Aluno> alunos) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Aluno aluno : alunos) {
				outputFile.writeObject(aluno);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar aluno no disco!");
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
