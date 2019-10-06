/*FORMAT OF THE SIGN:
    0. 
    1. [GATE] <- identifier
    2. insert name of gate
    3.
*/
package gate;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.Sound;

public class EventHandle implements Listener{
    
    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        
        if(event == null || event.getClickedBlock() == null || event.getPlayer() == null){
            return;
        }
        
        Player steve = event.getPlayer();
        
        if(event.getClickedBlock().getBlockData() instanceof org.bukkit.block.data.type.Sign || event.getClickedBlock().getBlockData() instanceof WallSign){
            if(event.getAction() == Action.RIGHT_CLICK_BLOCK){
                Sign sign = (Sign)event.getClickedBlock().getState();
                
                if(sign.getLine(0).equalsIgnoreCase("creeper")){
                    steve.sendMessage("aww man");
                }
                if(sign.getLine(0).equalsIgnoreCase("persona")){
                    steve.sendMessage("persona");
                }
                if(sign.getLine(0).equalsIgnoreCase("hmm")){
                    steve.sendMessage("hmm");
                    event.getClickedBlock().getLocation().getWorld().playSound(event.getClickedBlock().getLocation(), Sound.ENTITY_VILLAGER_AMBIENT, 5, 1);
                }
                
                if(sign.getLine(1).equals("[GATE]")){//check if [GATE] is written on second line
                   if(sign.getLine(2).equals(Gate.gates.keySet().contains(sign.getLine(2)))){//check if the gate name written on the third line exists
                       if(Gate.gates.get(sign.getLine(2)).getCoordsLocation().getBlock().getType() == Material.AIR){//check if the gate is open.
                           Gate.gates.get(sign.getLine(2)).close();
                       }
                       else{
                           Gate.gates.get(sign.getLine(2)).open();
                       }
                   }
                }
            }
        }
        
    }//end of onRightClick
    
}//end of class
