package gate;

import java.util.*;
import org.bukkit.*;
import org.bukkit.configuration.file.*;
import org.bukkit.util.BlockVector;

public class ConfigIO {
    
    private ConfigManager manager;
    private FileConfiguration CFG;
    
    //labelling important info of each gate that is made.
    private static final String TYPE = ".type"; 
    private static final String COORD1 = ".coord1";
    private static final String COORD2 = ".coord2";
    private static final String WORLD = ".world";
    private static final String X = ".x";
    private static final String Y = ".y";
    private static final String Z = ".z";
    
    public ConfigIO(ConfigManager m){
        
        manager = m;
        CFG = manager.gateCFG;
    }
    
    public void setGate(Gate g){
        CFG.set(g.getName() + TYPE, colourChoices.getColourFromMaterial(g.getMaterial()));//setting TYPE to the material translated to the colour
        CFG.set(g.getName() + COORD1 + X, g.getCoords().getBlockX());
        CFG.set(g.getName() + COORD1 + Y, g.getCoords().getBlockY());
        CFG.set(g.getName() + COORD1 + Z, g.getCoords().getBlockZ());
        CFG.set(g.getName() + COORD2 + X, g.getCoords2().getBlockX());
        CFG.set(g.getName() + COORD2 + Y, g.getCoords2().getBlockY());
        CFG.set(g.getName() + COORD2 + Z, g.getCoords2().getBlockZ());
        CFG.set(g.getName() + WORLD, g.getWorld().getUID().toString());
    }
    
    public Gate getGate(String name){
        String type = CFG.getString(name + TYPE);
        int coord1x = CFG.getInt(name + COORD1 + X);
        int coord1y = CFG.getInt(name + COORD1 + Y);
        int coord1z = CFG.getInt(name + COORD1 + Z);
        int coord2x = CFG.getInt(name + COORD2 + X);
        int coord2y = CFG.getInt(name + COORD2 + Y);
        int coord2z = CFG.getInt(name + COORD2 + Z);
        String World = CFG.getString(name + WORLD);
        
        Material m = colourChoices.getMaterialFromColour(type);
        BlockVector c1 = new BlockVector(coord1x, coord1y, coord1z);
        BlockVector c2 = new BlockVector(coord2x, coord2y, coord2z);
        World w = Bukkit.getServer().getWorld(UUID.fromString(World));
        
        return new Gate(c1, c2, m, w, name);
    }
    
    public List<String> getAll(){
        return CFG.getStringList("");
    }
    
    public void save(){
        manager.saveConfig();
    }
}
