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
