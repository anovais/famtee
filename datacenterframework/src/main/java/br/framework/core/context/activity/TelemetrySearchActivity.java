package br.framework.core.context.activity;

import java.io.File;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.json.CDL;
import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import br.framework.core.context.Context;
import br.framework.core.context.IActivity;
import br.framework.dao.impl.TelemetryDAO;
import br.framework.domain.mission.Telemetry;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class TelemetrySearchActivity implements IActivity<Telemetry>{

	@Autowired
	TelemetryDAO dao;
	
	@Override
	public void execute(Telemetry telemetry, Context context) {
		dao.findByPeriod(telemetry);
	}

}
