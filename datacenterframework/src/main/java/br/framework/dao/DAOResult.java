package br.framework.dao;

import java.util.List;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Para expressar o resultado de uma operação no banco de dados 
 */
public class DAOResult {

	// true em operações com sucesso, e false em operações sem sucesso
	private boolean success;
	//Armazenar uma lista com os resultados de uma consulta no banco   
	private List<? extends GenericEntity> resultQuery;
	
	
	public DAOResult(boolean success){
		this.success = success;
	}
	
	public boolean isSuccess(){
		return success;
	}
	
	public void fail(){
		success = false;
	}

	/**
	 * @return the resultQuery
	 */
	public List<? extends GenericEntity> getResultQuery() {
		return resultQuery;
	}

	/**
	 * @param resultQuery the resultQuery to set
	 */
	public void setResultQuery(List<? extends GenericEntity> resultQuery) {
		this.resultQuery = resultQuery;
	}
	
	
}
