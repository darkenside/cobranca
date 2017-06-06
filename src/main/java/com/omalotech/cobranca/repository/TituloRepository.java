package com.omalotech.cobranca.repository;


import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

//import org.springframework.data.repository.query.Param;

import com.omalotech.cobranca.model.Titulo;
import com.omalotech.cobranca.model.User;

public interface TituloRepository extends MongoRepository<Titulo, Long>{


	public List<Titulo> findByDescricaoContainingAndDataVencimento(String descricao,
			Date dataVencimento);
	
	
	public Titulo findByCodigo(ObjectId codigo);
	public Long deleteByCodigo(ObjectId codigo);
	public List<Titulo> findByUsuario(User user);
	
/*	
	@Query(nativeQuery = false)
    List<Titulo> findByTituloRecuperarNamedOrmXml();

	@Query(nativeQuery = true)
	List<Titulo> findByTituloRecuperarSelecaoNamedOrmXml(@Param("descricao") String descricao
			,@Param("status2") String status,@Param("dataVencimento") Date dataVencimento,@Param("dataVencimentoFinal")
				Date dataVencimentoFinal,@Param("idUsuario")Long idUsuario);*/
}
