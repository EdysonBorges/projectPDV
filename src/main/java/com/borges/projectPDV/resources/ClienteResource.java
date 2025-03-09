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

import com.borges.projectPDV.domain.Cliente;
import com.borges.projectPDV.dto.ClienteDTO;
import com.borges.projectPDV.dto.ClienteNewDTO;
import com.borges.projectPDV.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService clienteService;

	//////////////////////////////////////////////////////

	// Método controlador para [INSERIR] um novo Cliente

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDto) {
		Cliente obj = clienteService.fromDto(objDto);
		obj = clienteService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	////////////////////////////////////////////////////////

	// Método controlador para [ATUALIZAR] um Cliente

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDto, @PathVariable Integer id) {
		Cliente obj = clienteService.fromDto(objDto);
		obj.setId(id);
		obj = clienteService.update(obj);
		return ResponseEntity.noContent().build();
	}

	///////////////////////////////////////////////////////////

	// Método controlador para [EXCLUIR] um Cliente

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}

/////////////////////////////////////////////////////////////

	// Método controlador para [LISTAR POR ID] um Cliente

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {

		Cliente obj = clienteService.find(id);
		return ResponseEntity.ok().body(obj);
	}
//////////////////////////////////////////////////////////////

	// Metodo controlador para listar [TODOS OS CLIENTES]

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> list = clienteService.findAll();
		List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());

		return ResponseEntity.ok().body(listDto);

	}
///////////////////////////////////////////////////////////////

	// Método controlador para listar [PAGINAÇÃO] de Clientes

	@RequestMapping(value = "/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> list = clienteService.findPage(page, linesPerPage, orderBy, direction);
		Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO(obj));

		return ResponseEntity.ok().body(listDto);

	}
//////////////////////////////////////////////////////////////////

}
