/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.admin;

import Logica.asignaturaLogica;
import Modelo.Asignatura;
import Modelo.Programa;
import Persistencia.ProgramaJpaController;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ander
 */
public class gstAsignPane extends javax.swing.JPanel {

    /**
     * Creates new form gstAsignPane
     */
    private asignaturaLogica asigLogica = new asignaturaLogica();
    Asignatura asigEdicion;
    private ProgramaJpaController programDao = new ProgramaJpaController();
    
    public gstAsignPane(){
        initComponents();
        getProgramaCmb(ProgramaCmb);
        llenarTabla();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Nombre = new javax.swing.JTextField();
        Registrar = new javax.swing.JButton();
        Modificar = new javax.swing.JButton();
        Eliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaAsignaturas = new javax.swing.JTable();
        ProgramaCmb = new javax.swing.JComboBox<>();
        codigo = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("GESTIONAR ASIGNATURA");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("NOMBRE:");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("COD. PROGRAMA:");

        jLabel10.setBackground(new java.awt.Color(255, 255, 255));
        jLabel10.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 0, 0));
        jLabel10.setText("CODIGO:");

        Registrar.setBackground(new java.awt.Color(255, 0, 0));
        Registrar.setForeground(new java.awt.Color(255, 255, 255));
        Registrar.setText("REGISTRAR");
        Registrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RegistrarActionPerformed(evt);
            }
        });

        Modificar.setBackground(new java.awt.Color(255, 0, 0));
        Modificar.setForeground(new java.awt.Color(255, 255, 255));
        Modificar.setText("MODIFICAR");
        Modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ModificarActionPerformed(evt);
            }
        });

        Eliminar.setBackground(new java.awt.Color(255, 0, 0));
        Eliminar.setForeground(new java.awt.Color(255, 255, 255));
        Eliminar.setText("ELIMINAR");
        Eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarActionPerformed(evt);
            }
        });

        TablaAsignaturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaAsignaturas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TablaAsignaturasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TablaAsignaturas);

        ProgramaCmb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ProgramaCmbActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ProgramaCmb, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Registrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Modificar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Eliminar)))
                .addGap(17, 17, 17))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 178, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(ProgramaCmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Registrar)
                    .addComponent(Modificar)
                    .addComponent(Eliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void getProgramaCmb(JComboBox cmbProgama){
	programDao.getProgramaCmb(cmbProgama);
    }
    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
         // TODO add your handling code here:
        try {    
            Asignatura asig = new Asignatura();
            asig.setCodprograma((Programa) ProgramaCmb.getSelectedItem());
            asig.setCodasignatura(Integer.parseInt(codigo.getText()));
            asig.setNombreprograma(Nombre.getText());
            limpiar();
            llenarTabla();
            asigLogica.registrarAsignatura(asig);
            JOptionPane.showMessageDialog(this, "Asignatura registrado correctamente"); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Asignatura ya esta registrado");
        }
        llenarTabla();
    }//GEN-LAST:event_RegistrarActionPerformed
    
    public void llenarTabla() {
        List<Asignatura> listaAsignaturas = asigLogica.consultarAsignaturas();
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.addColumn("Cod. Asignatura");
        modelo.addColumn("Nombre");
        modelo.addColumn("Cod. Programa");
        String[] datos = new String[3];
        String tipoPrograma = ProgramaCmb.getSelectedItem().toString();
        for (Asignatura u : listaAsignaturas) {
            if(tipoPrograma.equals(u.getCodprograma().toString())){
                datos[0] = u.getCodasignatura().toString();
                datos[1] = u.getNombreprograma();
                datos[2] = u.getCodprograma().toString(); 
                modelo.addRow(datos);
                TablaAsignaturas.setModel(modelo);
            }
        }
    }
    
    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        if (asigEdicion != null) {
            try {
                // TODO add your handling code here:
                
                asigEdicion.setNombreprograma(Nombre.getText());
                asigEdicion.setCodasignatura(Integer.parseInt(codigo.getText()));
                asigEdicion.setCodprograma((Programa) ProgramaCmb.getSelectedItem());
                
                asigLogica.modificarAsignatura(asigEdicion);
                llenarTabla();
                limpiar();
                JOptionPane.showMessageDialog(this, "Usuario actualizado correctamente");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
         llenarTabla(); 
        Registrar.setEnabled(true);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        codigo.setEnabled(true);
        ProgramaCmb.setEnabled(true);
    }//GEN-LAST:event_ModificarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        if (asigEdicion != null) {
            int seguro = JOptionPane.showConfirmDialog(this, "¿Esta seguro que desea borrar la asignatura "+asigEdicion.getCodasignatura()+" con id "+"?");
            if(seguro==0){
                try {
                    asigLogica.eliminarAsignatura(asigEdicion.getCodasignatura());
                    JOptionPane.showMessageDialog(this, "asignatura eliminado correctamente");
                    limpiar();
                    
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
        llenarTabla();
        Registrar.setEnabled(true);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        codigo.setEnabled(true);
        ProgramaCmb.setEnabled(true);
    }//GEN-LAST:event_EliminarActionPerformed

    private void ProgramaCmbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ProgramaCmbActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_ProgramaCmbActionPerformed

    private void TablaAsignaturasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TablaAsignaturasMouseClicked
        // TODO add your handling code here:
        int fila = TablaAsignaturas.getSelectedRow();
        Registrar.setEnabled(false);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        codigo.setEnabled(false);
        ProgramaCmb.setEnabled(false);
        
        Integer cod = Integer.parseInt(TablaAsignaturas.getValueAt(fila, 0).toString());
        asigEdicion = asigLogica.consultarsignatura(cod);
        if (asigEdicion != null) {
            ProgramaCmb.setSelectedItem(asigEdicion.getCodprograma());
            codigo.setText(asigEdicion.getCodasignatura().toString());
            Nombre.setText(asigEdicion.getNombreprograma());
        }
    }//GEN-LAST:event_TablaAsignaturasMouseClicked

     public void limpiar() {
        codigo.setText("");
        Nombre.setText("");
        ProgramaCmb.setSelectedItem("Seleccione");
        Registrar.setEnabled(true);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        codigo.setEnabled(true);
        ProgramaCmb.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField Nombre;
    private javax.swing.JComboBox<Programa> ProgramaCmb;
    private javax.swing.JButton Registrar;
    private javax.swing.JTable TablaAsignaturas;
    private javax.swing.JTextField codigo;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
