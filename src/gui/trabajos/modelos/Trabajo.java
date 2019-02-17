/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.trabajos.modelos;

import java.awt.geom.Area;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gabinete
 */
public class Trabajo implements Comparable<Trabajo>{

	private String titulo;
	private int meses;
	private LocalDate fechaPresentacion;
	private LocalDate fechaAprobacion;
	private LocalDate fechaExposicion;
	private List<Area> unasAreas;
	DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");

	@Override
	public int compareTo(Trabajo o) {
		int val = this.fechaPresentacion.compareTo(o.fechaPresentacion);

		//Si la fecha es la misma revisamos el titulo.
		if( val != 0 ){
			return val;
		}
		
		return this.titulo.compareToIgnoreCase(o.titulo);
	}
        
	
	public String getTitulo() {
		return titulo;
	}
	
	
	public void setFechaAprobacion(LocalDate fechaAprobacion) {

		this.fechaAprobacion = fechaAprobacion;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 61 * hash + Objects.hashCode(this.titulo);
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
		final Trabajo other = (Trabajo) obj;
		if (!Objects.equals(this.titulo.toUpperCase(), other.titulo.toUpperCase())) {
			return false;
		}
		return true;
	}

    public List<Area> getAreas() {
        return unasAreas;
    }
	
    public LocalDate getFechaExposicion() {
		return fechaExposicion;
	}

	public void setFechaExposicion(LocalDate fechaExposicion) {
		this.fechaExposicion = fechaExposicion;
	}
}
