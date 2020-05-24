package com.livraria.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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

import com.livraria.entities.Editora;
import com.livraria.entities.Livro;
import com.livraria.services.EditoraService;
import com.livraria.services.exceptions.DataIntegrityException;

@RestController
@RequestMapping(value = "/editoras")
public class EditoraResource {

	@Autowired
	private EditoraService editoraService;
	
	@GetMapping
	public ResponseEntity<List<Editora>> findAll(){
		List<Editora> list = editoraService.findAll();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping(path = "/{id}")
	public ResponseEntity<Editora> findById(@PathVariable("id") Long id){
		Editora editora = editoraService.findById(id);
		return new ResponseEntity<>(editora, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<Editora> save(@RequestBody Editora editora){
		
		try {
			
			editora = editoraService.save(editora);
			URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(editora.getId()).toUri();
			return ResponseEntity.created(uri).body(editora);
			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Editora já cadastrada.");
		}
	}	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Editora> update(@PathVariable Long id, @RequestBody Editora editora){
		editora = editoraService.update(editora);
		return new ResponseEntity<>(editora, HttpStatus.OK);
	}
	
	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Livro> delete(@PathVariable Long id){
		editoraService.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
