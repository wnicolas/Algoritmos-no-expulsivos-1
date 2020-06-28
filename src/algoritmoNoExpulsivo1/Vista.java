package algoritmoNoExpulsivo1;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import org.jfree.ui.RefineryUtilities;

public class Vista extends JFrame implements ActionListener {

    JButton btnIniciar;
    JButton btnCalcular;
    JButton btnGraficar;
    JTextField txtProcesos;
    JPanel panelCentral;
    DefaultTableModel modelo;
    Lista miLista;
    int contador = 0;

    public Vista() {

        miLista = new Lista();

        setTitle("Gestión de procesos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(750, 115, 525, 250);
        setResizable(false);
        setLayout(new BorderLayout());
        //PANEL SUPERIOR
        JPanel panelBotones = new JPanel();
        add(panelBotones, BorderLayout.NORTH);
        JPanel panelBotones2 = new JPanel();
        add(panelBotones2, BorderLayout.SOUTH);

        txtProcesos = new JTextField("Procesos");
        panelBotones.add(txtProcesos);
        btnIniciar = new JButton("Iniciar simulación");
        btnIniciar.addActionListener(this);
        btnCalcular = new JButton("Calcular");
        btnCalcular.addActionListener(this);
        btnCalcular.setEnabled(false);
        btnGraficar = new JButton("Graficar");
        btnGraficar.addActionListener(this);
        btnGraficar.setEnabled(false);
        panelBotones.add(btnIniciar);
        //PANEL CENTRAL Y TABLA
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        add(panelCentral, BorderLayout.CENTER);

        panelBotones2.add(btnCalcular, BorderLayout.SOUTH);
        panelBotones2.add(btnGraficar, BorderLayout.SOUTH);

        modelo = new DefaultTableModel();
        modelo.addColumn("Proceso");
        modelo.addColumn("T. llegada");
        modelo.addColumn("Ráfaga");
        modelo.addColumn("T. comienzo");
        modelo.addColumn("T. final");
        modelo.addColumn("T. retorno");
        modelo.addColumn("T. espera");

        JTable miTabla = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(miTabla);
        scroll.setBounds(10, 10, 500, 140);
        panelCentral.add(scroll);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnIniciar) {
            int procesos = Integer.parseInt(txtProcesos.getText());

            for (int i = 0; i < procesos; i++) {
                miLista.insertarNodo(((int) (Math.random() * 15) + 1));
            }
            Nodo recorrido = miLista.inicio;
            for (int i = 0; i < procesos; i++) {
                Object p[] = {recorrido.getTurno(), recorrido.gettLlegada(), recorrido.getRafaga()};
                modelo.addRow(p);
                recorrido = recorrido.siguiente;
            }

            btnIniciar.setEnabled(false);
            btnCalcular.setEnabled(true);
            //miLista.mostrarLista();

        } else {
            if (contador < Integer.parseInt(txtProcesos.getText())) {
                int proceso, llegada, rafaga, comienzo, fin, retorno, espera;
                proceso = (int) modelo.getValueAt(contador, 0);
                llegada = (int) modelo.getValueAt(contador, 1);
                rafaga = (int) modelo.getValueAt(contador, 2);

                if (contador == 0) {
                    comienzo = 0;
                } else {
                    comienzo = (int) modelo.getValueAt(contador - 1, 4);
                }
                fin = rafaga + comienzo;
                retorno = fin - llegada;
                espera = retorno - rafaga;

                Object p[] = {proceso, llegada, rafaga, comienzo, fin, retorno, espera};
                modelo.removeRow(contador);
                modelo.insertRow(contador, p);

                contador++;
            } else if (e.getSource() == btnCalcular) {
                JOptionPane.showMessageDialog(null, "No hay más cálculos por realizar.");
                btnCalcular.setEnabled(false);
                btnGraficar.setEnabled(true);
            } else {

                int matriz1[][] = new int[Integer.parseInt(txtProcesos.getText())][2];
                int matriz2[][] = new int[Integer.parseInt(txtProcesos.getText())][2];

                for (int i = 0; i < Integer.parseInt(txtProcesos.getText()); i++) {
                    matriz1[i][0] = (int) modelo.getValueAt(i, 3);
                    matriz2[i][0] = (int) modelo.getValueAt(i, 1);
                }
                for (int i = 0; i < Integer.parseInt(txtProcesos.getText()); i++) {
                    matriz1[i][1] = (int) modelo.getValueAt(i, 4);
                    matriz2[i][1] = (int) modelo.getValueAt(i, 3);
                }

                final Gantt demo = new Gantt("Diagrama de Gantt",matriz1,matriz2,Integer.parseInt(txtProcesos.getText()));
                demo.pack();
                RefineryUtilities.centerFrameOnScreen(demo);
                demo.setVisible(true);

            }

        }
    }

}
