package visual;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class Tema {

    public static final Color FONDO = new Color(0xF4F6FB);
    public static final Color PANEL = new Color(0xFFFFFF);
    public static final Color PRIMARIO = new Color(0x2D6CDF);
    public static final Color TEXTO = new Color(0x2B2F36);
    public static final Color TEXTO_SUAVE = new Color(0x6B7280);
    public static final Color BORDE = new Color(0xD9DEE8);
    public static final Color EXITO = new Color(0x2E9E5B);

    public static final Font TITULO = new Font("Segoe UI", Font.BOLD, 22);
    public static final Font SUBTITULO = new Font("Segoe UI", Font.BOLD, 15);
    public static final Font NORMAL = new Font("Segoe UI", Font.PLAIN, 13);
    public static final Font KPI = new Font("Segoe UI", Font.BOLD, 28);

    public static JButton botonPrimario(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(PRIMARIO);
        b.setForeground(Color.WHITE);
        b.setFont(SUBTITULO);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBorderPainted(false);
        b.setBorder(BorderFactory.createEmptyBorder(9, 18, 9, 18));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }

    public static JButton botonSecundario(String texto) {
        JButton b = new JButton(texto);
        b.setBackground(PANEL);
        b.setForeground(TEXTO);
        b.setFont(SUBTITULO);
        b.setFocusPainted(false);
        b.setOpaque(true);
        b.setBorder(BorderFactory.createLineBorder(BORDE));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return b;
    }
}