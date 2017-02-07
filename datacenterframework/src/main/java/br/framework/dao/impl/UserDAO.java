package br.framework.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import br.framework.dao.GenericDAO;
import br.framework.domain.secutiry.User;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados para armazenamento e leitura de{@linkplain User usuários}
 *
 */
@Repository
public class UserDAO extends GenericDAO<User> implements UserDetailsService{

	public UserDAO() {
		super(User.class);
	}

	@Override
	public UserDetails loadUserByUsername(String user)
			throws UsernameNotFoundException {

		String jpql = "select u from User u where u.email = :email";
		 Query q = manager.createQuery(jpql, User.class);
		 q.setParameter("email", user);
		 List<User> users = q.getResultList();
		 
		if(users.isEmpty()) throw new UsernameNotFoundException("Usuáro não encontrado");
		return users.get(0);
		
	}
	
}
