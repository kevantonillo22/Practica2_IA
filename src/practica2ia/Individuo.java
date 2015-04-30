/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2ia;

/**
 *
 * @author Kev
 */
public class Individuo implements Comparable {
    public String cadena;
    public int parejaSeleccion;
    public int fitness;
    
    public Individuo(String cadena)
    {
        this.cadena = cadena;
        this.parejaSeleccion = -1;
        this.fitness = 0;
    }

    @Override
    public int compareTo(Object t) 
    {
        int c = ((Individuo)t).fitness;
        return this.fitness - c;
    }
    
    
    
    
    
}
