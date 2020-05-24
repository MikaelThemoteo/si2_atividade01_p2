package com.livraria.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.livraria.entities.Editora;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long>{
	
	List<Editora> findByNome(String nome);
	
}
