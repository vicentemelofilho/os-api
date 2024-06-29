package com.vicente.os.dtos;

import java.io.Serializable;

import com.vicente.os.domain.CPF;
import com.vicente.os.domain.Cliente;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message="O campo nome é obrigatório")
	@NotNull(message="O campo nome é obrigatório")
	private String nome;
	
	@CPF
	@NotEmpty(message="O campo CPF é obrigatório")
	private String cpf;
	
	@NotEmpty(message="O campo telefone é obrigatório")
	private String telefone;
	
	public ClienteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.telefone = obj.getTelefone();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	
}
