package gate;

import org.bukkit.block.Sign;
import org.bukkit.block.data.type.WallSign;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;

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
            }
        }
        
    }//end of onRightClick
    
}//end of class
