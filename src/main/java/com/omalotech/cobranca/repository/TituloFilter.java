package com.omalotech.cobranca.repository;

import java.util.Date;

//import javax.persistence.Temporal;
//import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class TituloFilter {

	
	private String descricao;
	
	@DateTimeFormat(pattern="dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private Date dataVencimento;

	@DateTimeFormat(pattern="dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private Date dataVencimentoFinal;
	
    
	private StatusTitulo status;
	
	public Date getDataVencimentoFinal() {
		return dataVencimentoFinal;
	}

	public void setDataVencimentoFinal(Date dataVencimentoFinal) {
		this.dataVencimentoFinal = dataVencimentoFinal;
	}

	

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public StatusTitulo getStatus() {
		return status;
	}

	public void setStatus(StatusTitulo status) {
		this.status = status;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}
