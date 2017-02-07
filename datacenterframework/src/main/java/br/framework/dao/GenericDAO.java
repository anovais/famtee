package br.framework.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import br.framework.conf.JPAConfiguration;
import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Superclasse de um objeto de repositório de dados que utiliza Hibernate ORM para persistência. 
 */
@Repository
public class GenericDAO<T extends GenericEntity> {

	private Class<T> entityClass;
	
	@PersistenceContext
	protected EntityManager manager;

	public GenericDAO(){
		entityClass = (Class<T>) GenericEntity.class;
	}
	
	public GenericDAO(Class<T> classe) {
		this.entityClass = classe;
	}

	
	
	public void insert(T target) {manager.persist(target);}
	
	public void merge(T target) {manager.merge(target);}

	public void remove(T target) { manager.remove(target);}

	public void update(T target) { manager.merge(target);}

	public List<T> listAll() {
		CriteriaQuery<T> query = manager.getCriteriaBuilder().createQuery(entityClass);
		query.select(query.from(entityClass));
		List<T> lista = manager.createQuery(query).getResultList();		
		return lista;
	}

	public T findById(Long id) {
		T instancia = manager.find(entityClass, id);
		return instancia;
	}

	public Class<T> getClasse() {
		return entityClass;
	}

	public void setEntityClass(Class classe) {
		this.entityClass = classe;
	}

	

}
