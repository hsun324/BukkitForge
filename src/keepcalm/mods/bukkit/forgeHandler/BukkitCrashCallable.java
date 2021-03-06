package keepcalm.mods.bukkit.forgeHandler;

import keepcalm.mods.bukkit.bukkitAPI.BukkitServer;

import org.bukkit.plugin.Plugin;

import cpw.mods.fml.common.ICrashCallable;

public class BukkitCrashCallable implements ICrashCallable {

	@Override
	public String call() throws Exception {
		String versionINFO = BukkitServer.version + " (Bukkit API version " + BukkitServer.apiVer + ")\nPlugins Loaded:";
		String endMsg;
		if (BukkitServer.instance() != null) {
			String plugins = "";
			int len = BukkitServer.instance().getPluginManager().getPlugins().length;
			int j = 0;
			for (Plugin i : BukkitServer.instance().getPluginManager().getPlugins()){
				String name = i.getDescription().getFullName() + ": " + (i.isEnabled() ? "Enabled" : "Disabled");
				if (j == 0) {
					plugins += name;
				}
				else if (j == len - 1) {
					plugins += " and " + name;
				}
				else {
					plugins += ", " + name;
				}
				j++;
			}
			if (plugins.isEmpty()) {
				plugins = "[No plugins loaded]";
			}
			endMsg = versionINFO + plugins;
		}
		else {
			endMsg = versionINFO + "[No plugins loaded yet]";
		}
		return endMsg;
	}

	@Override
	public String getLabel() {
		return "BukkitForge Details";
	}

}
