package gate;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin{
	
        public static Main plugin;
    
	private commandManager cmdManager;
        private ConfigIO config;
        
    @Override
    public void onEnable(){
        plugin = this;//current object working with
        
        Bukkit.getServer().getConsoleSender().sendMessage("persona");
        
        cmdManager = new commandManager();
        config = new ConfigIO(new ConfigManager());
        
        getCommand("gate").setExecutor(cmdManager);
        getCommand("gate").setTabCompleter(cmdManager);
        
        getServer().getPluginManager().registerEvents(new EventHandle(), this);
    }
    
    @Override
    public void onDisable(){
        config.save();
    }
}
