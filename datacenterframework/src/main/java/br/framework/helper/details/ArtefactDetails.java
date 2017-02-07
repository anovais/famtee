package br.framework.helper.details;

import org.springframework.web.multipart.MultipartFile;

import br.framework.domain.assessment.Artefact;
import br.framework.domain.instrument.Instrument;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016
 * Description: Usado para simplificar a apresentação dos dados de um {@linkplain Artefact artefato} na camada de visualização.
 */
public class ArtefactDetails {

	
	private MultipartFile file;
	private Artefact artefact;
	
	public ArtefactDetails(Artefact artefact){
		this.artefact = artefact;
	}
	public ArtefactDetails(){}
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public Artefact getArtefact() {
		return artefact;
	}
	public void setArtefact(Artefact artefact) {
		this.artefact = artefact;
	}

	
}
