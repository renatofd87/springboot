package com.br.iftm.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.iftm.Atividade;

@RestController
@RequestMapping(value="/atividades")
public class AtividadeResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public List<Atividade> listar() {
		
		Atividade at1 = new Atividade(1, "Teste1");
		Atividade at2 = new Atividade(2, "Teste2");
		
		return Arrays.asList(at1, at2);
	}
}
