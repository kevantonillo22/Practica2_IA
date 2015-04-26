/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2ia;

import java.util.Random;

/**
 *
 * @author Kev
 */
public class Genetico {
    final int  MIN = 32;
    final int MAX = 126;
    
    final int  MIN1 = 48;
    final int MAX1 = 57;
    final int  MIN2 = 65;
    final int MAX2 = 90;
    final int  MIN3 = 97;
    final int MAX3 = 122;
    
    
    public String[] poblacion;
    public int tamPoblacion;
    public int tamIndividuo;
    
    public void generarPoblacion(int tamPoblacion, int tamIndividuo)
    {
        poblacion = new String[tamPoblacion];
        this.tamPoblacion = tamPoblacion;
        this.tamIndividuo = tamIndividuo;
        
        
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            //genero un individuo random
            String individuo = "";
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                Random rand = new Random();
                int n = rand.nextInt(MAX-MIN + 1) + MIN;
                individuo = individuo +Character.toString ((char) n);
            }
            poblacion[i] = individuo;
        }
    }
    
    public void mostrarPoblacion()
    {
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            String cadena = "";
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                cadena = cadena + poblacion[i].charAt(j) + ",";
            }
            System.out.println( (i+1) +"-- "+ cadena);
        }
    }
    
}
