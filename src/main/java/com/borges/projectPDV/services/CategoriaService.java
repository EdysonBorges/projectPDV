package com.borges.projectPDV.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.borges.projectPDV.domain.Categoria;
import com.borges.projectPDV.dto.CategoriaDTO;
import com.borges.projectPDV.repositories.CategoriaRepository;
import com.borges.projectPDV.services.exceptions.DataIntegrityException;
import com.borges.projectPDV.services.exceptions.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repo;

	/////////////////////////////////////////////////////////////////////////////////////////

	// Método para [INSERIR] uma nova Categoria

	public Categoria insert(Categoria obj) {
		obj.setId(null);
		return repo.save(obj);
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// Método para [ATUALIZAR] uma Categoria

	public Categoria updateData(Categoria obj) {
		Categoria newObj = find(obj.getId());
		updateData(newObj, obj);

		return repo.save(newObj);
	}
	////////////////////////////////////////////////////////////////////////////////////////

	// Método para [EXCLUIR] uma Categoria

	public void delete(Integer id) {
		find(id);

		try {
			repo.deleteById(id);
		}

		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException(
					"Não é possível excluir uma categoria que contém produtos relacionados a ela.");
		}

	}
	///////////////////////////////////////////////////////////////////////////////////////

	// Método para [BUSCAR POR ID] uma Categoria

	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);

		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + " , Tipo: " + Categoria.class.getName()));
	}

	////////////////////////////////////////////////////////////////////////////////////////

	// Método para [BUSCAR TODAS] as Categorias

	public List<Categoria> findAll() {
		return repo.findAll();
	}

	////////////////////////////////////////////////////////////////////////////////////////

	// Método para [PAGINAÇÃO] de Categorias

	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}

	public Categoria fromDto(CategoriaDTO objDto) {

		return new Categoria(objDto.getId(), objDto.getNome());
	}

	private void updateData(Categoria newObj, Categoria obj) {
		newObj.setNome(obj.getNome());

	}

}
