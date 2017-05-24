package com.omalotech.cobranca.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.repository.StatusTitulo;
import com.omalotech.cobranca.repository.TituloFilter;
import com.omalotech.cobranca.repository.TituloRepository;

@Service
public class CadastroTituloService {

	@Autowired
	private TituloRepository titulos;

	public void salvar(Titulo titulo) {
		try {
			titulos.save(titulo);
		} catch (DataIntegrityViolationException e) {

			throw new IllegalArgumentException("Formato de data invalido");
		}

	}

	public void excluir(Long codigo) {

		titulos.delete(codigo);

	}

	public String mudarStatusTitulo(Long codigo) {

		Titulo t1 = titulos.findOne(codigo);

		t1.setStatus(StatusTitulo.RECEBIDO);

		titulos.save(t1);

		return t1.getStatus().getDescricao();
	}

	
	public List<Titulo> filtrarTitulos(TituloFilter filtro){
		
		String descricao=filtro.getDescricao() == null ? "%" : filtro.getDescricao();
		Date dataVencimento=filtro.getDataVencimento();
		//StatusTitulo status =filtro.getStatus().equals("") ? null : filtro.getStatus();
		List<Titulo> titulos = this.titulos.findByDescricaoContainingAndDataVencimento(
				descricao,dataVencimento);
		return titulos;
	} 
	
}