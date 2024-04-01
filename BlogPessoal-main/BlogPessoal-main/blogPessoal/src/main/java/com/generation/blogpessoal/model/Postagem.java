package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

//Entidade JPA
@Entity
@Table(name = "tb_postagens") //Define o nome da tabela no banco de dados
public class Postagem {
	
	//ID - chave primária
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	//Título
	@NotBlank(message = "O atributo título é obrigatório!") //Não pode estar vazio
	@Size(min = 5, max = 100, message = "O atributo título deve conter de 05-100 caracteres") //Tamanho
	private String titulo;
	
	@NotBlank(message = "O atributo texto é obrigatório!") //Não pode estar vazio
	@Size(min = 5, max = 1000, message = "O atributo texto deve conter de 05-1000 caracteres") //Tamanho
	private String texto;
	
	//Data
	@UpdateTimestamp
	private LocalDateTime data; //Atualiza automaticamente o campo com a data e hora atual sempre que a entidade é atualizada
	
	//Cardinalidade do relacionamento - muitos para um
	//Tema
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Tema tema;

	//Usuario
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;

	//Getters e Setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
