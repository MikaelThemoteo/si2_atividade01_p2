package com.livraria;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.livraria.entities.Editora;
import com.livraria.entities.Livro;
import com.livraria.repositories.EditoraRepository;
import com.livraria.repositories.LivroRepository;

@SpringBootApplication

public class LivrariaApp implements CommandLineRunner{
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	@Autowired
	private LivroRepository livroRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(LivrariaApp.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Editora erica = new Editora("Editora Érica");
		Editora prenticeH = new Editora("Prentice Hall");
		Editora pearson = new Editora("Pearson");
		Editora ltc = new Editora("LTC");
		
		Livro livro1 = new Livro("Elementos de Eletrônica Digital", 524, erica);
		Livro livro2 = new Livro("Digital Logic & Computer Desing", 612, prenticeH);
		Livro livro3 = new Livro("Cálculo", 100, pearson);
		Livro livro4 = new Livro("Como Programar em C - Deitel", 486, ltc);
		Livro livro5 = new Livro("Como Programar em Java - Deitel", 900, ltc);
		Livro livro6 = new Livro("Como Programar em C# - Deitel", 620, ltc);
		
		erica.getLivros().addAll(Arrays.asList(livro1));
		prenticeH.getLivros().addAll(Arrays.asList(livro2));
		pearson.getLivros().addAll(Arrays.asList(livro3));
		ltc.getLivros().addAll(Arrays.asList(livro4, livro5, livro6));
		
		editoraRepository.saveAll(Arrays.asList(erica, prenticeH, pearson, ltc));
		livroRepository.saveAll(Arrays.asList(livro1, livro2, livro3, livro4, livro5, livro6));
	}

}