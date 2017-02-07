package br.framework.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.dao.MongoDB;
import br.framework.domain.mission.RowData;
import br.framework.domain.mission.Telemetry;
import br.framework.domain.util.Period;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Sorts;


/**
 * Author: Andre Novais <br>
 * Date: 10/2016 <br>
 * Description: Acesso ao banco de dados NoSql para armazenamento de telemetrias.
 *
 */
@Component
public class TelemetryDAO {

	@Autowired
	MongoDB mongo;
	
	
	public void insert(Telemetry telemetry){
		MongoCollection collection = mongo.getCollection(telemetry.getMission().getCode().toUpperCase(), telemetry.getSourceType().toLowerCase());
		collection.insertMany(toDocuments(telemetry.getData()));
	}
	
	public Telemetry findByPeriod(Telemetry t){
		Period period = t.getPeriod();
		MongoCollection<Document> collection = mongo.getCollection(t.getMission().getCode().toUpperCase(), t.getSourceType().toLowerCase());
		Bson query = Filters.and(
				Filters.gte("Timestamp", period.getStart().getTime())
				,Filters.lte("Timestamp", period.getEnd().getTime()) );
		MongoCursor<Document> find = 
				collection.find(query).projection(new Document("_id",0))
					.sort(Sorts.ascending("Timestamp")).iterator();
		while(find.hasNext()){
			t.addRegister(RowData.fromDocument(find.next()));
		}
		return t;
	}
	
	
	
	
	private List toDocuments(List<RowData> telemetries) {
		List<Document> documents = new ArrayList<Document>();
		for (RowData data : telemetries)
			documents.add( new Document(data.toMap()) );
		return documents;
		
	}
	
}
