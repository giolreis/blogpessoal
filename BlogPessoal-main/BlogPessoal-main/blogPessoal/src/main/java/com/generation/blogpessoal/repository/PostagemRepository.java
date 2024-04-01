package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.blogpessoal.model.Postagem;

//Repositório
@Repository

//A interface PostagemRepository estende JpaRepository, que é uma interface do Spring Data JPA
//Isso fornece métodos para operações CRUD (Create, Read, Update, Delete) e permite a criação de consultas personalizadas
public interface PostagemRepository extends JpaRepository<Postagem, Long>{

	//Método personalizado para buscar todas as postagens cujo título contém um texto específico, ignorando a diferença entre maiúsculas e minúsculas
	//A anotação @Param é usada para vincular o parâmetro da consulta ao método
	public List<Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

}
