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
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lista.add(i+1);   
        }
        
        //Procedo a buscar y emparejar
        int j = 0;
        for (int i = 1; i <= tamPoblacion/2; i++) 
        {
            int min = 1;
            int max = numeroMayorLista(lista);
            Random rand = new Random();
            int nn = rand.nextInt(max-min + 1);
            int n =  nn + min;
            
            poblacion[(int)lista.get(0) - 1].parejaSeleccion = (int)lista.get(n);
            poblacion[(int)lista.get(n) - 1].parejaSeleccion = (int)lista.get(j);
            lista.remove(n);
            lista.remove(0);
            
        }
        
        
    }
    
    private int numeroMayorLista(ArrayList lista)
    {
        int iNumeroMayor, iPosicion;
 
        //Presuponemos que el numero mayor es el primero
        iNumeroMayor = (int)lista.get(0);
        iPosicion = 0;
        for (int x=1; x <= lista.size() - 1; x++)
        {
            if ((int)lista.get(x)>iNumeroMayor)
            {
                iNumeroMayor = (int)lista.get(x);
                iPosicion = x;
            }    
        }
        return iPosicion;
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
            System.out.println( (i+1) +"-- "+ cadena + " -- " + poblacion[i].parejaSeleccion);
        }
    }
    
}
