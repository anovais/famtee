package br.framework.core.context.activity;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import br.framework.core.context.Context;
import br.framework.core.context.IActivity;
import br.framework.core.context.SaveInstrumentContext;
import br.framework.domain.PhotoEntity;
import br.framework.domain.instrument.Instrument;
import br.framework.domain.mission.SpaceMission;
import br.framework.helper.FilePathBuilder;
import br.framework.helper.FileSaver;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Uma atividade do contexto {@link SaveInstrumentContext}<br>
 * Utilizada para gravar a foto da tecnologia na estrutura de armazenamento de arquivos interna<br>  
 *
 */
@Component
public class PhotoUploadActivity implements IActivity<PhotoEntity>{
	

	@Autowired
	private FilePathBuilder pathBuilder;

	@Autowired
	private FileSaver fileSaver;
	
	@Override
	public void execute(PhotoEntity entity, Context context) {
		MultipartFile photo = (MultipartFile) context.getParameter("photo");
		String path;
		if(photo != null){
			path = pathBuilder.photoDirectory(entity);
			path = fileSaver.write(path, photo);
		}
		else 
			path = (String) context.getParameter("defaultPhoto");

		entity.setPhotoPath(path);
	}
}
