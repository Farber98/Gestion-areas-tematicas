package gui.areas.modelos;
import gui.interfaces.IGestorAreas;
import gui.trabajos.modelos.GestorTrabajos;
import gui.trabajos.modelos.Trabajo;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author juan
 */
    public class GestorAreas implements IGestorAreas {
    // <editor-fold defaultstate="collapsed" desc="VARIABLES DE INSTANCIA">

        private static GestorAreas gestor;
        private List<Area> listaAreas = new ArrayList<>();
        private int posicionUltimaArea;

// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="SINGLETON">
        private GestorAreas() {
            this.leerAreas();
        }

        ; 
    
	
    public static GestorAreas instanciar() {
            
            if (gestor == null) {
                gestor = new GestorAreas();
            }
            return gestor;
        }

// </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="METODOS">
        @Override
        public String nuevaArea(String nombre) {
            String escritura = this.escribirAreas(); //Como el metodo escribirAreas devuelve un String con el resultado de la operacion, lo almacenamos en la variable escritura.
            Area UnArea;
            
            if (nombre.trim().isEmpty() || nombre == null) //Si el nombre esta vacio o es nulo.
            {
                return ERROR_NUEVA_AREA_VACIA;                
            }
            
            UnArea = new Area(nombre);
            if (listaAreas.contains(UnArea)) //Si la lista ya contiene dicha area.
            {
                return ERROR_NUEVA_AREA_DUPLICADA;                
            }
            
            listaAreas.add(UnArea);     //Si pasa los controles, el area es agregada.
            Collections.sort(this.listaAreas); //Ordenamos areas alfabeticamente.
            if (escritura.equals(ESCRITURA_OK)) //Si la escritura es exitosa.
            {
                escribirAreas();            //Actualizo archivo.
                return EXITO_NUEVA_AREA;
            }
            
            return ERROR_NUEVA_AREA;
            
        }
        
        @Override
        public String borrarArea(Area area) {
            String escritura = this.escribirAreas();
            GestorTrabajos miGestorTrabajos = GestorTrabajos.instanciar();
            
            if (area == null) 
            {
                return ERROR_BORRAR_AREA;
            }
            
            if (!listaAreas.contains(area)) //No se puede borrar un area que no esta contenida en la lista.
            {                
                return ERROR_BORRAR_AREA_INEXISTENTE;                
            }
            
            for (Trabajo i : miGestorTrabajos.buscarTrabajos()) {                
                if (i.getAreas().contains(area)) 
                {                    
                    return ERROR_BORRAR_AREA_EN_USO;     //No podemos borrar un area contenida en un trabajo.
                }
            }
            
            for (Area i : listaAreas) {
                if (i.equals(area))      //Si algun area de la lista es igual al area que se quiere borrar.
                {
                    
                    listaAreas.remove(i);       //Borramos esa area
                    i = null;
                    if (escritura.equals(ESCRITURA_OK)) //Si la escritura se realiza sin errores.
                    {
                        escribirAreas();                //Actualizo archivo.
                        return EXITO_BORRAR_AREA;
                    } else {
                        return ESCRITURA_ERROR;
                    }
                }
            }
            
            return ERROR_BORRAR_AREA;
        }
        
        @Override
        public List<Area> buscarAreas(String nombre) {
            List<Area> areasBuscadas = new ArrayList<>();
            List<Area> listaVacia = new ArrayList<>();
            
            if (nombre == null || nombre.isEmpty()) //Si el nombre es igual a nulo o esta vacio       
            {
                Collections.sort(listaAreas);       //Ordeno alfabeticamente y devuelto todo.
                return listaAreas;                
            }
            
            for (Area area : listaAreas) {
                if (area.verNombre().toUpperCase().startsWith(nombre.toUpperCase())) //Comparo si coinciden los nombres en mayusculas. Metodo StartsWith para implementar busqueda parcial. 
                {                    
                    areasBuscadas.add(area);        //Si coinciden, agrego esa area a la lista a retornar.
                }
            }
            
            if (areasBuscadas.isEmpty()) {                
                return listaVacia;            //Si no se encontraron coincidencias, devuelvo la lista vacia.
            }
            
            Collections.sort(areasBuscadas);
            return areasBuscadas;
        }
        
        @Override
        public Area dameArea(String nombre) {
            
            for (Area unArea : listaAreas) {
                if (unArea.verNombre().equalsIgnoreCase(nombre)) // En caso de coincidencia, devuelve ese nombre. Sino sigue buscando.
                {
                    return unArea;
                }
            }
            return null; // Si no se encontro area devolvemos nulo.
        }
        
        public int verUltimaArea() {            
            this.posicionUltimaArea = listaAreas.size();     //El metodo size devuelve el tamaño de la lista. Lo que haremos sera almacenar el tamaño en la variable posicionUltimaArea ya que ahi se encontrara el ultimo elemento de la tabla. 
            return this.posicionUltimaArea;                          
        }
        
        public void cancelar() {
            this.posicionUltimaArea = -1;  //Asigmanos la variable posicionUltimaArea en -1.
        }
        
        public int ordenArea(Area area) {
            int posicion = -1;
            for (Area unArea : listaAreas) {
                if (area.equals(unArea)) {
                    posicion = listaAreas.indexOf(unArea);  //Metodo indexOf devuelve la posicion de unArea, la cual coincide con el parametro area que recibe el metodo.
                    return posicion;                    
                }
            }                                               
            
            return posicion;        //Si no hubo coincidencias, retornamos el -1 por defecto.
        }

// </editor-fold>  
    
    // <editor-fold defaultstate="collapsed" desc="ARCHIVOS">
        public String escribirAreas() {
            File f = new File("./Areas.txt");
            try {
                FileWriter fw = new FileWriter(f);
                BufferedWriter bfw = new BufferedWriter(fw);
                
                for (Area i : listaAreas) {
                    bfw.write(i.verNombre());
                    bfw.newLine();
                }
                bfw.close();
                return ESCRITURA_OK;                
            } catch (IOException ex) {
                return ESCRITURA_ERROR;
            }
        }
        
        public String leerAreas() {
            File f = new File("./Areas.txt");
            
            if (f.exists()) {
                try (BufferedReader bfr = new BufferedReader(new FileReader(f))) {
                    
                    String nombreArea;
                    while ((nombreArea = bfr.readLine()) != null) {
                        Area unArea = new Area(nombreArea);
                        this.listaAreas.add(unArea);
                    }
                    bfr.close();
                    return LECTURA_OK;                    
                } catch (IOException ex) {
                    return LECTURA_ERROR;
                }
            }
            return ARCHIVO_INEXISTENTE;
        }

// </editor-fold>  
    
}

