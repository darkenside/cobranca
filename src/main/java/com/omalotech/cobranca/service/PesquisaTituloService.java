package com.omalotech.cobranca.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.stereotype.Service;


import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.model.User;
import com.omalotech.cobranca.repository.TituloRepository;


@Service
public class PesquisaTituloService {

    @Autowired
    MongoOperations mongoOperations;

	@Autowired
	private TituloRepository tituloRepositorio;
	
	
	
	public List<Titulo> recuperarTitulos(){
		
		
		
		return tituloRepositorio.findAll();
		//return tituloRepositorio.findByTituloRecuperarNamedOrmXml();
		
		
		
	}
	
	public List<Titulo> recuperarTituloPesquisa(String descricao,String status,
			Date dataVencimento,Date dataVencimentoFinal,User usuario) {
		Query query3 = new Query();
		int pesq=0;
		
		if(descricao != null){
			query3.addCriteria(Criteria.where("descricao").regex(descricao));
			pesq=1;}
		if(dataVencimento != null && dataVencimentoFinal ==null){
			query3.addCriteria(Criteria.where("dataVencimento").gte(dataVencimento));	
		    pesq=1;}
		else if(dataVencimentoFinal != null && dataVencimento ==null){
			query3.addCriteria(Criteria.where("dataVencimento").lte(dataVencimentoFinal));	
			pesq=1;}
		else if(dataVencimentoFinal != null && dataVencimento !=null){
			query3.addCriteria(Criteria.where("dataVencimento").gte(dataVencimento).lte(dataVencimentoFinal));	
		}
		if(usuario != null){
			query3.addCriteria(Criteria.where("usuario").is(usuario));	
		    pesq=1;}
		if(status != null){
			query3.addCriteria(Criteria.where("status").is(status));	
	     	pesq=1; }
		
		List<Titulo> titulos = new ArrayList<>();
		if(pesq ==1){
		titulos =  mongoOperations.find(query3, Titulo.class);
		}else {
			titulos= tituloRepositorio.findByUsuario(usuario);
		}
		
		return titulos;
		//return tituloRepositorio.findByTituloRecuperarSelecaoNamedOrmXml
		//(descricao, status, dataVencimento,dataVencimentoFinal,usuario.getId());
		
	}
	
	
	
	
	
}

