/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2ia;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Kev
 */
public class Genetico {
    final int  MIN = 0;
    final int MAX = 62;
    
    final int  MIN1 = 10;
    final int MAX1 = 62;
    
    
    public Individuo[] poblacion;
    
    public int[] caracteres = 
    {48,49,50,51,52,53,54,55,56,57,32,
     65,66,67,68,69,70,71,72,73,74,
     75,76,77,78,79,80,81,82,83,84,85,
     86,87,88,89,90,97,98,99,100,101,102,
     103,104,105,106,107,108,109,110,111,112,113,
     114,115,116,117,118,119,120,121,122};
    
    public int tamPoblacion;
    public int tamIndividuo;
    
    public void generarPoblacionAlfaNumerico(int tamPoblacion, int tamIndividuo)
    {
        poblacion = new Individuo[tamPoblacion];
        this.tamPoblacion = tamPoblacion;
        this.tamIndividuo = tamIndividuo;
        
        
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            //genero un individuo random
            String individuo = "";
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                int n = 0;
                Random rand = new Random();
                n = rand.nextInt(MAX-MIN + 1) + MIN;
                individuo = individuo +Character.toString ((char) caracteres[n]);
            }
            Individuo ind = new Individuo(individuo);
            poblacion[i] = ind;
        }
    }
    
    public void generarPoblacionLetras(int tamPoblacion, int tamIndividuo)
    {
        poblacion = new Individuo[tamPoblacion];
        this.tamPoblacion = tamPoblacion;
        this.tamIndividuo = tamIndividuo;
        
        
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            //genero un individuo random
            String individuo = "";
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                int n = 0;
                Random rand = new Random();
                n = rand.nextInt(MAX1-MIN1 + 1) + MIN1;
                individuo = individuo +Character.toString ((char) caracteres[n]);
            }
            Individuo ind = new Individuo(individuo);
            poblacion[i] = ind;
        }
    }
    
    public void seleccionTipo1()
    {
        
    }
    
    public void Emparejar()
    {
        ArrayList lista = new ArrayList();
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lista.add(i);
            
        }
        
        System.out.println(lista.indexOf(5));
        lista.remove(5);
    }
    
    public void mostrarPoblacion()
    {
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            String cadena = "";
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                cadena = cadena + poblacion[i].cadena.charAt(j) + ",";
            }
            System.out.println( (i+1) +"-- "+ cadena);
        }
    }
    
}
