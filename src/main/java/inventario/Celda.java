package inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Item;

public class Celda extends JPanel {

  
    private BufferedImage item;
    
    private JLabel label;

	private void actionListenersYLabel(Item item) {
		StringBuilder s = new StringBuilder();
		label = new JLabel(new ImageIcon(this.item.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
        if(!item.getNombre().equals("No Item")) {
        	s.append("<html>" + item.getNombre() + "<br>");
        	if(item.getBonusSalud() != 0) {
        		s.append("+" + item.getBonusSalud() + " Salud " + "<br>");
        	}
        	if(item.getBonusEnergia() != 0) {
        		s.append("+" + item.getBonusEnergia() + " Energia " + "<br>");
        	}
        	if(item.getBonusAtaque() != 0) {
        		s.append("+" + item.getBonusAtaque() + " Ataque " + "<br>");
        	}
        	if(item.getBonusDefensa() != 0) {
        		s.append("+" + item.getBonusDefensa() + " Defensa " + "<br>");
        	}
        	if(item.getBonusMagia() != 0) {
        		s.append("+" + item.getBonusMagia() + " Magia");
        	}
        	s.append("</html>");
			label.setToolTipText(s.toString());
	       
	        label.addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		if(e.getClickCount() == 2) {
	        			JOptionPane.showMessageDialog(getParent(), "Lo toque");
	        		}
	        	}
	        });
	        
	        addMouseListener(new MouseAdapter() {
	        	@Override
	        	public void mouseClicked(MouseEvent e) {
	        		if(e.getClickCount() == 2) {
	        		JOptionPane.showMessageDialog(getParent(), "Lo toque");
	        		}
	        	}
	        });
        }
        add(label);
        this.validate();
        this.repaint();
	}

    public Celda(Item item) {
		this.item = item.getFoto();
		actionListenersYLabel(item);
	}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }
}
