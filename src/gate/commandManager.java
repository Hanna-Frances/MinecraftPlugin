package gate;

import java.util.List;
import java.util.ArrayList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.util.BlockVector;
import java.lang.Integer;
import org.bukkit.entity.Player;

public class commandManager implements TabExecutor {

    /*
    This method is for tab complete 
    */
    
    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] strings) {
       
        if(!(cs instanceof Player)){
            return null;
        }
        
        if (cmnd.getName().equalsIgnoreCase("gate")){
            if (strings.length == 1){
                ArrayList<String> myGift = new ArrayList<>();//ArrayList will be the suggested options that show up
                myGift.add("create");
                myGift.add("delete");
                
                return myGift;
            }
            else if(strings.length == 2){
                if(strings[0].equalsIgnoreCase("delete")){
                    ArrayList<String> deleteName = new ArrayList<>();
                    for(String key : Gate.gates.keySet()){
                        deleteName.add(key);
                    }
                    return deleteName;
                }
                else if(strings[0].equalsIgnoreCase("create")){
                    
                    return new ArrayList<>();
                }
            }
            else if(strings.length == 3){
                if(strings[0].equalsIgnoreCase("create")){
                    return colourChoices.returnList();
                }
            }
        }
        
       return null;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        
        if(!(cs instanceof Player)){//if anything but a player tries to perform a command
            cs.sendMessage("sucks to suck, you can't do that.");
        }
        
        if (cmnd.getName().equalsIgnoreCase("gate")){
            if (strings.length > 0){
                if(strings[0].equalsIgnoreCase("create")){
                    if(strings.length == 9){
                        Material mat = colourChoices.getMaterialFromColour(strings[2]);//convert String to material
                        if(mat != null){
                            int x1 = 0, y1 = 0, z1 = 0, x2 = 0, y2 = 0, z2 = 0;
                            
                            try{
                            x1 = Integer.parseInt(strings[3]);
                            y1 = Integer.parseInt(strings[4]);
                            z1 = Integer.parseInt(strings[5]);
                            x2 = Integer.parseInt(strings[6]);
                            y2 = Integer.parseInt(strings[7]);
                            z2 = Integer.parseInt(strings[8]);
                           }catch(Exception e){
                                cs.sendMessage("bad coords");
                                return true;
                           }
                           //if((x1 != null) && (y1 != null) && (z1 != null) && (x2 != null) && (y2 != null) && (z2 != null)){
                               Gate.gates.put(strings[1], new Gate(new BlockVector(getSmall(x1, x2), getSmall(y1, y2), getSmall(z1, z2)), new BlockVector(getBig(x1, x2), getBig(y1, y2), getBig(z1, z2)), mat, ((Player)cs).getWorld(), strings[1]));
                               Main.plugin.saveConfig();
                               cs.sendMessage("Coordinates were done successfully.");
                               return true;
                           /*}
                           else{
                                cs.sendMessage("bad coords");
                                //badMessage(cs);
                                return true;
                           }*/
                        }//end of valid material check
                        else{
                            //cs.sendMessage("bad material");
                            badMessage(cs);
                            return true;
                        }
                    }//end of string length = 9 check
                    else{
                        badMessage(cs);
                        //cs.sendMessage("bad length");
                        return true;
                    }
                }//end of create check
                else if (strings[0].equalsIgnoreCase("delete")){
                    
                    if(strings.length == 2){
                        if (Gate.gates.containsKey(strings[1])){
                            Gate.gates.remove(strings[1]);
                            Main.plugin.config.removeGate(strings[1]);
                            cs.sendMessage("Delete was done successfully.");
                        }
                        else{
                            cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4that doesnt exist >:("));
                        }
                    }//end of strings length = 2 check
                    else{
                        badMessage(cs);
                        return true;
                    }
                }//end of delete check
                else{
                    badMessage(cs);
                    return true;
                }
            }//end of strings length > 0 check
            else {
                badMessage(cs);
                return true;
            }
        }//end of gate check
        return false;
    }
    
    /*
    * badMessage
    * Returns a message to the player stating the suggested commands that they can use for the gate plugin
    */
    private void badMessage(CommandSender cs){
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lSucks to suck, you can't do that >:("));
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/gate create &3<gate name> <block> <x1> <y1> <z1> <x2> <y2> <z2>"));
        cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6/gate delete &3<gate name>"));
    }
    
    private int getSmall(int a, int b){
        int c = a <= b ? a : b;
        return c;
    }
    private int getBig(int a, int b){
        int c = a >= b ? a : b;
        return c;
    }
}
