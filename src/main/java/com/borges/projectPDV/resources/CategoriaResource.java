package com.borges.projectPDV.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.borges.projectPDV.domain.Categoria;
import com.borges.projectPDV.dto.CategoriaDTO;
import com.borges.projectPDV.services.CategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService categoriaService;

//////////////////////////////////////////////////////

// Método controlador para [INSERIR] uma nova Categoria

	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = categoriaService.fromDto(objDto);
		obj = categoriaService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

////////////////////////////////////////////////////////

// Método controlador para [ATUALIZAR] uma Categoria

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDto, @PathVariable Integer id) {
		Categoria obj = categoriaService.fromDto(objDto);
		obj.setId(id);
		obj = categoriaService.updateData(obj);
		return ResponseEntity.noContent().build();
	}

///////////////////////////////////////////////////////////

// Método controlador para [EXCLUIR] uma Categoria

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		categoriaService.delete(id);
		return ResponseEntity.noContent().build();
	}

/////////////////////////////////////////////////////////////

// Método controlador para [BUSCAR POR ID] uma Categoria

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Categoria obj = categoriaService.find(id);
		return ResponseEntity.ok().body(obj);

	}

	//////////////////////////////////////////////////////////////

	// Metodo controlador para listar [TODAS AS CATEGORIAS]

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> list = categoriaService.findAll();
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);

	}

///////////////////////////////////////////////////////////////

// Método controlador para listar [PAGINAÇÃO] de Categorias

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> list = categoriaService.findPage(page, linesPerPage, orderBy, direction);
		Page<CategoriaDTO> listDto = list.map(obj -> new CategoriaDTO(obj));

		return ResponseEntity.ok().body(listDto);

	}

}
