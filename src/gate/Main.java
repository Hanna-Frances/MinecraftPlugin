package gate;

import org.bukkit.plugin.java.JavaPlugin;
import java.util.*;
import org.bukkit.Bukkit;

public class Main extends JavaPlugin{

    @Override
    public void onEnable(){
        Bukkit.getServer().getConsoleSender().sendMessage("persona");
    }
}
