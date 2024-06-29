package com.vicente.os.services;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vicente.os.domain.Cliente;
import com.vicente.os.domain.OS;
import com.vicente.os.domain.Tecnico;
import com.vicente.os.domain.enuns.Prioridade;
import com.vicente.os.domain.enuns.Status;
import com.vicente.os.repositories.ClienteRepository;
import com.vicente.os.repositories.OSRepository;
import com.vicente.os.repositories.TecnicoRepository;

@Service
public class DBService {
	@Autowired
	private TecnicoRepository tecnicoRepository;
		
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private OSRepository osRepository;	
	
	public void instanciaDB() {
		Tecnico t1 = new Tecnico(null, "Vicente Melo", "466.357.430-06", "(88) 98888-8888");
		Tecnico t2 = new Tecnico(null, "Victor Melo", "641.760.040-88", "(88) 96666-6666");
		Cliente c1 = new Cliente(null, "Betina Campos", "179.374.150-61", "(88) 97777-7777");
		
		OS os1 = new OS(null, Prioridade.ALTA, "Teste create OS", Status.ABERTO, t1, c1); 
		t1.getList().add(os1);
		c1.getList().add(os1);
		
		tecnicoRepository.saveAll(Arrays.asList(t1, t2));
		clienteRepository.saveAll(Arrays.asList(c1));
		osRepository.saveAll(Arrays.asList(os1));
	}
}
