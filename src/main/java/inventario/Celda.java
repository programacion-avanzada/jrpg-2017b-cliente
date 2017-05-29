package inventario;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dominio.Item;

public class Celda extends JPanel {

  
    private BufferedImage item;
    
    private JLabel label;
    public Celda() throws IOException {
        item = ImageIO.read(new File("recursos//noItem.png"));
//        actionListenersYLabel();
    }

	private void actionListenersYLabel(Item item) {
		label = new JLabel(new ImageIcon(this.item.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
        label.setToolTipText(item.getNombre());
        this.validate();
        this.repaint();
        label.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount() == 2) {
        			JOptionPane.showMessageDialog(getParent(), "Lo toque");
        		}
        	}
        });
        add(label);
        addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if(e.getClickCount() == 2) {
        		JOptionPane.showMessageDialog(getParent(), "Lo toque");
        		}
        	}
        });
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
