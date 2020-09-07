/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista.admin;

import Vista.CambiaPanel;
import Vista.login;
import java.awt.Color;
import javax.swing.JPanel;

/**
 *
 * @author ander
 */
public class admPrimcipal extends javax.swing.JFrame {

    
    /**
     * Creates new form admHome
     */
    public admPrimcipal() {
        initComponents();
        this.setResizable(false);
        
        this.setLocationRelativeTo(null);
       this.gstAdmi.setSelected(true);
       new CambiaPanel(PrincipalPane, new admHomePane());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PeneContent = new javax.swing.JPanel();
        PrincipalPane = new javax.swing.JPanel();
        cerrar = new javax.swing.JLabel();
        Desplegable = new javax.swing.JPanel();
        gstProf = new Vista.recurso.RSButtonMetro();
        gstFun = new Vista.recurso.RSButtonMetro();
        gstProg = new Vista.recurso.RSButtonMetro();
        gstAdmi = new Vista.recurso.RSButtonMetro();
        gstVigi = new Vista.recurso.RSButtonMetro();
        gstAsign = new Vista.recurso.RSButtonMetro();
        gsdAmb = new Vista.recurso.RSButtonMetro();
        gstSede = new Vista.recurso.RSButtonMetro();
        admHouse = new javax.swing.JLabel();
        back = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        PeneContent.setBackground(new java.awt.Color(255, 255, 255));
        PeneContent.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(255, 0, 0)));

        PrincipalPane.setBackground(new java.awt.Color(255, 255, 255));
        PrincipalPane.setPreferredSize(new java.awt.Dimension(704, 400));
        PrincipalPane.setLayout(new javax.swing.BoxLayout(PrincipalPane, javax.swing.BoxLayout.LINE_AXIS));

        cerrar.setBackground(new java.awt.Color(255, 255, 255));
        cerrar.setForeground(new java.awt.Color(230, 0, 0));
        cerrar.setText(" X ");
        cerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
        });

        Desplegable.setBackground(new java.awt.Color(255, 0, 0));

        gstProf.setText("Gestionar Profesor");
        gstProf.setColorHover(new java.awt.Color(255, 255, 255));
        gstProf.setColorNormal(new java.awt.Color(255, 0, 0));
        gstProf.setColorPressed(new java.awt.Color(222, 4, 4));
        gstProf.setColorTextHover(new java.awt.Color(255, 0, 0));

        gstFun.setText("Gestionar Funcionario");
        gstFun.setColorHover(new java.awt.Color(255, 255, 255));
        gstFun.setColorNormal(new java.awt.Color(255, 0, 0));
        gstFun.setColorPressed(new java.awt.Color(222, 4, 4));
        gstFun.setColorTextHover(new java.awt.Color(255, 0, 0));
        gstFun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gstFunActionPerformed(evt);
            }
        });

        gstProg.setText("Gestionar Programa");
        gstProg.setColorHover(new java.awt.Color(255, 255, 255));
        gstProg.setColorNormal(new java.awt.Color(255, 0, 0));
        gstProg.setColorPressed(new java.awt.Color(222, 4, 4));
        gstProg.setColorTextHover(new java.awt.Color(255, 0, 0));

        gstAdmi.setText("Gestionar Administrador");
        gstAdmi.setColorHover(new java.awt.Color(255, 255, 255));
        gstAdmi.setColorNormal(new java.awt.Color(255, 0, 0));
        gstAdmi.setColorPressed(new java.awt.Color(222, 4, 4));
        gstAdmi.setColorTextHover(new java.awt.Color(255, 0, 0));
        gstAdmi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gstAdmiActionPerformed(evt);
            }
        });

        gstVigi.setText("Gestionar Vigilante");
        gstVigi.setColorHover(new java.awt.Color(255, 255, 255));
        gstVigi.setColorNormal(new java.awt.Color(255, 0, 0));
        gstVigi.setColorPressed(new java.awt.Color(222, 4, 4));
        gstVigi.setColorTextHover(new java.awt.Color(255, 0, 0));

        gstAsign.setText("Gestionar Asignatura");
        gstAsign.setColorHover(new java.awt.Color(255, 255, 255));
        gstAsign.setColorNormal(new java.awt.Color(255, 0, 0));
        gstAsign.setColorPressed(new java.awt.Color(222, 4, 4));
        gstAsign.setColorTextHover(new java.awt.Color(255, 0, 0));

        gsdAmb.setText("Gestionar Ambiente");
        gsdAmb.setColorHover(new java.awt.Color(255, 255, 255));
        gsdAmb.setColorNormal(new java.awt.Color(255, 0, 0));
        gsdAmb.setColorPressed(new java.awt.Color(222, 4, 4));
        gsdAmb.setColorTextHover(new java.awt.Color(255, 0, 0));

        gstSede.setText("Gestionar Sede");
        gstSede.setColorHover(new java.awt.Color(255, 255, 255));
        gstSede.setColorNormal(new java.awt.Color(255, 0, 0));
        gstSede.setColorPressed(new java.awt.Color(222, 4, 4));
        gstSede.setColorTextHover(new java.awt.Color(255, 0, 0));

        admHouse.setBackground(new java.awt.Color(255, 0, 0));
        admHouse.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/recurso/home.png"))); // NOI18N
        admHouse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        admHouse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                admHouseMouseClicked(evt);
            }
        });

        back.setBackground(new java.awt.Color(255, 0, 0));
        back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vista/recurso/back.png"))); // NOI18N
        back.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout DesplegableLayout = new javax.swing.GroupLayout(Desplegable);
        Desplegable.setLayout(DesplegableLayout);
        DesplegableLayout.setHorizontalGroup(
            DesplegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gstFun, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gstProf, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gstVigi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gstAsign, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gstProg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gsdAmb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(gstSede, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DesplegableLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE)
                .addGap(80, 80, 80)
                .addComponent(admHouse)
                .addContainerGap())
            .addComponent(gstAdmi, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        DesplegableLayout.setVerticalGroup(
            DesplegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(DesplegableLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(DesplegableLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(admHouse, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(2, 2, 2)
                .addComponent(gstAdmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gstProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gstFun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gstVigi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gstAsign, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gstProg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gsdAmb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(gstSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout PeneContentLayout = new javax.swing.GroupLayout(PeneContent);
        PeneContent.setLayout(PeneContentLayout);
        PeneContentLayout.setHorizontalGroup(
            PeneContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PeneContentLayout.createSequentialGroup()
                .addComponent(Desplegable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(PrincipalPane, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cerrar))
        );
        PeneContentLayout.setVerticalGroup(
            PeneContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(Desplegable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(PeneContentLayout.createSequentialGroup()
                .addGroup(PeneContentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PeneContentLayout.createSequentialGroup()
                        .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PeneContentLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(PrincipalPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PeneContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PeneContent, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        dispose();
        System.exit(0);
        this.setVisible(false);
    }//GEN-LAST:event_cerrarMouseClicked
     
    private void backMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backMouseClicked
        login Login = new login();
        Login.setVisible(true);
        dispose();
        this.setVisible(false);
        
    }//GEN-LAST:event_backMouseClicked

    private void gstAdmiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gstAdmiActionPerformed
        
        new CambiaPanel(PrincipalPane, new gstAminPane());
        /*
        if(gstAdmi.isSelected()){
            gstAdmi.setColorNormal(new Color(255, 255, 255));
            gstAdmi.setColorTextNormal(new Color(255, 0, 0));
        }else{
            gstAdmi.setColorNormal(new Color(255, 0, 0));
            gstAdmi.setColorTextNormal(new Color(255, 255, 255));
        }*/
        
    }//GEN-LAST:event_gstAdmiActionPerformed

    private void admHouseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_admHouseMouseClicked
      new CambiaPanel(PrincipalPane, new admHomePane());
    }//GEN-LAST:event_admHouseMouseClicked

    private void gstFunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gstFunActionPerformed
        new CambiaPanel(PrincipalPane, new gstFuncPane());
    }//GEN-LAST:event_gstFunActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Desplegable;
    private javax.swing.JPanel PeneContent;
    private javax.swing.JPanel PrincipalPane;
    private javax.swing.JLabel admHouse;
    private javax.swing.JLabel back;
    private javax.swing.JLabel cerrar;
    private Vista.recurso.RSButtonMetro gsdAmb;
    private Vista.recurso.RSButtonMetro gstAdmi;
    private Vista.recurso.RSButtonMetro gstAsign;
    private Vista.recurso.RSButtonMetro gstFun;
    private Vista.recurso.RSButtonMetro gstProf;
    private Vista.recurso.RSButtonMetro gstProg;
    private Vista.recurso.RSButtonMetro gstSede;
    private Vista.recurso.RSButtonMetro gstVigi;
    // End of variables declaration//GEN-END:variables
}