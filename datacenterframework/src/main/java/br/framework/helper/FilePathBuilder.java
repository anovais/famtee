package br.framework.helper;

import org.springframework.stereotype.Component;

import br.framework.domain.PhotoEntity;

/**
 * Author: Andre Novais <br>
 * Date: 10/2016
 * Description: Gerencia a estrutura interna de arquivos para cada {@linkplain PhotoEntity CodifiedEntityo}
 */
@Component
public class FilePathBuilder {
	

	/*
	 * Raiz da árvore de diretórios que guardam todos os arquivos 
	 */
	public static final String BASE_DIRECTORY = "archives";
	
	public String initFile(PhotoEntity entity){		
		
		StringBuilder path = new StringBuilder("/")
		.append(BASE_DIRECTORY).append("/")
		.append(entity.getCode());
		return path.toString();
		
	}

	public String artefactsDirectory(PhotoEntity entity) {
		return rootDirectory(entity) + "/artefacts";
	}

	public String photoDirectory(PhotoEntity entity) {
		return rootDirectory(entity) + "/photos";
	}

	public String rootDirectory(PhotoEntity entity){		
		String entityDirectory = entity.getCode();		
		return BASE_DIRECTORY + "/" + entityDirectory;
		
	}
	
	

}
