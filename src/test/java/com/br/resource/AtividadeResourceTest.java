package com.br.resource;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AtividadeResourceTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;
	
	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
	
	@Test
	public void teste01testaId() throws Exception {
		this.mvc.perform(get("url"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("nome", is("Visita Técnica GDG 2018")));
	}
	
	@Test
	public void teste02testaNotFound() throws Exception{
		this.mvc.perform(get("/atividade/10"))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void teste03testaRequisicaoDeleteSucesso() throws Exception{
		String url = "/atividade/10";
		
		this.mvc.perform(delete(url))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("10")));
	}
	
	@Test
	public void teste04testaRequisicaoDeleteFalha() throws Exception{
		String url = "/atividade/101";
		
		this.mvc.perform(delete(url))
		.andExpect(status().isNotFound());
	}
	
	@Test
	public void teste05testaRequisicaoPostSucesso() throws Exception{
		String url = "/atividade";
		
		this.mvc.perform(post(url).content("{\"nome\": \"Simpo\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(header().string("Location", is("http://localhost/atividade/atividades/10")))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste06testaRequisicaoPostFalha() throws Exception{
		String url = "/atividade";
		
		this.mvc.perform(post(url).content("{\"nome\": \"Simpo\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste07testaRequisicaoPutSucesso() throws Exception{
		String url = "/atividade";
		
		this.mvc.perform(put(url).content("{\"id\":10, \"nome\": \"Simpo\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNoContent())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste08testaRequisicaoPutFalha() throws Exception{
		String url = "/atividade";
		
		this.mvc.perform(put(url).content("{\"id\":101, \"nome\": \"Simpo\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isNotFound())
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	public void teste09testaRequisicaoPutFalhaDescricao() throws Exception{
		String url = "/atividade";
		
		this.mvc.perform(put(url).content("{\"id\":10, \"nome2\": \"Simpo\"}")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andDo(MockMvcResultHandlers.print());
	}
}
