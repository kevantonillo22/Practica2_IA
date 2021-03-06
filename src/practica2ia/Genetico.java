/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica2ia;

import java.util.ArrayList;
import java.util.Collections;
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
    public Individuo[] poblacion_ant;
    
    
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
    public int num_mutaciones;
    
    public int fitnessTotal_sig = 0;
    public int fitnessTotal_ant = 0;
    
    
    Genetico(int tamPoblacion, int tamIndividuo, String frase, int numMutaciones) 
    {
        poblacion = new Individuo[tamPoblacion];
        poblacion_ant = new Individuo[tamPoblacion];
        
        this.tamPoblacion = tamPoblacion;
        this.tamIndividuo = tamIndividuo;
        this.frase = frase;
        this.num_mutaciones = numMutaciones;
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
        
        //seteo la poblacion que va a quedar como la poblacion inicial luego de todo el proceso
        
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            Individuo ind = new Individuo(poblacion[i].cadena);
            poblacion_ant[i] = ind;
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
        
        //seteo la poblacion que va a quedar como la poblacion inicial luego de todo el proceso
        
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            Individuo ind = new Individuo(poblacion[i].cadena);
            poblacion_ant[i] = ind;
        }
    }
    
    public void calcularFitness_sig()
    {
        fitnessTotal_sig = 0;
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            int valTotal = 0;
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                int val1 = poblacion[i].cadena.charAt(j);
                int val2 = frase.charAt(j);
                valTotal = valTotal + Math.abs(val1 - val2);
            }
            poblacion[i].fitness = valTotal;
            fitnessTotal_sig = fitnessTotal_sig + valTotal;
        }
        
        
    }
    
    public void calcularFitness_ant()
    {
        fitnessTotal_ant = 0;
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            int valTotal = 0;
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                int val1 = poblacion_ant[i].cadena.charAt(j);
                int val2 = frase.charAt(j);
                valTotal = valTotal + Math.abs(val1 - val2);
            }
            poblacion_ant[i].fitness = valTotal;
            fitnessTotal_ant = fitnessTotal_ant + valTotal;
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
             r = r + (i+1) +"-- "+ cadena + " -- " + "Fitness=" + poblacion[i].fitness + "\n";
        }
        
        return r;
    }
    
    public String mostrar10mejores()
    {
        //creamos copias de ambas poblaciones antes de hacer el reemplazo
        ArrayList copia_ant = new ArrayList();
        ArrayList copia_sig = new ArrayList();
        
        for (int i = 0; i <= this.tamPoblacion - 1; i++) 
        {
            Individuo ind = new Individuo(poblacion_ant[i].cadena);
            ind.fitness = poblacion_ant[i].fitness;
            ind.parejaSeleccion = poblacion_ant[i].parejaSeleccion;
            copia_ant.add(ind);
            
            Individuo ind2 = new Individuo(poblacion[i].cadena);
            ind2.fitness = poblacion[i].fitness;
            ind2.parejaSeleccion = poblacion[i].parejaSeleccion;
            copia_sig.add(ind2);
        }
        
        Collections.sort(copia_ant);
        Collections.sort(copia_sig);
        String resultado = "";
        String r1 = "";
        String r2 = "";
        try{
            for (int i = 0; i <= 10 - 1; i++) 
            {
                String cadena1 = "";
                String cadena2 = "";

                for (int j = 0; j <= tamIndividuo - 1; j++) 
                {
                    cadena1 = cadena1 + ((Individuo)copia_ant.get(i)).cadena.charAt(j) + ",";
                    cadena2 = cadena2 + ((Individuo)copia_sig.get(i)).cadena.charAt(j) + ",";
                }
                 r1 = r1 + (i+1) +"-- "+ cadena1 + " -- " + "Fitness=" + ((Individuo)copia_ant.get(i)).fitness + "\n";
                 r2 = r2 + (i+1) +"-- "+ cadena2 + " -- " + "Fitness=" + ((Individuo)copia_sig.get(i)).fitness + "\n";
            }
        }
        catch(Exception e)
        {
        }
        resultado = "PADRES\n" + r1;
        resultado = resultado + "DESCENDIENTES\n" + r2;
        
        
        return resultado;
    }
    
    public void mutar()
    {
        ArrayList lis = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lis.add(i);   
        }
        
        ArrayList lista = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= num_mutaciones - 1; i++) 
        {
            Random rand = new Random();
            int n = rand.nextInt(lis.size());
            
            lista.add(lis.get(n));
            lis.remove(n);
        }
        
        
        //con los valores dentro de la variable lista
        //procedemos a realizar las mutaciones entre 
        Random rand = new Random();
        double probabilidad_mutacion = rand.nextDouble();
        for (int i = 0; i <= lista.size() - 1; i++) 
        {
            int pos = Integer.parseInt(lista.get(i).toString());
            StringBuilder hijo_mutar = new StringBuilder(poblacion[pos].cadena);
            
            for (int j = 0; j <= tamIndividuo - 1; j++) 
            {
                Random ran = new Random();
                double prob = ran.nextDouble();
                if(prob < probabilidad_mutacion)
                {
                    int valIndividuo = poblacion[pos].cadena.charAt(j);
                    int valFrase = this.frase.charAt(j);
                    
                    if(valIndividuo > valFrase)
                    {
                        if(valIndividuo == 65)
                        {
                            int v = 57;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        else if(valIndividuo == 97)
                        {
                            int v = 90;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        else if(valIndividuo == 48)
                        {
                            int v = 32;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        else
                        {
                            int v = valIndividuo - 1;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        
                    }else if(valIndividuo < valFrase)
                    {
                        if(valIndividuo == 32)
                        {
                            int v = 48;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        else if(valIndividuo == 57)
                        {
                            int v = 65;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        else if(valIndividuo == 90)
                        {
                            int v = 97;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                        else
                        {
                            int v = valIndividuo + 1;
                            hijo_mutar.setCharAt(j, (char)v);
                        }
                    }
                }
            }
            
            poblacion[pos].cadena = hijo_mutar.toString();
        }
    }
    
    
    
    public void reemplazoEtilistaGeneral()
    {
        //creamos copias de ambas poblaciones antes de hacer el reemplazo
        ArrayList copia_ant = new ArrayList();
        ArrayList copia_sig = new ArrayList();
        
        for (int i = 0; i <= this.tamPoblacion - 1; i++) 
        {
            Individuo ind = new Individuo(poblacion_ant[i].cadena);
            ind.fitness = poblacion_ant[i].fitness;
            ind.parejaSeleccion = poblacion_ant[i].parejaSeleccion;
            copia_ant.add(ind);
            
            Individuo ind2 = new Individuo(poblacion[i].cadena);
            ind2.fitness = poblacion[i].fitness;
            ind2.parejaSeleccion = poblacion[i].parejaSeleccion;
            copia_sig.add(ind2);
        }
        
        Collections.sort(copia_ant);
        Collections.sort(copia_sig);
        
        /*System.out.println("********************");
        for (int i = 0; i <= copia_ant.size() - 1; i++) 
        {
            System.out.println("Cadena: " + ((Individuo)copia_ant.get(i)).cadena + "---fitness: " + ((Individuo)copia_ant.get(i)).fitness);
        }
        System.out.println("--------------------");
        for (int i = 0; i <= copia_sig.size() - 1; i++) 
        {
            System.out.println("Cadena: " + ((Individuo)copia_sig.get(i)).cadena + "---fitness: " + ((Individuo)copia_sig.get(i)).fitness);
        }*/
        
        int t = (this.tamPoblacion/2);
        
        int zz = 0;
        for (int i = 0; i <= t - 1; i++) 
        {
            poblacion[zz].cadena = ((Individuo)copia_ant.get(i)).cadena;
            poblacion[zz].fitness = ((Individuo)copia_ant.get(i)).fitness;
            zz++;
        }
        
        for (int i = 0; i <= t - 1; i++) 
        {
            poblacion[zz].cadena = ((Individuo)copia_sig.get(i)).cadena;
            poblacion[zz].fitness = ((Individuo)copia_sig.get(i)).fitness;
            zz++;
        }
        
    }
    
    public void reemplazoAleatorio()
    {
        ArrayList lis = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            lis.add(i);   
        }
        
        int mitad = (tamPoblacion/2);
        Random ra = new Random();
        int descendientes = ra.nextInt(mitad) ;
        ArrayList lista = new ArrayList();
        //Lleno mi lista con las posiciones
        for (int i = 0; i <= descendientes; i++) 
        {
            Random rand = new Random();
            int n = rand.nextInt(lis.size());
            
            lista.add(lis.get(n));
            lis.remove(n);
        }
        
        for (int i = 0; i <= lista.size() - 1; i++) 
        {
            int val = Integer.parseInt(lista.get(i).toString());
            
            poblacion[val].cadena = poblacion_ant[val].cadena;
            poblacion[val].fitness = poblacion_ant[val].fitness;
        }
        System.out.println(descendientes);
    }
    
    public void prepararNuevaIteracion()
    {
        //seteo la poblacion que va a quedar como la poblacion inicial luego de todo el proceso
        
        for (int i = 0; i <= tamPoblacion - 1; i++) 
        {
            Individuo ind = new Individuo(poblacion[i].cadena);
            ind.fitness = poblacion[i].fitness;
            poblacion_ant[i] = ind;
        }
    }
}
