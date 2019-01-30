/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.modelos;

import gui.interfaces.IGestorAreas;
import java.awt.geom.Area;
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
    
    
    
    private GestorAreas(){}; 
    
	
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
        Areas UnArea;
        
        if (nombre.isEmpty() || nombre == null){
            return ERROR_NUEVA_AREA_VACIA; 
        }
        
        UnArea = new Areas(nombre);
        if(listaAreas.contains(UnArea)) 
        {
           return ERROR_NUEVA_AREA_DUPLICADA; 
        }
        
        listaAreas.add(UnArea);
        
        return EXITO_NUEVA_AREA;
    }

    @Override
    public String borrarArea(Areas area) {
        GestorTrabajos miGestorTrabajos = GestorTrabajos.instanciar();

        if( area == null)
        {
            return ERROR_BORRAR_AREA;
        }
 
        if( !listaAreas.contains(area))
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
            if( i.equals(area))
            {
                
                listaAreas.remove(i);
                i = null;
                
                return EXITO_BORRAR_AREA;
            }
        }
        
        return ERROR_BORRAR_AREA;
    }
    
     @Override
    public List<Areas> buscarAreas(String nombre) 
    {
        List<Areas> areasBuscadas = new ArrayList<>();
        
        if( nombre == null || nombre.isEmpty() )       
        {
            Collections.sort(listaAreas);       //Ordeno alfabeticamente y devuelto todo.
            return listaAreas;                      
        }
        
        for( Areas area : listaAreas)
        {
            if( area.getNombre().toUpperCase().startsWith(nombre.toUpperCase()))      //Comparo si coinciden los nombres en mayusculas. 
            { 
                areasBuscadas.add(area);        //Si coinciden, agrego esa area a la lista a retornar.
                
                                                //PROBAR LO DE PARCIAL STARTSWITH
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
         int posicionUltimaArea = listaAreas.size();    // ¿Si cuando se agrega un area se cancela la operacion devuelve -1?
         return posicionUltimaArea;                     //¿Como hago para que se actualize? ¿Archivos?
     }

    public void cancelar()    
     {
         int auxiliar = -1;
         auxiliar = verUltimaArea();        //CHEQUEAR IMPLEMENTACION.
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


    @Override
    public void mostrarAreas() 
    {
        
        Collections.sort(listaAreas);       //Ordeno alfabeticamente.
		
        for( Areas unArea : listaAreas)
        {
            System.out.println(unArea.toString());      //Muestro areas una por una.
        }
    }
    
    
    
    public String escribirAreas()
    {
        try 
        {
            File f = new File("Areas.txt");
            FileWriter fw = new FileWriter(f);
            BufferedWriter bfw = new BufferedWriter(fw);
            
            for( Areas i : listaAreas)
            {
                bfw.write(i.getNombre()+"\n");
            }
            
            bfw.close();
			return ESCRITURA_OK;
        } catch (IOException ex) 
        {
			return ESCRITURA_ERROR;
        }
    }
   
    public String leerAreas()
    {
        String nombreArea;
        
        try 
        {
            File f = new File("Areas.txt");
            FileReader fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);
            Areas Unarea;
            
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
    }
    
    
}

