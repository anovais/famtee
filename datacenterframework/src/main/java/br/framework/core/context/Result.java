package br.framework.core.context;

import br.framework.domain.GenericEntity;


/**
 * Author: Andre Novais <br>
 * Date: 19/10/2016 <br>
 * Description: Comporta os resultados da execução de um {@link Context}  
 *
 */
public class Result{

	private Boolean success;
	private Throwable exception;
	private GenericEntity resultEntity;
	private String message;
	private Long returnCode;
	
	
	
	
	public Result withMessage(String message){
		this.message = message;
		return this;
	}
	
	
	
	
	
	/**
	 * @return the resultEntity
	 */
	public GenericEntity getResultEntity() {
		return resultEntity;
	}





	/**
	 * @param resultEntity the resultEntity to set
	 */
	public void setResultEntity(GenericEntity resultEntity) {
		this.resultEntity = resultEntity;
	}

	public boolean isSucess() {
		return success;
	}
	public void setSucesso(boolean sucesso) {
		this.success = sucesso;
	}
	
	public Throwable getException() {
		return exception;
	}
	public void setException(Throwable exception) {
		this.exception = exception;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getReturnCode() {
		return returnCode;
	}
	public void setReturnCode(Long returnCode) {
		this.returnCode = returnCode;
	}
	
	
	
	
}
