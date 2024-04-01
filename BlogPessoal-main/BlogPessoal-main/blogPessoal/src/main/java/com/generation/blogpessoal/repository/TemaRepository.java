package com.generation.blogpessoal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.generation.blogpessoal.model.Tema;

public interface TemaRepository extends JpaRepository<Tema, Long> {

	//Método personalizado para buscar todos os temas cuja descrição contém
	//uma string específica, ignorando a diferença entre maiúsculas e minúsculas
	//o uso de @Param("descricao") permite que o parâmetro seja nomeado, facilitando
	//a referência no código
	public List<Tema> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);

}
