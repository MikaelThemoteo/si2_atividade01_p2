package com.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.livraria.entities.Editora;
import com.livraria.entities.Livro;
import com.livraria.entities.dto.AtualLivroDto;
import com.livraria.repositories.LivroRepository;
import com.livraria.services.exceptions.DataIntegrityException;
import com.livraria.services.exceptions.ResourceNotFoundException;

@Service
public class LivroService {

	@Autowired
	private LivroRepository repository;

	@Autowired
	private EditoraService editoraService;

	public List<Livro> findAll() {
		return repository.findAll();
	}

	public Livro findById(Long id) {
		Optional<Livro> livro = repository.findById(id);
		return livro.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Livro save(Livro livro) {
		Editora editora = editoraService.findById(livro.getEditora().getId());
		livro.setEditora(editora);
		Livro newLivro = repository.save(livro);

		editora.getLivros().add(newLivro);
		editoraService.update(editora);

		return newLivro;
	}

	public Livro update(Livro livro) {
		Livro entity = findById(livro.getId());
		updateData(entity, livro);
		return repository.save(entity);
	}

	private void updateData(Livro newObj, Livro obj) {
		newObj.setTitulo(obj.getTitulo());
		newObj.setEditora(obj.getEditora());
		newObj.setPaginas(obj.getPaginas());
		this.delete(obj.getId());
	}

	public void delete(Long id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir porque há Livros relacionados!");
		} 
	}

	public Livro fromDTO(AtualLivroDto dto) {
		Editora editora = editoraService.findById(dto.getEditora());
		Livro livro = new Livro(dto.getTitulo(), dto.getPaginas(), editora);
		return livro;
	}

}
