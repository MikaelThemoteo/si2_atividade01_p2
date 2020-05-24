package com.livraria.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.livraria.entities.Editora;
import com.livraria.repositories.EditoraRepository;
import com.livraria.services.exceptions.DatabaseException;
import com.livraria.services.exceptions.ResourceNotFoundException;

@Service
public class EditoraService {
	
	@Autowired
	private EditoraRepository editoraRepository;
	
	public List<Editora> findAll(){
		return editoraRepository.findAll();
	}
	
	public Editora findById(Long id) {
		Optional<Editora> editora = editoraRepository.findById(id);
		return editora.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public Editora save(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	public Editora update(Editora editora) {
		return editoraRepository.save(editora);
	}
	
	public void delete(Long id) {
		try {
			editoraRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
