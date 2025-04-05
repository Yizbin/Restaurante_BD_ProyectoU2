/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModuloIngredientes;

import Enums.UnidadMedida;
import Exception.NegocioException;
import ModuloIngredienteBO.IingredienteBO;
import ModuloIngredientesDTOs.IngredienteDTO;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 *
 * @author Abraham Coronel Bringas
 */
public class ListaIngredientes extends JPanel {

    private IingredienteBO ingredienteBO;
    private JPanel panelFiltros;
    private JPanel panelIngredientes;
    private JTextField txtFiltroNombre;
    private JComboBox<UnidadMedida> cmbUnidadMedida;

    public ListaIngredientes(IingredienteBO ingredienteBO) {
        this.ingredienteBO = ingredienteBO;
        initComponents();
        cargarIngredientes();
    }

    private void initComponents() {
        setLayout(new BorderLayout());

        // Panel de filtros
        panelFiltros = new JPanel();
        panelFiltros.add(new JLabel("Filtrar por nombre:"));
        txtFiltroNombre = new JTextField(15);
        panelFiltros.add(txtFiltroNombre);

        panelFiltros.add(new JLabel("Filtrar por unidad de medida:"));
        cmbUnidadMedida = new JComboBox<>(UnidadMedida.values());
        cmbUnidadMedida.insertItemAt(null, 0); // Opción para mostrar todos
        cmbUnidadMedida.setSelectedIndex(0);
        panelFiltros.add(cmbUnidadMedida);

        JButton btnFiltrar = new JButton("Filtrar");
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtrarIngredientes();
            }
        });
        panelFiltros.add(btnFiltrar);

        add(panelFiltros, BorderLayout.NORTH);

        // Panel de ingredientes
        panelIngredientes = new JPanel();
        panelIngredientes.setLayout(new GridLayout(0, 1, 5, 5));

        JScrollPane scrollPane = new JScrollPane(panelIngredientes);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void cargarIngredientes() {
        try {
            List<IngredienteDTO> ingredientes = ingredienteBO.buscarTodos();
            mostrarIngredientes(ingredientes);
        } catch (NegocioException ex) {
            ex.printStackTrace();
            // Aquí podrías mostrar un mensaje de error al usuario
        }
    }

    private void filtrarIngredientes() {
        String nombre = txtFiltroNombre.getText().trim();
        UnidadMedida unidad = (UnidadMedida) cmbUnidadMedida.getSelectedItem();

        try {
            List<IngredienteDTO> ingredientes;
            if (!nombre.isEmpty() && unidad != null) {
                // Buscar por ambos filtros
                IngredienteDTO ingrediente = ingredienteBO.buscarPorNombre(nombre, unidad);
                mostrarIngredientes(ingrediente != null ? List.of(ingrediente) : List.of());
            } else if (!nombre.isEmpty()) {
                // Buscar solo por nombre (necesitarías un método adicional en la interfaz)
                ingredientes = ingredienteBO.buscarTodos().stream()
                        .filter(i -> i.getNombre().toLowerCase().contains(nombre.toLowerCase()))
                        .toList();
                mostrarIngredientes(ingredientes);
            } else if (unidad != null) {
                // Buscar solo por unidad de medida
                ingredientes = ingredienteBO.buscarTodos().stream()
                        .filter(i -> i.getUnidadMedida() == unidad)
                        .toList();
                mostrarIngredientes(ingredientes);
            } else {
                // Mostrar todos
                cargarIngredientes();
            }
        } catch (NegocioException ex) {
            ex.printStackTrace();
            // Aquí podrías mostrar un mensaje de error al usuario
        }
    }

    private void mostrarIngredientes(List<IngredienteDTO> ingredientes) {
        panelIngredientes.removeAll();

        if (ingredientes.isEmpty()) {
            panelIngredientes.add(new JLabel("No se encontraron ingredientes"));
        } else {
            for (IngredienteDTO ingrediente : ingredientes) {
                JButton btnIngrediente = new JButton(crearTextoBoton(ingrediente));
                btnIngrediente.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Aquí puedes manejar lo que sucede cuando se hace clic en un ingrediente
                        System.out.println("Seleccionado: " + ingrediente.getNombre());
                    }
                });
                panelIngredientes.add(btnIngrediente);
            }
        }

        panelIngredientes.revalidate();
        panelIngredientes.repaint();
    }

    private String crearTextoBoton(IngredienteDTO ingrediente) {
        return "<html><b>" + ingrediente.getNombre() + "</b><br>"
                + "Unidad medida: " + ingrediente.getUnidadMedida() + "<br>"
                + "Stock disponible: " + ingrediente.getStock() + "</html>";
    }

}
