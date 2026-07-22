package visual; 

import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class FondoMenu extends JPanel {

    private Image imagenFondo;

    public FondoMenu(String rutaImagen) {
        URL imageURL = getClass().getResource(rutaImagen);
        
        if (imageURL != null) {
            this.imagenFondo = new ImageIcon(imageURL).getImage();
        } else {
            System.err.println("Error: No se pudo encontrar la imagen en la ruta: " + rutaImagen);
        }
        
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}