package com.omalotech.cobranca.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.omalotech.cobranca.repository.StatusTitulo;



//@Entity

@Document(collection = "titulo")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Titulo implements Serializable{

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	@Id
	//@GeneratedValue(strategy=GenerationType.IDENTITY)
	private ObjectId codigo ;
	
	@NotEmpty(message="A descrição é obrigatória")
	@Size(max=60, message="A descrição não pode ser maior que 60 caracteres")
	private String descricao;
	
	@NotNull(message="Data é obrigatória")
	@DateTimeFormat(pattern="dd/MM/yyyy")
	//@Temporal(TemporalType.DATE)
	private Date dataVencimento;
	
	@NotNull(message="Valor é obrigatório")
	@NumberFormat(pattern="#,##0.00")
	@DecimalMin(value="0.01" ,message="Valor não pode ser menor que R$0,01")
	@DecimalMax(value="99999999.99",message="Valor não pode ser maior que R$99.999.999,99")
	private BigDecimal valor;
	
	@NotNull(message="Status é obrigatório")
	//@Enumerated(EnumType.STRING)
	private StatusTitulo status;
	
	
	@JsonIgnore
	@NotNull
	//@ManyToOne(fetch = FetchType.LAZY)
	//@JoinColumn(name="id_user",nullable=false, foreignKey =@ForeignKey(name="fk_titulo_user"))
	@DBRef()
	private User usuario;
	

	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public ObjectId getCodigo() {
		return codigo;
	}
	public void setCodigo(ObjectId codigo) {
		this.codigo = codigo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public StatusTitulo getStatus() {
		return status;
	}
	public void setStatus(StatusTitulo status) {
		this.status = status;
	}


}
