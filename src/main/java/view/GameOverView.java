/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

/**
 *
 * @author bonnychen
 */
public class GameOverView extends javax.swing.JPanel {

    /**
     * Creates new form GameOverView
     */
    public GameOverView() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameOverPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        mainMenuButton = new javax.swing.JButton();
        restartButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setLayout(null);

        gameOverPanel.setBackground(new java.awt.Color(255, 204, 102));

        titleLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 24)); // NOI18N
        titleLabel.setText("All your animals have died :(");

        mainMenuButton.setBackground(new java.awt.Color(255, 238, 173));
        mainMenuButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        mainMenuButton.setText("Main Menu");
        mainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainMenuButtonActionPerformed(evt);
            }
        });

        restartButton.setBackground(new java.awt.Color(255, 238, 173));
        restartButton.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        restartButton.setText("Restart");
        restartButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restartButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout gameOverPanelLayout = new javax.swing.GroupLayout(gameOverPanel);
        gameOverPanel.setLayout(gameOverPanelLayout);
        gameOverPanelLayout.setHorizontalGroup(
            gameOverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(gameOverPanelLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(titleLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameOverPanelLayout.createSequentialGroup()
                .addGap(0, 123, Short.MAX_VALUE)
                .addGroup(gameOverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(121, 121, 121))
        );
        gameOverPanelLayout.setVerticalGroup(
            gameOverPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, gameOverPanelLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(titleLabel)
                .addGap(41, 41, 41)
                .addComponent(restartButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addComponent(mainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(64, Short.MAX_VALUE))
        );

        add(gameOverPanel);
        gameOverPanel.setBounds(260, 110, 400, 340);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/farm.png"))); // NOI18N
        add(jLabel1);
        jLabel1.setBounds(0, 0, 940, 570);
    }// </editor-fold>//GEN-END:initComponents

    private void mainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainMenuButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mainMenuButtonActionPerformed

    private void restartButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restartButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_restartButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel gameOverPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton mainMenuButton;
    private javax.swing.JButton restartButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}