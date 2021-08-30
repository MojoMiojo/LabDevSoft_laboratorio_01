package dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


import business.Oferta;

public  class OfertaDAO implements DAO<Oferta, String> {
	private File file;
	private FileOutputStream fos;
	private ObjectOutputStream outputFile;
	

	public OfertaDAO(String filename) throws IOException {
		file = new File(filename);
		if (file.exists())
			file.delete();
		fos = new FileOutputStream(file, false); 
		outputFile = new ObjectOutputStream(fos);
	}
	
	
	@Override
	public Oferta get(String chave) {
		Oferta oferta = null;

		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {
			while (fis.available() > 0) {
				oferta = (Oferta) inputFile.readObject();

				Long codigo = oferta.getCodigo();
				if (chave.equals(String.valueOf(codigo))) {
					return oferta;
				}
			}
		} catch (Exception e) {
			System.out.println("ERRO ao ler o oferta '" + chave + "' do disco!");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void add(Oferta oferta) {
		try {
			outputFile.writeObject(oferta);
		} catch (Exception e) {
			System.out.println("ERRO ao gravar o oferta '" + oferta.getCodigo() + "' no disco!");
			e.printStackTrace();
		}
		
	}

	@Override
	public void update(Oferta oferta) {
		List<Oferta> ofertas = getAll();
		int index = ofertas.indexOf(oferta);
		if (index != -1) {
			ofertas.set(index, oferta);
		}
		saveToFile(ofertas);
		
	}

	@Override
	public void remove(Oferta oferta) {
		List<Oferta> ofertas = getAll();
		int index = ofertas.indexOf(oferta);
		if (index != -1) {
			ofertas.remove(index);
		}
		saveToFile(ofertas);
	}

	@Override
	public List<Oferta> getAll() {
		List<Oferta> ofertas = new ArrayList<Oferta>();
		Oferta oferta = null;
		try (FileInputStream fis = new FileInputStream(file); ObjectInputStream inputFile = new ObjectInputStream(fis)) {

			while (fis.available() > 0) {
				oferta = (Oferta) inputFile.readObject();
				ofertas.add(oferta);
			}
		} catch (Exception e) {
			System.out.println("ERRO ao gravar oferta no disco!");
			e.printStackTrace();
		}
		return ofertas;
	}

	private void saveToFile(List<Oferta> ofertas) {
		try {
			close();
			fos = new FileOutputStream(file, false); 
			outputFile = new ObjectOutputStream(fos);

			for (Oferta oferta : ofertas) {
				outputFile.writeObject(oferta);
			}
			outputFile.flush();
		} catch (Exception e) {
			System.out.println("ERRO ao gravar oferta no disco!");
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
