/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import DAO.AdminCRUD;
import DAO.EmployeeCRUD;
import DAO.InfoVisiteCRUD;
import DAO.MessageCRUD;
import DAO.UserCRUD;
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

/**
 *
 * @author mega
 */
public class GUI_ADMIN extends javax.swing.JFrame {

    /**
     * Creates new form menu
     */
    public GUI_ADMIN() {
        initComponents();

        switchp(visiteurP);

        tadmin.setText(ConGUI.a.getNom());

        showMsgEnv(ConGUI.a.getNom());

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

        //combobox users in admin GUI
        UserCRUD uc = new UserCRUD();
        ArrayList<User> la = uc.showUsers();
        for (User u : la) {
            cbm.addItem(u.getNom());
        }

        //
        Message m = new Message();
        m.setDate_m(String.valueOf(datej));

    }

    //all info_visit information
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
    //all info by id cin
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
    // info visite jtable
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
    // show visit by id
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

    //show visiteur by id in jtable
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

    //*****************
    //show list employees in jtable
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

    //show employee by name in jtable
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

    //show messages in jtable
    public void showMsgEnv(String ch) {
        MessageCRUD mc = new MessageCRUD();
        ArrayList<Message> list = new ArrayList();
        list = mc.showMsgAdmin(ch);
        DefaultTableModel model = (DefaultTableModel) tr.getModel();
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

    //**************
    //number char verfication
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
        jLabel38 = new javax.swing.JLabel();
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
        jPanel3 = new javax.swing.JPanel();
        jLabel31 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        dcnx = new javax.swing.JButton();
        tadmin = new javax.swing.JLabel();
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
        dlt_by_cin = new javax.swing.JButton();
        dlt_all_visites = new javax.swing.JButton();
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
        delv = new javax.swing.JButton();
        delallv = new javax.swing.JButton();
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
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        nom_emp = new javax.swing.JTextField();
        prenom_emp = new javax.swing.JTextField();
        dep_emp = new javax.swing.JComboBox<>();
        ocu_emp = new javax.swing.JComboBox<>();
        tel_emp = new javax.swing.JTextField();
        mail_emp = new javax.swing.JTextField();
        add_emp = new javax.swing.JButton();
        update_emp = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        delete_emp = new javax.swing.JButton();
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
        tr = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jScrollPane8 = new javax.swing.JScrollPane();
        msg_txt = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel38.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(0, 0, 0));
        jLabel38.setText("ADMIN :  ");

        tab3.setBackground(new java.awt.Color(0, 102, 204));
        tab3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tab3MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Dialog", 3, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/View/profil.png"))); // NOI18N
        jLabel3.setText("Gerer les employés");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
        jLabel29.setText("Admin Dashboard");

        jLabel30.setFont(new java.awt.Font("Eras Bold ITC", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(0, 0, 204));
        jLabel30.setText("Front-Door");

        jPanel3.setBackground(new java.awt.Color(102, 102, 102));
        jPanel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel3MouseClicked(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(204, 204, 0));
        jLabel31.setText("Gerer Les Utilisateurs");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addGap(37, 37, 37))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addContainerGap())
        );

        dcnx.setBackground(new java.awt.Color(204, 0, 0));
        dcnx.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        dcnx.setForeground(new java.awt.Color(255, 255, 255));
        dcnx.setText("Deconnecté");
        dcnx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dcnxActionPerformed(evt);
            }
        });

        tadmin.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tadmin.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout pleftLayout = new javax.swing.GroupLayout(pleft);
        pleft.setLayout(pleftLayout);
        pleftLayout.setHorizontalGroup(
            pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tab1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tab4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pleftLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel30)
                .addGap(64, 64, 64))
            .addComponent(jSeparator8)
            .addComponent(jSeparator11, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(dcnx, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(pleftLayout.createSequentialGroup()
                .addGroup(pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pleftLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel38)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tadmin, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pleftLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jLabel29)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        pleftLayout.setVerticalGroup(
            pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pleftLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(jLabel30)
                .addGap(18, 18, 18)
                .addComponent(jLabel29)
                .addGap(30, 30, 30)
                .addGroup(pleftLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tadmin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(33, 33, 33)
                .addComponent(tab1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(tab4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jSeparator8, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42)
                .addComponent(jSeparator11, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(119, 119, 119)
                .addComponent(dcnx, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(296, Short.MAX_VALUE))
        );

        lp.setBackground(new java.awt.Color(255, 51, 51));

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        visiteurP.setBackground(new java.awt.Color(153, 153, 153));

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
        jLabel4.setText("CIN :");

        name.setText(" ");

        jLabel5.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel5.setText("Nom :");

        jLabel6.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel6.setText("Prenom : ");

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
        jLabel8.setText("Description ");

        reason.setColumns(20);
        reason.setRows(5);
        reason.setText("Reason de visit : \n-\n\n\n");
        jScrollPane1.setViewportView(reason);

        jSeparator1.setBackground(new java.awt.Color(153, 153, 153));
        jSeparator1.setForeground(new java.awt.Color(102, 102, 102));
        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel9.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel9.setText("Temp d'entree : ");

        jLabel10.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel10.setText("Temp de sortie : ");

        jLabel11.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel11.setText("Date :");

        datej.setEditable(false);

        jLabel12.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel12.setText("Telephone : ");

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

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel13.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
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

        jLabel14.setText("Recherche par CIN : ");

        jButton1.setBackground(new java.awt.Color(102, 0, 153));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Rechercher");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dlt_by_cin.setBackground(new java.awt.Color(204, 0, 0));
        dlt_by_cin.setForeground(new java.awt.Color(255, 255, 255));
        dlt_by_cin.setText("Supprimer");
        dlt_by_cin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlt_by_cinActionPerformed(evt);
            }
        });

        dlt_all_visites.setBackground(new java.awt.Color(0, 0, 0));
        dlt_all_visites.setForeground(new java.awt.Color(255, 255, 255));
        dlt_all_visites.setText("Supprimer tout");
        dlt_all_visites.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dlt_all_visitesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout visiteurPLayout = new javax.swing.GroupLayout(visiteurP);
        visiteurP.setLayout(visiteurPLayout);
        visiteurPLayout.setHorizontalGroup(
            visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(visiteurPLayout.createSequentialGroup()
                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(visiteurPLayout.createSequentialGroup()
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
                                .addComponent(reset)
                                .addGap(313, 313, 313))))
                    .addGroup(visiteurPLayout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(visiteurPLayout.createSequentialGroup()
                                .addComponent(update)
                                .addGap(56, 56, 56)
                                .addComponent(add))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(25, 25, 25)
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
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addComponent(dlt_all_visites)
                                .addGap(43, 43, 43)
                                .addComponent(dlt_by_cin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane1)
                                .addGap(28, 28, 28)
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(reset, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(add, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(update, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                                .addGroup(visiteurPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(refresh)
                                    .addComponent(dlt_by_cin)
                                    .addComponent(dlt_all_visites))))
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, visiteurPLayout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(57, 57, 57))))
        );

        searchP.setBackground(new java.awt.Color(153, 153, 153));

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
        jLabel15.setText("CIN : ");

        vcin.setEditable(false);
        vcin.setForeground(new java.awt.Color(0, 0, 0));

        jLabel16.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel16.setText("Nom : ");

        jLabel17.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel17.setText("Prenom : ");

        jLabel18.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel18.setText("Telephone : ");

        jLabel19.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel19.setText("Lieu : ");

        vnom.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N

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

        jLabel20.setText("Recherche par CIN : ");

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

        jPanel4.setBackground(new java.awt.Color(102, 102, 102));
        jPanel4.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel22.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
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

        delv.setBackground(new java.awt.Color(204, 0, 0));
        delv.setForeground(new java.awt.Color(255, 255, 255));
        delv.setText("Supprimer");
        delv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delvActionPerformed(evt);
            }
        });

        delallv.setBackground(new java.awt.Color(0, 0, 0));
        delallv.setForeground(new java.awt.Color(255, 255, 255));
        delallv.setText("Supprimer tout");
        delallv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delallvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchPLayout = new javax.swing.GroupLayout(searchP);
        searchP.setLayout(searchPLayout);
        searchPLayout.setHorizontalGroup(
            searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchPLayout.createSequentialGroup()
                .addContainerGap(231, Short.MAX_VALUE)
                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchPLayout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(46, 46, 46)
                                .addComponent(vrcin, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(vrech))
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
                                .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(searchPLayout.createSequentialGroup()
                                        .addComponent(delallv)
                                        .addGap(43, 43, 43)
                                        .addComponent(delv)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton3))
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 439, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                        .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(delv)
                                .addComponent(delallv))
                            .addComponent(jButton3))
                        .addContainerGap(26, Short.MAX_VALUE))))
        );

        empP.setBackground(new java.awt.Color(153, 153, 153));

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

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));
        jPanel5.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel23.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
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

        tttttt1.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        tttttt1.setText("Employee :");

        idemployee.setEditable(false);

        jLabel32.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel32.setText("Nom : ");

        jLabel33.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel33.setText("Prenom : ");

        jLabel34.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel34.setText("Department : ");

        jLabel35.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel35.setText("Telephone : ");

        jLabel36.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel36.setText("Ocupation : ");

        jLabel37.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel37.setText("E-mail : ");

        dep_emp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Informatique", "Mechanique", "Electrique", "Fashion", " " }));
        dep_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dep_empActionPerformed(evt);
            }
        });

        ocu_emp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ingenieur", "Technicien", "Modelist", " " }));

        add_emp.setBackground(new java.awt.Color(0, 51, 51));
        add_emp.setForeground(new java.awt.Color(255, 255, 255));
        add_emp.setText("Ajouter");
        add_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_empActionPerformed(evt);
            }
        });

        update_emp.setBackground(new java.awt.Color(0, 51, 102));
        update_emp.setForeground(new java.awt.Color(255, 255, 255));
        update_emp.setText("Modifier");
        update_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_empActionPerformed(evt);
            }
        });

        jButton5.setText("Reset");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        delete_emp.setBackground(new java.awt.Color(204, 0, 0));
        delete_emp.setForeground(new java.awt.Color(255, 255, 255));
        delete_emp.setText("Supprimer");
        delete_emp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_empActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout empPLayout = new javax.swing.GroupLayout(empP);
        empP.setLayout(empPLayout);
        empPLayout.setHorizontalGroup(
            empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empPLayout.createSequentialGroup()
                .addGap(100, 100, 100)
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
                            .addGroup(empPLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(erech))
                            .addGroup(empPLayout.createSequentialGroup()
                                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(empPLayout.createSequentialGroup()
                                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(empPLayout.createSequentialGroup()
                                                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel35)
                                                    .addComponent(jLabel37))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(mail_emp)
                                                    .addGroup(empPLayout.createSequentialGroup()
                                                        .addComponent(tel_emp, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                                                        .addGap(103, 103, 103))))
                                            .addGroup(empPLayout.createSequentialGroup()
                                                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel36)
                                                    .addComponent(jLabel34)
                                                    .addComponent(jLabel32))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addComponent(ocu_emp, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(dep_emp, 0, 134, Short.MAX_VALUE)
                                                    .addComponent(prenom_emp)
                                                    .addComponent(nom_emp)))
                                            .addComponent(jLabel33))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, empPLayout.createSequentialGroup()
                                        .addGap(0, 92, Short.MAX_VALUE)
                                        .addComponent(jButton5)
                                        .addGap(43, 43, 43)
                                        .addComponent(update_emp)
                                        .addGap(38, 38, 38)
                                        .addComponent(add_emp)
                                        .addGap(97, 97, 97)))
                                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(empPLayout.createSequentialGroup()
                                        .addComponent(delete_emp)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(aemp))
                                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))))
        );
        empPLayout.setVerticalGroup(
            empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(empPLayout.createSequentialGroup()
                .addContainerGap(107, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(erech)
                    .addComponent(nomemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tttttt1)
                    .addComponent(idemployee, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(empPLayout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(empPLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel32)
                            .addComponent(nom_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(prenom_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(dep_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel36)
                            .addComponent(ocu_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel35)
                            .addComponent(tel_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel37)
                            .addComponent(mail_emp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(add_emp)
                            .addComponent(update_emp)
                            .addComponent(jButton5))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(empPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(aemp)
                    .addComponent(delete_emp))
                .addGap(44, 44, 44))
        );

        idemployee.setVisible(false);

        msgP.setBackground(new java.awt.Color(153, 153, 153));
        msgP.setForeground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(102, 102, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(102, 102, 102)));

        jLabel24.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
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
        jLabel21.setText("Envoyer à : ");

        jLabel25.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel25.setText("Titre : ");

        jLabel26.setFont(new java.awt.Font("Dialog", 3, 14)); // NOI18N
        jLabel26.setText("Sujet : ");

        ms.setColumns(20);
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

        tr.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "-", "-", "Titre", "Message", "date"
            }
        ));
        tr.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trMouseClicked(evt);
            }
        });
        jScrollPane6.setViewportView(tr);

        jLabel27.setFont(new java.awt.Font("Dialog", 2, 11)); // NOI18N
        jLabel27.setText("Messages recu");

        msg_txt.setEditable(false);
        msg_txt.setColumns(20);
        msg_txt.setRows(5);
        jScrollPane8.setViewportView(msg_txt);

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
                            .addComponent(jLabel26))
                        .addGap(18, 18, 18)
                        .addGroup(msgPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(mbnull)
                            .addComponent(mbenv))
                        .addGap(43, 43, 43))
                    .addGroup(msgPLayout.createSequentialGroup()
                        .addGap(71, 71, 71)
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33)
                        .addComponent(jSeparator10, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(159, 159, 159)
                .addComponent(visiteurP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(1196, Short.MAX_VALUE))
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

    //switch between tabs
    public void switchp(JPanel p) {
        jPanel1.removeAll();
        jPanel1.add(p);
        jPanel1.repaint();
        jPanel1.revalidate();

    }

    // query methode
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

        switchp(visiteurP);
        mob.setVisible(true);
        jLabel12.setVisible(true);
    }//GEN-LAST:event_tab1MouseClicked

    private void tab2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab2MouseClicked

        switchp(searchP);
    }//GEN-LAST:event_tab2MouseClicked

    private void tab3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tab3MouseClicked

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
        if (cin.getText().equals("") || name.getText().equals("") || prename.getText().equals("") || mob.getText().equals("") || reason.getText().equals("") || timeE.getText().equals("") || timeS.getText().equals("") || datej.getText().equals("")) {
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

                con.commit();
                System.out.println("Records inserted......");
                JOptionPane.showMessageDialog(this, "Les informations sont ajoutées avec succès !", "Succes", JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showp();

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

                req.addBatch(insert2);
                req.executeBatch();
                con.commit();
                System.out.println("Records inserted......");
                JOptionPane.showMessageDialog(this, "Les informations sont ajoutées avec succès !", "Succes", JOptionPane.INFORMATION_MESSAGE);

                DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
                model.setRowCount(0);
                showp();

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
        if (nom.isBlank() || nom.isEmpty() || prenom.isBlank() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "nom et prenom vide/invalid !", "Visiteur", JOptionPane.INFORMATION_MESSAGE);
        } else {
            vc.updatev(v);
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            showtv();
        }

    }//GEN-LAST:event_modifActionPerformed

    private void vrechActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vrechActionPerformed
        try {

            if (vrcin.getText().isBlank() || vrcin.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Champ CIN est null!", "Visteurs", JOptionPane.WARNING_MESSAGE);
            } else {
                DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
                model.setRowCount(0);
                showVisiteurById();
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
        nom_emp.setText(model.getValueAt(i, 1).toString());
        prenom_emp.setText(model.getValueAt(i, 2).toString());
        tel_emp.setText(model.getValueAt(i, 5).toString());
        mail_emp.setText(model.getValueAt(i, 6).toString());
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
            String admin = ConGUI.a.getNom();
            String user = cbm.getSelectedItem().toString();
            String titre = mt.getText();
            String msg = ms.getText();
            ///
            String pattern = "dd-MM-yyyy";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String d = simpleDateFormat.format(new Date());
            String dat = String.valueOf(d);

            Message x = new Message(user, admin, titre, msg, dat , "user_send");

            MessageCRUD mc = new MessageCRUD();
            mc.addMsg(x);
            JOptionPane.showMessageDialog(this, "Le Message envoyée avec succès !", "Succes", JOptionPane.INFORMATION_MESSAGE);

            mt.setText("");
            ms.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_mbenvActionPerformed

    private void dep_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dep_empActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dep_empActionPerformed

    private void dcnxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dcnxActionPerformed
        dispose();
        ConGUI congui = new ConGUI();
        congui.setVisible(true);
    }//GEN-LAST:event_dcnxActionPerformed

    private void dlt_by_cinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlt_by_cinActionPerformed
        InfoVisiteCRUD ivc = new InfoVisiteCRUD();
        int del = JOptionPane.showConfirmDialog(this, "êtes-vous sûr de supprimer toutes les informations de " + cin.getText() + " ?", "Supprimer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (del == JOptionPane.YES_OPTION) {
            ivc.deletev(Integer.valueOf(cin.getText()));
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            showp();
            cin.setText("");
            name.setText("");
            prename.setText("");
            mob.setText("");
            reason.setText("");

        }

    }//GEN-LAST:event_dlt_by_cinActionPerformed

    private void dlt_all_visitesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dlt_all_visitesActionPerformed
        InfoVisiteCRUD ivc = new InfoVisiteCRUD();
        int del = JOptionPane.showConfirmDialog(this, "êtes-vous sûr de supprimer tout ?", "Supprimer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (del == JOptionPane.YES_OPTION) {
            ivc.deletev();
            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            model.setRowCount(0);
            showp();
            cin.setText("");
            name.setText("");
            prename.setText("");
            mob.setText("");
            reason.setText("");
        }
    }//GEN-LAST:event_dlt_all_visitesActionPerformed

    private void delvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delvActionPerformed
        VisiteurCRUD vc = new VisiteurCRUD();
        int del = JOptionPane.showConfirmDialog(this, "êtes-vous sûr de supprimer le visiteur " + vcin.getText() + " ?", "Supprimer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (del == JOptionPane.YES_OPTION) {
            vc.deletev(Integer.valueOf(vcin.getText()));
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            showtv();
            vcin.setText("");
            vnom.setText("");
            vprenom.setText("");
            vtel.setText("");
            vlieu.setText("");
        }
    }//GEN-LAST:event_delvActionPerformed

    private void delallvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delallvActionPerformed
        VisiteurCRUD vc = new VisiteurCRUD();
        int del = JOptionPane.showConfirmDialog(this, "êtes-vous sûr de supprimer tous les visiteurs ?", "Supprimer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (del == JOptionPane.YES_OPTION) {
            vc.deletev();
            DefaultTableModel model = (DefaultTableModel) jTable2.getModel();
            model.setRowCount(0);
            showtv();
            vcin.setText("");
            vnom.setText("");
            vprenom.setText("");
            vtel.setText("");
            vlieu.setText("");
        }
    }//GEN-LAST:event_delallvActionPerformed

    private void add_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_empActionPerformed
        if (isValidMail(mail_emp.getText()) == false) {
            JOptionPane.showMessageDialog(this, "E-mail invalid !", "Invalid", JOptionPane.WARNING_MESSAGE);
        } else if (nom_emp.getText().isBlank() || nom_emp.getText().isEmpty() || prenom_emp.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "champ vide !", "vide", JOptionPane.INFORMATION_MESSAGE);
        } else if (isInteger(tel_emp.getText()) == false) {
            JOptionPane.showMessageDialog(this, "invalid telephone number !", "Invalid", JOptionPane.WARNING_MESSAGE);
        } else {

            String nom = nom_emp.getText();
            String prenom = prenom_emp.getText();
            String dep = String.valueOf(dep_emp.getSelectedItem());
            String ocu = String.valueOf(ocu_emp.getSelectedItem());
            int tel = Integer.valueOf(tel_emp.getText());
            String mail = mail_emp.getText();
            EmployeeCRUD ec = new EmployeeCRUD();

            Employee e = new Employee(nom, prenom, dep, ocu, tel, mail);

            ec.addE(e);
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            showEmp();
            JOptionPane.showMessageDialog(this, "Employe a ete ajouté avec sucess !", "Employes", JOptionPane.INFORMATION_MESSAGE);

        }

    }//GEN-LAST:event_add_empActionPerformed

    private void update_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_empActionPerformed
        int id = Integer.valueOf(idemployee.getText());
        String nom = nom_emp.getText();
        String prenom = prenom_emp.getText();
        String dep = String.valueOf(dep_emp.getSelectedItem());
        String ocu = String.valueOf(ocu_emp.getSelectedItem());
        int tel = Integer.valueOf(tel_emp.getText());
        String mail = mail_emp.getText();
        EmployeeCRUD ec = new EmployeeCRUD();

        Employee e = new Employee(id, nom, prenom, dep, ocu, tel, mail);
        if (nom.isBlank() || nom.isEmpty() || prenom.isBlank() || prenom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "champ vide !", "vide", JOptionPane.INFORMATION_MESSAGE);
        } else {
            ec.updateE(e);
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            showEmp();
            JOptionPane.showMessageDialog(this, "l'employe a ete modifié avec sucess !", "Employes", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_update_empActionPerformed

    private void delete_empActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_empActionPerformed
        EmployeeCRUD ec = new EmployeeCRUD();
        int id = Integer.valueOf(idemployee.getText());
        int del = JOptionPane.showConfirmDialog(this, "êtes-vous sûr de supprimer cette employee ?", "Supprimer", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (del == JOptionPane.YES_OPTION) {
            ec.deleteE(id);
            DefaultTableModel model = (DefaultTableModel) jTable3.getModel();
            model.setRowCount(0);
            showEmp();
        }
    }//GEN-LAST:event_delete_empActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed

        nom_emp.setText("");
        prenom_emp.setText("");
        tel_emp.setText("");
        mail_emp.setText("");

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jPanel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel3MouseClicked
        GestUserGUI gug = new GestUserGUI();
        gug.setVisible(true);
    }//GEN-LAST:event_jPanel3MouseClicked

    private void trMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trMouseClicked
        // Get The Index Of The Slected Row 
        int i = tr.getSelectedRow();

        TableModel model = tr.getModel();

        // Display Slected Row In JTexteFields
        msg_txt.setText("--- Titre --- \n" + model.getValueAt(i, 2).toString() + "\n--- Message --- : \n" + model.getValueAt(i, 3).toString());

    }//GEN-LAST:event_trMouseClicked

    // check visiteur if already exist
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

    // get visiteur id
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
    // jtable visiteur display
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

    //validating e-mail
    static boolean isValidMail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
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
            java.util.logging.Logger.getLogger(GUI_ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUI_ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUI_ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUI_ADMIN.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUI_ADMIN().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add;
    private javax.swing.JButton add_emp;
    private javax.swing.JButton aemp;
    private javax.swing.JComboBox<String> cbm;
    private javax.swing.JTextField cin;
    private javax.swing.JButton clean;
    private javax.swing.JTextField datej;
    private javax.swing.JButton dcnx;
    private javax.swing.JButton delallv;
    private javax.swing.JButton delete_emp;
    private javax.swing.JButton delv;
    private javax.swing.JComboBox<String> dep_emp;
    private javax.swing.JButton dlt_all_visites;
    private javax.swing.JButton dlt_by_cin;
    private javax.swing.JPanel empP;
    private javax.swing.JButton erech;
    private javax.swing.JButton getDj;
    private javax.swing.JButton getTe;
    private javax.swing.JButton getTs;
    private javax.swing.JTextField idemployee;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
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
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
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
    private javax.swing.JTextField mail_emp;
    private javax.swing.JButton mbenv;
    private javax.swing.JButton mbnull;
    private javax.swing.JTextField mob;
    private javax.swing.JButton modif;
    private javax.swing.JTextArea ms;
    private javax.swing.JPanel msgP;
    private javax.swing.JTextArea msg_txt;
    private javax.swing.JTextField mt;
    private javax.swing.JTextField name;
    private javax.swing.JTextField nom_emp;
    private javax.swing.JTextField nomemp;
    private javax.swing.JComboBox<String> ocu_emp;
    private javax.swing.JPanel pleft;
    private javax.swing.JTextField prename;
    private javax.swing.JTextField prenom_emp;
    private javax.swing.JTextField rcin;
    private javax.swing.JTextArea reason;
    private javax.swing.JButton refresh;
    private javax.swing.JButton reset;
    private javax.swing.JPanel searchP;
    private javax.swing.JPanel tab1;
    private javax.swing.JPanel tab2;
    private javax.swing.JPanel tab3;
    private javax.swing.JPanel tab4;
    private javax.swing.JLabel tadmin;
    private javax.swing.JTextField tel_emp;
    private javax.swing.JTextField timeE;
    private javax.swing.JTextField timeS;
    private javax.swing.JTable tr;
    private javax.swing.JLabel tttttt1;
    private javax.swing.JButton update;
    private javax.swing.JButton update_emp;
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
