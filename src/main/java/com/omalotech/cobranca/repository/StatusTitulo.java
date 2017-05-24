package com.omalotech.cobranca.repository;

public enum StatusTitulo {

	PENDENTE("Pendente"),
	RECEBIDO("Recebido")
	;

	String descricao;
	StatusTitulo(String descricao){
		
		this.descricao = descricao;
		
	}
	public String getDescricao() {
		return descricao;
	}
	
}
