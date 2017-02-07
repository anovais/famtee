package br.framework.core.context.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.Context;
import br.framework.core.context.IActivity;
import br.framework.core.context.UploadTelemetryContext;
import br.framework.dao.impl.TelemetryDAO;
import br.framework.domain.mission.Telemetry;

/**
 * Author: Andre Novais <br>
 * Date: 11/2016 <br>
 * Description: Responsável por inserir as telemetrias em um banco de dados NOSql
 */
@Component
public class TelemetryInsertActivity implements IActivity<Telemetry>{

	@Autowired
	TelemetryDAO dao;

	@Override
	public void execute(Telemetry telemetry, Context context) {
		dao.insert(telemetry);
	}

}
