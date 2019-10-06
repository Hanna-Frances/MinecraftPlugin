/*
This class will deal with the fences part of the gate
*/

package gate;

import org.bukkit.util.BlockVector;
import org.bukkit.*;
import java.util.HashMap;

public class Gate {
    
    //storing all the gates
    public static HashMap<String, Gate> gates = new HashMap<String, Gate>();
    
    //these new variables will define the coordinates of the gate.
    private BlockVector coords;
    private BlockVector coords2;
    private Material type;
    private World world;
    private String name;
    
    protected BlockVector getCoords(){
        return coords;
    }
    
    protected Location getCoordsLocation(){
        return new Location(world, coords.getX(), coords.getY(), coords.getZ());
    }
    
    protected BlockVector getCoords2(){
        return coords2;
    }
    
    protected Material getMaterial(){
        return type;
    }
    
    protected World getWorld(){
        return world;
    }
    
    protected String getName(){
        return name;
    }
    
    public Gate(BlockVector c1, BlockVector c2, Material t, World w, String s){//constructor, main method for gate java not really
        coords = c1;
        coords2 = c2;
        type = t;
        world = w;
        name = s;
    }//end of gate
    
    public void open(){
        for(int x = coords.getBlockX(); x <= coords2.getBlockX(); x++){
            for(int y = coords.getBlockY(); y <= coords2.getBlockY(); y++){
                for(int z = coords.getBlockZ(); z <= coords2.getBlockZ(); z++){
                    world.getBlockAt(x, y, z).setType(Material.AIR);
                }//end of z
            }//end of y
        }//end of x
    }//end of open
    
    public void close(){
        for(int x = coords.getBlockX(); x <= coords2.getBlockX(); x++){
            for(int y = coords.getBlockY(); y <= coords2.getBlockY(); y++){
                for(int z = coords.getBlockZ(); z <= coords2.getBlockZ(); z++){
                    world.getBlockAt(x, y, z).setType(type);
                }//end of z
            }//end of y
        }//end of x
    }//end of open
}
