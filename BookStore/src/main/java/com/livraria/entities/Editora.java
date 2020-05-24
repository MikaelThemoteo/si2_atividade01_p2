package com.livraria.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livraria.entities.dto.LivroDto;

@Entity
@Table(name = "editoras")
public class Editora implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(unique = true)
	private String nome;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@JsonIgnore
	@OneToMany(mappedBy = "editora", cascade = CascadeType.ALL)
	private List<Livro> livros = new ArrayList<Livro>();
	
	public Editora() {
		super();
	}
	
	public Editora(String nome) {
		super();
		this.nome = nome;
	}

	public List<Livro> getLivros() {
		return livros;
	}
	
	public List<LivroDto> getLivrosPublicados() {

		List<LivroDto> dtos = new ArrayList<>();
		
		for(Livro l: livros) {
			dtos.add(new LivroDto(l));
		}
		
		return dtos;
	}

	public void setLivros(List<Livro> livro) {
		this.livros = livro;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nome);
	}

	@Override
	public boolean equals(Object obj) {
		return Objects.equals(this, obj);
	}
}