package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/postagens") //Endpoint localhost:8080/postagens
@CrossOrigin(origins = "*", allowedHeaders = "*") //@CrossOrigin permite que requisições de origens diferentes sejam aceitas
public class PostagemController {

	//@Autowired injeta automaticamente uma instância de PostagemRepository
	@Autowired
	private PostagemRepository postagemRepository;

	//@GetMapping mapeia requisições GET para o método getAll - Rastreia todos os valores da tabela
	@GetMapping
	public ResponseEntity<List<Postagem>> getAll(){
		//Retorna todas as postagens como uma lista
		return ResponseEntity.ok(postagemRepository.findAll());
	}

	//Rastreia valores da tabela pelo ID
	@GetMapping("/{id}")
	public ResponseEntity<Postagem> getById(@PathVariable Long id){
		//Retorna a postagem com o ID especificado ou 404 se não encontrar
		return postagemRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	//Rastreia valores da tabela pelo título
	@GetMapping("titulo/{titulo}") //Endpoint localhost:8080/postagens/titulo/{titulo}
	public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo){
		//Retorna postagens cujo título contém o texto especificado
		return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
	}

	//Publica valores a tabela - "posta" uma postagem
	@PostMapping
	public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem){
		//Salva a nova postagem e retorna a postagem salva com status 201 (Created)
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(postagemRepository.save(postagem));
	}

	//Atualiza os dados da tabela a partir do ID
	@PutMapping
	public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem){
		//Atualiza a postagem existente com o ID especificado e retorna a postagem atualizada
		return postagemRepository.findById(postagem.getId())
				.map(resposta -> ResponseEntity.status(HttpStatus.OK)
						.body(postagemRepository.save(postagem)))
				.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
	}
	
	//Deletar
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		//Verifica se a postagem com o ID especificado existe
		Optional<Postagem> postagem = postagemRepository.findById(id);
		
		//Se não encontrar, lança uma exceção com status 404 (Not Found)
		if(postagem.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		
		//Caso contrário, deleta a postagem
		postagemRepository.deleteById(id);
	}

}
