package br.framework.dao;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description:Conexão com o banco de dados NoSQL
 * Responsável por obter um cliente que consome serviços do servidor do MongoDB 
 */
@Component
public class MongoDB {
	
	//database
	@Autowired
	private MongoClient client;
	public MongoClient getClient() {
		return client;
	}


	public void setClient(MongoClient client) {
		this.client = client;
	}


	private List<String> collectionsOpen;
	
	
	/**
	 * Realiza o acesso às coleções com controle de concorrência
	 * @param nameCollection: o nome da coleção
	 * @param databaseName: nome da base de dados que a coleção se encontra
	 * @return coleção
	 */
	public MongoCollection getCollection(String databaseName, String nameCollection){
		return client.getDatabase(databaseName).getCollection(nameCollection);
	}
	
	
	public MongoDatabase getDatabase(String name){
		return client.getDatabase(name);
	}

}
