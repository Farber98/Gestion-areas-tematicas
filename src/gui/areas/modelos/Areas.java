/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.areas.modelos;

import java.util.Objects;

/**
 *
 * @author juan
 */

    public class Areas implements Comparable<Areas>
    
    {
    
        // <editor-fold defaultstate="collapsed" desc="VARIABLES DE INSTANCIA">
        private String nombre;

// </editor-fold>

        // <editor-fold defaultstate="collapsed" desc="CONSTRUCTOR">
        public Areas(String nombre) {
            this.nombre = nombre;
        }

// </editor-fold>
	
        // <editor-fold defaultstate="collapsed" desc="METODOS">
        @Override
        public int compareTo(Areas o) 
        {
            return this.nombre.compareToIgnoreCase(o.nombre);       //Usamos el metodo IgnoreCase para ignorar mayusculas y minusculas.
        }
        
        @Override
        public String toString() 
        {
            return "Area: " + nombre;
        }

// </editor-fold>
	
        //<editor-fold desc="GET & SET">
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //</editor-fold>
	
        // <editor-fold defaultstate="collapsed" desc="EQUALS Y HASHCODE">
        @Override
        public int hashCode() {
            int hash = 7;
            hash = 23 * hash + Objects.hashCode(this.nombre);
            return hash;
        }
        
        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            if (getClass() != obj.getClass()) {
                return false;
            }
            final Areas other = (Areas) obj;
            if (!Objects.equals(this.nombre.toUpperCase(), other.nombre.toUpperCase())) {
                return false;
            }
            return true;
        }
// </editor-fold>	
	
    }

     