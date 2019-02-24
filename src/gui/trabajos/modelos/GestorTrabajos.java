package gui.trabajos.modelos;
import gui.areas.modelos.Area;
import gui.interfaces.IGestorAreas;
import java.util.ArrayList;

/**
 *
 * @author gabinete
 */
public class GestorTrabajos {
    private static GestorTrabajos gestor;
    private ArrayList<Trabajo> listaTrabajos = new ArrayList<>();
    
    private GestorTrabajos(){};
    
    
    public static GestorTrabajos instanciar(){
        
        if( gestor == null){
            gestor = new GestorTrabajos();
        }
        return gestor;
    }
        
        /**
         * Metodo usado por GestorAreas para controlar si el area esta siendo usada por un trabajo.
         * @param area
         * @return STRING - (AREA_EN_USO || AREA_SIN_USO)
         */
        public String ChequearAreaEnTrabajo(Area area)    
        {
            for(Trabajo unTrabajo : this.listaTrabajos)
            {
                for(Area unArea : unTrabajo.verAreas())
                {
                    if(unArea.equals(area))
                    {
                        return IGestorAreas.AREA_EN_USO;
                    }
                }
                
            }
            return IGestorAreas.AREA_SIN_USO;
        }

    
}
