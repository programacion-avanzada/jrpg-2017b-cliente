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

public class Celda extends JPanel {

  
    private BufferedImage item;
    
    private JLabel label;
    public Celda() throws IOException  {
        item = ImageIO.read(new File("test.png"));
        
        //itemScaled = (BufferedImage) item.getScaledInstance(label.WIDTH, label.HEIGHT, Image.SCALE_SMOOTH);
        label = new JLabel(new ImageIcon(item.getScaledInstance(49, 49, Image.SCALE_DEFAULT)));
        label.setToolTipText("Item 1");
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

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(60, 60);
    }
}
