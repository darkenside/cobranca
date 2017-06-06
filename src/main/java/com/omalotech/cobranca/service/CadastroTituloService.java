package com.omalotech.cobranca.service;



import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.repository.StatusTitulo;
import com.omalotech.cobranca.repository.TituloRepository;


@Service
public class CadastroTituloService {

	//@Autowired
//	private TituloRepository titulos;
	@Autowired
	TituloRepository tituloImp;

	public void salvar(Titulo titulo) {
		try {
			tituloImp.save(titulo);
		} catch (DataIntegrityViolationException e) {

			throw new IllegalArgumentException("com.omalotech.forma.data");
		}

	}

	public void excluir(ObjectId codigo) {

		tituloImp.deleteByCodigo(codigo);

	}

	public String mudarStatusTitulo(ObjectId codigo) {
  
		Titulo t1 = (Titulo) tituloImp.findByCodigo(codigo);

		t1.setStatus(StatusTitulo.RECEBIDO);

		tituloImp.save(t1);

		return t1.getStatus().getDescricao();
	}

	/*
	 * public List<Titulo> filtrarTitulos(TituloFilter filtro){
	 * 
	 * String descricao=filtro.getDescricao() == null ? "%" :
	 * filtro.getDescricao(); Date dataVencimento=filtro.getDataVencimento();
	 * //StatusTitulo status =filtro.getStatus().equals("") ? null :
	 * filtro.getStatus(); List<Titulo> titulos =
	 * this.titulos.findByDescricaoContainingAndDataVencimento(
	 * descricao,dataVencimento); return titulos; }
	 * 
	 *
	 */
	
	public Titulo recuperaTitulo(ObjectId codigo){
		
		return tituloImp.findByCodigo(codigo);
	}
	
	

}