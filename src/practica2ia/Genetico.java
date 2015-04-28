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
    public String frase;

    Genetico(int tamPoblacion, int tamIndividuo, String frase) 
    {
        poblacion = new Individuo[tamPoblacion];
        this.tamPoblacion = tamPoblacion;
        this.tamIndividuo = tamIndividuo;
        this.frase = frase;
    }
    
    public void generarPoblacionAlfaNumerico()
    {
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
    
    public void generarPoblacionLetras()
    {
        poblacion = new Individuo[tamPoblacion];
        
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
    
    //saco el valor cada elemento del individuo y cada
    //uno de esos valores lo resto con la posicion correspondiente de la frase
    //para luego todos los resultados de las restas sumarlo y comparar ambos padres
    public void seleccionTipo1()
    {
        //Emparejar();
        
        ArrayList<Integer> lista = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lista.add(i);   
        }
        
        while (!lista.isEmpty()) 
        {
            int valTotalPadre = 0;
            int valTotalMadre = 0;
            int i = lista.get(0);
            
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                int valPadre;
                int valMadre;
                int valFrase;
                
                valPadre = (int)poblacion[i].cadena.charAt(j);
                int posPareja = poblacion[i].parejaSeleccion - 1;
                valMadre = (int)poblacion[posPareja].cadena.charAt(j);
                valFrase = frase.charAt(j);
                
                valTotalPadre = valTotalPadre + Math.abs(valPadre - valFrase);
                valTotalMadre = valTotalMadre + Math.abs(valMadre - valFrase);
            }
            
            //si el padre es mas parecido me quedo con el padre en ambos
            //y sustituyo a la pareja por el padre
            if(valTotalPadre < valTotalMadre)
            {
                int posPareja = poblacion[i].parejaSeleccion - 1;
                poblacion[posPareja].cadena = poblacion[i].cadena;
                boolean t = lista.remove((Integer)posPareja);
                boolean t2 = lista.remove((Integer)i);
            }
            else
            {
                int posPareja = poblacion[i].parejaSeleccion - 1;
                poblacion[i].cadena = poblacion[posPareja].cadena;
                boolean t = lista.remove((Integer)posPareja);
                boolean t2 = lista.remove((Integer)i);
            }
        }
    }
    
    //sumo todos los valores del individuo y la suma resultante
    //la resto con el valor total de la frase 
    public void SeleccionTipo2()
    {
        //Emparejar();
        
        ArrayList<Integer> lista = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lista.add(i);   
        }
        
        while (!lista.isEmpty()) 
        {
            int valTotalPadre = 0;
            int valTotalMadre = 0;
            int valTotalFrase = 0;
            int i = lista.get(0);
            
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                int valPadre;
                int valMadre;
                int valFrase;
                
                valPadre = (int)poblacion[i].cadena.charAt(j);
                int posPareja = poblacion[i].parejaSeleccion - 1;
                valMadre = (int)poblacion[posPareja].cadena.charAt(j);
                valFrase = frase.charAt(j);
                
                valTotalPadre = valTotalPadre + valPadre;
                valTotalMadre = valTotalMadre + valMadre;
                valTotalFrase = valTotalMadre + valFrase;
                
            }
            
            int totPadre = Math.abs(valTotalPadre - valTotalFrase);
            int totMadre = Math.abs(valTotalMadre - valTotalFrase);
            
            //si el padre es mas parecido me quedo con el padre en ambos
            //y sustituyo a la pareja por el padre
            if(totPadre < totMadre)
            {
                int posPareja = poblacion[i].parejaSeleccion - 1;
                poblacion[posPareja].cadena = poblacion[i].cadena;
                boolean t = lista.remove((Integer)posPareja);
                boolean t2 = lista.remove((Integer)i);
            }
            else
            {
                int posPareja = poblacion[i].parejaSeleccion - 1;
                poblacion[i].cadena = poblacion[posPareja].cadena;
                boolean t = lista.remove((Integer)posPareja);
                boolean t2 = lista.remove((Integer)i);
            }
        }
    }
    
    public void cruceMascarCruce()
    {
        ArrayList<Integer> lista = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lista.add(i);   
        }
        
        while (!lista.isEmpty()) 
        {
            int posPadre = lista.get(0);
            int posMadre = poblacion[posPadre].parejaSeleccion - 1;
            
            //generamos nuestra mascara de cruce
            String mascara_cruce1 = "";
            String mascara_cruce2 = "";
            for (int j = 0; j <= this.tamIndividuo - 1; j++) 
            {
                Random rand = new Random();
                int r1 = rand.nextInt(2);
                mascara_cruce1 = mascara_cruce1 + r1;
                int r2 = rand.nextInt(2);
                mascara_cruce2 = mascara_cruce2 + r2;
            }
            System.out.println(mascara_cruce1);
            System.out.println(mascara_cruce2);
            
            //procedemos a generar el hijo1
            //y haciendolo con logica negativa 1 == padre, 0 == madre
            StringBuilder hijo1 = new StringBuilder(poblacion[posPadre].cadena);
            for (int j = 0; j <= this.tamIndividuo - 1; j++) 
            {
                char s = mascara_cruce1.charAt(j);
                if(s == '1')
                {
                    hijo1.setCharAt(j, poblacion[posPadre].cadena.charAt(j));
                }
                else
                {
                    hijo1.setCharAt(j, poblacion[posMadre].cadena.charAt(j));
                }
            }
            
            //procedemos a generar el hijo2
            //teniendo nuestra mascara de cruce distinta al primer hijo
            //y haciendolo con logica negativa 1 == madre, 0 == padre
            StringBuilder hijo2 = new StringBuilder(poblacion[posPadre].cadena);
            for (int j = 0; j <= this.tamIndividuo - 1; j++) 
            {
                char s = mascara_cruce2.charAt(j);
                if(s != '1')
                {
                    hijo2.setCharAt(j, poblacion[posPadre].cadena.charAt(j));
                }
                else
                {
                    hijo2.setCharAt(j, poblacion[posMadre].cadena.charAt(j));
                }
            }
            
            poblacion[posPadre].cadena = hijo1.toString();
            poblacion[posMadre].cadena = hijo2.toString();
            boolean t = lista.remove((Integer)posMadre);
            boolean t2 = lista.remove((Integer)posPadre);
        }
        
        
        
        
    }
    
    public void cruce1X()
    {
        ArrayList<Integer> lista = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lista.add(i);   
        }
        
        while (!lista.isEmpty()) 
        {
            //decidimos un punto de cruce (de una vez me devuelve el valor con un valor de indice empezando desde 0)
            int largo_cruce = this.tamIndividuo - 1;
            Random rand = new Random();
            int punto_cruce = rand.nextInt(largo_cruce);
            int posPadre = lista.get(0);
            
            //se setean las posiciones del padre
            //del primer hijo
            StringBuilder hijo1 = new StringBuilder(poblacion[posPadre].cadena);
            for (int j = 0; j <= punto_cruce; j++) 
            {
                hijo1.setCharAt(j, poblacion[posPadre].cadena.charAt(j));
            }
            
            //se setean las posiciones de la madre
            //del primer hijo
            int posMadre = poblacion[posPadre].parejaSeleccion - 1;
            for (int j = punto_cruce + 1; j <= largo_cruce; j++) 
            {
                hijo1.setCharAt(j, poblacion[posMadre].cadena.charAt(j));
            }
            
            
            //se setean las posiciones del padre
            //del segundo hijo
            StringBuilder hijo2 = new StringBuilder(poblacion[posMadre].cadena);
            for (int j = 0; j <= punto_cruce; j++) 
            {
                hijo2.setCharAt(j, poblacion[posMadre].cadena.charAt(j));
            }
            
            //se setean las posiciones de la madre
            //del segundo hijo
            for (int j = punto_cruce + 1; j <= largo_cruce; j++) 
            {
                hijo2.setCharAt(j, poblacion[posPadre].cadena.charAt(j));
            }
            
            poblacion[posPadre].cadena = hijo1.toString();
            poblacion[posMadre].cadena = hijo2.toString();
            boolean t = lista.remove((Integer)posMadre);
            boolean t2 = lista.remove((Integer)posPadre);
        }
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
    
    public String mostrarPoblacion()
    {
        String r = "";
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            String cadena = "";
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                cadena = cadena + poblacion[i].cadena.charAt(j) + ",";
            }
             r = r + (i+1) +"-- "+ cadena + " -- " + poblacion[i].parejaSeleccion + "\n";
        }
        
        return r;
    }
    
}
