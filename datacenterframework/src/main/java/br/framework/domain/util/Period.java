package br.framework.domain.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Embeddable;

import org.springframework.format.annotation.DateTimeFormat;

import br.framework.domain.GenericEntity;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Period.java
 * Description: Compreende um periodo de tempo 
 *
 */
@Embeddable
public class Period implements GenericEntity{
	
	@DateTimeFormat
	private Calendar start;
	@DateTimeFormat
	private Calendar end;
	
	
	public Period(){
		start = new GregorianCalendar();
	}
	
	public Period(Calendar start){
		this.start = start;
	}

	public Period(Calendar from, Calendar to) {
		start = from;
		end = to;
	}

	public Period(Date from, Date to) {
		start = Calendar.getInstance();
		end = Calendar.getInstance();
		start.setTime(from);
		end.setTime(to);
	}

	public void finish(Calendar date){
		end = date;
	}
	
	public void finish(){
		end = new GregorianCalendar();
	}
	
	
	
	
	
	/**
	 * @return the start
	 */
	public Calendar getStart() {
		return start;
	}
	
	/**
	 * @return the end
	 */
	public Calendar getEnd() {
		return end;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}
	
	
	
	

}
