package juanjo.ico;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;



public class Celda extends JButton {
	
	private String label;
	private int x;
	private int y;
	private float f;
	private float g;
	private float h;
	private float v;
    private Celda padre;
    private boolean visitado;

    public boolean isVisitado() {
		return visitado;
	}

	public void setVisitado(boolean visitado) {
		this.visitado = visitado;
	}

	public void sucesores(int x, int y, int tamano){
    	Celda[][] suc = new Celda[tamano][tamano];
    	if(x==1 && y==1)
    	{
    	//	tres casos
    		//sucesores.add(Celda[][]);
    	}
    	else if(x==tamano && y==tamano)
    	{
    	//tres casos
    		
    	}
    	else if(y==1 &&(x!=tamano ||x!=1) && (y!=tamano)){
    		//5 casos
    		
    	}
    	else if(y==tamano &&(x!=tamano ||x!=1) && (y!=1) ){
    		//5 casos
    		
    	}
    	else
    	{
    	//8 casos
    		
    	}
    	
    	
    }
    
    public float calcularHeuristica(int tamano) {
        return Math.max(Math.abs(this.getPosX() - tamano),Math.abs(this.getPosY() - tamano));
    }
    
    public double getV() {
		return v;
	}
    
	public void setV() {
    	if(this.getText()=="Camino"){
    		v=1;
    		
    	}
    	else if(this.getText()=="Campo"){
    		v=(float) 0.9;
    		
    	}
    	else if(this.getText()=="Bosque"){
    		v=(float) 0.8;
    	}
    	else if(this.getText()=="Rio"){
			v=(float) 0.7;
		}
    	else if(this.getText()=="Montaña"){
			v=0;
		}
		this.v = v;
	}

	private Color circleColor = Color.BLACK;
    
    public Celda(String label) {
        //

        
    }
    public Celda(String label, int x, int y,float g, float h) {
        super(label);
        super.setAlignmentX(x);
        super.setAlignmentY(y);
        this.label=label;
        this.x = x;
        this.y = y;
        
        this.g = g;
        this.h = h;
        this.f=g+h;
        
    }

    public int getPosX() {
		return x;
	}
    
    public int getPosY() {
		return y;
	}
    	
    public float calcularG(){
    	float g=0;   	
    	
		return g;
    }
    

    public int calcularF(){
    	int f=0; 
    	
    	//calcularHeuristica();
    	calcularG();

    	f = (int) (h + g);
    	setF(h,g);
    	System.out.println("");
    	return f;
    }

	public float getF() {
		return f;
	}

	public float getG() {
		return g;
	}

	public float getH() {
		return h;
	}
	
	public Celda getPadre() {
        return padre;
    }

    public void setPadre(Celda padre) {
        this.padre = padre;
    }


	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setF(float g, float h) {
		this.f = g+h;
	}
	public void setG(float g) {
		//this.g = this.v + padre.getG();
		this.g = g;
	}
	public void setH(float h) {
		this.h = h;
	}
	public void setCircleColor(Color circleColor) {
		this.circleColor = circleColor;
	}
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Dimension originalSize = super.getPreferredSize();
        int gap = (int) (originalSize.height * 0.2);
        int x = originalSize.width + gap;
        int y = gap;
        int diameter = originalSize.height - (gap * 2);

        g.setColor(circleColor);
       // g.fillOval(x, y, diameter, diameter);
        g.drawString("F:"+this.f, 5, 15);
        g.drawString("G:"+this.g, 40, 15);
        g.drawString("H:"+this.h, 5, 30);
        g.drawString("V:" +this.v, 40, 30);
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension size = super.getPreferredSize();
        size.width += size.height;
        return size;
    }
    
    public int compareTo(Object o) {
        Celda celda = (Celda) o;
        if (celda.getF() > this.getF())
            return -1;
        return 1;
    }

}
