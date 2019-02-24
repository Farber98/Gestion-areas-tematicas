/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;


import gui.areas.modelos.Area;
import java.util.List;

/**
 *
 * @author gabinete
 */
public class Trabajo
{	
	private List<Area> unasAreas;
	
    public List<Area> verAreas()        //Implementamos solo lo necesario para chequear que un area no se encuentre en uso por un trabajo.
    {
        return unasAreas;
    }
}
	
 
