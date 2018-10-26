package com.br.iftm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.iftm.domain.Atividade;
import com.br.iftm.repositories.AtividadeRepository;

@Service
public class AtividadeService {
	
	@Autowired
	private AtividadeRepository repo;
	
	public Atividade buscar(Integer id) {
		Atividade atividade = repo.findById(id).get();
		return atividade;
	}
	
	public boolean deletar(Integer id) {
		repo.deleteById(id);
		return true;
	}
	
	public Atividade salvar(Atividade atividade) {
		return repo.save(atividade);
	}
	
	public boolean editar(Atividade atividade) {
		Optional<Atividade> a = repo.findById(atividade.getId());
		
		if(a.isPresent()) {
			 repo.save(atividade);
		}
		
		return true;
	}
}
