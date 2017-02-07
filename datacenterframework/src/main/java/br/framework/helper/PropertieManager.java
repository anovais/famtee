package br.framework.helper;

import java.util.Locale;
import java.util.ResourceBundle;

import br.framework.domain.instrument.Instrument;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016
 * Description: Gerencia properties e arquivos de mensagens.
 */
public class PropertieManager {
	
	public static String getMessage(String key){
		ResourceBundle r = ResourceBundle.getBundle("messages", new Locale("en","US"));
		return r.getString(key);
	}
	
}
