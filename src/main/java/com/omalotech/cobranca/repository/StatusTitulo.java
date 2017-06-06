package com.omalotech.cobranca.repository;

public enum StatusTitulo {

	PENDENTE("Pendente"),
	RECEBIDO("Pago")
	;

	String descricao;
	StatusTitulo(String descricao){
		
		this.descricao = descricao;
		
	}
	public String getDescricao() {
		return descricao;
	}
	
}
