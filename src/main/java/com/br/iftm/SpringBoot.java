package com.br.iftm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.iftm.domain.Atividade;
import com.br.iftm.repositories.AtividadeRepository;

@SpringBootApplication
public class SpringBoot implements CommandLineRunner{
	
	@Autowired
	private AtividadeRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringBoot.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception{
		Atividade a1 = new Atividade();
		a1.setNome("simpos");
		repo.save(a1);
	}
}
