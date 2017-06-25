package juanjo.ico;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panels extends JFrame{
	/**
	* Creamos y calculamos el laberinto con IDA*
	*/
	private Celda[][] laberinto;
	ArrayList<Celda> listaCelda = new ArrayList<>();
	
	private static final long serialVersionUID = 1L;
	private static int tamano = 5;
	
	public void generar(){

        Busquedas busq=new Busquedas();
		busq.setTamano(tamano);
      //Generamos el laberinto
		Container cp = getContentPane();
		GridLayout gl = new GridLayout(tamano, tamano);
		setSize(new Dimension(1000, 700));
		setLocationRelativeTo(null);
		
		gl.setHgap(5);
		gl.setVgap(5);
		
		JButton bloq = new JButton("Iniciar Búsquedas");
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("A Menu");
		menu.setMnemonic(KeyEvent.VK_A);
		menuBar.add(bloq);
		
		//JButton buseq=new JButton("HOLA");
		//add(gl1, BorderLayout.NORTH);
		//cp.add(buseq);
		//cp.setLayout(gl);
		//cp.add(buseq,BorderLayout.NORTH);

		laberinto= new Celda[tamano+1][tamano+1];
		//busq.calcularHeuristicas(listaCelda);
		
		for (int i = 1; i <= tamano; i++) {
			for (int j = 1; j <= tamano; j++) {
				
				Celda b = new Celda("Camino",i,j,0,0);
				b.setH(b.calcularHeuristica(tamano));
				b.setF(b.getG(), b.getH());
				b.sucesores(b.getPosX(), b.getPosY(), tamano);
				b.setV();
				
				if(i==1 && j==1)
				{
					b.setText("Inicio");
					b.setBorder(new LineBorder(Color.GREEN));
					//b.setBackground(Color.GREEN);
					
				}
				else if(i==tamano && j==tamano)
				{
					b.setText("Final");
					b.setBorder(new LineBorder(Color.RED));
				}
				b.getModel().addChangeListener(new ChangeListener() {

		            @Override
		            public void stateChanged(ChangeEvent e) {
		                ButtonModel model = (ButtonModel) e.getSource();
		                if (model.isPressed()) {
		                	/**
			            	 * Cambiar propiedades
			            	 */
			            	//cambiar label
		                    String btnText = (b.getText());

		                    if (btnText.equals("Camino")) {
		                    	 b.setText("Campo");
		                    	b.setH(b.calcularHeuristica(tamano));
		                    	b.setG(b.calcularG());
		                    	b.setF(b.getG(), b.getH());
		                    	b.setV();

		                    }
		                    if (btnText.equals("Campo")) {
		                    	b.setText("Bosque");
		                    	b.setH(b.calcularHeuristica(tamano));
		                    	b.setG(b.calcularG());
		                    	b.setF(b.getG(), b.getH());
		                    	b.setV();
		                    }
		                    if (btnText.equals("Bosque")) {
		                    	b.setText("Rio");
		                    	b.setH(b.calcularHeuristica(tamano));
		                    	b.setG(b.calcularG());
		                    	b.setF(b.getG(), b.getH());
		                    	b.setV();
		                    }
		                    if (btnText.equals("Rio")) {
		                    	b.setText("Montaña");
		                    	b.setH(b.calcularHeuristica(tamano));
		                    	b.setG(b.calcularG());
		                    	b.setF(b.getG(), b.getH());
		                    	b.setV();
		                    }
		                    if (btnText.equals("Montaña")) {
		                    	b.setText("Camino");
		                    	b.setH(b.calcularHeuristica(tamano));
		                    	b.setG(b.calcularG());
		                    	b.setF(b.getG(), b.getH());
		                    	b.setV();
		                    	//laberinto[b.getPosX()][b.getPosY()]= b;
		                    }
		                }
		            }
		        });
				laberinto[i][j]= b;
				listaCelda.add((Celda) b);
				
				b.addActionListener(new java.awt.event.ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		            	//repaint();
		            }
		        });
				cp.add(b);
			}
		}		cp.setLayout(gl);
		
		bloq.addActionListener(new ActionListener()
		{
		  public void actionPerformed(ActionEvent e)
		  {
		    // display/center the jdialog when the button is pressed
			 busq.setCeldas(laberinto, tamano);
				/*for (int i = 1; i <= tamano; i++) {
					for (int j = 1; j <= tamano; j++) {
						System.out.println(laberinto[i][j].getText());
					}
				}*/
			 
			 busq.buscar();
			 generarSolucion(busq.getCeldas());
			 
		  }
		});
		
	}
	public void generarSolucion(Celda[][] laberinto){
		
      //Generamos el laberinto
		JFrame frame = new JFrame();
		Container cp = getContentPane();
		GridLayout gl = new GridLayout(tamano, tamano);
		frame.setSize(new Dimension(1000, 700));
		frame.setLocationRelativeTo(null);
		
		gl.setHgap(5);
		gl.setVgap(5);
		
		
		for (int i = 1; i <= tamano; i++) {
			for (int j = 1; j <= tamano; j++) {
				
				Celda b = laberinto[i][j];
				frame.add(b);
			}
		}		frame.setLayout(gl);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
		
		
	}

    
	public Panels() {
		super("Laberinto");
		
		tamanolab();
		generar();
				
		

	}
	
	public static void tamanolab() {
		String dialog = "5";
		dialog = JOptionPane.showInputDialog("Introduzca el tama�o del laberinto (Entre 5 y 10): ", dialog);
		tamano = Integer.parseInt(dialog);
		if (tamano < 5)
			tamano = 5;
		if (tamano > 10)
			tamano = 10;
	}

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                Panels main = new Panels();
                main.setVisible(true);
            }
        });
			
	}
}
