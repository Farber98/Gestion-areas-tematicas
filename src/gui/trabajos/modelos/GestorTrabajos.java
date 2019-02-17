/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import gui.trabajos.modelos.Trabajo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabinete
 */
public class GestorTrabajos {
    private static GestorTrabajos gestor;
    private ArrayList<Trabajo> listaTrabajos = new ArrayList<>();
    
    private GestorTrabajos(){}; // Constructor Privado// Constructor Privado// Constructor Privado// Constructor Privado// Constructor Privado// Constructor Privado// Constructor Privado// Constructor Privado
    
    public static GestorTrabajos instanciar(){
        
        if( gestor == null){
            gestor = new GestorTrabajos();
        }
        return gestor;
    }

   
   
    public ArrayList<Trabajo> buscarTrabajos(String titulo) {
		ArrayList<Trabajo> misTrabajos = new ArrayList<>();
		
		// VERIFICO LOS PARAMETROS.
		
		if(titulo.isEmpty()){
			//Me aseguro que los trabajos no tengan nada y paso la lista.
			misTrabajos.clear();
			return misTrabajos;
		}
		
		// VERIFICO QUE EL STRING ESTE CONTENIDA EN EL TITULO.
		
		for( Trabajo i : listaTrabajos){ 
			if(titulo.toUpperCase().contains(i.getTitulo().toUpperCase())){
				misTrabajos.add(i);
			}
		}
		
		return misTrabajos;
	}
	
	public List<Trabajo> buscarTrabajos(){ //doy una copia de los trabajos.
		List<Trabajo> misTrabajos = new ArrayList<>();
		
		misTrabajos.addAll(listaTrabajos);
		
		return misTrabajos;
	}

    
}
