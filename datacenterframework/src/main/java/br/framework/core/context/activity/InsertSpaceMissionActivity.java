package br.framework.core.context.activity;

import org.apache.log4j.net.SMTPAppender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.framework.core.context.Context;
import br.framework.core.context.IActivity;
import br.framework.dao.impl.SpaceMissionDAO;
import br.framework.domain.mission.SpaceMission;

@Component
public class InsertSpaceMissionActivity implements IActivity<SpaceMission>{

	@Autowired
	SpaceMissionDAO dao;
	@Override
	public void execute(SpaceMission mission, Context context) {
		dao.insert(mission);
	}

}
