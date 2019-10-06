package gate;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin{
	
        public static Main plugin;
    
	private commandManager cmdManager;
        protected ConfigIO config;
        
    @Override
    public void onEnable(){
        plugin = this;//current object working with
        
        Bukkit.getServer().getConsoleSender().sendMessage("persona");
        
        cmdManager = new commandManager();
        config = new ConfigIO(new ConfigManager());
        
        config.initialize();
        
        getCommand("gate").setExecutor(cmdManager);
        getCommand("gate").setTabCompleter(cmdManager);
        
        getServer().getPluginManager().registerEvents(new EventHandle(), this);
    }//end of onEnable
    
    @Override
    public void onDisable(){
        config.save();
    }
    
    
    public void saveConfig(){
        for(String key : Gate.gates.keySet()){
            config.setGate(Gate.gates.get(key));
        }
    }//end of saveConfig
}
