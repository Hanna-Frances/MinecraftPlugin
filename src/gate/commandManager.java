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

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] strings) {
       
        if(!(cs instanceof Player)){
            return null;
        }
        
        if (cmnd.getName().equalsIgnoreCase("gate")){
            if (strings.length == 1){
                ArrayList<String> myGift = new ArrayList<>();
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
            }
        }
        
       return null;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        
        if(!(cs instanceof Player)){
            cs.sendMessage("sucks to suck, you can't do that.");
        }
        
        if (cmnd.getName().equalsIgnoreCase("gate")){
            if (strings.length > 0){
                if(strings[0].equalsIgnoreCase("create")){
                    if(strings.length == 9){
                        Material mat = Material.getMaterial(strings[2]);
                        if(mat != null){
                           Integer x1 = Integer.getInteger(strings[3]);
                           Integer y1 = Integer.getInteger(strings[4]);
                           Integer z1 = Integer.getInteger(strings[5]);
                           Integer x2 = Integer.getInteger(strings[6]);
                           Integer y2 = Integer.getInteger(strings[7]);
                           Integer z2 = Integer.getInteger(strings[8]);
                           
                           if(x1 != null && y1 != null && z1 != null && x2 != null && y2 != null && z2 != null){
                               Gate.gates.put(strings[1], new Gate(new BlockVector(getSmall(x1, x2), getSmall(y1, y2), getSmall(z1, z2)), new BlockVector(getBig(x1, x2), getBig(y1, y2), getBig(z1, z2)), mat, ((Player)cs).getWorld(), strings[1]));
                               cs.sendMessage("Coordinates were done successfully.");
                               return true;
                           }
                           else{
                                badMessage(cs);
                                return true;
                           }
                        }
                        else{
                            badMessage(cs);
                            return true;
                        }
                    }
                    else{
                        badMessage(cs);
                        return true;
                    }
                }
                else if (strings[0].equalsIgnoreCase("delete")){
                    
                    if(strings.length == 2){
                        if (Gate.gates.containsKey(strings[1])){
                            Gate.gates.remove(strings[1]);
                            cs.sendMessage("Delete was done successfully.");
                        }
                        else{
                            cs.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4that doesnt exist >:("));
                        }
                    }
                    else{
                        badMessage(cs);
                        return true;
                    }
                }
                else{
                    badMessage(cs);
                    return true;
                }
            }
            else {
                badMessage(cs);
                return true;
            }
        }
        return false;
    }
    
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