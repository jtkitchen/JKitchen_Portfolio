/**
 * Python Program Tester
 * Roberts/Houghton 2015
 * 
 * GUI that allows matching of filenames when running the tests.
 * @author willhoft_robert
 */
package pythonprogramtester;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author willhoft_robert
 */
public class FileNameMapper extends javax.swing.JDialog {

    /**
     * Creates new form FileNameMapperDialog
     */
    public FileNameMapper( java.awt.Frame parent, boolean modal,
                                 List<String> testFile, String testingPath ) throws InvalidTagFormatException{
        super(parent, modal);
        
        filePath = testingPath;
        initMap( testFile, filePath );
        
        userFiles = getFiles( testingPath, ".py" );
        
        unknownFiles = new LinkedList<>();

        for( String str : nameMap.keySet() )
        {
            if( !userFiles.contains( nameMap.get(str) ) )
            {
                unknownFiles.add( str );
            }
        }
        
        userFiles.add(0,"(none)");      
        userFilesArray = userFiles.toArray( new String[userFiles.size()]);
        
        initComponents();

        if( 0 < unknownFiles.size() )
        {
            testName0_jLabel.setText( unknownFiles.get(0) );
        }
        
        if( 1 < unknownFiles.size() )
        {
            testName1_jLabel.setText( unknownFiles.get(1) );
        }
        else
        {
            testName1_jLabel.setText( "" );
            userName1_jComboBox.setVisible( false );
        }
        
        if( 2 < unknownFiles.size() )
        {
            testName2_jLabel.setText( unknownFiles.get(2) );
        }
        else
        {
            testName2_jLabel.setText( "" );
            userName2_jComboBox.setVisible( false );
        }
        
        if( 3 < unknownFiles.size() )
        {
            testName3_jLabel.setText( unknownFiles.get(3) );
        }
        else
        {
            testName3_jLabel.setText( "" );
            userName3_jComboBox.setVisible( false );
        }
        
        if( 4 < unknownFiles.size() )
        {
            testName4_jLabel.setText( unknownFiles.get(4) );
        }
        else
        {
            testName4_jLabel.setText( "" );
            userName4_jComboBox.setVisible( false );
        }
        
        if( 5 < unknownFiles.size() )
        {
            testName5_jLabel.setText( unknownFiles.get(5) );
        }
        else
        {
            testName5_jLabel.setText( "" );
            userName5_jComboBox.setVisible( false );
        }
        
        if( 6 < unknownFiles.size() )
        {
            testName6_jLabel.setText( unknownFiles.get(6) );
        }
        else
        {
            testName6_jLabel.setText( "" );
            userName6_jComboBox.setVisible( false );
        }
        
        if( 7 < unknownFiles.size() )
        {
            testName7_jLabel.setText( unknownFiles.get(7) );
        }
        else
        {
            testName7_jLabel.setText( "" );
            userName7_jComboBox.setVisible( false );
        }
        
        if( 8 < unknownFiles.size() )
        {
            testName8_jLabel.setText( unknownFiles.get(8) );
        }
        else
        {
            testName8_jLabel.setText( "" );
            userName8_jComboBox.setVisible( false );
        }
        
        if( 9 < unknownFiles.size() )
        {
            testName9_jLabel.setText( unknownFiles.get(9) );
        }
        else
        {
            testName9_jLabel.setText( "" );
            userName9_jComboBox.setVisible( false );
        }
        
        if( 10 < unknownFiles.size() )
        {
            testName10_jLabel.setText( unknownFiles.get(10) );
        }
        else
        {
            testName10_jLabel.setText( "" );
            userName10_jComboBox.setVisible( false );
        }
        
        if( 11 < unknownFiles.size() )
        {
            testName11_jLabel.setText( unknownFiles.get(11) );
        }
        else
        {
            testName11_jLabel.setText( "" );
            userName11_jComboBox.setVisible( false );
        }
        
        if( 12 < unknownFiles.size() )
        {
            testName12_jLabel.setText( unknownFiles.get(12) );
        }
        else
        {
            testName12_jLabel.setText( "" );
            userName12_jComboBox.setVisible( false );
        }
        
        if( 13 < unknownFiles.size() )
        {
            testName13_jLabel.setText( unknownFiles.get(13) );
        }
        else
        {
            testName13_jLabel.setText( "" );
            userName13_jComboBox.setVisible( false );
        }
        
        if( 14 < unknownFiles.size() )
        {
            testName14_jLabel.setText( unknownFiles.get(14) );
        }
        else
        {
            testName14_jLabel.setText( "" );
            userName14_jComboBox.setVisible( false );
        }
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
        testName1_jLabel = new javax.swing.JLabel();
        userName1_jComboBox = new javax.swing.JComboBox( userFilesArray );
        jLabel1 = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();
        testName2_jLabel = new javax.swing.JLabel();
        userName2_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName3_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName4_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName5_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName6_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName7_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName14_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName12_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName9_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName10_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName8_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName11_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName0_jComboBox = new javax.swing.JComboBox( userFilesArray );
        userName13_jComboBox = new javax.swing.JComboBox( userFilesArray );
        testName3_jLabel = new javax.swing.JLabel();
        testName4_jLabel = new javax.swing.JLabel();
        testName6_jLabel = new javax.swing.JLabel();
        testName5_jLabel = new javax.swing.JLabel();
        testName7_jLabel = new javax.swing.JLabel();
        testName8_jLabel = new javax.swing.JLabel();
        testName9_jLabel = new javax.swing.JLabel();
        testName10_jLabel = new javax.swing.JLabel();
        testName11_jLabel = new javax.swing.JLabel();
        testName12_jLabel = new javax.swing.JLabel();
        testName13_jLabel = new javax.swing.JLabel();
        testName14_jLabel = new javax.swing.JLabel();
        testName0_jLabel = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(536, 436));
        setResizable(false);

        jPanel1.setMaximumSize(new java.awt.Dimension(530, 430));
        jPanel1.setMinimumSize(new java.awt.Dimension(530, 430));

        testName1_jLabel.setText("jLabel1");

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Unknown Files");

        okButton.setText("OK");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        testName2_jLabel.setText("jLabel1");

        testName3_jLabel.setText("jLabel1");

        testName4_jLabel.setText("jLabel1");

        testName6_jLabel.setText("jLabel1");

        testName5_jLabel.setText("jLabel1");

        testName7_jLabel.setText("jLabel1");

        testName8_jLabel.setText("jLabel1");

        testName9_jLabel.setText("jLabel1");

        testName10_jLabel.setText("jLabel1");

        testName11_jLabel.setText("jLabel1");

        testName12_jLabel.setText("jLabel1");

        testName13_jLabel.setText("jLabel1");

        testName14_jLabel.setText("jLabel1");

        testName0_jLabel.setText("jLabel1");

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        jButton1.setText("Clear");
        jButton1.setToolTipText("Clears all the file mappings");
        jButton1.setEnabled(false);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName1_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName1_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName2_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName2_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName3_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName3_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName4_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName4_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName5_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName5_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName6_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName6_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName7_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName7_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName8_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName8_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName9_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName9_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName10_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName10_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName11_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName11_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName12_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName12_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName13_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName13_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(userName14_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(testName14_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1)
                            .addComponent(userName0_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(testName0_jLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cancelButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(okButton)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(421, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(okButton)
                    .addComponent(cancelButton)
                    .addComponent(jButton1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName0_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName0_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName1_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName1_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName2_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName2_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName3_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName3_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName4_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName4_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName5_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName5_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName6_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName6_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName7_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName7_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName8_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName8_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName9_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName9_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName10_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName10_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName11_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName11_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName12_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName12_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName13_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName13_jLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userName14_jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(testName14_jLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jLabel1)
                    .addContainerGap(402, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 38, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 32, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        
        nameMap.put( testName0_jLabel.getText(), (String)userName0_jComboBox.getSelectedItem() );
        if( 1 < unknownFiles.size() )
        {
            nameMap.put( testName1_jLabel.getText(), (String)userName1_jComboBox.getSelectedItem() );
        }        
        if( 2 < unknownFiles.size() )
        {
            nameMap.put( testName2_jLabel.getText(), (String)userName2_jComboBox.getSelectedItem() );
        }        
        if( 3 < unknownFiles.size() )
        {
            nameMap.put( testName3_jLabel.getText(), (String)userName3_jComboBox.getSelectedItem() );
        }        
        if( 4 < unknownFiles.size() )
        {
            nameMap.put( testName4_jLabel.getText(), (String)userName4_jComboBox.getSelectedItem() );
        }        
        if( 5 < unknownFiles.size() )
        {
            nameMap.put( testName5_jLabel.getText(), (String)userName5_jComboBox.getSelectedItem() );
        }        
        if( 6 < unknownFiles.size() )
        {
            nameMap.put( testName6_jLabel.getText(), (String)userName6_jComboBox.getSelectedItem() );
        }        
        if( 7 < unknownFiles.size() )
        {
            nameMap.put( testName7_jLabel.getText(), (String)userName7_jComboBox.getSelectedItem() );
        }        
        if( 8 < unknownFiles.size() )
        {
            nameMap.put( testName8_jLabel.getText(), (String)userName8_jComboBox.getSelectedItem() );
        }        
        if( 9 < unknownFiles.size() )
        {
            nameMap.put( testName9_jLabel.getText(), (String)userName9_jComboBox.getSelectedItem() );
        }        
        if( 10 < unknownFiles.size() )
        {
            nameMap.put( testName10_jLabel.getText(), (String)userName10_jComboBox.getSelectedItem() );
        }        
        if( 11 < unknownFiles.size() )
        {
            nameMap.put( testName11_jLabel.getText(), (String)userName11_jComboBox.getSelectedItem() );
        }        
        if( 12 < unknownFiles.size() )
        {
            nameMap.put( testName12_jLabel.getText(), (String)userName12_jComboBox.getSelectedItem() );
        }        
        if( 13 < unknownFiles.size() )
        {
            nameMap.put( testName13_jLabel.getText(), (String)userName13_jComboBox.getSelectedItem() );
        }        
        if( 14 < unknownFiles.size() )
        {
            nameMap.put( testName14_jLabel.getText(), (String)userName14_jComboBox.getSelectedItem() );
        }
        
        try
        {
            TextFileWriter mapFile = new TextFileWriter( filePath + FILE_MAP );
            for( String key : nameMap.keySet() )
            {
                if( !key.equals( nameMap.get(key) ) )
                {
                    mapFile.writeLine( key + "-->" + nameMap.get(key) );
                } 
            }
            mapFile.close();
        }
        catch( IOException e )
        {
            throw( new RuntimeException( "Can't write " + filePath + FILE_MAP ) );
        }
        
        okButtonClicked = true;
        
        setVisible( false );
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        okButtonClicked = false;
        
        setVisible( false );
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    //private void initMap( List<String> testFile )
    private void initMap( List<String> testFile, String fileName ) throws InvalidTagFormatException
    {
        nameMap = new HashMap<>();
        boolean startOfTestCode = false;
        
        // Issue 69
        // find all the .pytest files in the same folder as the testfile
        // so, we need to get the path for testfile folder
        // call the modified getFiles methods
        // and do the code below for each
        // Can't do this here, it's too late! Already have read the test file.
        // Find all the file names
        for( String line : testFile )
        {
            // Brendan Villnave
            // I changed the call to line.contains to make the code more general
            // when looking for the test tag so it doesn't have to be at the beginning
            // of a given line
            //System.out.println( line );
            if( line.contains("<test code=") || line.contains("<test file="))
            {
                if( startOfTestCode ){
                    throw new InvalidTagFormatException( "<test code tag is invalid - Missing ending tag", "");
                }
                String file = line.substring(12);
                try {
                    file = file.substring( 0, file.indexOf("\"") );
                }
                catch( IndexOutOfBoundsException e ) {
                    throw new InvalidTagFormatException( "<test code tag is invalid - missing ending quote", "");
                }
                nameMap.put( file, file );
                startOfTestCode = true;
            }
            if( line.contains( "</test>") ){
                if( !startOfTestCode ){
                    throw new InvalidTagFormatException( "<test code tag is invalid - Missing opening tag", "");
                }
                else{
                    startOfTestCode = false;
                }
                
            }
        }
        
        try
        {
            TextFileReader mapFile = new TextFileReader( filePath + FILE_MAP );
            while( mapFile.hasNext() )
            {
                String line = mapFile.next();
                int arrow = line.indexOf( "-->" );
                nameMap.put(line.substring(0,arrow), line.substring(arrow+3));
            }
        }
        catch( IOException e )
        {
        }
        //catch ( InvalidTagFormatException e ){
        //}
    }
    
    public static List<String> getFiles( String path, String endsWithTarget )
    {
        File folder = new File( path );
        String[] fileArray = folder.list();
        List<String> fileList = new ArrayList<>();
        for( String file : fileArray )
        {
            if( file.endsWith( endsWithTarget ) )
            {
                fileList.add(file);
            }
        }
        return fileList;
    }
    
    public int unknownCount( )
    {
        return unknownFiles.size();
    }
    
    public String map( String testFileName )
    {
        return nameMap.get( testFileName );
    }
    
    public boolean okClicked()
    {
        return okButtonClicked;
    }

    private final String FILE_MAP = "$TEST$FILEMAP$.TXT";
    
    private Map<String,String> nameMap;
    private List<String> userFiles;
    private String[] userFilesArray;
    private List<String> unknownFiles;
    private String filePath;
    private boolean okButtonClicked;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel testName0_jLabel;
    private javax.swing.JLabel testName10_jLabel;
    private javax.swing.JLabel testName11_jLabel;
    private javax.swing.JLabel testName12_jLabel;
    private javax.swing.JLabel testName13_jLabel;
    private javax.swing.JLabel testName14_jLabel;
    private javax.swing.JLabel testName1_jLabel;
    private javax.swing.JLabel testName2_jLabel;
    private javax.swing.JLabel testName3_jLabel;
    private javax.swing.JLabel testName4_jLabel;
    private javax.swing.JLabel testName5_jLabel;
    private javax.swing.JLabel testName6_jLabel;
    private javax.swing.JLabel testName7_jLabel;
    private javax.swing.JLabel testName8_jLabel;
    private javax.swing.JLabel testName9_jLabel;
    private javax.swing.JComboBox userName0_jComboBox;
    private javax.swing.JComboBox userName10_jComboBox;
    private javax.swing.JComboBox userName11_jComboBox;
    private javax.swing.JComboBox userName12_jComboBox;
    private javax.swing.JComboBox userName13_jComboBox;
    private javax.swing.JComboBox userName14_jComboBox;
    private javax.swing.JComboBox userName1_jComboBox;
    private javax.swing.JComboBox userName2_jComboBox;
    private javax.swing.JComboBox userName3_jComboBox;
    private javax.swing.JComboBox userName4_jComboBox;
    private javax.swing.JComboBox userName5_jComboBox;
    private javax.swing.JComboBox userName6_jComboBox;
    private javax.swing.JComboBox userName7_jComboBox;
    private javax.swing.JComboBox userName8_jComboBox;
    private javax.swing.JComboBox userName9_jComboBox;
    // End of variables declaration//GEN-END:variables
}
