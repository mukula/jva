/*
Software of Nucleus-The SmartLock
made by G.Saikiran
last updated on 25-06-2015
*/
package myLock;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.*;
import java.net.*;
import java.io.*;
import java.util.Date;
import java.util.Calendar;
import myjson.makeJson;
import myjson.pjon;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

class OnOffThread extends Lock implements Runnable {

    Lock lok;

    OnOffThread(Lock lk) {
        lok = lk;
    }
    public void run() {
        while (true) {
            try {
                lok.checkLockStatus();
                Thread.sleep(500);
            } catch (Exception e) {
            }
        }
    }
}

class KeyTask extends Lock implements Runnable {
    Lock lock;
    public KeyTask(Lock lock) {
        this.lock = lock;
    }

    public void run() {
        while (true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(KeyTask.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                
                if (!lock.k1.isVisible()) {
                    lock.showKeys();
                }

                if (lock.isLogsTabSelected && lock.getLogInStatus()) {
                    lock.fillTable();
                }
                if (!lock.k1.isVisible()) {
                    lock.showKeys();
                }
            } catch (Exception e) {
                lock.isConnectedTab.setText("Disconnected");
                lock.isConnectedTab.setForeground(new Color(255, 102, 51));
            }
        }
    }

}

public class Lock extends javax.swing.JFrame {

    private static String fname = new String("F:\\myFile1.json");
    protected  static final int LOCKED=1;
    protected  static final int UNLOCKED=2;
    protected  static final int JUNK=3;
    private static StringBuffer dtc;
    private static Lock lock;
    private static int status = JUNK;
    private static int count;
    private boolean isKeysTabSelected = true;
    protected boolean isLogsTabSelected = true;
    private boolean isKeyAddPending = false;
    private boolean isLoggedIn = false;
    private DefaultTableModel defaultTableModel;
    private static int keyIndex;

    public static void main(String args[]) throws Exception {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Lock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Lock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Lock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Lock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        lock = new Lock();
        lock.setVisible(true);
        lock.dtc = new StringBuffer(lock.yearInput.getText() + "-" + lock.monthIinput.getText() + "-" + lock.dateInput.getText());
        OnOffThread onf = new OnOffThread(lock);
        KeyTask kTask=new KeyTask(lock);
        Thread thrd1 = new Thread(onf);
        Thread thrd2=new Thread(kTask);
        thrd1.setDaemon(true);
        thrd2.setDaemon(true);
        thrd1.start();
        thrd2.start();
        while (true) {

            if (status == JUNK) {
                lock.isConnectedLabel.setText("Disconnected");
                lock.isConnectedLabel.setForeground(new Color(255, 102, 51));
                lock.isConnectedTab.setText("Disconnected");
                lock.isConnectedTab.setForeground(new Color(255, 102, 51));
            } else if (status < JUNK) {
                lock.isConnectedLabel.setText("   Connected");
                lock.isConnectedLabel.setForeground(new Color(153, 255, 153));
                lock.isConnectedTab.setText("    Connected");
                lock.isConnectedTab.setForeground(new Color(153, 255, 153));
            }

            if (status == LOCKED) {
                lock.lockStatusLabel.setText("LOCKED");
                lock.lockUnlockButton.setText("UNLOCK");
            } else if (status == UNLOCKED) {
                lock.lockUnlockButton.setText("LOCK");
                lock.lockStatusLabel.setText("UNLOCKED");
            }
            
        }
    }
     
    public Lock() {
        initComponents();
        defaultTableModel = (DefaultTableModel) this.logsTable.getModel();
        setDate();
        fileDelete(fname);
        try {
            hideAllKeys();
            showKeys();
        } catch (Exception e) {
            e.printStackTrace();
        }
        keyPendingText.setVisible(false);

        setLocation(new Point(((Toolkit.getDefaultToolkit().getScreenSize().width) - (this.getSize().width)) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - this.getSize().height) / 2));
    }
    
    protected boolean getLogInStatus()
    {
    return isLoggedIn;
    }
    private void setLogInStatus(Boolean b)
    {
    isLoggedIn=b;
    }
    private void setKeySelected(int key)
    {
    keyIndex=key;
    }
    private int getKeySelected()
    {
    return keyIndex;
    }
    private void setDate() {
        Date date = new Date();

        dateInput.setText("" + date.getDate());

        if (date.getMonth() < 10) {
            monthIinput.setText("0" + (date.getMonth() + 1));
        } else if (date.getMonth() == 12) {
            monthIinput.setText("01");
        } else {
            monthIinput.setText("" + (date.getMonth() + 1));
        }
        yearInput.setText("" + Calendar.getInstance().getWeekYear());
        if (getLogInStatus()) {
            fillTable();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        addKeyDialog = new javax.swing.JDialog();
        addKeyPanel = new javax.swing.JPanel();
        keyNameInput = new javax.swing.JTextField();
        addKeyButton = new javax.swing.JButton();
        removeKeyDialog = new javax.swing.JDialog();
        removeKeyPanel = new javax.swing.JPanel();
        removeKeyButton = new javax.swing.JButton();
        notRemoveKeyButton = new javax.swing.JButton();
        removeKeyText = new javax.swing.JLabel();
        logInPanel = new javax.swing.JPanel();
        userNameInput = new javax.swing.JTextField();
        passwordInput = new javax.swing.JPasswordField();
        logInButton = new javax.swing.JButton();
        userName = new javax.swing.JLabel();
        passWord = new javax.swing.JLabel();
        isConnectedLabel = new javax.swing.JLabel();
        nucleusLogo = new javax.swing.JLabel();
        nucleusText = new javax.swing.JLabel();
        activityContainer = new javax.swing.JPanel();
        lockbar = new javax.swing.JPanel();
        lockStatusText = new javax.swing.JLabel();
        lockStatusLabel = new javax.swing.JLabel();
        lockUnlockButton = new javax.swing.JButton();
        tab = new javax.swing.JPanel();
        keysTabButton = new javax.swing.JLabel();
        logsTabButton = new javax.swing.JLabel();
        isConnectedTab = new javax.swing.JLabel();
        logOutButton = new javax.swing.JLabel();
        activityPanel = new javax.swing.JPanel();
        logsPanel = new javax.swing.JPanel();
        dateInput = new javax.swing.JTextField();
        monthIinput = new javax.swing.JTextField();
        yearInput = new javax.swing.JTextField();
        ddText = new javax.swing.JLabel();
        dateText = new javax.swing.JLabel();
        goToDateButton = new javax.swing.JButton();
        tableLogs = new javax.swing.JScrollPane();
        logsTable = new javax.swing.JTable();
        keysPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JLabel();
        keyBar = new javax.swing.JPanel();
        k1 = new javax.swing.JLabel();
        k2 = new javax.swing.JLabel();
        k3 = new javax.swing.JLabel();
        k4 = new javax.swing.JLabel();
        k5 = new javax.swing.JLabel();
        keyPendingText = new javax.swing.JLabel();

        addKeyDialog.setTitle("Add Key");
        addKeyDialog.setMinimumSize(new java.awt.Dimension(280, 161));
        addKeyDialog.setResizable(false);
        addKeyDialog.getContentPane().setLayout(new java.awt.CardLayout());

        addKeyPanel.setBackground(new java.awt.Color(0, 102, 255));

        keyNameInput.setFont(new java.awt.Font("Segoe UI Semilight", 0, 16)); // NOI18N

        addKeyButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        addKeyButton.setForeground(new java.awt.Color(102, 102, 102));
        addKeyButton.setText("Add");
        addKeyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addKeyButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addKeyPanelLayout = new javax.swing.GroupLayout(addKeyPanel);
        addKeyPanel.setLayout(addKeyPanelLayout);
        addKeyPanelLayout.setHorizontalGroup(
            addKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addKeyPanelLayout.createSequentialGroup()
                .addGroup(addKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addKeyPanelLayout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(keyNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addKeyPanelLayout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(addKeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(43, Short.MAX_VALUE))
        );
        addKeyPanelLayout.setVerticalGroup(
            addKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addKeyPanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(keyNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(addKeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        addKeyDialog.getContentPane().add(addKeyPanel, "card2");

        removeKeyDialog.setTitle("Remove Key");
        removeKeyDialog.setMinimumSize(new java.awt.Dimension(280, 161));
        removeKeyDialog.setResizable(false);
        removeKeyDialog.getContentPane().setLayout(new java.awt.CardLayout());

        removeKeyPanel.setBackground(new java.awt.Color(0, 102, 255));

        removeKeyButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        removeKeyButton.setForeground(new java.awt.Color(102, 102, 102));
        removeKeyButton.setText("Yes");
        removeKeyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                removeKeyButtonActionPerformed(evt);
            }
        });

        notRemoveKeyButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        notRemoveKeyButton.setForeground(new java.awt.Color(102, 102, 102));
        notRemoveKeyButton.setText("No");
        notRemoveKeyButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                notRemoveKeyButtonActionPerformed(evt);
            }
        });

        removeKeyText.setFont(new java.awt.Font("Segoe UI Semilight", 0, 20)); // NOI18N
        removeKeyText.setForeground(new java.awt.Color(204, 255, 255));
        removeKeyText.setText("Remove Key ?");

        javax.swing.GroupLayout removeKeyPanelLayout = new javax.swing.GroupLayout(removeKeyPanel);
        removeKeyPanel.setLayout(removeKeyPanelLayout);
        removeKeyPanelLayout.setHorizontalGroup(
            removeKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removeKeyPanelLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(removeKeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addComponent(notRemoveKeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
            .addGroup(removeKeyPanelLayout.createSequentialGroup()
                .addGap(77, 77, 77)
                .addComponent(removeKeyText, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        removeKeyPanelLayout.setVerticalGroup(
            removeKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, removeKeyPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(removeKeyText, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(removeKeyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(removeKeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(notRemoveKeyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36))
        );

        removeKeyDialog.getContentPane().add(removeKeyPanel, "card2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nucleus");
        setResizable(false);
        getContentPane().setLayout(new java.awt.CardLayout());

        logInPanel.setBackground(new java.awt.Color(0, 102, 255));

        userNameInput.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        userNameInput.setForeground(new java.awt.Color(51, 51, 51));
        userNameInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userNameInputActionPerformed(evt);
            }
        });
        userNameInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                userNameInputKeyTyped(evt);
            }
        });

        passwordInput.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        passwordInput.setForeground(new java.awt.Color(102, 102, 102));

        logInButton.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        logInButton.setText("Log in");
        logInButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logInButtonMouseClicked(evt);
            }
        });
        logInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logInButtonActionPerformed(evt);
            }
        });

        userName.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        userName.setForeground(new java.awt.Color(204, 255, 255));
        userName.setText("Username");

        passWord.setFont(new java.awt.Font("Segoe UI Semilight", 0, 17)); // NOI18N
        passWord.setForeground(new java.awt.Color(204, 255, 255));
        passWord.setText("Password");

        isConnectedLabel.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        isConnectedLabel.setForeground(new java.awt.Color(255, 102, 153));
        isConnectedLabel.setText("Disconnected");
        isConnectedLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isConnectedLabelMouseClicked(evt);
            }
        });

        nucleusLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/myLock/lock-logo.png"))); // NOI18N

        nucleusText.setFont(new java.awt.Font("Titillium Web", 0, 32)); // NOI18N
        nucleusText.setForeground(new java.awt.Color(251, 249, 249));
        nucleusText.setText("N u c l e u s");

        javax.swing.GroupLayout logInPanelLayout = new javax.swing.GroupLayout(logInPanel);
        logInPanel.setLayout(logInPanelLayout);
        logInPanelLayout.setHorizontalGroup(
            logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInPanelLayout.createSequentialGroup()
                .addGap(230, 230, 230)
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(logInPanelLayout.createSequentialGroup()
                        .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passWord, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userName, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(userNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(279, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logInPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logInPanelLayout.createSequentialGroup()
                        .addComponent(nucleusLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(137, 137, 137)
                        .addComponent(isConnectedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, logInPanelLayout.createSequentialGroup()
                        .addComponent(nucleusText, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(315, 315, 315))))
        );
        logInPanelLayout.setVerticalGroup(
            logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logInPanelLayout.createSequentialGroup()
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(logInPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(isConnectedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(logInPanelLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(nucleusLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(nucleusText, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(userNameInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(logInPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(passwordInput, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passWord, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(logInButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(172, Short.MAX_VALUE))
        );

        getContentPane().add(logInPanel, "card2");

        activityContainer.setBackground(new java.awt.Color(0, 102, 255));

        lockbar.setBackground(new java.awt.Color(0, 204, 255));

        lockStatusText.setFont(new java.awt.Font("Calibri Light", 0, 18)); // NOI18N
        lockStatusText.setForeground(new java.awt.Color(51, 51, 51));
        lockStatusText.setText("Lock status:");

        lockStatusLabel.setFont(new java.awt.Font("Segoe UI Light", 1, 28)); // NOI18N
        lockStatusLabel.setForeground(new java.awt.Color(0, 102, 102));
        lockStatusLabel.setText("Locked");

        lockUnlockButton.setFont(new java.awt.Font("Segoe UI Symbol", 0, 18)); // NOI18N
        lockUnlockButton.setForeground(new java.awt.Color(102, 102, 102));
        lockUnlockButton.setText("UNLOCK");
        lockUnlockButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lockUnlockButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout lockbarLayout = new javax.swing.GroupLayout(lockbar);
        lockbar.setLayout(lockbarLayout);
        lockbarLayout.setHorizontalGroup(
            lockbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(lockbarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lockStatusText)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lockStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lockUnlockButton, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        lockbarLayout.setVerticalGroup(
            lockbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, lockbarLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(lockbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lockUnlockButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(lockbarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lockStatusText, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                        .addComponent(lockStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        tab.setBackground(new java.awt.Color(0, 102, 204));

        keysTabButton.setBackground(new java.awt.Color(153, 0, 204));
        keysTabButton.setFont(new java.awt.Font("Segoe UI Semilight", 0, 21)); // NOI18N
        keysTabButton.setForeground(new java.awt.Color(255, 255, 255));
        keysTabButton.setText("  Keys");
        keysTabButton.setOpaque(true);
        keysTabButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                keysTabButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                keysTabButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                keysTabButtonMouseExited(evt);
            }
        });

        logsTabButton.setBackground(new java.awt.Color(153, 0, 204));
        logsTabButton.setFont(new java.awt.Font("Segoe UI Semilight", 0, 21)); // NOI18N
        logsTabButton.setForeground(new java.awt.Color(255, 255, 255));
        logsTabButton.setText("   Logs");
        logsTabButton.setOpaque(true);
        logsTabButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logsTabButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logsTabButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logsTabButtonMouseExited(evt);
            }
        });

        isConnectedTab.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        isConnectedTab.setForeground(new java.awt.Color(153, 255, 153));
        isConnectedTab.setText("   Connected");
        isConnectedTab.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                isConnectedTabMouseClicked(evt);
            }
        });

        logOutButton.setBackground(new java.awt.Color(153, 0, 204));
        logOutButton.setFont(new java.awt.Font("Segoe UI Semilight", 0, 21)); // NOI18N
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setText(" Log out");
        logOutButton.setOpaque(true);
        logOutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOutButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                logOutButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                logOutButtonMouseExited(evt);
            }
        });

        javax.swing.GroupLayout tabLayout = new javax.swing.GroupLayout(tab);
        tab.setLayout(tabLayout);
        tabLayout.setHorizontalGroup(
            tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLayout.createSequentialGroup()
                .addComponent(logsTabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(keysTabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(isConnectedTab, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1))
        );
        tabLayout.setVerticalGroup(
            tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(keysTabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logsTabButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(isConnectedTab, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        activityPanel.setLayout(new java.awt.CardLayout());

        logsPanel.setBackground(new java.awt.Color(0, 102, 255));

        dateInput.setBackground(new java.awt.Color(153, 204, 255));
        dateInput.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dateInput.setForeground(new java.awt.Color(51, 51, 51));
        dateInput.setText("26");

        monthIinput.setBackground(new java.awt.Color(153, 204, 255));
        monthIinput.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        monthIinput.setForeground(new java.awt.Color(51, 51, 51));
        monthIinput.setText("03");

        yearInput.setBackground(new java.awt.Color(153, 204, 255));
        yearInput.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        yearInput.setForeground(new java.awt.Color(51, 51, 51));
        yearInput.setText("2015");

        ddText.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        ddText.setForeground(new java.awt.Color(204, 204, 204));
        ddText.setText(" DD     MM      YYYY");

        dateText.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        dateText.setForeground(new java.awt.Color(204, 204, 204));
        dateText.setText("Date");

        goToDateButton.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        goToDateButton.setForeground(new java.awt.Color(102, 102, 102));
        goToDateButton.setText("Go");
        goToDateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                goToDateButtonActionPerformed(evt);
            }
        });

        logsTable.setFont(new java.awt.Font("Segoe UI Semilight", 0, 15)); // NOI18N
        logsTable.setForeground(new java.awt.Color(51, 51, 51));
        logsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Time", "Action", "Mode"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        logsTable.setGridColor(new java.awt.Color(204, 204, 204));
        tableLogs.setViewportView(logsTable);
        logsTable.getTableHeader().setForeground(new Color(51,51,51));
        logsTable.setRowHeight (26);
        logsTable.setShowHorizontalLines(true);
        logsTable.setShowVerticalLines (true);
        logsTable.getTableHeader().setFont(new java.awt.Font("Segoe UI Semilight", 0, 14));
        if (logsTable.getColumnModel().getColumnCount() > 0) {
            logsTable.getColumnModel().getColumn(0).setResizable(false);
            logsTable.getColumnModel().getColumn(1).setResizable(false);
            logsTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout logsPanelLayout = new javax.swing.GroupLayout(logsPanel);
        logsPanel.setLayout(logsPanelLayout);
        logsPanelLayout.setHorizontalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logsPanelLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ddText, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(logsPanelLayout.createSequentialGroup()
                        .addComponent(dateInput, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(monthIinput, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(yearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(goToDateButton))
                    .addComponent(tableLogs, javax.swing.GroupLayout.PREFERRED_SIZE, 694, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        logsPanelLayout.setVerticalGroup(
            logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(logsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(logsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dateInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(monthIinput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearInput, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(goToDateButton, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dateText, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addComponent(ddText, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tableLogs, javax.swing.GroupLayout.DEFAULT_SIZE, 328, Short.MAX_VALUE)
                .addContainerGap())
        );

        activityPanel.add(logsPanel, "card3");

        keysPanel.setBackground(new java.awt.Color(0, 102, 255));

        addButton.setBackground(new java.awt.Color(0, 102, 255));
        addButton.setFont(new java.awt.Font("Segoe UI Semilight", 0, 22)); // NOI18N
        addButton.setForeground(new java.awt.Color(204, 255, 255));
        addButton.setText("  ADD KEY");
        addButton.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        addButton.setOpaque(true);
        addButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addButtonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                addButtonMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                addButtonMouseExited(evt);
            }
        });

        keyBar.setBackground(new java.awt.Color(51, 153, 255));

        k1.setBackground(new java.awt.Color(51, 153, 255));
        k1.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        k1.setForeground(new java.awt.Color(204, 255, 255));
        k1.setText("  jLabel4");
        k1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        k1.setOpaque(true);
        k1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                k1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                k1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                k1MouseExited(evt);
            }
        });

        k2.setBackground(new java.awt.Color(51, 153, 255));
        k2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        k2.setForeground(new java.awt.Color(204, 255, 255));
        k2.setText("jLabel4");
        k2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        k2.setOpaque(true);
        k2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                k2MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                k2MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                k2MouseExited(evt);
            }
        });

        k3.setBackground(new java.awt.Color(51, 153, 255));
        k3.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        k3.setForeground(new java.awt.Color(204, 255, 255));
        k3.setText("jLabel4");
        k3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        k3.setOpaque(true);
        k3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                k3MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                k3MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                k3MouseExited(evt);
            }
        });

        k4.setBackground(new java.awt.Color(51, 153, 255));
        k4.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        k4.setForeground(new java.awt.Color(204, 255, 255));
        k4.setText("jLabel4");
        k4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 153, 204)));
        k4.setOpaque(true);
        k4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                k4MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                k4MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                k4MouseExited(evt);
            }
        });

        k5.setBackground(new java.awt.Color(51, 153, 255));
        k5.setFont(new java.awt.Font("Segoe UI Semilight", 0, 24)); // NOI18N
        k5.setForeground(new java.awt.Color(204, 255, 255));
        k5.setText("jLabel4");
        k5.setOpaque(true);
        k5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                k5MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                k5MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                k5MouseExited(evt);
            }
        });

        javax.swing.GroupLayout keyBarLayout = new javax.swing.GroupLayout(keyBar);
        keyBar.setLayout(keyBarLayout);
        keyBarLayout.setHorizontalGroup(
            keyBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keyBarLayout.createSequentialGroup()
                .addComponent(k1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(k2, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(k3, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(k4, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(k5, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE))
        );
        keyBarLayout.setVerticalGroup(
            keyBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keyBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(k1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(k2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(k3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(k4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(k5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        keyPendingText.setBackground(new java.awt.Color(255, 204, 204));
        keyPendingText.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        keyPendingText.setForeground(new java.awt.Color(255, 153, 153));
        keyPendingText.setText("Key add Pending !");

        javax.swing.GroupLayout keysPanelLayout = new javax.swing.GroupLayout(keysPanel);
        keysPanel.setLayout(keysPanelLayout);
        keysPanelLayout.setHorizontalGroup(
            keysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(keyBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(keysPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(keysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(keyPendingText, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        keysPanelLayout.setVerticalGroup(
            keysPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(keysPanelLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(keyBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(keyPendingText, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(148, Short.MAX_VALUE))
        );

        activityPanel.add(keysPanel, "card2");

        javax.swing.GroupLayout activityContainerLayout = new javax.swing.GroupLayout(activityContainer);
        activityContainer.setLayout(activityContainerLayout);
        activityContainerLayout.setHorizontalGroup(
            activityContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lockbar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(activityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        activityContainerLayout.setVerticalGroup(
            activityContainerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(activityContainerLayout.createSequentialGroup()
                .addComponent(lockbar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(activityPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(activityContainer, "card3");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void userNameInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userNameInputActionPerformed

    }//GEN-LAST:event_userNameInputActionPerformed

    private void userNameInputKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_userNameInputKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_userNameInputKeyTyped

    private void logInButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logInButtonMouseClicked
        if (userNameInput.getText().equals("sai") && passwordInput.getText().equals("sai121")) {
            setLogInStatus(true);
            logInPanel.setVisible(false);
            this.add(activityContainer);
            activityContainer.setVisible(true);
            keysPanel.setVisible(false);
        } else {
            JOptionPane.showMessageDialog(logInPanel, "Invalid Username or Password !", "Error",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_logInButtonMouseClicked

    private void logInButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logInButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_logInButtonActionPerformed

    private void logsTabButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsTabButtonMouseEntered
        logsTabButton.setBackground(new Color(255, 0, 153));

    }//GEN-LAST:event_logsTabButtonMouseEntered

    private void keysTabButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keysTabButtonMouseEntered
        keysTabButton.setBackground(new Color(255, 0, 153));   // TODO add your handling code here:
    }//GEN-LAST:event_keysTabButtonMouseEntered

    private void logsTabButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsTabButtonMouseExited
        if (isKeysTabSelected) {
            logsTabButton.setBackground(new Color(153, 0, 204));}        // TODO add your handling code here:
    }//GEN-LAST:event_logsTabButtonMouseExited

    private void keysTabButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keysTabButtonMouseExited
        if (isLogsTabSelected) {
            keysTabButton.setBackground(new Color(153, 0, 204));}        // TODO add your handling code here:
    }//GEN-LAST:event_keysTabButtonMouseExited

    private void logsTabButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logsTabButtonMouseClicked
        isKeysTabSelected = false;
        isLogsTabSelected = true;
        logsTabButton.setBackground(new Color(255, 0, 102));
        keysTabButton.setBackground(new Color(153, 0, 204));
        keysPanel.setVisible(false);
        logsPanel.setVisible(true);
// TODO add your handling code here:
    }//GEN-LAST:event_logsTabButtonMouseClicked

    private void keysTabButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_keysTabButtonMouseClicked
        isLogsTabSelected = false;
        isKeysTabSelected = true;

        lock.isConnectedTab.setText("Disconnected");

        lock.isConnectedLabel.setForeground(new Color(255, 102, 51));
        lock.isConnectedTab.setForeground(new Color(255, 102, 51));

        keysTabButton.setBackground(new Color(255, 0, 102));
        logsTabButton.setBackground(new Color(153, 0, 204));  // TODO add your handling code here:
        logsPanel.setVisible(false);
        keysPanel.setVisible(true);

    }//GEN-LAST:event_keysTabButtonMouseClicked

    private void lockUnlockButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lockUnlockButtonActionPerformed

        int u = 3;
        try {
            while (u == 3) {
                u = status;
            }
            if (u == 1) {
                lockUnlockButton.setText("LOCK");
                lockUnlockButton.setEnabled(false);
                while (lock.onupdate(4) != 6) {
                    Thread.sleep(800);
                }

                System.out.println("done");
                lockUnlockButton.setEnabled(true);
                lockStatusLabel.setText("UNLOCKED");

            } else if (u == 2) {
                lockUnlockButton.setText("UNLOCK");
                lockUnlockButton.setEnabled(false);
                while (lock.onupdate(8) != 6) {
                    Thread.sleep(800);
                }
                System.out.println("done");
                lockUnlockButton.setEnabled(true);
                lockStatusLabel.setText("LOCKED");

            }
            fillTable();
        } catch (Exception e) {
            lockUnlockButton.setEnabled(true);
            System.out.println(e);
        }
// T        // TODO add your handling code here:

    }//GEN-LAST:event_lockUnlockButtonActionPerformed

    private void addButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseEntered
        if (isKeyAddPending) {
            addButton.setBackground(new Color(153, 0, 153));}        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonMouseEntered

    private void addButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseExited
        addButton.setBackground(new Color(0, 102, 255));          // TODO add your handling code here:
    }//GEN-LAST:event_addButtonMouseExited

    private void addButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addButtonMouseClicked
        if (isKeyAddPending) {
            addKeyDialog.setLocation(new Point(((Toolkit.getDefaultToolkit().getScreenSize().width) - (addKeyDialog.getSize().width)) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - addKeyDialog.getSize().height) / 2));
            addKeyDialog.setVisible(true);
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_addButtonMouseClicked

    private void k1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k1MouseEntered
        k1.setBackground(new Color(0, 51, 255));        // TODO add your handling code here:
    }//GEN-LAST:event_k1MouseEntered

    private void k2MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k2MouseEntered
        k2.setBackground(new Color(0, 51, 255));        // TODO add your handling code here:
    }//GEN-LAST:event_k2MouseEntered

    private void k3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k3MouseClicked
        setKeySelected(3);
        showRemoveKeyDialog();
    }//GEN-LAST:event_k3MouseClicked

    private void k3MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k3MouseEntered
        k3.setBackground(new Color(0, 51, 255));        // TODO add your handling code here:
    }//GEN-LAST:event_k3MouseEntered

    private void k4MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k4MouseEntered
        k4.setBackground(new Color(0, 51, 255));        // TODO add your handling code here:
    }//GEN-LAST:event_k4MouseEntered

    private void k5MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k5MouseEntered
        k5.setBackground(new Color(0, 51, 255));        // TODO add your handling code here:
    }//GEN-LAST:event_k5MouseEntered

    private void k1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k1MouseExited
        k1.setBackground(keyBar.getBackground());        // TODO add your handling code here:
    }//GEN-LAST:event_k1MouseExited

    private void k2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k2MouseExited
        k2.setBackground(keyBar.getBackground());        // TODO add your handling code here:
    }//GEN-LAST:event_k2MouseExited

    private void k3MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k3MouseExited
        k3.setBackground(keyBar.getBackground());        // TODO add your handling code here:
    }//GEN-LAST:event_k3MouseExited

    private void k4MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k4MouseExited
        k4.setBackground(keyBar.getBackground());        // TODO add your handling code here:
    }//GEN-LAST:event_k4MouseExited

    private void k5MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k5MouseExited
        k5.setBackground(keyBar.getBackground());        // TODO add your handling code here:
    }//GEN-LAST:event_k5MouseExited

    private void addKeyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addKeyButtonActionPerformed
        if (count < 5) {
            try {

                while (addkey() != 6);
                while (keyPermit() != 6);
                addKeyDialog.setVisible(false);
                keyNameInput.setText("");
                showKeys();
            } // TODO add your handling code here:
            catch (Exception e) {
            }
        } else {
            System.out.println("nopes");
        }
    }//GEN-LAST:event_addKeyButtonActionPerformed

    private void k1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k1MouseClicked

        if (count == 1) {

            System.out.println(count);

            JOptionPane.showMessageDialog(keysPanel, "Can't remove last key !", "Error",
                    JOptionPane.WARNING_MESSAGE);
        } else {
            setKeySelected(1);
            showRemoveKeyDialog();
        }
    }//GEN-LAST:event_k1MouseClicked

    private void k2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k2MouseClicked

        setKeySelected(2);
        showRemoveKeyDialog();
    }//GEN-LAST:event_k2MouseClicked

    private void k4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k4MouseClicked
        setKeySelected(4);// TODO add your handling code here:
        showRemoveKeyDialog();
    }//GEN-LAST:event_k4MouseClicked

    private void k5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_k5MouseClicked
       setKeySelected(5);       // TODO add your handling code here:
        showRemoveKeyDialog();
    }//GEN-LAST:event_k5MouseClicked

    private void removeKeyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_removeKeyButtonActionPerformed

        try {
            removeKey1(keyIndex);
            removeKey2();
            removeKeyDialog.setVisible(false);
            JOptionPane.showMessageDialog(keysPanel,
                    "     Key removed !",
                    "Key Removed",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }//GEN-LAST:event_removeKeyButtonActionPerformed

    private void notRemoveKeyButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_notRemoveKeyButtonActionPerformed
        removeKeyDialog.setVisible(false);        // TODO add your handling code here:
    }//GEN-LAST:event_notRemoveKeyButtonActionPerformed

    private void goToDateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_goToDateButtonActionPerformed
        logsPanel.remove(tableLogs);
        logsPanel.add(tableLogs);
        fileDelete(fname);
        dtc = new StringBuffer(yearInput.getText() + "-" + monthIinput.getText() + "-" + dateInput.getText());
        fillTable();
    }//GEN-LAST:event_goToDateButtonActionPerformed

    private void logOutButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutButtonMouseClicked
        activityContainer.setVisible(false);
        logInPanel.setVisible(true);// TODO add your handling code here:
        userNameInput.setText("");
        passwordInput.setText("");

    }//GEN-LAST:event_logOutButtonMouseClicked

    private void logOutButtonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutButtonMouseEntered
        logOutButton.setBackground(new Color(255, 0, 153));        // TODO add your handling code here:
    }//GEN-LAST:event_logOutButtonMouseEntered

    private void logOutButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOutButtonMouseExited
        logOutButton.setBackground(new Color(153, 0, 204));        // TODO add your handling code here:
    }//GEN-LAST:event_logOutButtonMouseExited

    private void isConnectedLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_isConnectedLabelMouseClicked
        try {
            checkLockStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_isConnectedLabelMouseClicked

    private void isConnectedTabMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_isConnectedTabMouseClicked
        try {
            if (isLogsTabSelected && getLogInStatus()) {
                fillTable();
            }
            if (!k1.isVisible()) {
                showKeys();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_isConnectedTabMouseClicked
    protected void fillTable() {

        DefaultTableModel fx = defaultTableModel;
        int cot = 0;
        System.out.println(dtc);
        try {
            new makeJson(dtc.toString(), fname).jsonp();
            pjon p = new pjon(fname);

            for (int r = 0; r < logsTable.getRowCount(); r++) {
                fx.setValueAt("", r, 0);
                fx.setValueAt("", r, 1);
                fx.setValueAt("", r, 2);
            }
            for (int i = 1; i < p.siz(); i++) {
                if (p.getf2(i).charAt(1) != (p.getf2(i - 1).charAt(1))) {
                    StringBuffer sg = new StringBuffer(p.getf1(i));

                    if (sg.charAt(sg.indexOf("T") + 4) > '2') {
                        sg.setCharAt(sg.indexOf("T") + 4, (char) (sg.charAt(sg.indexOf("T") + 4) - 3));
                    } else {
                        sg.setCharAt(sg.indexOf("T") + 2, (char) (sg.charAt(sg.indexOf("T") + 2) - 1));
                        sg.setCharAt(sg.indexOf("T") + 4, (char) (sg.charAt(sg.indexOf("T") + 4) + 3));
                    }

                    if (cot >= fx.getRowCount()) {
                        Object[] row = {"", "", ""};
                        fx.addRow(row);
                    }
                    if (p.getf2(i).charAt(1) == '8') {
                        fx.setValueAt("Locked", cot, 1);
                    } else {
                        fx.setValueAt("Unlocked", cot, 1);
                    }
                    if (p.getf2(i).length() > 2) {
                        fx.setValueAt("Software", cot, 2);
                    } else {
                        fx.setValueAt("Key", cot, 2);
                    }
                    fx.setValueAt(sg.substring(sg.indexOf("T") + 1, sg.lastIndexOf("+")), cot, 0);
                    if (fx.getValueAt(cot, 0).toString().startsWith("1/")) {
                        StringBuffer dg = new StringBuffer(fx.getValueAt(cot, 0).toString());
                        dg.setCharAt(dg.indexOf("T") + 1, '0');
                        dg.setCharAt(dg.indexOf("T") + 2, '9');
                        fx.setValueAt(dg, cot, 0);
                    }
                    System.out.println(sg.substring(sg.indexOf("T") + 1, sg.lastIndexOf("+")));
                    if (sg.charAt(sg.indexOf("T") + 2) != '/') {
                        cot++;
                    }

                }

            }

            logsTable.setModel(fx);
            //jtbl.scrollRectToVisible(jtbl.getCellRect(jtbl.getRowCount() - 1, 0, true));
        } catch (Exception e) {

            e.printStackTrace();
        }

    }

    private void fileDelete(String fileName) {
        File file = new File(fileName);

        if (file.delete()) {
            System.out.println(file.getName() + " is deleted!");
        } else {
            System.out.println("Delete operation is failed.");
        }
    }

    /**
     * @param args the command line arguments
     */

    protected void checkLockStatus() throws Exception {
        int c;
        URL theURL = new URL("http://api.thingspeak.com/channels/28840/feeds/last.txt?key=PF87VU53HKRUJVE4");
        StringBuffer ds = new StringBuffer();
        URLConnection theConn = theURL.openConnection();
        InputStream urlInput = theConn.getInputStream();
        while (((c = urlInput.read()) != -1)) {
            ds.append((char) c);
        }
        if (ds.charAt(ds.indexOf("@") + 1) == '8') {
            status = LOCKED;
        } else if (ds.charAt(ds.indexOf("@") + 1) == '4') {
            status = UNLOCKED;
        } else {
            status = JUNK;
        }
        if (ds.charAt(ds.indexOf("*") + 1) == 'n') {
            isKeyAddPending = true;
            keyPendingText.setVisible(false);
        } else if (ds.charAt(ds.indexOf("*") + 1) == 'y') {

            isKeyAddPending = false;
            keyPendingText.setVisible(true);
        }

    }

    private int addkey() throws Exception {
        int c = 0;
        StringBuffer fd = new StringBuffer();
        StringBuffer ds = new StringBuffer();
        ds.append(showKeys());
        URL theURL = new URL("https://api.thingspeak.com/update?key=LJRD1ZLBA1H49OPP&field1=" + (count + 1) + "&field2=" + ds.substring(ds.indexOf("---1"), ds.indexOf("---" + "" + (count + 1))) + "---" + (count + 1) + "" + keyNameInput.getText() + "---" + (count + 2));
        URLConnection theConn = theURL.openConnection();
        InputStream urlInput = theConn.getInputStream();
        while (((c = urlInput.read()) != -1)) {
            fd.append((char) c);
        }
        System.out.println(ds);
        if (fd.length() == 1) {
            if (fd.equals("0") || fd.equals("-1")) {
                return 9;
            }
        } else if (fd.length() > 1 && fd.length() <= 4) {
            System.out.println("return 6");
            return 6;
        }
        return 3;

    }

    private int keyPermit() throws Exception {
        int c = 0;
        byte d = 0;
        if (status == LOCKED) {
            d = 8;// to lock
        } else if (status == UNLOCKED) {
            d = 4;//to unlock
        }
        StringBuffer fd = new StringBuffer();
        StringBuffer ds = new StringBuffer();
        ds.append(showKeys());
        URL theURL = new URL("https://api.thingspeak.com/update?key=3HIDRE08RNE9L376&field1=@" + d + "&field2=$yyyyy&field3=*y");
        URLConnection theConn = theURL.openConnection();
        InputStream urlInput = theConn.getInputStream();
        while (((c = urlInput.read()) != -1)) {
            fd.append((char) c);
        }
        System.out.println(ds);
        if (fd.length() == 1) {
            if (fd.equals("0") || fd.equals("-1")) {
                return 9;
            }
        } else if (fd.length() > 1 && fd.length() <= 4) {
            System.out.println("return 6");
            return 6;
        }
        return 3;
    }

    protected StringBuffer showKeys() throws MalformedURLException, IOException {

        URL theURL = new URL("http://api.thingspeak.com/channels/30501/feeds/last.txt?key=UAGRWNGCLSZFC1VN");
        int c;
        int r = 0;
        StringBuffer ds = new StringBuffer();
        URLConnection theConn = theURL.openConnection();
        System.out.println();
        InputStream urlInput = theConn.getInputStream();
        while (((c = urlInput.read()) != -1)) {
            ds.append((char) c);
        }
        int rt = 4;
        //System.out.println(ds.substring(ds.indexOf("---1"),ds.indexOf("---"+""+count))+""+jttf.getText());
        hideAllKeys();
        count = ds.charAt(ds.indexOf("\"field1\":\"") + 10) - 48;
        if (count > 0) {

            k1.setVisible(true);
            r = ds.indexOf("---1");
            k1.setText("  " + ds.substring(r + 4, ds.indexOf("---2")));

            if (count > 1) {
                k2.setVisible(true);
                r = ds.indexOf("---2");
                k2.setText("  " + ds.substring(r + 4, ds.indexOf("---3")));
            }
            if (count > 2) {
                k3.setVisible(true);
                r = ds.indexOf("---3");
                k3.setText("  " + ds.substring(r + 4, ds.indexOf("---4")));
            }
            if (count > 3) {
                k4.setVisible(true);
                r = ds.indexOf("---4", r + 1);
                k4.setText("  " + ds.substring(r + 4, ds.indexOf("---5")));
            }
            if (count > 4) {
                k5.setVisible(true);
                r = ds.indexOf("---5", r + 1);
                k5.setText("  " + ds.substring(r + 4, ds.indexOf("---6")));
                addButton.setVisible(false);
            }
            if (count < 5) {
                addButton.setVisible(true);
            }

        }
        return ds;

    }

    private int onupdate(int stt) throws MalformedURLException, IOException {

        int c;
        URL theURL = new URL("http://api.thingspeak.com//update?key=3HIDRE08RNE9L376&field1=@" + stt + "l&field2=$yyyyy&field3=*n");
        StringBuffer ds = new StringBuffer();
        URLConnection theConn = theURL.openConnection();
        InputStream urlInput = theConn.getInputStream();
        while (((c = urlInput.read()) != -1)) {
            ds.append((char) c);
        }
        System.out.println(ds);
        if (ds.length() == 1) {
            if (ds.equals("0") || ds.equals("-1")) {
                return 9;
            }
        } else if (ds.length() > 1 && ds.length() <= 4) {
            System.out.println("return 6");
            return 6;
        }
        return 3;
    }
    private void removeKey2() throws Exception {
        int c = 0;
        byte d = 0;
        StringBuffer fe = new StringBuffer();
        StringBuffer fd = new StringBuffer();
        URL thURL = new URL("http://api.thingspeak.com/channels/28840/field/2/last.txt?key=PF87VU53HKRUJVE4");
        StringBuffer dsd = new StringBuffer();
        URLConnection thConn = thURL.openConnection();
        InputStream urlInpu = thConn.getInputStream();
        while (((c = urlInpu.read()) != -1)) {
            dsd.append((char) c);
        }
        c = 0;
        if (status == LOCKED) {
            d = 8;//to lock
        } else if (status == UNLOCKED) {
            d = 4;//to unlock
        }
        fe.append(dsd);
        if (isKeyAddPending) {
            fe.setCharAt(keyIndex, 'n');
        } else {
            fe.setCharAt(keyIndex, 'y');
        }
        StringBuffer ds = new StringBuffer();
        ds.append(showKeys());
        URL theURL = new URL("https://api.thingspeak.com/update?key=3HIDRE08RNE9L376&field1=@" + d + "&field2=" + fe + "&field3=*n");
        int w = 0;
        while (w != 6) {
            URLConnection theConn = theURL.openConnection();
            InputStream urlInput = theConn.getInputStream();
            while (((c = urlInput.read()) != -1)) {
                fd.append((char) c);
            }
            System.out.println(ds);
            if (fd.length() == 1) {

                if (fd.equals("0") || fd.equals("-1")) {
                    System.out.println("fail in Keyupdate");
                    w = 9;
                }
            } else if (fd.length() > 1 && fd.length() <= 4) {
                System.out.println("return 6 k");
                w = 6;
            }
        }
    }

    private void removeKey1(int ct) throws Exception {
        int c;
        StringBuffer ds = new StringBuffer();
        if (getKeySelected() < 2) {
            k1.setText(k2.getText());
        }
        if (getKeySelected() < 3) {
            k2.setText(k3.getText());
        }
        if (getKeySelected() < 4) {
            k3.setText(k4.getText());
        }
        if (getKeySelected() < 5) {
            k4.setText(k5.getText());
        }
        count--;
        StringBuffer fd = new StringBuffer();
        fd.append("---1" + k1.getText().substring(2) + "---2");
        if (count > 1) {
            fd.append(k2.getText().substring(2) + "---3");
        }
        if (count > 2) {
            fd.append(k3.getText().substring(2) + "---4");
        }
        if (count > 3) {
            fd.append(k4.getText().substring(2) + "---5");
        }
        System.out.println(fd);
        while (removeKey1Update(fd) != 6);
        showKeys();
    }

    private byte removeKey1Update(StringBuffer fd) throws Exception {
        int c = 0;
        StringBuffer ds = new StringBuffer();
        URL theURL = new URL("http://api.thingspeak.com//update?key=LJRD1ZLBA1H49OPP&field1=" + count + "&field2=" + fd);

        URLConnection theConn = theURL.openConnection();
        InputStream urlInput = theConn.getInputStream();
        while (((c = urlInput.read()) != -1)) {
            ds.append((char) c);
        }
        if (ds.length() == 1) {
            System.out.println("fail in remup");
            if (ds.equals("0") || ds.equals("-1")) {
                return 9;
            }
        } else if (ds.length() > 1 && ds.length() <= 4) {
            System.out.println("return 6");
            return 6;
        }

        return 3;
    }

    private void hideAllKeys() {
        this.k1.setVisible(false);
        this.k2.setVisible(false);
        this.k3.setVisible(false);
        this.k4.setVisible(false);
        this.k5.setVisible(false);

    }

    private void showRemoveKeyDialog() {
        removeKeyDialog.setLocation(new Point(((Toolkit.getDefaultToolkit().getScreenSize().width) - (removeKeyDialog.getSize().width)) / 2, (Toolkit.getDefaultToolkit().getScreenSize().height - (removeKeyDialog.getSize().height)) / 2));
        removeKeyDialog.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel activityContainer;
    private javax.swing.JPanel activityPanel;
    private javax.swing.JLabel addButton;
    private javax.swing.JButton addKeyButton;
    private javax.swing.JDialog addKeyDialog;
    private javax.swing.JPanel addKeyPanel;
    private javax.swing.JTextField dateInput;
    private javax.swing.JLabel dateText;
    private javax.swing.JLabel ddText;
    private javax.swing.JButton goToDateButton;
    private javax.swing.JLabel isConnectedLabel;
    protected javax.swing.JLabel isConnectedTab;
    protected javax.swing.JLabel k1;
    protected javax.swing.JLabel k2;
    protected javax.swing.JLabel k3;
    protected javax.swing.JLabel k4;
    protected javax.swing.JLabel k5;
    private javax.swing.JPanel keyBar;
    private javax.swing.JTextField keyNameInput;
    private javax.swing.JLabel keyPendingText;
    private javax.swing.JPanel keysPanel;
    private javax.swing.JLabel keysTabButton;
    private javax.swing.JLabel lockStatusLabel;
    private javax.swing.JLabel lockStatusText;
    private javax.swing.JButton lockUnlockButton;
    private javax.swing.JPanel lockbar;
    private javax.swing.JButton logInButton;
    private javax.swing.JPanel logInPanel;
    private javax.swing.JLabel logOutButton;
    private javax.swing.JPanel logsPanel;
    private javax.swing.JLabel logsTabButton;
    private javax.swing.JTable logsTable;
    private javax.swing.JTextField monthIinput;
    private javax.swing.JButton notRemoveKeyButton;
    private javax.swing.JLabel nucleusLogo;
    private javax.swing.JLabel nucleusText;
    private javax.swing.JLabel passWord;
    private javax.swing.JPasswordField passwordInput;
    private javax.swing.JButton removeKeyButton;
    private javax.swing.JDialog removeKeyDialog;
    private javax.swing.JPanel removeKeyPanel;
    private javax.swing.JLabel removeKeyText;
    private javax.swing.JPanel tab;
    private javax.swing.JScrollPane tableLogs;
    private javax.swing.JLabel userName;
    private javax.swing.JTextField userNameInput;
    private javax.swing.JTextField yearInput;
    // End of variables declaration//GEN-END:variables
}
