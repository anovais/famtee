package br.framework.core.context;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * Author: Andre Novais <br>
 * Date: 19/10/2016 <br>
 * Description: Contexto é uma rotina onde várias {@linkplain IActivity atividades} são executadas em uma ordem específica. <br>
 * Sua execução pode gerar resultados como uma pesquisa na base de dados ou um outro tipo de retorno. <br>  
 *
 */
@Component
public class Context<T> {

	
	
	private NavigableEntity navigableEntity;
	
	private Map<String,Object> parametros;
	
	private Result result;
	
	private String contextName;
	
	private Boolean sucesso = true;
	
	private List<IActivity<T>> activities;
	
	
	protected  void run(NavigableEntity entity){
		navigateOver(entity);
		run();
	}
	
	protected void run(){
		List<IActivity<T>> activities = getActivities();
		//Navegação das atividades
		for (IActivity<T> activity : activities) {
			activity.execute((T)getNavigableEntity(), this);
			if(!isSucceed().booleanValue())
				break;
		}
	}
	
	protected void registerActivity(IActivity c){
			activities.add(c);
	}
	
	
	
	public Boolean isSucceed() {
		return sucesso;
	}
	
	public void reportFail() {
		this.sucesso = false;
	}

	public Context() {
		parametros = new HashMap<String, Object>();
		activities = new ArrayList<IActivity<T>>();
	}
	
	
	public Context<T> navigateOver(NavigableEntity entity){
		this.navigableEntity = entity;
		return this;
	}
	
	public List<IActivity<T>> getActivities() {
		return activities;
	}

	public Context<T> addParameter(String name, Object object){
		parametros.put(name, object);
		return this;
	}

	public NavigableEntity getNavigableEntity() {
		return navigableEntity;
	}

	public Object getParameter(String name) {
		return  parametros.get(name);
	}

	public String getContextName() {
		return contextName;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}


	public boolean hasResult(){
		return result != null;
	}
	
	
}
