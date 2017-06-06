
package com.omalotech.cobranca.controller;



import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;


import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.repository.TituloFilter;

@RequestMapping("/tituloClient")
@Controller
public class TituloControllerClient {

	private static final String RETORNO_VIEW="TituloClient";
	private static final String PATH_WEB_POR_USUARIO="http://localhost:8080/rest/nomeUser/";
	/**
	 * @return
	 */
	
	@RequestMapping("/pesquisa")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(RETORNO_VIEW);

		mv.addObject("filtro",new TituloFilter());
		return mv;

	}

	
	
	@RequestMapping(value = "/{usuario}", method=RequestMethod.GET)
	public ModelAndView usuario(@ModelAttribute("filtro") TituloFilter filtro) {
		ModelAndView mv = new ModelAndView(RETORNO_VIEW);

		if(!filtro.getDescricao().equals("")){
		RestTemplate client = new RestTemplate();
		Titulo rest[] = client.getForObject(PATH_WEB_POR_USUARIO+filtro.getDescricao(),
				Titulo[].class);
		
		List<Titulo> titulos=Arrays.asList(rest);
		
		mv.addObject("titulosPesquisa", titulos);
		} else {
			ModelAndView mv2 = new ModelAndView("redirect:/tituloClient/novo");
			return mv2;
		}
		return mv;

	}


	

}
