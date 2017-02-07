package br.framework.helper;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.framework.domain.instrument.Instrument;


/**
 * Author: Andre Novais <br>
 * Date: 10/2016
 * Description: Classe para realizar a gravação de arquivos 
 * O upload de arquivos feitos por formulários utilizam 
 * dessa classe para efetivamente gravar os arquivos em disco 
 * informando qual a url da pasta.
 */
@Component
public class FileSaver {

	
	@Autowired
	HttpServletRequest request;

	/**
	 * Grava e retorna o PATH do arquivo
	 * @param baseFolder pasta base do instrumento
	 * @param file	arquivo a ser gravado
	 * @return o caminho completo de onde o arquivo foi salvo.
	 */
	public String write(String baseFolder, MultipartFile file) {
		String realPath = request.getServletContext().getRealPath("/"+baseFolder);
		
		File directory = new File(realPath) ;
		if(!directory.exists()) directory.mkdirs();
		
		try {
			String path = realPath+"/"+file.getOriginalFilename();
			file.transferTo(new File(path));
			return baseFolder+"/"+file.getOriginalFilename();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}

