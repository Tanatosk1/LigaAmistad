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
        Image icon = Toolkit.getDefaultToolkit().getImage("logo.png");
        setIconImage(icon);       
        fondo = new FondoVentana();
        this.add(fondo);
        
        tCalendario.getColumnModel().getColumn(0).setPreferredWidth(50);
        tCalendario.getColumnModel().getColumn(1).setPreferredWidth(75);
        tCalendario.getColumnModel().getColumn(2).setPreferredWidth(120);
        tCalendario.getColumnModel().getColumn(3).setPreferredWidth(120);
        tCalendario.getColumnModel().getColumn(4).setPreferredWidth(120);
        tCalendario.getColumnModel().getColumn(5).setPreferredWidth(325);
        tCalendario.getColumnModel().getColumn(6).setPreferredWidth(325);
        tCalendario.getColumnModel().getColumn(7).setPreferredWidth(325);
        tCalendario.getColumnModel().getColumn(8).setPreferredWidth(300);
        tCalendario.getColumnModel().getColumn(9).setPreferredWidth(120);
        
        md.llenarComboCategorias(this.cbCategoria);
        md.llenarComboDivisiones(this.cbDivision);
        md.llenarComboJornadas(this.cbJornada);
        md.llenarComboJornadas(this.cbGenerarJornada);
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

        pInicioCalendario = new javax.swing.JPanel();
        lbInicioFechaInicio = new javax.swing.JLabel();
        dFechaInicioTemporada = new com.toedter.calendar.JDateChooser();
        lblInicioFechaFin = new javax.swing.JLabel();
        dFechaFinTemporada = new com.toedter.calendar.JDateChooser();
        btnGenerarInicio = new javax.swing.JButton();
        cbGenerarJornada = new javax.swing.JComboBox<>();
        lJoranda = new javax.swing.JLabel();
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
        mbCalendario = new javax.swing.JMenuBar();
        mImportar = new javax.swing.JMenu();
        miImportarcalendario = new javax.swing.JMenuItem();
        mExportar = new javax.swing.JMenu();
        miExportarExcel = new javax.swing.JMenuItem();
        miExportarPDF = new javax.swing.JMenuItem();
        mGestionarRestricciones = new javax.swing.JMenu();
        miGestionarFestivos = new javax.swing.JMenuItem();
        miGestionarAplazados = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Generación de Calendario");
        setMinimumSize(new java.awt.Dimension(1366, 768));
        setSize(new java.awt.Dimension(1366, 768));

        pInicioCalendario.setBackground(new java.awt.Color(255, 255, 255));
        pInicioCalendario.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 4, true), "  FECHA DE INICIO DE JORNADA", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12)), "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 18), new java.awt.Color(31, 87, 12))); // NOI18N
        pInicioCalendario.setPreferredSize(new java.awt.Dimension(978, 67));

        lbInicioFechaInicio.setBackground(new java.awt.Color(255, 255, 255));
        lbInicioFechaInicio.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lbInicioFechaInicio.setText("Fecha de Inicio");

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

        cbGenerarJornada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbGenerarJornada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione una Jornada" }));

        lJoranda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lJoranda.setText("Jornada");

        javax.swing.GroupLayout pInicioCalendarioLayout = new javax.swing.GroupLayout(pInicioCalendario);
        pInicioCalendario.setLayout(pInicioCalendarioLayout);
        pInicioCalendarioLayout.setHorizontalGroup(
            pInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pInicioCalendarioLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbInicioFechaInicio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dFechaInicioTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblInicioFechaFin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dFechaFinTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lJoranda)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbGenerarJornada, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnGenerarInicio, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pInicioCalendarioLayout.setVerticalGroup(
            pInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pInicioCalendarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dFechaFinTemporada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbInicioFechaInicio)
                        .addComponent(btnGenerarInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbGenerarJornada, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lJoranda))
                    .addGroup(pInicioCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
        cbCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbCategoriaActionPerformed(evt);
            }
        });

        lblDivision.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDivision.setText("División");

        cbDivision.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbDivision.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));

        lblJornada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblJornada.setText("Jornada");

        cbJornada.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        cbJornada.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todas" }));
        cbJornada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbJornadaActionPerformed(evt);
            }
        });

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
        tCalendario.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
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

        mbCalendario.setBackground(new java.awt.Color(31, 87, 12));
        mbCalendario.setForeground(new java.awt.Color(255, 255, 255));

        mImportar.setForeground(new java.awt.Color(255, 255, 255));
        mImportar.setText("Importar");

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
        mImportar.add(miImportarcalendario);

        mbCalendario.add(mImportar);

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

        mGestionarRestricciones.setForeground(new java.awt.Color(255, 255, 255));
        mGestionarRestricciones.setText("Gestión");

        miGestionarFestivos.setBackground(new java.awt.Color(31, 87, 12));
        miGestionarFestivos.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        miGestionarFestivos.setForeground(new java.awt.Color(255, 255, 255));
        miGestionarFestivos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/configuracion.png"))); // NOI18N
        miGestionarFestivos.setText(" Gestionar días festivos ");
        miGestionarFestivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGestionarFestivosActionPerformed(evt);
            }
        });
        mGestionarRestricciones.add(miGestionarFestivos);

        miGestionarAplazados.setBackground(new java.awt.Color(31, 87, 12));
        miGestionarAplazados.setFont(new java.awt.Font("Franklin Gothic Medium", 0, 24)); // NOI18N
        miGestionarAplazados.setForeground(new java.awt.Color(255, 255, 255));
        miGestionarAplazados.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/configuracion.png"))); // NOI18N
        miGestionarAplazados.setText(" Gestionar partidos aplazados");
        miGestionarAplazados.setEnabled(false);
        miGestionarAplazados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miGestionarAplazadosActionPerformed(evt);
            }
        });
        mGestionarRestricciones.add(miGestionarAplazados);

        mbCalendario.add(mGestionarRestricciones);

        setJMenuBar(mbCalendario);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollCalendario)
                    .addComponent(pCalendario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                    .addComponent(pInicioCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnCalendarioGenerar, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pInicioCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(scrollCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, 513, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btnCalendarioGenerar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCalendarioGenerarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioGenerarActionPerformed
        ImageIcon icon = new ImageIcon(getClass().getResource("/resources/guardar.png"));
        int input = JOptionPane.showConfirmDialog(null, "¿Desea guardar los cambios realizados en el calendario?", "Guardar cambios", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon);
        if (input == JOptionPane.YES_OPTION) {
              gc.guardarCalendario(this.tCalendario, this.cbGenerarJornada);           
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
            cd.crearExcel(fichero.getAbsolutePath()+"."+filter.getExtensions()[0], this.tCalendario);
        }
  
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
    }//GEN-LAST:event_miExportarPDFActionPerformed

    private void btnFiltroAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltroAceptarActionPerformed

        filtro = new Filtros(this.cbCategoria.getSelectedIndex(), this.cbDivision.getSelectedIndex(),
        this.cbJornada.getSelectedIndex(), this.tCalendario);
        miGestionarAplazados.setEnabled(true); 

    }//GEN-LAST:event_btnFiltroAceptarActionPerformed

    private void btnGenerarInicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarInicioActionPerformed
        if(dFechaInicioTemporada.getDate()==null){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fecha de inicio","Seleccione una  fecha de inicio", JOptionPane.QUESTION_MESSAGE, icon);
        
        }if(dFechaFinTemporada.getDate()==null){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una fecha de fin","Seleccione una  fecha de fin", JOptionPane.QUESTION_MESSAGE, icon);
        
        }if (cbGenerarJornada.getSelectedItem().toString().equals("Seleccione una Jornada")){
            ImageIcon icon = new ImageIcon(getClass().getResource("/resources/warning.png"));
            JOptionPane.showMessageDialog(rootPane, "Debe seleccionar una jornada","Seleccione una jornada", JOptionPane.QUESTION_MESSAGE, icon);
        
        }else{
              Date fechaInicio = this.dFechaInicioTemporada.getDate();
              Date fechaFin = this.dFechaFinTemporada.getDate();
              SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
              gc.generaFechas(formato.format(fechaInicio), formato.format(fechaFin), this.tCalendario, this.cbGenerarJornada);
        }      
 
    }//GEN-LAST:event_btnGenerarInicioActionPerformed

    private void miImportarcalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miImportarcalendarioActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo de Excel (*.xlsx)","xlsx");
        fileChooser.setFileFilter(filtro);
        int seleccion = fileChooser.showOpenDialog(new Principal());
        if (seleccion == JFileChooser.APPROVE_OPTION)
        {
            File fichero = fileChooser.getSelectedFile();
            new Thread(new LeerExcel(fichero, this.tCalendario, this.cbJornada, this.cbGenerarJornada)).start();
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

    private void cbCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbCategoriaActionPerformed

    private void cbJornadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbJornadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbJornadaActionPerformed

    private void miGestionarFestivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGestionarFestivosActionPerformed
        Inactividad  frm = new Inactividad ();
        frm.setVisible(true);
    }//GEN-LAST:event_miGestionarFestivosActionPerformed

    private void miGestionarAplazadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miGestionarAplazadosActionPerformed
        Aplazados  frm = new Aplazados ();
        frm.setVisible(true);
    }//GEN-LAST:event_miGestionarAplazadosActionPerformed
    
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
    private javax.swing.JButton btnFiltroAceptar;
    private javax.swing.JButton btnFiltroLimpiar;
    private javax.swing.JButton btnGenerarInicio;
    private javax.swing.JComboBox<String> cbCategoria;
    private javax.swing.JComboBox<String> cbDivision;
    private javax.swing.JComboBox<String> cbGenerarJornada;
    private javax.swing.JComboBox<String> cbJornada;
    private com.toedter.calendar.JDateChooser dFechaFinFiltro;
    private com.toedter.calendar.JDateChooser dFechaFinTemporada;
    private com.toedter.calendar.JDateChooser dFechaInicioTemporada;
    private com.toedter.calendar.JDateChooser dFechaInicoFiltro;
    private javax.swing.JLabel lJoranda;
    private javax.swing.JLabel lbInicioFechaInicio;
    private javax.swing.JLabel lblCategoria;
    private javax.swing.JLabel lblDivision;
    private javax.swing.JLabel lblFechaFin;
    private javax.swing.JLabel lblFechaInicio;
    private javax.swing.JLabel lblInicioFechaFin;
    private javax.swing.JLabel lblJornada;
    private javax.swing.JMenu mExportar;
    private javax.swing.JMenu mGestionarRestricciones;
    private javax.swing.JMenu mImportar;
    private javax.swing.JMenuBar mbCalendario;
    private javax.swing.JMenuItem miExportarExcel;
    private javax.swing.JMenuItem miExportarPDF;
    private javax.swing.JMenuItem miGestionarAplazados;
    private javax.swing.JMenuItem miGestionarFestivos;
    private javax.swing.JMenuItem miImportarcalendario;
    private javax.swing.JPanel pCalendario;
    private javax.swing.JPanel pInicioCalendario;
    private javax.swing.JScrollPane scrollCalendario;
    private javax.swing.JTable tCalendario;
    // End of variables declaration//GEN-END:variables
}
