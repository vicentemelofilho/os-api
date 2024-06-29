package com.vicente.os.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vicente.os.domain.OS;
import com.vicente.os.dtos.OsDTO;
import com.vicente.os.services.OsService;

import jakarta.validation.Valid;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/os")
public class OsResource {
	@Autowired
	private OsService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OsDTO> findById(@PathVariable Integer id){
		OS obj = service.findById(id);
		OsDTO objDTO = new OsDTO(obj);
		
		return ResponseEntity.ok().body(objDTO);		
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<OsDTO>> findAll() {
		List<OsDTO> listDTO = service.findAll()
				.stream().map(obj -> new OsDTO(obj)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listDTO);
	}
	
	@PostMapping(value = "/")
	public ResponseEntity<OsDTO> create(@Valid @RequestBody OsDTO objDTO){
		OS newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		
		return ResponseEntity.created(uri).build();		
	}
	
	@PutMapping(value = "/")
	public ResponseEntity<OsDTO> update(@Valid @RequestBody OsDTO objDTO){
		OsDTO newObj = new OsDTO(service.update(objDTO));
		
		return ResponseEntity.ok().body(newObj);		
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		service.delete(id);
		
		return ResponseEntity.noContent().build();		
	}
}
