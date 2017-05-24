package com.omalotech.cobranca.service;



import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.repository.TituloRepository;


@Service
public class PesquisaTituloService {



	@Autowired
	private TituloRepository tituloRepositorio;
	
	
	
	public List<Titulo> recuperarTitulos(){
		
		return tituloRepositorio.findByTituloRecuperarNamedOrmXml();
		
	}
	
	public List<Titulo> recuperarTituloPesquisa(String descricao,String status, Date dataVencimento,Date dataVencimentoFinal) {
		
		return tituloRepositorio.findByTituloRecuperarSelecaoNamedOrmXml(descricao, status, dataVencimento,dataVencimentoFinal);
		
	}
	
	
	
}

