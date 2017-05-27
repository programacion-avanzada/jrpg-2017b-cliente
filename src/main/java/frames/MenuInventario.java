package frames;

import java.awt.BorderLayout;
import java.io.IOException;
import java.util.LinkedList;

import javax.swing.JFrame;

import inventario.Inventario;

public class MenuInventario extends JFrame {


    public MenuInventario(LinkedList<Integer> items) {
                this.setTitle("Inventario");
                this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					this.setLayout(new BorderLayout());
					this.add(new Inventario());

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.pack();
				this.setLocationRelativeTo(null);
				this.setResizable(false);
				this.setVisible(true);
            }
        
    }

    

    

