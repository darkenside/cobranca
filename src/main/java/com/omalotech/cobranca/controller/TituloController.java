package com.omalotech.cobranca.controller;

import java.util.List;



import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.model.User;
import com.omalotech.cobranca.repository.StatusTitulo;
import com.omalotech.cobranca.repository.TituloFilter;
import com.omalotech.cobranca.service.CadastroTituloService;
import com.omalotech.cobranca.service.PesquisaTituloService;

@RequestMapping("/titulos")
@Controller
public class TituloController {

	private static final String CADASTRO_VIEW="CadastroTitulo";
	

	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	@Autowired
	private PesquisaTituloService pesquisaTituloService;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("nomeUsuarioLogado", nomeUsuarioLogado());
		mv.addObject(new Titulo());
		return mv;

	}

	@RequestMapping(method = RequestMethod.POST)
	public String salvar(@Validated Titulo titulo1, Errors erros, RedirectAttributes redirect) {

	
		
		if (erros.hasErrors()) {

			return CADASTRO_VIEW;

		}

		
		try{
			this.cadastroTituloService.salvar(titulo1);
			redirect.addFlashAttribute("mensagem", "Título salvo com sucesso!");
			return "redirect:/titulos/novo";
		} catch(IllegalArgumentException e) {
			
			erros.rejectValue("dataVencimento",null,e.getMessage()); 
			return CADASTRO_VIEW;
		}
		
	
	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		 
		String status;
		String descricao;
		Date dataVencimento = new Date();
		Date dataVencimentoFinal = new Date();
		

		if (filtro.getStatus() == null) {
			status = null;
		} else {
			status = filtro.getStatus().name();
		}

		if (filtro.getDescricao() == null || filtro.getDescricao().equals("")) {

			descricao = null;
		} else {

			descricao =filtro.getDescricao();
		}

		if (filtro.getDataVencimento() == null) {
			dataVencimento = null;

		} else {

			dataVencimento = filtro.getDataVencimento();
		}
		
		if (filtro.getDataVencimentoFinal() == null) {
			dataVencimentoFinal = null;

		} else {

			dataVencimentoFinal = filtro.getDataVencimentoFinal();
		}

		List<Titulo> titulos = this.pesquisaTituloService.recuperarTituloPesquisa(descricao, status, dataVencimento,dataVencimentoFinal);
		// List<Titulo> titulos = this.pesquisaTituloService.recuperarTitulos();
		// List<Titulo> titulos =
		// this.cadastroTituloService.filtrarTitulos(filtro);

		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulosPesquisa", titulos);
		mv.addObject("nomeUsuarioLogado", nomeUsuarioLogado());

		return mv;
	}

	
	@RequestMapping("{codigo}") //ja executa diretamente o findOne @PathVariable("codigo") e devolve o titulo pelo codigo
	public ModelAndView edicao(@PathVariable("codigo") Titulo tituloEdicao){
		
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject(tituloEdicao);
		return mv;
	}
	
	
	@RequestMapping(value="{codigo}",method=RequestMethod.DELETE)
	public String excluir (@PathVariable Long codigo,RedirectAttributes attributes){
		cadastroTituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", "Titulo excluido com sucesso!");
		return "redirect:/titulos";
	}
	
	
	@RequestMapping(value="/{codigo}/receber", method=RequestMethod.PUT)
	public @ResponseBody String  receber(@PathVariable Long codigo){
		
		return cadastroTituloService.mudarStatusTitulo(codigo);
		
	}
	
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo() {

		return Arrays.asList(StatusTitulo.values());
	}
	
	private String nomeUsuarioLogado(){
		
	return	SecurityContextHolder.getContext()
        .getAuthentication().getName();	
		
	}
	

}
