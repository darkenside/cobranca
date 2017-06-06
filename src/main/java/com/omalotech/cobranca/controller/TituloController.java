package com.omalotech.cobranca.controller;

import java.util.List;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.omalotech.cobranca.MessageResource;
import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.model.User;
import com.omalotech.cobranca.repository.StatusTitulo;
import com.omalotech.cobranca.repository.TituloFilter;

import com.omalotech.cobranca.service.CadastroTituloService;
import com.omalotech.cobranca.service.PesquisaTituloService;
import com.omalotech.cobranca.service.UserService;

@RequestMapping("/titulos")
@Controller
public class TituloController {

	private static final String CADASTRO_VIEW="CadastroTitulo";
	
	@Autowired 
	MessageResource message;
	

	@Autowired
	private UserService userService;
	
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

	int er=	validateTitulo(titulo1, erros, redirect);
		
		
		if (erros.hasErrors() && er==1) {

			return CADASTRO_VIEW;

		}

		
		
		try{
			
			User usuario = userService.findByUsername(nomeUsuarioLogado());
			titulo1.setUsuario(usuario);
			this.cadastroTituloService.salvar(titulo1);
			redirect.addFlashAttribute("mensagem", message.mensagemProperties("com.omalotech.salvar.sucesso"));
			return "redirect:/titulos/novo";
		} catch(IllegalArgumentException e) {
			
			erros.rejectValue("dataVencimento","com.omalotech.forma.data"); 
			return CADASTRO_VIEW;
		}
		
	
	}

	@RequestMapping
	public ModelAndView pesquisar(@ModelAttribute("filtro") TituloFilter filtro) {
		User usuario = userService.findByUsername(nomeUsuarioLogado());
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

		List<Titulo> titulos = this.pesquisaTituloService.recuperarTituloPesquisa(
				descricao, status, dataVencimento,dataVencimentoFinal,usuario);
		
		ModelAndView mv = new ModelAndView("PesquisaTitulos");
		mv.addObject("titulosPesquisa", titulos);
		mv.addObject("nomeUsuarioLogado", nomeUsuarioLogado());

		return mv;
	}

	
	@RequestMapping("{codigo}") //ja executa diretamente o findOne @PathVariable("codigo") e devolve o titulo pelo codigo
	public ModelAndView edicao(@PathVariable("codigo") ObjectId codigo){
		 
		Titulo tituloEdicao = cadastroTituloService.recuperaTitulo(codigo);
		
		
		ModelAndView mv = new ModelAndView(CADASTRO_VIEW);
		mv.addObject("nomeUsuarioLogado", nomeUsuarioLogado());
		mv.addObject(tituloEdicao);
		return mv;
	}
	
	
	@RequestMapping(value="{codigo}",method=RequestMethod.DELETE)
	public String excluir (@PathVariable ObjectId codigo,RedirectAttributes attributes){
		cadastroTituloService.excluir(codigo);
		attributes.addFlashAttribute("mensagem", message.mensagemProperties("com.omalotech.excluir.t"));
		return "redirect:/titulos";
	}
	
	
	@RequestMapping(value="/{codigo}/receber", method=RequestMethod.PUT)
	public @ResponseBody String  receber(@PathVariable ObjectId codigo){
		
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
	
	public int validateTitulo(Object o, Errors errors, RedirectAttributes redirect) {
		Titulo titulo = (Titulo) o;
		int e=0;
		
		if (titulo.getDescricao().equals("") || titulo.getDescricao() == null) {

			errors.rejectValue("descricao", "com.omalotech.desc.obriga");
			e=1;
			
		} else if (titulo.getDescricao().length() > 254) {

			errors.rejectValue("descricao", "com.omalotech.descricao.tamanho");
			e=1;
		}

		if (titulo.getDataVencimento() == null) {

			errors.rejectValue("dataVencimento", "com.omalotech.data.obrig");
			e=1;
		}

		if (titulo.getValor() == null) {

			errors.rejectValue("valor", "com.omalotech.valor.obrig");
			e=1;

		} else if (titulo.getValor().compareTo(new BigDecimal("9999999999")) == 1) {

			errors.rejectValue("valor", "com.omalotech.valor.maior");
			e=1;
		}
		
		return e;

	}

	
	

}

