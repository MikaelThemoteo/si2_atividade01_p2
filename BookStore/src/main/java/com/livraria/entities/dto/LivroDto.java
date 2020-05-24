package com.livraria.entities.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.livraria.entities.Livro;

public class LivroDto {

	@NotEmpty(message = "Campo de preenchimento obrigatório.")
	@Size(min = 2, max = 60, message = "O tamanho deve ser entre 2 e 60 caracteres.")
	private String titulo;
	private Integer paginas;
	
	@JsonIgnore
	private String editora;

	public LivroDto(Livro livro) {
		this.titulo = livro.getTitulo();
		this.paginas = livro.getPaginas();
		this.editora = livro.getEditora().getNome();
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getPaginas() {
		return paginas;
	}

	public void setPaginas(Integer paginas) {
		this.paginas = paginas;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

}
