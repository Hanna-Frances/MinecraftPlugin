
package gate;

import java.util.*;
import org.bukkit.Material;

public enum colourChoices {
    OAK("oak", Material.OAK_FENCE),
    BIRCH("birch", Material.BIRCH_FENCE),
    JUNGLE("jungle", Material.JUNGLE_FENCE),
    SPRUCE("spruce", Material.SPRUCE_FENCE),
    DARKOAK("dark_oak", Material.DARK_OAK_FENCE),
    ACACIA("acacia", Material.ACACIA_FENCE)
    ;
        String c;
        Material f;
        
        private colourChoices(String colour, Material fence){
            c = colour;
            f = fence;
        }
        
        @Override
        public String toString(){
            return c;
        }
        
        public Material getMaterial(){
            return f;
        }
        
        private static final List<colourChoices> LIST = Collections.unmodifiableList(Arrays.asList(values()));
        
        /*
        * Oak
        * Birch
        * Jungle
        * Spruce
        * DarkOak
        * Acacia
        */
        public static Material getMaterialFromColour(String colour){
            
            for(int a = 0; a < LIST.size(); a++){
                
                if(colour.equalsIgnoreCase(LIST.get(a).toString())){
                    
                    return LIST.get(a).getMaterial();
                    
                }
                
            }
            
            return LIST.get(0).getMaterial();//default to oak fence if they dont know how to spell
            
        }
        
        public static List<String> returnList(){
            ArrayList<String> l = new ArrayList<>();
            
            for(int b = 0; b < LIST.size(); b++){
                l.add(LIST.get(b).toString());
            }
            
            return l;
        }
        
        public static String getColourFromMaterial(Material m){
            
            for(int a = 0; a < LIST.size(); a++){
                
                if(m == LIST.get(a).getMaterial()){
                    
                    return LIST.get(a).toString();
                    
                }
                
            }
            
            return LIST.get(0).toString();//default to oak fence if they dont know how to spell
        }
        
}
