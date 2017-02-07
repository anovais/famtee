package br.framework.domain.mission;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.bson.Document;

/**
 * Author: Andre Novais <br>
 * Date:  10/2016 <br>
 * Description: Uma linha ou um registro de um {@link Telemetry} 
 */
public class RowData {
	
	
	private List<Column> columns;

	private Map<String,Object> map;
	
	public RowData(){
		columns = new ArrayList<Column>();
	}
	
	
	public void addColumn(String columnName, Object data){		
		columns.add(new Column(columnName,data));
		if(map!=null) refreshMap();
	}
	
	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	
	public Map<String,Object> toMap(){
		if( map != null)return map;
		map = new HashMap<String,Object>();
		for (Column c : columns) 
			map.put(c.getKey(), c.getValue());
		return map;
	}
	
	private void refreshMap(){
		map = new HashMap<String,Object>();
		for (Column c : columns) 
			map.put(c.getKey(), c.getValue());
	}

	public static RowData fromDocument(Document d){
		Set<String> keys = d.keySet();
		RowData row = new RowData();
		for (String key : keys) 
			row.addColumn(key, d.get(key) );
		return row;
	}

	public void addColumn(Column column) {
		columns.add( column );
	}
	
	
	public Set<String> keySet(){
		Set<String> s = new HashSet<String>();
		for (Column c: columns)
			s.add(c.getKey());
		return s;
		
	}
	
	public Object getColumnValue(String key){
		if(map == null) toMap();
		Object o = map.get(key);
		if(o == null)
			return " ";
		return o;
	}
}
