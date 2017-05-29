/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import sources.CrearDocumentos;
import sources.Filtros;
import sources.GeneraCalendario;
import sources.MostrarDatos;
import java.util.Date;
import sources.LeerExcel;

/**
 *
 * @author rob_a
 */
public class Calendario extends javax.swing.JFrame {
    
private FondoVentana fondo;
private final MostrarDatos md = new MostrarDatos();
private final GeneraCalendario gc = new GeneraCalendario();
private Filtros filtro;
private final CrearDocumentos cd = new CrearDocumentos();

    public Calendario() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);
        Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/resources/logo1.png"));
        setIconImage(icon);       
        fondo = new FondoVentana();
        this.add(fondo);
        
        md.llenarComboCategorias(this.cbCategoria);
        md.llenarComboDivisiones(this.cbDivision);
        md.llenarComboJornadas(this.cbJornada);
        md.llenarTCalendario(this.tCalendario);
        
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent evt) {
                close();
            }
   
        });
        
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e){
                  int x, y;
                  x = Calendario.this.getWidth();
                  y = Calendario.this.getHeight();
                  fondo.fondoZize(x, y);
            }
        });   
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInicioCalendario = new javax.swing.JPanel();
        lbInicioFechaInicio = new javax.swing.JLabel();
        dFechaInicioTemporada = new com.toedter.calendar.JDateChooser();
        lblInicioFechaFin = new javax.swing.JLabel();
        dFechaFinTemporada = new com.toedter.calendar.JDateChooser();
        btnGenerarInicio = new javax.swing.JButton();
        pCalendario = new javax.swing.JPanel();
        lblCategoria = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox<>();
        lblDivision = new javax.swing.JLabel();
        cbDivision = new javax.swing.JComboBox<>();
        lblJornada = new javax.swing.JLabel();
        cbJornada = new javax.swing.JComboBox<>();
        lblFechaInicio = new javax.swing.JLabel();
        lblFechaFin = new javax.swing.JLabel();
        btnFiltroLimpiar = new javax.swing.JButton();
        btnFiltroAceptar = new javax.swing.JButton();
        dFechaInicoFiltro = new com.toedter.calendar.JDateChooser();
        dFechaFinFiltro = new com.toedter.calendar.JDateChooser();
        scrollCalendario = new javax.swing.JScrollPane();
        tCalendario = new javax.swing.JTable();
        btnCalendarioGenerar = new javax.swing.JButton();
        btnCalendarioSalir = new javax.swing.JButton();
        mbCalendario = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        miImportarcalendario = new javax.swing.JMenuItem();
        mExportar = new javax.swing.JMenu();
        miExportarExcel = new javax.swing.JMenuItem();
        miExportarPDF = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generación de Calendario");
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        jInicioCalendario.setBackground(new java.awt.Color(255, 255, 255));
        jInicioCalendario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 4, true), "  FECHA DE INICIO DE TEMPORADA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12))); // NOI18N
        jInicioCalendario.setPreferredSize(new java.awt.Dimension(978, 67));

        lbInicioFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        lbInicioFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbInicioFechaInicio.setText("Fecha de Incio");

        lblInicioFechaFin.setBackground(new java.awt.Color(255, 255, 255));
        lblInicioFechaFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInicioFechaFin.setText("Fecha de Fin");

        btnGenerarInicio.setBackground(new java.awt.Color(31, 87, 12));
        btnGenerarInicio.setForeground(new java.awt.Color(255, 255, 255));
        btnGenerarInicio.setText("Generar");
        btnGenerarInicio.setMaximumSize(new java.awt.Dimension(120, 23));
        btnGenerarInicio.setMinimumSize(new java.awt.Dimension(120, 23));
        btnGenerarInicio.setPreferredSize(new java.awt.Dimension(120, 23));
        btnGenerarInicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarInicioActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInicioCalendarioLayout = new javax.swing.GroupLayout(jInicioCalendario);
        jInicioCalendario.setLayout(jInicioCalendarioLayout);
        jInicioCalendarioLayout.setHorizontalGroup(
            jInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInicioCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInicioFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dFechaInicioTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInicioFechaFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dFechaFinTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jInicioCalendarioLayout.setVerticalGroup(
            jInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInicioCalendarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dFechaFinTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbInicioFechaInicio)
                        .addComponent(btnGenerarInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblInicioFechaFin)
                        .addComponent(dFechaInicioTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(10, 10, 10))
        );

        pCalendario.setBackground(new java.awt.Color(255, 255, 255));
        pCalendario.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 4, true), "  GESTIONAR FILTROS DE EXPORTACIÓN  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12))); // NOI18N
        pCalendario.setPreferredSize(new java.awt.Dimension(1366, 105));

        lblCategoria.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblCategoria.setText("Categoría");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));

        lblDivision.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDivision.setText("División");

        cbDivision.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbDivision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        cbDivision.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbDivisionActionPerformed(evt);
            }
        });

        lblJornada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblJornada.setText("Jornada");

        cbJornada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbJornada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));

        lblFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFechaInicio.setText("Fecha de Incio");

        lblFechaFin.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblFechaFin.setText("Fecha de Fin");

        btnFiltroLimpiar.setBackground(new java.awt.Color(31, 87, 12));
        btnFiltroLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroLimpiar.setText("Limpiar Filtros");
        btnFiltroLimpiar.setFocusPainted(false);
        btnFiltroLimpiar.setMaximumSize(new java.awt.Dimension(120, 23));
        btnFiltroLimpiar.setMinimumSize(new java.awt.Dimension(120, 23));
        btnFiltroLimpiar.setPreferredSize(new java.awt.Dimension(120, 23));
        btnFiltroLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroLimpiarActionPerformed(evt);
            }
        });

        btnFiltroAceptar.setBackground(new java.awt.Color(31, 87, 12));
        btnFiltroAceptar.setForeground(new java.awt.Color(255, 255, 255));
        btnFiltroAceptar.setText("Aceptar");
        btnFiltroAceptar.setMaximumSize(new java.awt.Dimension(120, 23));
        btnFiltroAceptar.setMinimumSize(new java.awt.Dimension(120, 23));
        btnFiltroAceptar.setName(""); // NOI18N
        btnFiltroAceptar.setPreferredSize(new java.awt.Dimension(120, 23));
        btnFiltroAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltroAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pCalendarioLayout = new javax.swing.GroupLayout(pCalendario);
        pCalendario.setLayout(pCalendarioLayout);
        pCalendarioLayout.setHorizontalGroup(
            pCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCalendarioLayout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(lblCategoria)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDivision)
                .addGap(18, 18, 18)
                .addComponent(cbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblJornada)
                .addGap(18, 18, 18)
                .addComponent(cbJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dFechaInicoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblFechaFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dFechaFinFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 89, Short.MAX_VALUE)
                .addComponent(btnFiltroLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnFiltroAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pCalendarioLayout.setVerticalGroup(
            pCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblCategoria)
                        .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblDivision)
                        .addComponent(cbDivision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblJornada)
                        .addComponent(lblFechaInicio)
                        .addComponent(btnFiltroLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFiltroAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(dFechaInicoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lblFechaFin)
                        .addComponent(dFechaFinFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tCalendario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Jornada", "Fecha", "Día", "Hora", "Equipo Local", "Equipo Visitante", "Campo", "Competición", "Partido aplazado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, false, true, false, false, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tCalendario.getTableHeader().setReorderingAllowed(false);
        scrollCalendario.setViewportView(tCalendario);

        btnCalendarioGenerar.setBackground(new java.awt.Color(31, 87, 12));
        btnCalendarioGenerar.setForeground(new java.awt.Color(255, 255, 255));
        btnCalendarioGenerar.setText("Guardar Calendario");
        btnCalendarioGenerar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioGenerarActionPerformed(evt);
            }
        });

        btnCalendarioSalir.setBackground(new java.awt.Color(31, 87, 12));
        btnCalendarioSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnCalendarioSalir.setText("Salir");
        btnCalendarioSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioSalirActionPerformed(evt);
            }
        });

        mbCalendario.setBackground(new java.awt.Color(31, 87, 12));
        mbCalendario.setForeground(new java.awt.Color(255, 255, 255));

        jMenu1.setForeground(new java.awt.Color(255, 255, 255));
        jMenu1.setText("Importar");

        miImportarcalendario.setBackground(new java.awt.Color(31, 87, 12));
        miImportarcalendario.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        miImportarcalendario.setForeground(new java.awt.Color(255, 255, 255));
        miImportarcalendario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/calendario.png"))); // NOI18N
        miImportarcalendario.setText("Calendario");
        miImportarcalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miImportarcalendarioActionPerformed(evt);
            }
        });
        jMenu1.add(miImportarcalendario);

        mbCalendario.add(jMenu1);

        mExportar.setForeground(new java.awt.Color(255, 255, 255));
        mExportar.setText("Exportar");
        mExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mExportarActionPerformed(evt);
            }
        });

        miExportarExcel.setBackground(new java.awt.Color(31, 87, 12));
        miExportarExcel.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        miExportarExcel.setForeground(new java.awt.Color(255, 255, 255));
        miExportarExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/excel.png"))); // NOI18N
        miExportarExcel.setText("A Excel...");
        miExportarExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExportarExcelActionPerformed(evt);
            }
        });
        mExportar.add(miExportarExcel);

        miExportarPDF.setBackground(new java.awt.Color(31, 87, 12));
        miExportarPDF.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        miExportarPDF.setForeground(new java.awt.Color(255, 255, 255));
        miExportarPDF.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pdf.png"))); // NOI18N
        miExportarPDF.setText("A PDF...");
        miExportarPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miExportarPDFActionPerformed(evt);
            }
        });
        mExportar.add(miExportarPDF);

        mbCalendario.add(mExportar);

        setJMenuBar(mbCalendario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCalendarioGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnCalendarioSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollCalendario)
                    .addComponent(pCalendario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                    .addComponent(jInicioCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jInicioCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCalendarioSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCalendarioGenerar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalendarioGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioGenerarActionPerformed
        ImageIcon icon = new ImageIcon("src/resources/guardar.png");
        int input = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios realizados en el calendario?", "Guardar cambios", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
                
        }      
        
    }//GEN-LAST:event_btnCalendarioGenerarActionPerformed

    private void mExportarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mExportarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mExportarActionPerformed

    private void miExportarExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExportarExcelActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Excel (*.xlsx)", "xlsx");
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showSaveDialog(new Principal());
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();
            //System.out.println("Save as file: " + fichero.getAbsolutePath()+"."+filter.getExtensions()[0]);
            //aqui debe coger los datos de la BBDD y guardarlos en un excel
            cd.crearExcel(fichero.getAbsolutePath()+"."+filter.getExtensions()[0], this.tCalendario);

        }
//        String categoria2 = (String) cbCategoria.getSelectedItem();
//        String division2 = (String) cbDivision.getSelectedItem();
//        String jornada2 = (String) cbJornada.getSelectedItem();
//        
//        switch(categoria2) {
//            
//          case "Todas": 
//            switch(division2){
//                case "Primera":
//                  switch(jornada2){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioTODAS.xlsx");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                        ex.printStackTrace();
//                   }
//            }
//            }
//            break;
//            
//        case "Senior": 
//            switch(division2){
//                case "Primera":
//                  switch(jornada2){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioSenior.xlsx");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                        ex.printStackTrace();
//                   }
//            }
//            }
//            break;
//        case "Veteranos+30": 
//                switch(division2){
//                case "Primera":
//                  switch(jornada2){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioVet+30.xlsx");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                    }
//            }
//            }
//            break;
//        case "Veteranos+35": 
//            switch(division2){
//                case "Primera":
//                  switch(jornada2){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioVet+35.xlsx");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                    }
//            }
//            }
//            break;
//        }  
    }//GEN-LAST:event_miExportarExcelActionPerformed

    private void miExportarPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miExportarPDFActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo PDF (*.pdf)", "pdf");
        fileChooser.setFileFilter(filter);
        int seleccion = fileChooser.showSaveDialog(new Principal());
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();
            System.out.println("Save as file: " + fichero.getAbsolutePath());
            //aqui debe coger los datos de la BBDD y guardarlos en un PDF

        }
//        String categoria = (String) cbCategoria.getSelectedItem();
//        String division = (String) cbDivision.getSelectedItem();
//        String jornada = (String) cbJornada.getSelectedItem();
//        
//        switch(categoria) {
//            
//        case "Todas": 
//            switch(division){
//                case "Primera":
//                  switch(jornada){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioTODAS.pdf");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                        ex.printStackTrace();
//                   }
//            }
//            }
//            break;
//            
//        case "Senior": 
//            switch(division){
//                case "Primera":
//                  switch(jornada){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioSenior.pdf");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                        ex.printStackTrace();
//                   }
//            }
//            }
//            break;
//        case "Veteranos+30": 
//                switch(division){
//                case "Primera":
//                  switch(jornada){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioVet+30.pdf");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                    }
//            }
//            }
//            break;
//        case "Veteranos+35": 
//            switch(division){
//                case "Primera":
//                  switch(jornada){
//                    case "1": 
//                        try {
//                        File path = new File ("src\\calendarios\\EjemploCalendarioVet+35.pdf");
//                        Desktop.getDesktop().open(path);
//                   }catch (IOException ex) {
//                    }
//            }
//            }
//            break;
//        }
    }//GEN-LAST:event_miExportarPDFActionPerformed

    private void btnFiltroAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroAceptarActionPerformed

              filtro = new Filtros(this.cbCategoria.getSelectedIndex(), this.cbDivision.getSelectedIndex(),
              this.cbJornada.getSelectedIndex(), this.tCalendario);

    }//GEN-LAST:event_btnFiltroAceptarActionPerformed

    private void cbDivisionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbDivisionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbDivisionActionPerformed

    private void btnGenerarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarInicioActionPerformed

              Date fechaInicio = this.dFechaInicioTemporada.getDate();
              Date fechaFin = this.dFechaInicioTemporada.getDate();
              SimpleDateFormat formato = new SimpleDateFormat("d/MM/yyyy");
              gc.generaFechas(formato.format(fechaInicio), formato.format(fechaFin), this.tCalendario);
              
    //        gc.generaFechas(this.dFechaInicio.getText(), this.dFechaFin.getText(), this.tCalendario);

    }//GEN-LAST:event_btnGenerarInicioActionPerformed

    private void btnCalendarioSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioSalirActionPerformed
        close();            
    }//GEN-LAST:event_btnCalendarioSalirActionPerformed

    private void miImportarcalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImportarcalendarioActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Excel (*.xls; *.xlsx)", "xls", "xlsx");
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showOpenDialog(new Principal());
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();
            //Desktop.getDesktop().open(fichero);
            //aqui debe coger los datos del excel y cargarlos a la BBDD
            new Thread(new LeerExcel(fichero)).start();

        }
    }//GEN-LAST:event_miImportarcalendarioActionPerformed

    private void btnFiltroLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroLimpiarActionPerformed
        cbCategoria.setSelectedItem("Todas");
        cbDivision.setSelectedItem("Todas");
        cbJornada.setSelectedItem("Todas");
        dFechaInicoFiltro.setDate(null);
        dFechaFinFiltro.setDate(null);
        filtro = new Filtros(this.cbCategoria.getSelectedIndex(), this.cbDivision.getSelectedIndex(),
              this.cbJornada.getSelectedIndex(), this.tCalendario);
        
    }//GEN-LAST:event_btnFiltroLimpiarActionPerformed
    
    public void close() {
             dispose();             
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
            java.util.logging.Logger.getLogger(Calendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calendario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalendarioGenerar;
    private javax.swing.JButton btnCalendarioSalir;
    private javax.swing.JButton btnFiltroAceptar;
    private javax.swing.JButton btnFiltroLimpiar;
    private javax.swing.JButton btnGenerarInicio;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbDivision;
    private javax.swing.JComboBox<String> cbJornada;
    private com.toedter.calendar.JDateChooser dFechaFinFiltro;
    private com.toedter.calendar.JDateChooser dFechaFinTemporada;
    private com.toedter.calendar.JDateChooser dFechaInicioTemporada;
    private com.toedter.calendar.JDateChooser dFechaInicoFiltro;
    private javax.swing.JPanel jInicioCalendario;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JLabel lbInicioFechaInicio;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblInicioFechaFin;
    private javax.swing.JLabel lblJornada;
    private javax.swing.JMenu mExportar;
    private javax.swing.JMenuBar mbCalendario;
    private javax.swing.JMenuItem miExportarExcel;
    private javax.swing.JMenuItem miExportarPDF;
    private javax.swing.JMenuItem miImportarcalendario;
    private javax.swing.JPanel pCalendario;
    private javax.swing.JScrollPane scrollCalendario;
    private javax.swing.JTable tCalendario;
    // End of variables declaration//GEN-END:variables
}
