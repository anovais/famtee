package br.framework.domain.assessment;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.framework.domain.GenericEntity;
import br.framework.domain.util.Period;

/**
 * Author: Andre Novais <br>
 * Date: 04/2016 <br>
 * Description: Descreve como deve ser um Artefato de Maturidade Tecnológica
 */

@Entity
public class Artefact implements GenericEntity, Serializable{
	
	@Id @GeneratedValue(strategy = GenerationType.TABLE)
	private Long id;
	@Lob	
	private String description;
	
	private String name;
	
	private String code;
	
	@OneToOne
	private ArtefactType type;
	
	
	private String extension;
	
	@Embedded
	private Period period;
	
	@DateTimeFormat
	private Calendar uploadDate;
	
	@OneToOne
	private Environment environment;
	
	@Transient
	private MultipartFile file;
	
	private String resourceURL;

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ArtefactType getType() {
		return type;
	}

	public void setType(ArtefactType type) {
		this.type = type;
	}

	public Period getPeriod() {
		return period;
	}

	public void setPeriod(Period period) {
		this.period = period;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public String getResourceURL() {
		return resourceURL;
	}

	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public void fillUsingFile() {
		name = file.getName();
	}

	public Calendar getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Calendar uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public String getCode() {
			return code;		

	}

	public void setCode(String code) {
		this.code = code;
	}
	
}
