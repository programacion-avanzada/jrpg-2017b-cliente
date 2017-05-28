package frames;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import inventario.Inventario;
import mensajeria.PaquetePersonaje;

public class MenuInventario extends JFrame {

	private JButton cancelar = new JButton("Exit");
	
    public MenuInventario(PaquetePersonaje paquetePersonaje) {
                //cancelar.setBounds(90, 190, 50, 50);
    			cancelar.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						dispose();
						
					}
    				
    			});
    			this.setTitle("Inventario");
    			this.setUndecorated(true);
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					this.setLayout(new BorderLayout());
					this.add(new Inventario(paquetePersonaje));
					this.add(cancelar,BorderLayout.AFTER_LAST_LINE);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.setBounds(600, 600, 600, 600);
				this.pack();
				this.setLocationRelativeTo(null);
				
				this.setLocation(900,140);
				this.setResizable(false);
				this.setVisible(true);
            }
        
    }

    

    

