package br.framework.core.facade;

import java.util.Collection;

import br.framework.domain.GenericEntity;
/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Interface que define contrato dos objetos de fachada <br>
 * As fachadas ajudam a fornecer uma interface sólida, menos propensa a mudanças <br>
 *
 */

public interface IFacade<T extends GenericEntity> {

	public void insert(T target);

	public void delete(T target);

	public void update(T target);

	public Collection<T> listAll();

	public T findById(Long id);


}