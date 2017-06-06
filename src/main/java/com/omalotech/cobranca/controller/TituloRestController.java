package com.omalotech.cobranca.controller;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.omalotech.cobranca.MessageResource;
import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.model.User;
import com.omalotech.cobranca.service.CadastroTituloService;
import com.omalotech.cobranca.service.PesquisaTituloService;
import com.omalotech.cobranca.service.UserService;
import com.omalotech.cobranca.UnixEpochDateTypeAdapter;

@RestController
@RequestMapping("/rest")
public class TituloRestController {

@Autowired
UserService userService;
	
	@Autowired 
	MessageResource message;

	@Autowired
	private PesquisaTituloService pesquisaTituloService;
	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	

/*
	@RequestMapping(value="/rest",method=RequestMethod.GET,headers="Accept=application/json")
	public ResponseEntity<List<Titulo>> pesquisar() {
	
		
		 List<Titulo> titulos = this.pesquisaTituloService.recuperarTitulos();
				
		return new ResponseEntity<List<Titulo>>(new ArrayList<Titulo>(titulos), HttpStatus.OK)	;
	
	}*/

	@RequestMapping(value="/pesquisaTudo",method=RequestMethod.GET,headers="Accept=application/json")
	public List<Titulo> pesquisar2() {
	
		
		 List<Titulo> titulos = this.pesquisaTituloService.recuperarTitulos();
				
		return titulos	;
	
	}

		
	@RequestMapping(value="/nomeUser/{nomeUser}",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Titulo> pesquisarPorNome(@PathVariable("nomeUser") String nomeUser)  {
		User usuario = userService.findByUsername(nomeUser);
		List<Titulo> titulos = new ArrayList<>();
		
		if(usuario != null) {
				titulos = this.pesquisaTituloService.recuperarTituloPesquisa(null,null,null,null, usuario);
		}
		
		return titulos	;
	
	}
	
	
	@RequestMapping(value="/pesquisar",method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<Titulo>  pesquisarPorNomeParam(@RequestParam("nomeUser") final String nomeUser)  {
		User usuario = userService.findByUsername(nomeUser);
		List<Titulo> titulos = new ArrayList<>();
		
		if(usuario != null) {
				titulos = this.pesquisaTituloService.recuperarTituloPesquisa(null,null,null,null, usuario);
		}
		
		return titulos;
	
	}

	@RequestMapping(value = "/inserirTitulo", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody void inserirTituloParam(@RequestBody final String jTitulo) {

		final Gson gSon = new GsonBuilder()
				.registerTypeAdapter(Date.class, UnixEpochDateTypeAdapter.getUnixEpochDateTypeAdapter()).create();
		Titulo t1 = gSon.fromJson(jTitulo, Titulo.class);

		User usuario = userService.findByUsername(t1.getUsuario().getUsername());
		t1.setUsuario(usuario);

		if (usuario != null && t1.getDescricao() != null) {
			this.cadastroTituloService.salvar(t1);
		}

	}

}
