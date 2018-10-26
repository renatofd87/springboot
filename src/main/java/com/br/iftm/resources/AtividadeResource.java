package com.br.iftm.resources;

import java.net.URI;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.iftm.domain.Atividade;
import com.br.iftm.services.AtividadeService;

@RestController
@RequestMapping(value="/atividades")
public class AtividadeResource {
	
	@Autowired
	AtividadeService service;
	
	@RequestMapping(value="{id}", method=RequestMethod.GET)
	public ResponseEntity<?> find(@PathVariable Integer id) {
		
		try {
			Atividade atividade = service.buscar(id);
			
			return ResponseEntity.ok().body(atividade);
		}catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping(value="{id}")
	public ResponseEntity<?> deleteAtividadeById(@PathVariable Integer id){
		try {
			service.deletar(id);
			return ResponseEntity.ok(id);
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Atividade atividade){
		try {
			Atividade a = service.salvar(atividade);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(a.getId()).toUri();
			return ResponseEntity.created(location).build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<?> editar(@RequestBody Atividade atividade){
		try {
			service.editar(atividade);
			return ResponseEntity.noContent().build();
		}catch(EmptyResultDataAccessException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
