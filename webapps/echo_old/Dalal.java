/*
 * NewJApplet.java
 *
 * Created on September 14, 2006, 5:56 PM
 */

import java.net.*;
/**
 *
 * @author  cyber
 */
public class Dalal extends javax.swing.JApplet {
static final long serialVersionUID=2323;    
    public URL codebase;
    /** Initializes the applet NewJApplet */
    public void init() {
        codebase=getCodeBase();
	try {
            java.awt.EventQueue.invokeAndWait(new Runnable() {
                public void run() {
                    initComponents();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /** This method is called from within the init() method to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
        jTextField1 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        jLabel1.setText("Userid :");

        jButton1.setText("OK");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                submit(evt);
            }
        });

        jButton2.setText("Reset");
        jButton2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reset(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(29, 29, 29)
                .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 81, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(38, 38, 38)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 126, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(126, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .add(43, 43, 43)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 45, Short.MAX_VALUE)
                .add(jButton1)
                .add(191, 191, 191))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(51, 51, 51)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel1))
                .add(41, 41, 41)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jButton1)
                    .add(jButton2))
                .addContainerGap(164, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void submit(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_submit
// TODO add your handling code here:
     URL urlServlet;
     boolean booleanStart=true;
     try
     {
    if(booleanStart==true)
    {
          urlServlet = new URL(codebase, "echo"+"?querypoint=start");
                        booleanStart=false;
                }
                else
                {       urlServlet = new URL(codebase, "echo"+"?querypoint=continue");
                }
                URLConnection con = urlServlet.openConnection();
                // configuration
                con.setDoInput(true);
                con.setDoOutput(true);
                con.setUseCaches(false);
                con.setRequestProperty(
                        "Content-Type",
                        "application/x-java-serialized-object");
     }
     catch(Exception e)
     {}
    }//GEN-LAST:event_submit

    private void reset(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reset
// TODO add your handling code here:
        jTextField1.setText("");
    }//GEN-LAST:event_reset
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
    
}
