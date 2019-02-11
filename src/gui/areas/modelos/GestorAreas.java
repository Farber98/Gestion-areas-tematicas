/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.modelos;

import gui.interfaces.IGestorAreas;
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
    private static GestorAreas gestor;
    private List<Areas> listaAreas = new ArrayList<>();
    private int posicionUltimaArea;
    
    private GestorAreas()
    {
        this.leerAreas();
    }; 
    
	
    public static GestorAreas instanciar()
    {
        
        if( gestor == null)
        {
            gestor = new GestorAreas();
        }
        return gestor;
    }
    
    @Override
    public String nuevaArea(String nombre) 
    {
        String escritura = this.escribirAreas();
        Areas UnArea;
        
        if (nombre.isEmpty() || nombre == null)    //Si el nombre esta vacio o es nulo
        {
            return ERROR_NUEVA_AREA_VACIA; 
        }
        
        UnArea = new Areas(nombre);
        if(listaAreas.contains(UnArea))     //Si la lista ya contiene dicha area.
        {
           return ERROR_NUEVA_AREA_DUPLICADA; 
        }
        
        listaAreas.add(UnArea);     //Si pasa los controles, el area es agregada.
        Collections.sort(this.listaAreas); //Ordenamos areas alfabeticamente.
        if(escritura.equals(ESCRITURA_OK))
        {
            //VER AQUI
            return EXITO_NUEVA_AREA;
        }
        
        return ERROR_NUEVA_AREA;
        
    }

    @Override
    public String borrarArea(Areas area) {
        String escritura = this.escribirAreas();
        GestorTrabajos miGestorTrabajos = GestorTrabajos.instanciar();

        if( area == null)
        {
            return ERROR_BORRAR_AREA;
        }
 
        if( !listaAreas.contains(area))     //No se puede borrar un area que no esta contenida en la lista.
        {    
            return ERROR_BORRAR_AREA_INEXISTENTE;       
        }
        
        for( Trabajo i : miGestorTrabajos.buscarTrabajos())
        { 
            if( i.getAreas().contains(area))
            { 
                return ERROR_BORRAR_AREA_EN_USO;     //No podemos borrar un area contenida en un trabajo.
            }
        }
        
        
        for( Areas i : listaAreas)
        {
            if( i.equals(area))     //Si algun area de la lista es igual al area que se quiere borrar.
            {
                
                listaAreas.remove(i);       //Borramos esa area
                i = null;
                if(escritura.equals(ESCRITURA_OK))   //Si la escritura se realiza sin errores.
                {
                    return EXITO_BORRAR_AREA;
                }
                else
                {
                    return ESCRITURA_ERROR;
                }
            }
        }
        
        return ERROR_BORRAR_AREA;
    }
    
     @Override
    public List<Areas> buscarAreas(String nombre) 
    {
        List<Areas> areasBuscadas = new ArrayList<>();
        
        if( nombre == null || nombre.isEmpty() )   //Si el nombre es igual a nulo o esta vacio       
        {
            Collections.sort(listaAreas);       //Ordeno alfabeticamente y devuelto todo.
            return listaAreas;                      
        }
        
        for( Areas area : listaAreas)
        {
            if( area.getNombre().toUpperCase().startsWith(nombre.toUpperCase()))      //Comparo si coinciden los nombres en mayusculas. Metodo starts with para implementar busqueda parcial. 
            { 
                areasBuscadas.add(area);        //Si coinciden, agrego esa area a la lista a retornar.
            }
        }
        
        if(areasBuscadas.isEmpty())
        { 
            return null;            //Si no se encontraron coincidencias, devuelvo null.
        }
        
        Collections.sort(areasBuscadas);
        return areasBuscadas;
    }
    

    @Override
    public Areas dameArea(String nombre) 
    {
        
        for( Areas unArea : listaAreas)
        {
            if(unArea.getNombre().equalsIgnoreCase(nombre)) // En caso de coincidencia, devuelve ese nombre. Sino sigue buscando.
            {
                return unArea;
            }
        }
        return null; // Si no se encontro area devolvemos nulo.
    }
    
    public int verUltimaArea()
     {       
         this.posicionUltimaArea = listaAreas.size();  
         return this.posicionUltimaArea;     //CHEQUEAR IMPLEMENTACION                     
     }

    public void cancelar()    
     {
         this.posicionUltimaArea= -1;
                                    //CHEQUEAR IMPLEMENTACION.
     }
    
    public int ordenArea(Areas area)
    {
        int posicion = -1;
        for(Areas unArea : listaAreas)
        {
            if(area.equals(unArea))
            {
                posicion = listaAreas.indexOf(unArea);  //Metodo indexOf devuelve la posicion de unArea, la cual coincide con el parametro area que recibe el metodo.
                return posicion;                        
            }
        }                                               //CHEQUEAR IMPLEMENTACION
            
        return posicion;        //Si no hubo coincidencias, retornamos el -1 por defecto.
    }
    
    
    public String escribirAreas()
    {
        File f = new File("Areas.txt");
        try 
        {
            FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw);
            
            for( Areas i : listaAreas)
            {
                bfw.write(i.getNombre()+"\n");
                bfw.newLine();
            }
            bfw.close();
            return ESCRITURA_OK;        
        } 
        catch (IOException ex) 
        {
			return ESCRITURA_ERROR;
        }
    }
   
    public String leerAreas()
    {
        File f = new File("Areas.txt");
        
        if(f.exists())
        try 
        {
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            String nombreArea;
            while( ( nombreArea = bfr.readLine() )!= null )
            {
                
                GestorAreas.instanciar().nuevaArea(nombreArea);
            }
            bfr.close();
            return LECTURA_OK;              
        }
        catch (IOException ex) 
        {
            return LECTURA_ERROR;
        }
        return ARCHIVO_INEXISTENTE;
    }
    
    
}

