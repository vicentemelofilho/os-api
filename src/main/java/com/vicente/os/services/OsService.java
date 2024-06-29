package com.vicente.os.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.os.domain.Cliente;
import com.vicente.os.domain.OS;
import com.vicente.os.domain.Tecnico;
import com.vicente.os.domain.enuns.Prioridade;
import com.vicente.os.domain.enuns.Status;
import com.vicente.os.dtos.OsDTO;
import com.vicente.os.repositories.OSRepository;
import com.vicente.os.services.exceptions.ObjectNotFoundException;

import jakarta.validation.Valid;

@Service
public class OsService {
	@Autowired
	private OSRepository repository;
	
	@Autowired
	private TecnicoService tecnicoService;
	
	@Autowired
	private ClienteService clienteService;
	
	public OS findById(Integer id) {
		Optional<OS> obj = repository.findById(id);
		
		//return obj.orElse(null);
		return obj.orElseThrow(() -> 
			new ObjectNotFoundException("OS não encontrada! Id: " + id + ", Tipo: " + OS.class.getName())
			);
	}
	
	public List<OS> findAll() {
		return repository.findAll();
	}
	
	public OS create(@Valid OsDTO objDTO) {
		OS newObj = fromDTO(objDTO);
		return newObj;	
	}
	
	private OS fromDTO(OsDTO objDTO) {
		OS newObj = new OS();
		newObj.setId(objDTO.getId());
		newObj.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()) );
		newObj.setObservacoes(objDTO.getObservacoes());
		newObj.setStatus(Status.toEnum(objDTO.getStatus()));
		
		Tecnico tecnico = tecnicoService.findById(objDTO.getTecnico());
		newObj.setTecnico(tecnico);
				
		Cliente cliente = clienteService.findById(objDTO.getCliente());
		newObj.setCliente(cliente);
		
		if (newObj.getStatus() == Status.ENCERRADO && objDTO.getDataFechamento() == null) {
			newObj.setDataFechamento(LocalDateTime.now());
		}
				
		return repository.save(newObj);
	}

	public OS update(@Valid OsDTO objDTO) {
		findById(objDTO.getId());
		return fromDTO(objDTO);
	}

	public void delete(Integer id) {
		//OS obj = findById(id);
		
		repository.deleteById(id);
		
	}
}
