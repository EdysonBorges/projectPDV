package com.borges.projectPDV.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.projectPDV.domain.Cliente;
import com.borges.projectPDV.repositories.ClienteRepository;
import com.borges.projectPDV.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repo; 
	
	public Cliente buscar(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new  ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id+ " , Tipo: " + Cliente.class.getName()));
	}
}
