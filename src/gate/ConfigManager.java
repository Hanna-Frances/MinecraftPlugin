package gate;

import java.io.*;
import java.util.*;
import org.bukkit.*;
import org.bukkit.configuration.file.*;
import org.bukkit.entity.*;

public class ConfigManager {
    
    //list of final Strings to name the files.
    public static final String CONFIGFILENAME = "gates.yml";
    public static final String CONFIGFILENAME_BROKEN = "gates_broken.yml";
    public static final String CONFIGFILENAME_OLD = "gates_old.yml";
    
    public FileConfiguration gateCFG;//CFG = configuration
    public File gateFile;
    
    /*
    * SetupFile
    * Makes new files if files to store gate plugin info does not exist.
    */
    public void SetupFile(){
        if(!Main.plugin.getDataFolder().exists()){//checks if folders exist, otherwise make a new folder to store files.
            Main.plugin.getDataFolder().mkdir();
        }
        //tests if gate.ymls exists
        gateFile = new File (Main.plugin.getDataFolder(), CONFIGFILENAME);
        
        if(!gateFile.exists()){
            Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " does not exist! >:( Creating new file.");
            try{
                gateFile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " has been created! (yay)");
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage("Could not create " + CONFIGFILENAME + ". Shutting down :(");
                return;
            }
            //yml file creation
           
        }//if ends
        
		try {
			gateCFG = YamlConfiguration.loadConfiguration(gateFile);
		} catch (Exception e) {
			resetConfig(true);// make them method!!
		}
		
		Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " has been loaded!");
        
    }//end of SetupFile
    
    /*
    * resetConfig
    * Makes new files if old files were found but broken.
    */
    public void resetConfig(boolean broken){
        if(broken){
            Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " is broken, making new files. Old files will be marked broken.");
            File newGatesFile = new File(Main.plugin.getDataFolder(), CONFIGFILENAME_BROKEN);
            
            try{
                FileWriter writer = new FileWriter(newGatesFile);
                FileReader reader = new FileReader(gateFile);
                
                writer.write(reader.read());
                
                writer.close();
                reader.close();
                
                
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage("Cannot create broken files, continuing.");
            }
        }
        else{
            Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " is an outdated file! Making new files and marking them old.");
            
            File newGatesFile = new File(Main.plugin.getDataFolder(), CONFIGFILENAME_OLD);
            
            try{
                FileWriter writer = new FileWriter(newGatesFile);
                FileReader reader = new FileReader(gateFile);
                
                writer.write(reader.read());
                
                writer.close();
                reader.close();
                
                
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage("Cannot create broken files, continuing.");
            }
        }//end of else
        
        try{
            gateFile.createNewFile();
            Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " was created! :D");
        }catch (IOException e){
            Bukkit.getServer().getConsoleSender().sendMessage("Created " + CONFIGFILENAME + " (yay) Shutting down...");
        }
        
        gateCFG = YamlConfiguration.loadConfiguration(gateFile);
        Bukkit.getServer().getConsoleSender().sendMessage("Reset Complete.");
    }//end of resetConfig
    
    
    /*
    * saveConfig
    * saves files, if cannot be saved then an error message is sent
    */
    public void saveConfig(){
        
        try{
            gateCFG.save(gateFile);
        }catch (IOException e){
            Main.plugin.getLogger().warning("File cannot be saved.");
        }
        
    }//end of saveConfig
    
    public void reloadConfig(){
        //tests if gate.ymls exists
        gateFile = new File (Main.plugin.getDataFolder(), CONFIGFILENAME);
        
        if(!gateFile.exists()){
            Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " does not exist! >:( Creating new file.");
            try{
                gateFile.createNewFile();
                Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " has been created! (yay)");
            }catch (IOException e){
                Bukkit.getServer().getConsoleSender().sendMessage("Could not create " + CONFIGFILENAME + ". Shutting down :(");
                return;
            }
            //yml file creation
            try{
                gateCFG = YamlConfiguration.loadConfiguration(gateFile);
            }catch (Exception e){
                resetConfig(true);
            }
            Bukkit.getServer().getConsoleSender().sendMessage(CONFIGFILENAME + " has been loaded!");
        }
    }//end of reloadConfig
    
}//end of class
