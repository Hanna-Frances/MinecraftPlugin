package gate;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin{
	
	private commandManager cmdManager;

    @Override
    public void onEnable(){
        Bukkit.getServer().getConsoleSender().sendMessage("persona");
        
        cmdManager = new commandManager();
        
        getCommand("gate").setExecutor(cmdManager);
        getCommand("gate").setTabCompleter(cmdManager);
        
    }
    
    
}
