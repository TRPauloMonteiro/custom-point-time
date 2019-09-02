package tr.customteam.point_time.core;

import java.util.HashMap;

import tr.customteam.point_time.core.modules.FCModule;
import tr.customteam.point_time.core.modules.IModule;
import tr.customteam.point_time.core.options.OptionsProfile;

public class Core {
	private OptionsProfile profile;
	private HashMap<String, IModule> activeModules;
	
	public Core(OptionsProfile profile) {
		this.profile = profile;
		activeModules = new HashMap<String, IModule>();
	}
	
	public Core initModules() {
		if(profile.isFolhaCertaModule()) {
			activeModules.put("fc", new FCModule(profile));
		}
//		
//		if(profile.isJiraModule()) {
//			activeModules.put("jira", true);
//		}
		
		return this;
	}
	
}
