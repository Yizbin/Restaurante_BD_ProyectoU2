/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductos;

import DependencyInjectors.DependencyInjector;
import Exception.NegocioException;
import ModuloProductosBO.IProductoBO;
import ModuloProductosDTOs.ProductoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;

/**
 *
 * @author sonic
 */
public class ListaProductos extends JPanel {

    private IProductoBO productoBO;
    private ButtonGroup buttonGroup = new ButtonGroup();

    public ListaProductos() {
        productoBO = DependencyInjector.crearProductoBO();
        initComponents();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));

        try {
            List<ProductoDTO> productos = productoBO.obtenerProductos();
            for (ProductoDTO dto : productos) {
                JToggleButton btn = createProductButton(dto);
                contentPanel.add(btn);
                contentPanel.add(Box.createVerticalStrut(10)); // Espacio entre botones
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar productos", "Error", JOptionPane.ERROR_MESSAGE);
        }

        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setPreferredSize(new Dimension(400, 600));

        add(scrollPane, BorderLayout.CENTER);
    }

    private JToggleButton createProductButton(ProductoDTO producto) {
        JToggleButton button = new JToggleButton();
        button.setLayout(new BorderLayout());
        button.setPreferredSize(new Dimension(350, 100));

        // Contenido del botón con formato HTML
        String htmlContent = "<html><body style='width: 300px; padding: 5px;'>"
                + "<h3 style='margin: 0; color: #2c3e50;'>" + producto.getNombre() + "</h3>"
                + "<p style='margin: 2px 0; color: #e67e22; font-weight: bold;'>$" + producto.getPrecio() + "</p>"
                + "<p style='margin: 2px 0; color: #27ae60;'>" + producto.getTipo() + "</p>"
                + "<hr style='border-color: #bdc3c7; margin: 5px 0;'>"
                + "<p style='margin: 2px 0; color: #7f8c8d; font-size: 0.9em;'>" + producto.getDescripcion() + "</p>"
                + "</body></html>";

        button.setText(htmlContent);
        button.setVerticalTextPosition(SwingConstants.TOP);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setFont(new Font("Arial", Font.PLAIN, 12));

        // Estilos del botón
        button.setBackground(Color.WHITE);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(52, 152, 219), 1),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        button.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                button.setBackground(new Color(225, 245, 254));
            } else {
                button.setBackground(Color.WHITE);
            }
        });

        buttonGroup.add(button);
        return button;
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Menú de Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ListaProductos());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
