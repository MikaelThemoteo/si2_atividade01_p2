package com.livraria.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.livraria.entities.Livro;
import com.livraria.entities.dto.AtualLivroDto;
import com.livraria.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {
	
	@Autowired
	private LivroService service;
		
	@GetMapping
	public ResponseEntity<List<Livro>> findAll(){
		List<Livro> list = service.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Livro> findById(@PathVariable("id") Long id){
		Livro livro = service.findById(id);
		return new ResponseEntity<>(livro, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Livro> save(@Valid @RequestBody AtualLivroDto livro){
		
		Livro newLivro = service.fromDTO(livro);
		
		newLivro = service.save(newLivro);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newLivro.getId()).toUri();
		return ResponseEntity.created(uri).body(newLivro);
	}	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Livro> update(@PathVariable Long id, @RequestBody AtualLivroDto livro){
		Livro newLivro = service.fromDTO(livro);
		newLivro.setId(id);
		newLivro = service.update(newLivro);
		return new ResponseEntity<>(newLivro, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Livro> delete(@PathVariable Long id){
		service.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
