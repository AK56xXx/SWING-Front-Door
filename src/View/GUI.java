/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AdminCRUD;
import DAO.EmployeeCRUD;
import DAO.MessageCRUD;
import DB.Singleton1;
import Model.InfoVisit;
import Model.Visiteur;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.sql.*;
import DAO.VisiteurCRUD;
import Model.Admin;
import Model.Employee;
import Model.Message;
import Model.User;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 *
 * @author mega
 */
public class GUI extends javax.swing.JFrame {

    /**
     * Creates new form menu
     */
    public GUI() {
        initComponents();
        showMsgEnv(ConGUI.u.getNom());
        switchp(visiteurP);
        tuser.setText(ConGUI.u.getNom());
        System.out.println("**********" + ConGUI.u.getNom());
        Locale locale = new Locale("fr", "FR");
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        String time = dateFormat.format(new Date());
        timeE.setText(time);
        timeS.setText(time);

        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(new Date());
        datej.setText(date);
        showp();
        showtv();
        showEmp();

        //combobox admins in user GUI
        AdminCRUD ac = new AdminCRUD();
        ArrayList<Admin> la = ac.showAdmins();
        for (Admin a : la) {
            cbm.addItem(a.getNom());
        }

        //
        Message m = new Message();
        m.setDate_m(String.valueOf(datej));

    }

    // all info_visit
    public ArrayList<InfoVisit> getAllinfo() {
        ArrayList listinf = new ArrayList();
        try {
            Connection con = Singleton1.getInstance();
            Statement req = con.createStatement();
            ResultSet res = req.executeQuery("select info_visit.cin_v,visiteur.nom,visiteur.prenom,info_visit.reason,info_visit.enter_t,info_visit.exit_t,info_visit.datej from info_visit join visiteur on visiteur.cin=info_visit.cin_v");
            InfoVisit inf;
            while (res.next()) {
                inf = new InfoVisit(res.getInt("cin_v"), res.getString("reason"), res.getString("enter_t"), res.getString("exit_t"), res.getString("datej"), res.getString("nom"), res.getString("prenom"));
                listinf.add(inf);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");

        }
        return listinf;

    }

    //
    // get info visit by id
    public ArrayList<InfoVisit> getAllInfoById(int id) {
        ArrayList listinf = new ArrayList();
        try {
            Connection con = Singleton1.getInstance();
            Statement req = con.createStatement();
            ResultSet res = req.executeQuery("select info_visit.cin_v,visiteur.nom,visiteur.prenom,info_visit.reason,info_visit.enter_t,info_visit.exit_t,info_visit.datej from info_visit join visiteur on visiteur.cin=info_visit.cin_v where cin=" + id);
            InfoVisit inf;
            while (res.next()) {
                inf = new InfoVisit(res.getInt("cin_v"), res.getString("reason"), res.getString("enter_t"), res.getString("exit_t"), res.getString("datej"), res.getString("nom"), res.getString("prenom"));
                listinf.add(inf);

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error!");

        }
        return listinf;

    }

    //
    // show info visit in jtable
    public void showp() {
        ArrayList<InfoVisit> list = getAllinfo();
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCin();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getReason();
            row[4] = list.get(i).getEnterTime();
            row[5] = list.get(i).getExitTime();
            row[6] = list.get(i).getDatej();

            model.addRow(row);

        }

    }

    //**************
    //show visit by id
    public void showVisitById() {
        ArrayList<InfoVisit> list = getAllInfoById(Integer.valueOf(rcin.getText()));
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCin();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getReason();
            row[4] = list.get(i).getEnterTime();
            row[5] = list.get(i).getExitTime();
            row[6] = list.get(i).getDatej();

            model.addRow(row);

        }

    }

    //show visiteur by id
    public void showVisiteurById() {
        VisiteurCRUD vc = new VisiteurCRUD();
        ArrayList<Visiteur> list = new ArrayList();
        list = vc.searchv(Integer.valueOf(vrcin.getText()));
        System.out.println(list);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCin();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getTel();
            row[4] = list.get(i).getLieu();

            model.addRow(row);

            vcin.setText(String.valueOf(list.get(i).getCin()));
            vnom.setText(list.get(i).getNom());
            vprenom.setText(list.get(i).getPrenom());
            vtel.setText(String.valueOf(list.get(i).getTel()));
            vlieu.setText(list.get(i).getLieu());

        }

    }
    
     //show visiteur by nom
    public void showVisiteurByNom() {
        VisiteurCRUD vc = new VisiteurCRUD();
        ArrayList<Visiteur> list = new ArrayList();
        list = vc.searchv_n(vrcin.getText());
        System.out.println(list);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCin();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getTel();
            row[4] = list.get(i).getLieu();

            model.addRow(row);

            vcin.setText(String.valueOf(list.get(i).getCin()));
            vnom.setText(list.get(i).getNom());
            vprenom.setText(list.get(i).getPrenom());
            vtel.setText(String.valueOf(list.get(i).getTel()));
            vlieu.setText(list.get(i).getLieu());

        }

    }
    

    //*****************
    // show employees list in jtable  
    public void showEmp() {
        EmployeeCRUD ec = new EmployeeCRUD();
        ArrayList<Employee> list = ec.showE();
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getDep();
            row[4] = list.get(i).getOcu();
            row[5] = list.get(i).getTel();
            row[6] = list.get(i).getMail();

            model.addRow(row);

        }

    }

    // search employee by name
    public void showEmpByNom() {
        EmployeeCRUD ec = new EmployeeCRUD();
        ArrayList<Employee> list = new ArrayList();
        list = ec.searchE(nomemp.getText());
        System.out.println(list);
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        Object[] row = new Object[7];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getId();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getDep();
            row[4] = list.get(i).getOcu();
            row[5] = list.get(i).getTel();
            row[6] = list.get(i).getMail();

            model.addRow(row);

        }

    }

    //**************
    // char integer verfication
    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    
    // validate time format
    boolean isValidDateTime(String date) {
        try {
            DateTimeFormatter.ISO_TIME.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            return false;
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

        jSeparator9 = new javax.swing.JSeparator();
        pleft = new javax.swing.JPanel();
        tab3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        tab1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        tab2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        tab4 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jLabel31 = new javax.swing.JLabel();
        tuser = new javax.swing.JLabel();
        dcnx = new javax.swing.JButton();
        lp = new javax.swing.JLayeredPane();
        jPanel1 = new javax.swing.JPanel();
        visiteurP = new javax.swing.JPanel();
        update = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        cin = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        prename = new javax.swing.JTextField();
        add = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        reason = new javax.swing.JTextArea();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        timeE = new javax.swing.JTextField();
        timeS = new javax.swing.JTextField();
        datej = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        mob = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        getTs = new javax.swing.JButton();
        getDj = new javax.swing.JButton();
        getTe = new javax.swing.JButton();
        refresh = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        vid = new javax.swing.JButton();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        rcin = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        searchP = new javax.swing.JPanel();
        modif = new javax.swing.JButton();
        clean = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        vcin = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        vnom = new javax.swing.JTextField();
        vprenom = new javax.swing.JTextField();
        vtel = new javax.swing.JTextField();
        vlieu = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel20 = new javax.swing.JLabel();
        vrcin = new javax.swing.JTextField();
        vrech = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        empP = new javax.swing.JPanel();
        aemp = new javax.swing.JButton();
        erech = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        nomemp = new javax.swing.JTextField();
        tttttt1 = new javax.swing.JLabel();
        idemployee = new javax.swing.JTextField();
        msgP = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        cbm = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        mt = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        ms = new javax.swing.JTextArea();
        mbenv = new javax.swing.JButton();
        mbnull = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        tmsgR = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        msg_u = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pleft.setBackground(new java.awt.Color(0, 0, 0));

        tab3.setBackground(new java.awt.Color(0, 102, 204));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/profil.png"))); // NOI18N
        jLabel3.setText("Informations sur les employés");

        javax.swing.GroupLayout tab3Layout = new javax.swing.GroupLayout(tab3);
        tab3.setLayout(tab3Layout);
        tab3Layout.setHorizontalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        tab3Layout.setVerticalGroup(
            tab3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(2, 2, 2)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tab1.setBackground(new java.awt.Color(153, 102, 255));
        tab1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab1MouseClicked(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/info.png"))); // NOI18N
        jLabel1.setText("Les visites");

        javax.swing.GroupLayout tab1Layout = new javax.swing.GroupLayout(tab1);
        tab1.setLayout(tab1Layout);
        tab1Layout.setHorizontalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        tab1Layout.setVerticalGroup(
            tab1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(2, 2, 2)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tab2.setBackground(new java.awt.Color(102, 0, 255));
        tab2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab2MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/info2.png"))); // NOI18N
        jLabel2.setText("Les visiteurs");

        javax.swing.GroupLayout tab2Layout = new javax.swing.GroupLayout(tab2);
        tab2.setLayout(tab2Layout);
        tab2Layout.setHorizontalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        tab2Layout.setVerticalGroup(
            tab2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(2, 2, 2)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tab4.setBackground(new java.awt.Color(0, 153, 153));
        tab4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab4MouseClicked(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/msg.png"))); // NOI18N
        jLabel7.setText("Messages");

        javax.swing.GroupLayout tab4Layout = new javax.swing.GroupLayout(tab4);
        tab4.setLayout(tab4Layout);
        tab4Layout.setHorizontalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(tab4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addContainerGap(189, Short.MAX_VALUE))
            .addComponent(jSeparator5, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        tab4Layout.setVerticalGroup(
            tab4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, tab4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(2, 2, 2)
                .addComponent(jSeparator5, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel29.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 0, 51));
        jLabel29.setText("Security Dashboard");

        jLabel30.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 204));
        jLabel30.setText("Front-Door");

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("USER : ");

        tuser.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tuser.setForeground(new java.awt.Color(255, 255, 255));
        tuser.setText(" ");

        dcnx.setBackground(new java.awt.Color(204, 0, 0));
        dcnx.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        dcnx.setForeground(new java.awt.Color(255, 255, 255));
        dcnx.setText("Deconnecté");
        dcnx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcnxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pleftLayout = new javax.swing.GroupLayout(pleft);
        pleft.setLayout(pleftLayout);
        pleftLayout.setHorizontalGroup(
            pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pleftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel29)
                .addGap(39, 39, 39))
            .addComponent(jSeparator8)
            .addComponent(jSeparator11)
            .addGroup(pleftLayout.createSequentialGroup()
                .addGroup(pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pleftLayout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel30))
                    .addGroup(pleftLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel31)
                        .addGap(18, 18, 18)
                        .addComponent(tuser)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(dcnx, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        pleftLayout.setVerticalGroup(
            pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pleftLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addGap(28, 28, 28)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7)
                .addGroup(pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel31)
                    .addComponent(tuser))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(dcnx, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(418, Short.MAX_VALUE))
        );

        lp.setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        visiteurP.setBackground(new java.awt.Color(51, 51, 51));

        update.setBackground(new java.awt.Color(153, 102, 0));
        update.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setText("Modefier");
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 153, 51));
        jLabel4.setText("CIN :");

        name.setBackground(new java.awt.Color(0, 0, 51));
        name.setForeground(new java.awt.Color(255, 255, 255));
        name.setText(" ");

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 153, 51));
        jLabel5.setText("Nom :");

        cin.setBackground(new java.awt.Color(0, 0, 51));
        cin.setForeground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 153, 51));
        jLabel6.setText("Prenom : ");

        prename.setBackground(new java.awt.Color(0, 0, 51));
        prename.setForeground(new java.awt.Color(255, 255, 255));
        prename.setText(" ");

        add.setBackground(new java.awt.Color(0, 51, 51));
        add.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        add.setForeground(new java.awt.Color(255, 255, 255));
        add.setText("Ajouter");
        add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addActionPerformed(evt);
            }
        });

        reset.setBackground(new java.awt.Color(153, 153, 153));
        reset.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        reset.setForeground(new java.awt.Color(51, 51, 51));
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 153, 51));
        jLabel8.setText("Description ");

        reason.setBackground(new java.awt.Color(0, 0, 51));
        reason.setColumns(20);
        reason.setForeground(new java.awt.Color(255, 255, 255));
        reason.setRows(5);
        reason.setText("Reason de visit : \n-\n\n\n");
        jScrollPane1.setViewportView(reason);

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 153, 51));
        jLabel9.setText("Temp d'entree : ");

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 153, 51));
        jLabel10.setText("Temp de sortie : ");

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 153, 51));
        jLabel11.setText("Date :");

        timeE.setBackground(new java.awt.Color(0, 0, 51));
        timeE.setForeground(new java.awt.Color(255, 255, 255));

        timeS.setBackground(new java.awt.Color(0, 0, 51));
        timeS.setForeground(new java.awt.Color(255, 255, 255));

        datej.setEditable(false);
        datej.setBackground(new java.awt.Color(0, 0, 51));
        datej.setForeground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 153, 51));
        jLabel12.setText("Telephone : ");

        mob.setBackground(new java.awt.Color(0, 0, 51));
        mob.setForeground(new java.awt.Color(255, 255, 255));

        jTable1.setBackground(new java.awt.Color(0, 0, 51));
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIN", "NOM", "PRENOM", "DESCRIPTION", "TEMP D'ENTREE", "TEMP DE SORTIE", "DATE"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);

        getTs.setBackground(new java.awt.Color(0, 102, 102));
        getTs.setForeground(new java.awt.Color(255, 255, 255));
        getTs.setText("Obtenir");
        getTs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTsActionPerformed(evt);
            }
        });

        getDj.setBackground(new java.awt.Color(0, 102, 102));
        getDj.setForeground(new java.awt.Color(255, 255, 255));
        getDj.setText("Obtenir");
        getDj.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getDjActionPerformed(evt);
            }
        });

        getTe.setBackground(new java.awt.Color(0, 102, 102));
        getTe.setForeground(new java.awt.Color(255, 255, 255));
        getTe.setText("Obtenir");
        getTe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                getTeActionPerformed(evt);
            }
        });

        refresh.setBackground(new java.awt.Color(51, 51, 51));
        refresh.setForeground(new java.awt.Color(255, 255, 255));
        refresh.setText("Actualiser");
        refresh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshActionPerformed(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(255, 153, 51));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel13.setText("Gestion des visites");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(193, 193, 193))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        vid.setBackground(new java.awt.Color(102, 102, 0));
        vid.setForeground(new java.awt.Color(255, 255, 255));
        vid.setText("Verifier si exist");
        vid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vidActionPerformed(evt);
            }
        });

        jLabel14.setForeground(new java.awt.Color(255, 204, 153));
        jLabel14.setText("Recherche par CIN : ");

        rcin.setBackground(new java.awt.Color(0, 0, 51));
        rcin.setForeground(new java.awt.Color(255, 255, 255));

        jButton1.setBackground(new java.awt.Color(102, 0, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Rechercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visiteurPLayout = new javax.swing.GroupLayout(visiteurP);
        visiteurP.setLayout(visiteurPLayout);
        visiteurPLayout.setHorizontalGroup(
            visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visiteurPLayout.createSequentialGroup()
                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addGap(79, 79, 79)
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel4))
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(name, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(visiteurPLayout.createSequentialGroup()
                                        .addGap(37, 37, 37)
                                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(mob, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(prename, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(jLabel8))
                        .addGap(18, 18, 18)
                        .addComponent(vid))
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(visiteurPLayout.createSequentialGroup()
                                        .addComponent(datej, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(getDj))
                                    .addGroup(visiteurPLayout.createSequentialGroup()
                                        .addComponent(timeE, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(getTe))
                                    .addGroup(visiteurPLayout.createSequentialGroup()
                                        .addComponent(timeS, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(getTs))))
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addGap(61, 61, 61)
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rcin, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButton1)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator6)
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 7, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(refresh)))))
                .addContainerGap())
            .addGroup(visiteurPLayout.createSequentialGroup()
                .addGap(224, 224, 224)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        visiteurPLayout.setVerticalGroup(
            visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visiteurPLayout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4)
                                    .addComponent(vid))
                                .addGap(18, 18, 18)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(14, 14, 14)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(prename, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6))
                                .addGap(18, 18, 18)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12)))
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(timeE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getTe))
                                .addGap(23, 23, 23)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(timeS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getTs))
                                .addGap(23, 23, 23)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(datej, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(getDj))))
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1)
                                .addGap(28, 28, 28)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jSeparator6, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(rcin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton1))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(refresh)))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );

        searchP.setBackground(new java.awt.Color(51, 51, 51));

        modif.setBackground(new java.awt.Color(0, 102, 0));
        modif.setForeground(new java.awt.Color(255, 255, 255));
        modif.setText("Modifier");
        modif.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifActionPerformed(evt);
            }
        });

        clean.setBackground(new java.awt.Color(51, 51, 51));
        clean.setForeground(new java.awt.Color(255, 255, 255));
        clean.setText("Reset");
        clean.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 153, 51));
        jLabel15.setText("CIN : ");

        vcin.setEditable(false);
        vcin.setBackground(new java.awt.Color(0, 0, 51));
        vcin.setForeground(new java.awt.Color(255, 255, 255));

        jLabel16.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 153, 51));
        jLabel16.setText("Nom : ");

        jLabel17.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 153, 51));
        jLabel17.setText("Prenom : ");

        jLabel18.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 153, 51));
        jLabel18.setText("Telephone : ");

        jLabel19.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 153, 51));
        jLabel19.setText("Lieu : ");

        vnom.setBackground(new java.awt.Color(0, 0, 51));
        vnom.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        vnom.setForeground(new java.awt.Color(255, 255, 255));

        vprenom.setBackground(new java.awt.Color(0, 0, 51));
        vprenom.setForeground(new java.awt.Color(255, 255, 255));

        vtel.setBackground(new java.awt.Color(0, 0, 51));
        vtel.setForeground(new java.awt.Color(255, 255, 255));

        vlieu.setBackground(new java.awt.Color(0, 0, 51));
        vlieu.setForeground(new java.awt.Color(255, 255, 255));

        jTable2.setBackground(new java.awt.Color(0, 0, 51));
        jTable2.setForeground(new java.awt.Color(255, 255, 255));
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "CIN", "NOM", "PRENOM", "TELEPHONE", "LIEU"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(jTable2);

        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("Recherche par CIN : ");

        vrcin.setBackground(new java.awt.Color(0, 0, 51));
        vrcin.setForeground(new java.awt.Color(255, 255, 255));

        vrech.setBackground(new java.awt.Color(153, 102, 255));
        vrech.setForeground(new java.awt.Color(255, 255, 255));
        vrech.setText("Recherche");
        vrech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vrechActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(51, 51, 51));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setText("Actualiser");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jPanel4.setBackground(new java.awt.Color(255, 153, 51));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel22.setBackground(new java.awt.Color(255, 153, 51));
        jLabel22.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(0, 0, 0));
        jLabel22.setText("Gestion des visiteurs");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jLabel22)
                .addGap(193, 193, 193))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel22)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);

        javax.swing.GroupLayout searchPLayout = new javax.swing.GroupLayout(searchP);
        searchP.setLayout(searchPLayout);
        searchPLayout.setHorizontalGroup(
            searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPLayout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                        .addComponent(clean)
                                        .addGap(29, 29, 29)
                                        .addComponent(modif)
                                        .addGap(71, 71, 71))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel18))
                                        .addGap(9, 9, 9)
                                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vtel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(vlieu, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(83, 83, 83))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jLabel15))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(9, 9, 9)))
                                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(vprenom, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(vcin, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(vnom, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(83, 83, 83)))
                                .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(46, 46, 46)
                                .addComponent(vrcin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(vrech)))
                        .addGap(39, 39, 39))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(163, 163, 163))))
        );
        searchPLayout.setVerticalGroup(
            searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPLayout.createSequentialGroup()
                .addGap(106, 106, 106)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125)
                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vrcin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addComponent(vrech))
                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(searchPLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vcin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(18, 18, 18)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vnom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addGap(18, 18, 18)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vprenom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17))
                        .addGap(18, 18, 18)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vtel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18))
                        .addGap(18, 18, 18)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vlieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19))
                        .addGap(39, 39, 39)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(clean)
                            .addComponent(modif))
                        .addGap(239, 239, 239))
                    .addGroup(searchPLayout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 411, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jSeparator7, javax.swing.GroupLayout.PREFERRED_SIZE, 422, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        empP.setBackground(new java.awt.Color(51, 51, 51));

        aemp.setBackground(new java.awt.Color(51, 51, 51));
        aemp.setForeground(new java.awt.Color(255, 255, 255));
        aemp.setText("Actualiser");
        aemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aempActionPerformed(evt);
            }
        });

        erech.setBackground(new java.awt.Color(102, 0, 102));
        erech.setForeground(new java.awt.Color(255, 255, 255));
        erech.setText("Recherche");
        erech.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                erechActionPerformed(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(255, 153, 51));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel23.setBackground(new java.awt.Color(255, 153, 51));
        jLabel23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(0, 0, 0));
        jLabel23.setText("Information employés");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jLabel23)
                .addGap(193, 193, 193))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel23)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jTable3.setBackground(new java.awt.Color(0, 0, 51));
        jTable3.setForeground(new java.awt.Color(255, 255, 255));
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Nom", "Prenom", "Department", "Ocupation", "Telephone", "E-mail"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable3);

        nomemp.setBackground(new java.awt.Color(0, 0, 51));
        nomemp.setForeground(new java.awt.Color(255, 255, 255));

        tttttt1.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        tttttt1.setForeground(new java.awt.Color(255, 153, 51));
        tttttt1.setText("Employee :");

        idemployee.setEditable(false);

        javax.swing.GroupLayout empPLayout = new javax.swing.GroupLayout(empP);
        empP.setLayout(empPLayout);
        empPLayout.setHorizontalGroup(
            empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empPLayout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, empPLayout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(146, 146, 146))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, empPLayout.createSequentialGroup()
                        .addComponent(idemployee, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(108, 108, 108)
                        .addComponent(tttttt1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomemp, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(120, 120, 120))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, empPLayout.createSequentialGroup()
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(aemp)
                            .addComponent(erech)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 913, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23))))
        );
        empPLayout.setVerticalGroup(
            empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empPLayout.createSequentialGroup()
                .addContainerGap(101, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(erech)
                    .addComponent(nomemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tttttt1)
                    .addComponent(idemployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(aemp)
                .addGap(44, 44, 44))
        );

        idemployee.setVisible(false);

        msgP.setBackground(new java.awt.Color(51, 51, 51));
        msgP.setForeground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 153, 51));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel24.setBackground(new java.awt.Color(255, 153, 51));
        jLabel24.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(0, 0, 0));
        jLabel24.setText("Messagerie");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(170, Short.MAX_VALUE)
                .addComponent(jLabel24)
                .addGap(193, 193, 193))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel21.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 102, 51));
        jLabel21.setText("Envoyer à : ");

        jLabel25.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 102, 51));
        jLabel25.setText("Titre : ");

        mt.setBackground(new java.awt.Color(0, 0, 51));
        mt.setForeground(new java.awt.Color(255, 255, 255));

        jLabel26.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 102, 51));
        jLabel26.setText("Sujet : ");

        ms.setBackground(new java.awt.Color(0, 0, 51));
        ms.setColumns(20);
        ms.setForeground(new java.awt.Color(255, 255, 255));
        ms.setRows(5);
        jScrollPane5.setViewportView(ms);

        mbenv.setBackground(new java.awt.Color(0, 51, 51));
        mbenv.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        mbenv.setForeground(new java.awt.Color(255, 255, 255));
        mbenv.setText("Envoyer");
        mbenv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mbenvActionPerformed(evt);
            }
        });

        mbnull.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        mbnull.setText("Annuler");

        tmsgR.setBackground(new java.awt.Color(0, 0, 51));
        tmsgR.setForeground(new java.awt.Color(255, 255, 255));
        tmsgR.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "-", "-", "Titre", "Message", "date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tmsgR.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tmsgRMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tmsgR);

        jLabel27.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Messages recu");

        msg_u.setEditable(false);
        msg_u.setColumns(20);
        msg_u.setRows(5);
        jScrollPane8.setViewportView(msg_u);

        javax.swing.GroupLayout msgPLayout = new javax.swing.GroupLayout(msgP);
        msgP.setLayout(msgPLayout);
        msgPLayout.setHorizontalGroup(
            msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msgPLayout.createSequentialGroup()
                .addContainerGap(152, Short.MAX_VALUE)
                .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, msgPLayout.createSequentialGroup()
                        .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, msgPLayout.createSequentialGroup()
                                .addComponent(mbnull)
                                .addGap(46, 46, 46)
                                .addComponent(mbenv))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, msgPLayout.createSequentialGroup()
                                .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel26)
                                    .addComponent(jLabel21)
                                    .addComponent(jLabel25))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(mt, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbm, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(74, 74, 74)
                        .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 507, Short.MAX_VALUE)
                            .addComponent(jSeparator10)
                            .addComponent(jScrollPane8))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, msgPLayout.createSequentialGroup()
                        .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(381, 381, 381))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, msgPLayout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addGap(267, 267, 267))))
        );
        msgPLayout.setVerticalGroup(
            msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(msgPLayout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(msgPLayout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel21))
                        .addGap(18, 18, 18)
                        .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(mt, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                        .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26)))
                    .addGroup(msgPLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane8)))
                .addGap(18, 18, 18)
                .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(mbnull)
                    .addComponent(mbenv))
                .addGap(43, 43, 43))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(visiteurP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1105, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(searchP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(70, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(36, 36, 36)
                    .addComponent(empP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(67, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(msgP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(482, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(visiteurP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(400, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(34, 34, 34)
                    .addComponent(searchP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(73, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(empP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(71, Short.MAX_VALUE)))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(53, 53, 53)
                    .addComponent(msgP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(293, Short.MAX_VALUE)))
        );

        lp.setLayer(jPanel1, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout lpLayout = new javax.swing.GroupLayout(lp);
        lp.setLayout(lpLayout);
        lpLayout.setHorizontalGroup(
            lpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        lpLayout.setVerticalGroup(
            lpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pleft, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lp))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pleft, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(lp)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    //switch between menu tabs

    public void switchp(JPanel p) {
        jPanel1.removeAll();
        jPanel1.add(p);
        jPanel1.repaint();
        jPanel1.revalidate();

    }

    //sql query methode
    public void executeSQlQuery(String query, String message) {
        Connection con = Singleton1.getInstance();
        Statement st;
        try {
            st = con.createStatement();
            if ((st.executeUpdate(query)) == 1) {
                // refresh jtable data
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showp();

                JOptionPane.showMessageDialog(null, "Data " + message + " Succefully");
            } else {
                JOptionPane.showMessageDialog(null, "Data Not " + message);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void tab1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab1MouseClicked
        /* b.setVisible(true);
        g.setVisible(false);
        y.setVisible(false); */

        switchp(visiteurP);
        mob.setVisible(true);
        jLabel12.setVisible(true);
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked
        /*  b.setVisible(false);
         g.setVisible(true);
         y.setVisible(false); */

        switchp(searchP);
    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked
        /* b.setVisible(false);
        g.setVisible(false);
        y.setVisible(true); */

        switchp(empP);
    }//GEN-LAST:event_tab3MouseClicked

    private void tab4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab4MouseClicked
        switchp(msgP);
    }//GEN-LAST:event_tab4MouseClicked

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        cin.setText("");
        name.setText("");
        prename.setText("");
        mob.setText("");
        reason.setText("");
        mob.setVisible(true);
        jLabel12.setVisible(true);
    }//GEN-LAST:event_resetActionPerformed

    private void addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addActionPerformed
        if (cin.getText().equals("") || name.getText().equals("") || prename.getText().equals("") || mob.getText().equals("") || reason.getText().equals("") || timeE.getText().equals("") || isValidDateTime(timeE.getText())==false || timeS.getText().equals("") || isValidDateTime(timeS.getText())==false || datej.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Formulaire vide ou invalide !", "Erreur!", JOptionPane.WARNING_MESSAGE);
        } else if (cin.getText().length() != 8 || isInteger(cin.getText()) == false) {
            JOptionPane.showMessageDialog(this, "CIN invalide ! CIN est une id de 8 nombres - Exemple : 11 111 111", "Erreur!", JOptionPane.WARNING_MESSAGE);
        } else if (mob.getText().length() != 8 || isInteger(mob.getText()) == false) {
            JOptionPane.showMessageDialog(this, "GSM invalide ! Exemple : 11 111 111", "Erreur!", JOptionPane.WARNING_MESSAGE);
        } else if (checkVisiteur(Integer.valueOf(cin.getText())) == true) {
            JOptionPane.showMessageDialog(this, "Le visiteur deja enregistre dans la base ", "Visteurs", JOptionPane.INFORMATION_MESSAGE);
            try {

                Connection con = Singleton1.getInstance();
                Statement req = con.createStatement();
                // int res = req.executeUpdate("insert into stk.produit values(4,'madriya',20)");
                int idv = Integer.valueOf(cin.getText());
                String nomv = name.getText();
                String prenomv = prename.getText();
                int telv = Integer.decode(mob.getText());
                String disc = reason.getText();
                String te = timeE.getText();
                String ts = timeS.getText();
                String dj = datej.getText();
                con.setAutoCommit(false);

                String query = "UPDATE visiteur SET nom='" + name.getText() + "',prenom='" + prename.getText() + "',tel=" + mob.getText() + " WHERE cin =" + cin.getText();
                req.executeUpdate(query);

                String insert2 = "insert into info_visit(cin_v,reason,enter_t,exit_t,datej) values (" + idv + ",'" + disc + "','" + te + "','" + ts + "','" + dj + "'" + ")";
                req.execute(insert2);
                /*  Produit p = new Produit(libelle,prixp);
          System.out.println(p.toString());*/

                con.commit();
                System.out.println("Records inserted......");
                JOptionPane.showMessageDialog(this, "Les informations sont ajoutées avec succès !", "Succes", JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showp();

                // this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error!");

            }

        } //***************************************************************
        else {
            try {

                Connection con = Singleton1.getInstance();
                Statement req = con.createStatement();
                // int res = req.executeUpdate("insert into stk.produit values(4,'madriya',20)");
                int idv = Integer.valueOf(cin.getText());
                String nomv = name.getText();
                String prenomv = prename.getText();
                int telv = Integer.decode(mob.getText());
                String disc = reason.getText();
                String te = timeE.getText();
                String ts = timeS.getText();
                String dj = datej.getText();
                con.setAutoCommit(false);

                String insert1 = "insert into visiteur(cin,nom,prenom,tel) values (" + idv + ",'" + nomv + "','" + prenomv + "'," + telv + ")";
                req.addBatch(insert1);

                String insert2 = "insert into info_visit(cin_v,reason,enter_t,exit_t,datej) values (" + idv + ",'" + disc + "','" + te + "','" + ts + "','" + dj + "'" + ")";
                /*  Produit p = new Produit(libelle,prixp);
          System.out.println(p.toString());*/
                req.addBatch(insert2);
                req.executeBatch();
                con.commit();
                System.out.println("Records inserted......");
                JOptionPane.showMessageDialog(this, "Les informations sont ajoutées avec succès !", "Succes", JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showp();

                // this.dispose();
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Error!");

            }

        }
    }//GEN-LAST:event_addActionPerformed

    private void getTeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTeActionPerformed

        Locale locale = new Locale("fr", "FR");
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        String time = dateFormat.format(new Date());
        timeE.setText(time);

    }//GEN-LAST:event_getTeActionPerformed

    private void getTsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getTsActionPerformed

        Locale locale = new Locale("fr", "FR");
        DateFormat dateFormat = DateFormat.getTimeInstance(DateFormat.DEFAULT, locale);
        String time = dateFormat.format(new Date());
        timeS.setText(time);

    }//GEN-LAST:event_getTsActionPerformed

    private void getDjActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_getDjActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_getDjActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // Get The Index Of The Slected Row 
        int i = jTable1.getSelectedRow();

        TableModel model = jTable1.getModel();

        // Display Slected Row In JTexteFields
        cin.setText(model.getValueAt(i, 0).toString());
        name.setText(model.getValueAt(i, 1).toString());
        prename.setText(model.getValueAt(i, 2).toString());
        reason.setText(model.getValueAt(i, 3).toString());
        timeE.setText(model.getValueAt(i, 4).toString());
        timeS.setText(model.getValueAt(i, 5).toString());
        datej.setText(model.getValueAt(i, 6).toString());
        /*  mob.setVisible(true);
        jLabel12.setVisible(true);*/


    }//GEN-LAST:event_jTable1MouseClicked

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed

        if (cin.getText().equals("") || name.getText().equals("") || prename.getText().equals("") || mob.getText().equals("") || reason.getText().equals("") || timeE.getText().equals("") || timeS.getText().equals("") || datej.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Formulaire vide ou invalide !", "Erreur!", JOptionPane.WARNING_MESSAGE);
        } else if (cin.getText().length() != 8 || isInteger(cin.getText()) == false) {
            JOptionPane.showMessageDialog(this, "CIN invalide ! CIN est une id de 8 nombres - Exemple : 11 111 111", "Erreur!", JOptionPane.WARNING_MESSAGE);
        } else {

            String query = "UPDATE visiteur SET nom='" + name.getText() + "',prenom='" + prename.getText() + "',tel=" + mob.getText() + " WHERE cin =" + cin.getText();
            executeSQlQuery(query, "Updated");
            String update2 = "update info_visit set reason ='" + reason.getText() + "',enter_t='" + timeE.getText() + "',exit_t='" + timeS.getText() + "',datej= '" + datej.getText() + "' where cin_v=" + cin.getText() + "";
            executeSQlQuery(update2, "Updated");
        }


    }//GEN-LAST:event_updateActionPerformed

    private void refreshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        jTable1.repaint();
        showp();
    }//GEN-LAST:event_refreshActionPerformed

    private void vidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vidActionPerformed
        if (cin.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "CIN vide ou invalide", "Visteurs", JOptionPane.ERROR_MESSAGE);
        } else if (checkVisiteur(Integer.valueOf(cin.getText())) == true) {
            JOptionPane.showMessageDialog(this, "Le visiteur deja enregistre dans la base ", "Visteurs", JOptionPane.INFORMATION_MESSAGE);
            name.setText(getvisiteurById(Integer.valueOf(cin.getText())).getNom());
            prename.setText(getvisiteurById(Integer.valueOf(cin.getText())).getPrenom());
            int idd = Integer.valueOf(cin.getText());
            int t = getvisiteurById(idd).getTel();
            mob.setText(String.valueOf(t));
            //*****
            timeE.setText("");
            timeS.setText("");
        } else if (checkVisiteur(Integer.valueOf(cin.getText())) == false) {
            JOptionPane.showMessageDialog(this, "Le visiteur n'est pas dans la base !", "Visteurs", JOptionPane.INFORMATION_MESSAGE);
        } else {
        }


    }//GEN-LAST:event_vidActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (rcin.getText().isBlank() || rcin.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Champ CIN est null!", "Visteurs", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showVisitById();
                
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "CIN vide ou invalide", "Visteurs", JOptionPane.ERROR_MESSAGE);
            }
        }


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        // Get The Index Of The Slected Row 
        int i = jTable2.getSelectedRow();

        TableModel model = jTable2.getModel();

        // Display Slected Row In JTexteFields
        vcin.setText(model.getValueAt(i, 0).toString());
        vnom.setText(model.getValueAt(i, 1).toString());
        vprenom.setText(model.getValueAt(i, 2).toString());
        vtel.setText(model.getValueAt(i, 3).toString());
        vlieu.setText(model.getValueAt(i, 4).toString());

    }//GEN-LAST:event_jTable2MouseClicked

    private void modifActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifActionPerformed
        int cinv = Integer.parseInt(vcin.getText());
        String nom = vnom.getText();
        String prenom = vprenom.getText();
        int tel = Integer.valueOf(vtel.getText());
        String lieu = vlieu.getText();

        Visiteur v = new Visiteur(cinv, nom, prenom, tel, lieu);
        System.out.println(v.toString());
        VisiteurCRUD vc = new VisiteurCRUD();
        vc.updatev(v);
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        showtv();


    }//GEN-LAST:event_modifActionPerformed

    private void vrechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vrechActionPerformed
        try {

            if (vrcin.getText().isBlank() || vrcin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Champ CIN est null!", "Visteurs", JOptionPane.WARNING_MESSAGE);
            } else {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);
                showVisiteurById();
                showVisiteurByNom();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ce Visiteur n'est pas dans la base !", "Visteurs", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_vrechActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        model.setRowCount(0);
        showtv();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void cleanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanActionPerformed

        vcin.setText("");
        vnom.setText("");
        vprenom.setText("");
        vtel.setText("");
        vlieu.setText("");

    }//GEN-LAST:event_cleanActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        // Get The Index Of The Slected Row 
        int i = jTable3.getSelectedRow();

        TableModel model = jTable3.getModel();

        // Display Slected Row In JTexteFields
        idemployee.setText(model.getValueAt(i, 0).toString());
    }//GEN-LAST:event_jTable3MouseClicked

    private void erechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_erechActionPerformed
        try {

            if (nomemp.getText().isBlank() || nomemp.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "tapez un nom !", "Employee", JOptionPane.INFORMATION_MESSAGE);
            } else {
                DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
                model.setRowCount(0);
                showEmpByNom();
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ce Employee n'est pas dans la base !", "Visteurs", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_erechActionPerformed

    private void aempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aempActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
        model.setRowCount(0);
        showEmp();
    }//GEN-LAST:event_aempActionPerformed

    private void mbenvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mbenvActionPerformed
        try {
            String u = ConGUI.u.getNom();
            String admin = cbm.getSelectedItem().toString();
            String titre = mt.getText();
            String msg = ms.getText();
            ///
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String d = simpleDateFormat.format(new Date());
            String dat = String.valueOf(d);

            Message x = new Message(u, admin, titre, msg, dat,"admin_send");

            MessageCRUD mc = new MessageCRUD();
            mc.addMsg(x);
            JOptionPane.showMessageDialog(this, "Le Message envoyée avec succès !", "Succes", JOptionPane.INFORMATION_MESSAGE);

            mt.setText("");
            ms.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_mbenvActionPerformed

    private void dcnxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcnxActionPerformed
        dispose();
        ConGUI congui = new ConGUI();
        congui.setVisible(true);
    }//GEN-LAST:event_dcnxActionPerformed

    private void tmsgRMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tmsgRMouseClicked
        // Get The Index Of The Slected Row 
        int i = tmsgR.getSelectedRow();

        TableModel model = tmsgR.getModel();

        // Display Slected Row In JTexteFields
        msg_u.setText("--- Titre --- \n" + model.getValueAt(i, 2).toString() + "\n--- Message --- : \n" + model.getValueAt(i, 3).toString());
    }//GEN-LAST:event_tmsgRMouseClicked

    //check visiteur if already exist 
    public boolean checkVisiteur(int n) {
        Connection con = Singleton1.getInstance();
        String sql = "select * from visiteur where cin=" + n + " ";
        PreparedStatement ps;
        try {
            Statement req = con.createStatement();
            ResultSet rs = req.executeQuery(sql);
            System.out.println("crud ok");
            if (rs.next()) {
                System.out.println("crud nrxt");
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //get visiteur by id
    public Visiteur getvisiteurById(int id) {

        Connection con = Singleton1.getInstance();
        String sql = "select * from visiteur where cin=" + id;
        java.sql.PreparedStatement ps;
        try {
            ps = (java.sql.PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                int idv = rs.getInt("cin");
                String nomv = rs.getString("nom");
                String prenomv = rs.getString("prenom");
                int telv = rs.getInt("tel");
                String lieuv = rs.getString("lieu");
                return new Visiteur(idv, nomv, prenomv, telv, lieuv);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //************************ page 2 *********************************//
    // display visteurs list in jtable
    public void showtv() {
        VisiteurCRUD vc = new VisiteurCRUD();
        ArrayList<Visiteur> list = new ArrayList();
        list = vc.showv();

        DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getCin();
            row[1] = list.get(i).getNom();
            row[2] = list.get(i).getPrenom();
            row[3] = list.get(i).getTel();
            row[4] = list.get(i).getLieu();
            model.addRow(row);

        }

    }

    // display messages in jtable
    public void showMsgEnv(String ch) {
        MessageCRUD mc = new MessageCRUD();
        ArrayList<Message> list = new ArrayList();
        list = mc.showMsgUser(ch);
        DefaultTableModel model = (DefaultTableModel) tmsgR.getModel();
        Object[] row = new Object[5];
        for (int i = 0; i < list.size(); i++) {
            row[0] = list.get(i).getNom_u();
            row[1] = list.get(i).getNom_a();
            row[2] = list.get(i).getTitre();
            row[3] = list.get(i).getMsg();
            row[4] = list.get(i).getDate_m();

            model.addRow(row);

        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton aemp;
    private javax.swing.JComboBox<String> cbm;
    private javax.swing.JTextField cin;
    private javax.swing.JButton clean;
    private javax.swing.JTextField datej;
    private javax.swing.JButton dcnx;
    private javax.swing.JPanel empP;
    private javax.swing.JButton erech;
    private javax.swing.JButton getDj;
    private javax.swing.JButton getTe;
    private javax.swing.JButton getTs;
    private javax.swing.JTextField idemployee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JLayeredPane lp;
    private javax.swing.JButton mbenv;
    private javax.swing.JButton mbnull;
    private javax.swing.JTextField mob;
    private javax.swing.JButton modif;
    private javax.swing.JTextArea ms;
    private javax.swing.JPanel msgP;
    private javax.swing.JTextArea msg_u;
    private javax.swing.JTextField mt;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nomemp;
    private javax.swing.JPanel pleft;
    private javax.swing.JTextField prename;
    private javax.swing.JTextField rcin;
    private javax.swing.JTextArea reason;
    private javax.swing.JButton refresh;
    private javax.swing.JButton reset;
    private javax.swing.JPanel searchP;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JTextField timeE;
    private javax.swing.JTextField timeS;
    private javax.swing.JTable tmsgR;
    private javax.swing.JLabel tttttt1;
    private javax.swing.JLabel tuser;
    private javax.swing.JButton update;
    private javax.swing.JTextField vcin;
    private javax.swing.JButton vid;
    private javax.swing.JPanel visiteurP;
    private javax.swing.JTextField vlieu;
    private javax.swing.JTextField vnom;
    private javax.swing.JTextField vprenom;
    private javax.swing.JTextField vrcin;
    private javax.swing.JButton vrech;
    private javax.swing.JTextField vtel;
    // End of variables declaration//GEN-END:variables
}
