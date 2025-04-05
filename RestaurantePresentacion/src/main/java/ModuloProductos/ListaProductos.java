/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloProductos;

import ControlNavegacion.Control;
import DependencyInjectors.DependencyInjector;
import Enums.TipoPlatillo;
import Exception.NegocioException;
import Exception.PresentacionException;
import ModuloProductosBO.IProductoBO;
import ModuloProductosDTOs.ProductoDTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author sonic
 */
public class ListaProductos extends JPanel {

    private IProductoBO productoBO;
    private ButtonGroup buttonGroup = new ButtonGroup();
    private JPanel contentPanel;
    private JTextField txtFiltroNombre;
    private JComboBox<TipoPlatillo> comboFiltroTipo;

    public ListaProductos() throws PresentacionException {
        productoBO = DependencyInjector.crearProductoBO();
        initComponents();
    }

    private void initComponents() throws PresentacionException {
        setLayout(new BorderLayout());

        // Panel de filtros
        JPanel filterPanel = crearPanelFiltros();
        add(filterPanel, BorderLayout.NORTH);

        // Panel de contenido con scroll
        contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(contentPanel);
        scrollPane.setPreferredSize(new Dimension(400, 500));
        add(scrollPane, BorderLayout.CENTER);

        // Carga inicial
        aplicarFiltros();
    }

    private JPanel crearPanelFiltros() {
        JPanel filterPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        filterPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtFiltroNombre = new JTextField();
        comboFiltroTipo = new JComboBox<>(TipoPlatillo.values());
        comboFiltroTipo.insertItemAt(null, 0);
        comboFiltroTipo.setSelectedIndex(0);

        // Listeners para filtros en tiempo real
        DocumentListener filterListener = new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                try {
                    aplicarFiltros();
                } catch (PresentacionException ex) {
                    Logger.getLogger(ListaProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void removeUpdate(DocumentEvent e) {
                try {
                    aplicarFiltros();
                } catch (PresentacionException ex) {
                    Logger.getLogger(ListaProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            public void changedUpdate(DocumentEvent e) {
                try {
                    aplicarFiltros();
                } catch (PresentacionException ex) {
                    Logger.getLogger(ListaProductos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };

        txtFiltroNombre.getDocument().addDocumentListener(filterListener);
        comboFiltroTipo.addActionListener(e -> {
            try {
                aplicarFiltros();
            } catch (PresentacionException ex) {
                Logger.getLogger(ListaProductos.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        filterPanel.add(new JLabel("Filtrar por nombre:"));
        filterPanel.add(new JLabel("Filtrar por tipo:"));
        filterPanel.add(txtFiltroNombre);
        filterPanel.add(comboFiltroTipo);

        return filterPanel;
    }

    private void aplicarFiltros() throws PresentacionException {
        String nombre = txtFiltroNombre.getText().trim();
        TipoPlatillo tipo = (TipoPlatillo) comboFiltroTipo.getSelectedItem();

        try {
            List<ProductoDTO> resultados = obtenerResultadosFiltrados(nombre, tipo);
            actualizarListaProductos(resultados);
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private List<ProductoDTO> obtenerResultadosFiltrados(String nombre, TipoPlatillo tipo) throws NegocioException, PresentacionException {
        if (!nombre.isEmpty() && tipo != null) {
            return Control.getInstancia().obtenerProductosPorTipoYNombre(nombre, tipo);
        } else if (!nombre.isEmpty()) {
            return Control.getInstancia().obtenerProductosPorNombre(nombre);
        } else if (tipo != null) {
            return Control.getInstancia().obtenerProductosPorTipo(tipo);
        } else {
            return Control.getInstancia().obtenerProductos();
        }
    }

    private void actualizarListaProductos(List<ProductoDTO> productos) {
        contentPanel.removeAll();

        for (ProductoDTO dto : productos) {
            JToggleButton btn = createProductButton(dto);
            contentPanel.add(btn);
            contentPanel.add(Box.createVerticalStrut(10));
        }

        contentPanel.revalidate();
        contentPanel.repaint();
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

    public static void main(String[] args) throws PresentacionException {
        JFrame frame = new JFrame("Menú de Productos");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new ListaProductos());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
