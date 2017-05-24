package com.omalotech.cobranca.repository;


import java.util.Date;
import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import com.omalotech.cobranca.model.Titulo;

public interface TituloRepository extends JpaRepository<Titulo, Long>,Repository<Titulo,Long>{


	public List<Titulo> findByDescricaoContainingAndDataVencimento(String descricao,
			Date dataVencimento);
	
	
	@Query(nativeQuery = false)
    List<Titulo> findByTituloRecuperarNamedOrmXml();

	@Query(nativeQuery = true)
	List<Titulo> findByTituloRecuperarSelecaoNamedOrmXml(@Param("descricao") String descricao
			,@Param("status2") String status,@Param("dataVencimento") Date dataVencimento,@Param("dataVencimentoFinal") Date dataVencimentoFinal);
}
