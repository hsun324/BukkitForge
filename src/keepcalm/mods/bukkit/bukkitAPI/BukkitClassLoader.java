package keepcalm.mods.bukkit.bukkitAPI;

import java.net.URL;
import java.net.URLClassLoader;

public class BukkitClassLoader extends URLClassLoader {
	
	public BukkitClassLoader(URL[] urls, ClassLoader parent) {
		super(urls, parent);
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String newName = name;
		
		if (newName.startsWith("org.bukkit.craftbukkit")) {
			newName.replace("org.bukkit.craftbukkit", "keepcalm.mods.bukkit.bukkitAPI");
			newName.replace("Craft", "Bukkit");
		}
		
		if (newName.startsWith("com.google")) {
			System.out.print("Redirecting class request: " + newName);
			// guava 10 auto redirect - no more ess port!
			newName = "guava10." + newName;
			System.out.println(" to " + newName);
		}
		
		
		return super.findClass(newName);
	}

}