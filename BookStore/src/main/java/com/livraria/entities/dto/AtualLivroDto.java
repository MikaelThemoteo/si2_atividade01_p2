package com.livraria.entities.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AtualLivroDto {

	@NotEmpty(message = "Campo de preenchimento obrigatório.")
	@Size(min = 3, max = 30, message = "O tamanho deve ser entre 3 e 30 caracteres.")
	private String titulo;
	
	@NotNull(message = "Campo de preenchimento obrigatório.")
	@Min(value = 1, message = "Numero de página no minimo 1")
	@Max(value = 99999)
	private Integer paginas;
	
	@NotNull(message = "Campo de preenchimento obrigatório.")
	private Long editora;
 
	public AtualLivroDto() {
		super();
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

	public Long getEditora() {
		return editora;
	}

	public void setEditora(Long editora) {
		this.editora = editora;
	}
	

}
