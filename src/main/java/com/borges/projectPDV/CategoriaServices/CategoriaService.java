package com.borges.projectPDV.CategoriaServices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.borges.projectPDV.CategoriaRepositories.CategoriaRepository;
import com.borges.projectPDV.CategoriaServices.exceptions.ObjectNotFoundException;
import com.borges.projectPDV.domain.Categoria;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo; 
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> new  ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id+ " , Tipo: " + Categoria.class.getName()));
	}
}
