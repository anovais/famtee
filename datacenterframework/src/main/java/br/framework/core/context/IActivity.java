package br.framework.core.context;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description:  Interface que define contrato de toda atividade inserida em um {@linkplain Context contexto}<br> 
 *
 */
public interface IActivity<T> {

	public void execute(T entity, Context context);
}
