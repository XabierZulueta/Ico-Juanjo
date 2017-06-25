package juanjo.ico;

import java.util.ArrayList;
import java.util.Collections;

public class Busquedas {
	private Celda[][] laberinto;

    private ArrayList<Celda> abiertos = new ArrayList<>();

    private ArrayList<Celda> cerrados = new ArrayList<>();
    private int tamano;

	public int getTamano() {
		return tamano;
	}

	public void setTamano(int tamano) {
		this.tamano = tamano;
	}

	public void astar(){
		
	}
	
	public void idastar(){
		
	}
	public Celda[][] getCeldas() {
        return laberinto;
    }
	public void setCeldas( Celda[][] laberinto, int tamano) {
		this.laberinto = new Celda[tamano][tamano];
		
		this.tamano = tamano;
		this.laberinto = laberinto;
    }
	public void buscar() {
    	/**
    	 * Añadimos la celda inicial a abiertos.
    	 */
		
        abiertos.add(laberinto[1][1]);
        while (!abiertos.isEmpty()) {
   
            Celda celdaActual = abiertos.remove(0);
            
            cerrados.add(celdaActual);
            // Marcamos la celdaActual como celda visitada.
            celdaActual.setVisitado(true);
            // En caso de que sea la celda final entramos.
            if (celdaActual.getPosX() ==laberinto[tamano][tamano].getPosX() &&
            		celdaActual.getPosY() ==laberinto[tamano][tamano].getPosY()) {
            	// En caso de que sea dijkstra o astar calculamos el coste.
            	celdaActual.setG(celdaActual.getPadre().getG() + 1);	
            	celdaActual.setF(celdaActual.getG(),celdaActual.getH()); 
                guardar(celdaActual);
                return;
            } else {
            	
    	  		explorarCelda(celdaActual);                
    	  		
            }
        }
        /**
         * Si llega hasta aquí es que ha buscado todos los posibles estados y no ha encontrado la solución
         */

    }
	
	private void guardar(Celda celdaActual)
	{
		if (celdaActual.getPadre() != null) {
            guardar(celdaActual.getPadre());
        }
        
        if (!celdaActual.getText().equals("Inicio") && !celdaActual.getText().equals("Final")){
        	celdaActual.setVisitado(true);
        }
	}
	
	private void explorarCelda(Celda celdaActual) {

		
        ArrayList<Celda> sucesores = sucesores(celdaActual);
        int esta=0;
        for (Celda sucesor : sucesores) {

            if (cerrados.indexOf(sucesor) == -1 && abiertos.indexOf(sucesor) == -1) {
            	for(int i=0; i<cerrados.size();i++)
            	{
            		if(cerrados.get(i).getPosX()==sucesor.getPosX() &&
            				cerrados.get(i).getPosY()==sucesor.getPosY()	)
            			esta=1;
            	}
            	if(esta!=1){
                abiertos.add(sucesor);
                laberinto[sucesor.getPosX()][sucesor.getPosY()] = sucesor;
                
                // Ordenamos el array de abierto en función de F(n).
                Collections.sort(abiertos);}}
                
            
        }
    }
	
	 private ArrayList<Celda> sucesores(Celda celdaActual) {
		 ArrayList<Celda> sucesores = new ArrayList<>();
		 
		 Celda sucesor;
	  
	        if (celdaActual.getPosX() > 1) {
	            if (laberinto[celdaActual.getPosX() - 1][celdaActual.getPosY()].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, -1, 0);
	                sucesores.add(sucesor);
	            }
	        }

	        if (celdaActual.getPosY() < tamano && celdaActual.getPosX()>1) {
	            if (laberinto[celdaActual.getPosX() - 1][celdaActual.getPosY()+1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, -1, 1);
	            	sucesores.add(sucesor);
	            }
	        }
	        if (celdaActual.getPosY() < tamano) {
	            if (laberinto[celdaActual.getPosX()][celdaActual.getPosY() + 1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 0, 1);
	                sucesores.add(sucesor);
	            }
	        }
	        if (celdaActual.getPosY() < tamano && celdaActual.getPosX()< tamano) {
	            if (laberinto[celdaActual.getPosX() + 1][celdaActual.getPosY()+1].getText() != "Montaña"
	            		)
	            {
	            	
	            	sucesor = hijo(celdaActual, 1, 1);

	            	sucesores.add(sucesor);
	            }
	        }
	        
	        if (celdaActual.getPosX() < tamano ) {
	        	
	            if (laberinto[celdaActual.getPosX() + 1][celdaActual.getPosY()].getText() != "Montaña") {
	            	
	            	sucesor = hijo(celdaActual, 1, 0);
	            	sucesores.add(sucesor);
	            }
	        }
	        
	        if (celdaActual.getPosY() >1 && celdaActual.getPosX()< tamano-1) {
	            if (laberinto[celdaActual.getPosX() + 1][celdaActual.getPosY()-1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 1, -1);
	            	sucesores.add(sucesor);
	            }
	        }
	        
	        if (celdaActual.getPosY() > 1) {
	            if (laberinto[celdaActual.getPosX()][celdaActual.getPosY() - 1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 0, -1);
	                sucesores.add(sucesor);
	            }
	        }
	        
	        if (celdaActual.getPosY() >1 && celdaActual.getPosX()>1) {
	            if (laberinto[celdaActual.getPosX() - 1][celdaActual.getPosY()-1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, -1, -1);
	            	sucesores.add(sucesor);
	            }
	        }
	    	
		 return sucesores;
	 
	 }
	 private Celda hijo(Celda celdaActual, int x, int y){
	    	Celda sucesor = null;
	    
	    			sucesor = new Celda(
    					laberinto[celdaActual.getPosX() + x][celdaActual.getPosY() + y].getText(),
    					celdaActual.getPosX() + x,
		                celdaActual.getPosY() + y,
		                (float)(1/laberinto[celdaActual.getPosX() + x][celdaActual.getPosY() + y].getV() + celdaActual.getG()),
		                laberinto[celdaActual.getPosX() + x][celdaActual.getPosY() + y].getH()
		                );	
	    	
	    	sucesor.setPadre(celdaActual);
	    	return sucesor;
	    }
	
}
