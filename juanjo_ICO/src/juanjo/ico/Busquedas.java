package juanjo.ico;

import java.util.ArrayList;

public class Busquedas {
	private Celda[][] laberinto;

    private ArrayList<Celda> abiertos;

    private ArrayList<Celda> cerrados;
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
	public void setCeldas( Celda[][] laberinto) {
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
            if (celdaActual==laberinto[tamano][tamano]) {
            	// En caso de que sea dijkstra o astar calculamos el coste.
            	celdaActual.setG(celdaActual.getPadre().getG() + 1);	
            	celdaActual.setF(celdaActual.getG(),celdaActual.getH()); 
                guardar(celdaActual);
                return;
            } else {

    	//   		explorarCelda(celdaActual);                
      
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
	
	 private ArrayList<Celda> sucesores(Celda celdaActual) {
		 ArrayList<Celda> sucesores = new ArrayList<>();
		 
		 Celda sucesor;
	        // IZQUIERDA(ARRIBA)
	        if (celdaActual.getPosX() > 0) {
	            if (laberinto[celdaActual.getPosX() - 1][celdaActual.getPosY()].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, -1, 0);
	                sucesores.add(sucesor);
	            }
	        }
	        //DIAGONAL IZQUIERDA ARRIBA(D.D.AR)
	        if (celdaActual.getPosY() < tamano - 1 && celdaActual.getPosX()>0) {
	            if (laberinto[celdaActual.getPosX() - 1][celdaActual.getPosY()+1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, -1, 1);
	            	sucesores.add(sucesor);
	            }
	        }
	        // ARRIBA(DERECHA)
	        if (celdaActual.getPosY() < tamano - 1) {
	            if (laberinto[celdaActual.getPosX()][celdaActual.getPosY() + 1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 0, 1);
	                sucesores.add(sucesor);
	            }
	        }
	        //DIAGONAL DERECHA ARRIBA(D.D.AB)
	        if (celdaActual.getPosY() < tamano - 1 && celdaActual.getPosX()< tamano-1) {
	            if (laberinto[celdaActual.getPosX() + 1][celdaActual.getPosY()+1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 1, 1);
	            	sucesores.add(sucesor);
	            }
	        }
	        // DERECHA(ABAJO)
	        if (celdaActual.getPosX() < tamano - 1) {
	            if (laberinto[celdaActual.getPosX() + 1][celdaActual.getPosY()].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 1, 0);
	            	sucesores.add(sucesor);
	            }
	        }
	        //DIAGONAL DERECHA ABAJO(D.I.AB)
	        if (celdaActual.getPosY() >0 && celdaActual.getPosX()< tamano-1) {
	            if (laberinto[celdaActual.getPosX() + 1][celdaActual.getPosY()-1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 1, -1);
	            	sucesores.add(sucesor);
	            }
	        }
	        // ABAJO(IZQUIERDA)
	        if (celdaActual.getPosY() > 0) {
	            if (laberinto[celdaActual.getPosX()][celdaActual.getPosY() - 1].getText() != "Montaña") {
	            	sucesor = hijo(celdaActual, 0, -1);
	                sucesores.add(sucesor);
	            }
	        }
	        //DIAGONAL IZQUIERDA ABAJO(D.I.AR)
	        if (celdaActual.getPosY() >0 && celdaActual.getPosX()>0) {
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
			                laberinto[celdaActual.getPosX() + x][celdaActual.getPosY() + y].getH(),
			                (int) (1/laberinto[celdaActual.getPosX() + x][celdaActual.getPosY() + y].getV() + celdaActual.getG())
			                );

	    	
	    	sucesor.setPadre(celdaActual);
	    	return sucesor;
	    }
	
}
