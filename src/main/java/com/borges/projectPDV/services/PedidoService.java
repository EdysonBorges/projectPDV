package com.borges.projectPDV.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.projectPDV.domain.Pedido;
import com.borges.projectPDV.repositories.PedidoRepository;
import com.borges.projectPDV.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repo; 
	
	public Pedido buscar(Integer id) {
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new  ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id+ " , Tipo: " + Pedido.class.getName()));
	}
}
