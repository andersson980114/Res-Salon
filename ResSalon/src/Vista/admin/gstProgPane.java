/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.admin;

import Logica.programaLogica;
import Modelo.Programa;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ander
 */
public class gstProgPane extends javax.swing.JPanel {

    /**
     * Creates new form gstProgPane
     */
    
     private programaLogica programaLogica = new programaLogica();
    Programa programaEdicion;
    
    public gstProgPane() {
        initComponents();
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
        tablaPrograma = new javax.swing.JTable();
        jornada = new javax.swing.JComboBox<>();
        Codigo = new javax.swing.JTextField();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 0, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 0, 0));
        jLabel3.setText("GESTIONAR PROGRAMA");

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 0, 0));
        jLabel4.setText("NOMBRE:");

        jLabel9.setBackground(new java.awt.Color(255, 255, 255));
        jLabel9.setFont(new java.awt.Font("Segoe UI Historic", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 0, 0));
        jLabel9.setText("JORNADA:");

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

        tablaPrograma.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaPrograma.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProgramaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaPrograma);

        jornada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Diurna", "Nocturna" }));
        jornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jornadaActionPerformed(evt);
            }
        });

        Codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CodigoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(Registrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Modificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Eliminar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 606, Short.MAX_VALUE))
                        .addGap(17, 17, 17))))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jornada, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 235, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(Codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void RegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RegistrarActionPerformed
        // TODO add your handling code here:
        int jor =0;
        if(jornada.getSelectedItem().equals("Diurna")){
            jor = 1;
        }else{
            jor = 2;
        }
         try {    
            Programa programa = new Programa();
            programa.setJornada(jor);
            programa.setNombreprograma(Nombre.getText());
            programa.setCodprograma(Integer.parseInt(Codigo.getText())); 
            limpiar();
            llenarTabla();
            programaLogica.registrarPrograma(programa);
            JOptionPane.showMessageDialog(this, "Programa registrado correctamente"); 
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Programa ya esta registrado");
        }
        llenarTabla();
    }//GEN-LAST:event_RegistrarActionPerformed
    
   public void llenarTabla() {
        List<Programa> listaPrograma = programaLogica.consultarPrograma();
        DefaultTableModel modelo = new DefaultTableModel();
        
        String jorn= " ", jorn2;
        int jor=0;
        modelo.addColumn("Codigo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Jornada");
        String[] datos = new String[3];
        for (Programa u : listaPrograma) {
            jorn2 = jornada.getSelectedItem().toString();
            if(jorn2.equals("Diurna")){
                jor=1;
            }else{
                jor=2;
            }
            if(jor == u.getJornada()){
                if(u.getJornada()==1){
                    jorn = "Diurna";
                }else{
                    jorn = "Nocturna";
                }
                datos[0] = u.getCodprograma().toString();
                datos[1] = u.getNombreprograma(); 
                datos[2] = jorn;

                modelo.addRow(datos);
                tablaPrograma.setModel(modelo);
            }
            
        }
   }
   
   
    
    private void jornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jornadaActionPerformed
        // TODO add your handling code here:
        llenarTabla();
    }//GEN-LAST:event_jornadaActionPerformed

    private void CodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CodigoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CodigoActionPerformed

    private void tablaProgramaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProgramaMouseClicked
        // TODO add your handling code here:
        int fila = tablaPrograma.getSelectedRow();
        Registrar.setEnabled(false);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        Codigo.setEnabled(false);
        
        Integer codigo = Integer.parseInt(tablaPrograma.getValueAt(fila, 0).toString());
        programaEdicion = programaLogica.consultarPrograma(codigo);
        if (programaEdicion != null) {
            int jor = programaEdicion.getJornada();
            String jorn;
            if(jor==1){jorn = "Diurna";}else{jorn="Nocturna";}
        
            jornada.setSelectedItem(jorn);
            Codigo.setText(programaEdicion.getCodprograma().toString());
            Nombre.setText(programaEdicion.getNombreprograma());
            
        }
    }//GEN-LAST:event_tablaProgramaMouseClicked

    private void ModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ModificarActionPerformed
        // TODO add your handling code here:
        int jor = programaEdicion.getJornada();
        if (programaEdicion != null) {
            try {
                // TODO add your handling code here:
                
                programaEdicion.setNombreprograma(Nombre.getText());
                programaEdicion.setCodprograma(Integer.parseInt(Codigo.getText()));
                programaEdicion.setJornada(jor);
                
                programaLogica.modificarPrograma(programaEdicion);
                llenarTabla();
                limpiar();
                JOptionPane.showMessageDialog(this, "Programa actualizado correctamente");
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, ex.getMessage());
            }
        }
          
        Registrar.setEnabled(true);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        Codigo.setEnabled(true);
    }//GEN-LAST:event_ModificarActionPerformed

    private void EliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarActionPerformed
        // TODO add your handling code here:
        if (programaEdicion != null) {
            int seguro = JOptionPane.showConfirmDialog(this, "Esta seguro que desea borrar el Programa "+programaEdicion.getNombreprograma()+" con codigo "+programaEdicion.getCodprograma()+"?");
            if(seguro==0){
                try {
                    programaLogica.eliminarPrograma(programaEdicion.getCodprograma());
                    JOptionPane.showMessageDialog(this, "Programa eliminado correctamente");
                    limpiar();
                    llenarTabla();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, ex.getMessage());
                }
            }
        }
         Registrar.setEnabled(true);
        Modificar.setEnabled(true);
        Eliminar.setEnabled(true);
        Codigo.setEnabled(true);
    }//GEN-LAST:event_EliminarActionPerformed
    
    public void limpiar() {
        Codigo.setText("");
        Nombre.setText("");
        jornada.setSelectedItem("Seleccione");
        Registrar.setEnabled(true);
        Modificar.setEnabled(false);
        Eliminar.setEnabled(false);
        Codigo.setEditable(true);
        Eliminar.setEnabled(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Codigo;
    private javax.swing.JButton Eliminar;
    private javax.swing.JButton Modificar;
    private javax.swing.JTextField Nombre;
    private javax.swing.JButton Registrar;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jornada;
    private javax.swing.JTable tablaPrograma;
    // End of variables declaration//GEN-END:variables
}
