package telecomunicaciones.swing.vista;

import telecomunicaciones.swing.controlador.InterfazControlador;
import telecomunicaciones.swing.modelo.InterfazModeloControlador;
import telecomunicaciones.swing.modelo.InterfazModeloVista;

import javax.swing.*;
import java.awt.*;
import static java.lang.Integer.parseInt;

// todo Podéis extraer cada uno de los paneles a una clase independiente, para que el código de esta no sea tan largo.
// todo En la solapa periodo, veo los botones superpuestos a los textfields.
public class Vista implements InterfazVistaControlador, InterfazVistaModelo {

    private InterfazControlador controlador;
    private InterfazModeloControlador modeloControlador;
    private InterfazModeloVista modeloVista;
    public Vista() {
        creaInterfazVisual();
    }
    public void setControlador(InterfazControlador controlador) {
        this.controlador = controlador;
    }
    public void setModeloControlador(InterfazModeloControlador modelo) {
        this.modeloControlador = modelo;
    }
    public void setModeloVista(InterfazModeloVista modelo) {
        this.modeloVista = modelo;
    }

    private JTextArea jtaInfoClientes;
    private JLabel jlNumeroEntradasLlamadas;
    private JLabel jlNumeroEntradasClientes;
    private JLabel jlNumeroEntradasFacturas;

    //Campos Pestanya Clientes
    private JTextField jtfNIF;
    private JTextField jtfNombre;
    private JTextField jtfApellidos;
    private JTextField jtfPoblacion;
    private JTextField jtfProvincia;
    private JTextField jtfCodPostal;
    private JTextField jtfEmail;
    private JRadioButton jrbNoche;
    private JRadioButton jrbTarde;
    private JRadioButton jrbBasica;

    //Campos Pestanya Llamadas
    private JTextField jtfNIFLlamada;
    private JTextField jtfTelefonoLlamada;
    private  JTextField jtfDia;
    private  JTextField jtfMes;
    private  JTextField jtfAnyo;
    private  JTextField jtfHora;
    private  JTextField jtfDuracion;

    //Campos Pestanya Facturas
    private JTextField jtfNIFFactura;
    private JTextField jtfCodFactura;
    private  JTextField jtfDiaInicio;
    private  JTextField jtfMesInicio;
    private  JTextField jtfAnyoInicio;
    private  JTextField jtfDiaFinal;
    private  JTextField jtfMesFinal;
    private  JTextField jtfAnyoFinal;

    //Campos pestanya listados

    private JRadioButton JRBCliente;
    private JRadioButton JRBFactura;
    private JRadioButton JRBLlamada;
    private  JTextField jtfNIFListadoPeriodo;
    private  JTextField jtfDiaInicioPeriodo;
    private  JTextField jtfMesInicioPeriodo;
    private  JTextField jtfAnyoInicioPeriodo;
    private  JTextField jtfDiaFinalPeriodo;
    private  JTextField jtfMesFinalPeriodo;
    private  JTextField jtfAnyoFinalPeriodo;

    private void creaInterfazVisual(){

        //Botones archivo

        JButton cargarDatos = new JButton("Cargar Datos BBDD");
        JButton grabarDatos = new JButton("Grabar Datos en BBDD");
        JPanel jpBotonesFichero = new JPanel();
        jpBotonesFichero.setLayout(new BoxLayout(jpBotonesFichero, BoxLayout.X_AXIS));
        jpBotonesFichero.add(cargarDatos);
        jpBotonesFichero.add(grabarDatos);
        cargarDatos.addActionListener(e -> {
            controlador.cargarDatos();
            System.out.println("El boton cargarDatos ha sido pulsado.");
        });
        grabarDatos.addActionListener(e -> {
            controlador.grabarDatos();
            System.out.println("El boton grabarDatos ha sido pulsado.");
        });

        //Pestanya Clientes
        jtfNIF = new JTextField(10);
        jtfNombre = new JTextField(10);
        jtfApellidos = new JTextField(10);
        jtfEmail = new JTextField(10);
        jtfPoblacion = new JTextField(10);
        jtfProvincia = new JTextField(10);
        jtfCodPostal = new JTextField(10);
        jrbNoche = new JRadioButton();
        jrbTarde = new JRadioButton();
        jrbBasica = new JRadioButton();

        JButton jbNuevo = new JButton("Nuevo");
        JButton jbCambiarTarifa= new JButton("Cambiar Tarifa");
        JButton jbBuscar = new JButton("Buscar");
        JButton jbBorrar= new JButton("Borrar");
        JButton jbLimpar = new JButton("Limpiar");
        JButton jbListarClientes = new JButton("Listar Clientes");
        JButton jbListarFacturas = new JButton("Listar Facturas");

        JPanel jpBotonesClientes = new JPanel();
        jpBotonesClientes.setLayout(new BoxLayout(jpBotonesClientes, BoxLayout.X_AXIS));
        jpBotonesClientes.add(jbNuevo);
        jpBotonesClientes.add(jbCambiarTarifa);
        jpBotonesClientes.add(jbBorrar);
        jpBotonesClientes.add(jbBuscar);
        jpBotonesClientes.add(jbListarClientes);
        jpBotonesClientes.add(jbLimpar);

        jbListarClientes.addActionListener(e -> {
            controlador.buscaListadoClientes();
            System.out.println("El boton jbListarClientes ha sido pulsado.");
        });

        jbNuevo.addActionListener(e -> {
            controlador.nuevaEntradaCliente();
            System.out.println("El boton jbNuevo ha sido pulsado.");
        });

        jbBuscar.addActionListener(e -> {
            controlador.buscaEntradaCliente();
            System.out.println("El boton jbBuscar ha sido pulsado.");
        });

        jbLimpar.addActionListener(e -> {
            jtaInfoClientes.setText("");
            System.out.println("El boton jbLimpiar ha sido pulsado.");
        });

        jbBorrar.addActionListener(e -> {
            controlador.borraEntradaCliente();
            System.out.println("El boton jbBorrar ha sido pulsado.");
        });

        jbCambiarTarifa.addActionListener(e -> {
            controlador.cambiaTarifaCliente();
            System.out.println("El boton jbCambiarTarifa ha sido pulsado.");
        });

        JPanel jpClientes = new JPanel();
        jpClientes.setLayout(new GridLayout(10,2));
        jpClientes.add(new JLabel("NIF: "));
        jpClientes.add(jtfNIF);
        jpClientes.add(new JLabel("Nombre: "));
        jpClientes.add(jtfNombre);
        jpClientes.add(new JLabel("Apellidos: "));
        jpClientes.add(jtfApellidos);
        jpClientes.add(new JLabel("Poblacion: "));
        jpClientes.add(jtfPoblacion);
        jpClientes.add(new JLabel("Provincia: "));
        jpClientes.add(jtfProvincia);
        jpClientes.add(new JLabel("Codigo Postal: "));
        jpClientes.add(jtfCodPostal);
        jpClientes.add(new JLabel("Email: "));
        jpClientes.add(jtfEmail);
        ButtonGroup grupoBotonesRadio = new ButtonGroup();
        grupoBotonesRadio.add(jrbNoche);
        grupoBotonesRadio.add(jrbTarde);
        grupoBotonesRadio.add(jrbBasica);

        JPanel jpRadioBotton = new JPanel();
        jpRadioBotton.setLayout(new BoxLayout(jpRadioBotton, BoxLayout.X_AXIS));
        jpRadioBotton.add(new JLabel("Tarifa Noche: "));
        jpRadioBotton.add(jrbNoche);
        jpRadioBotton.add(new JLabel("Tarifa Tarde: "));
        jpRadioBotton.add(jrbTarde);
        jpRadioBotton.add(new JLabel("Tarifa Basica: "));
        jpRadioBotton.add(jrbBasica);

        jpClientes.add(jpRadioBotton);
        jlNumeroEntradasClientes = new JLabel( "  Numero clientes: 0");
        jpBotonesClientes.add(jlNumeroEntradasClientes);
        jpClientes.add(jpBotonesClientes,BorderLayout.PAGE_END);


        //Pestanya Llamadas
        jtfNIFLlamada = new JTextField(10);
        jtfTelefonoLlamada = new JTextField(10);
        jtfDia = new JTextField(10);
        jtfMes = new JTextField(10);
        jtfAnyo = new JTextField(10);
        jtfHora = new JTextField(10);
        jtfDuracion = new JTextField(10);

        JButton jbNuevaLlamada = new JButton("Nuevo");
        JButton jbBuscarLlamada = new JButton("Listar Llamadas");
        JButton jbLimparLlamada = new JButton("Limpiar");
        JPanel jpBotonesLlamadas = new JPanel();
        jpBotonesLlamadas.setLayout(new BoxLayout(jpBotonesLlamadas, BoxLayout.X_AXIS));
        jpBotonesLlamadas.add(jbNuevaLlamada);
        jpBotonesLlamadas.add(jbBuscarLlamada);
        jpBotonesLlamadas.add(jbLimparLlamada);

        jbNuevaLlamada.addActionListener(e -> {
            controlador.nuevaEntradaLlamada();
            System.out.println("El boton jbNuevaLlamada ha sido pulsado.");
        });

        jbBuscarLlamada.addActionListener(e -> {
            controlador.buscaEntradaLlamada();
            System.out.println("El boton jbBuscarLlamada ha sido pulsado.");
        });

        jbLimparLlamada.addActionListener(e -> {
            jtaInfoClientes.setText("");
            System.out.println("El boton jbLimpiarLlamada ha sido pulsado.");
        });

        JPanel jpLlamadas = new JPanel();

        jpLlamadas.add(new JLabel("NIF: "));
        jpLlamadas.add(jtfNIFLlamada);
        jpLlamadas.add(new JLabel("Telefono llamada: "));
        jpLlamadas.add(jtfTelefonoLlamada);
        jpLlamadas.add(new JLabel("Hora: "));
        jpLlamadas.add(jtfHora);
        jpLlamadas.add(new JLabel("Dia: "));
        jpLlamadas.add(jtfDia);
        jpLlamadas.add(new JLabel("Mes: "));
        jpLlamadas.add(jtfMes);
        jpLlamadas.add(new JLabel("Anyo: "));
        jpLlamadas.add(jtfAnyo);
        jpLlamadas.add(new JLabel("Duracion: "));
        jpLlamadas.add(jtfDuracion);
        jlNumeroEntradasLlamadas = new JLabel("Numero clientes con llamadas: 0");
        jpLlamadas.add(jpBotonesLlamadas,BorderLayout.PAGE_END);
        jpLlamadas.add(jlNumeroEntradasLlamadas);
        jpLlamadas.setLayout(new GridLayout(10,2));

        //Pestanya Facturacion

        jtfNIFFactura = new JTextField(10);
        jtfCodFactura = new JTextField(10);
        jtfDiaInicio = new JTextField(10);
        jtfMesInicio = new JTextField(10);
        jtfAnyoInicio = new JTextField(10);
        jtfDiaFinal = new JTextField(10);
        jtfMesFinal = new JTextField(10);
        jtfAnyoFinal = new JTextField(10);

        JButton jbNuevaFactura = new JButton("Nuevo");
        JButton jbBuscarFactura = new JButton("Buscar");
        JButton jbLimparFactura = new JButton("Limpiar");

        JPanel jpBotonesFacturas = new JPanel();
        jpBotonesFacturas.setLayout(new BoxLayout(jpBotonesFacturas, BoxLayout.X_AXIS));
        jpBotonesFacturas.add(jbNuevaFactura);
        jpBotonesFacturas.add(jbBuscarFactura);
        jpBotonesFacturas.add(jbListarFacturas);
        jpBotonesFacturas.add(jbLimparFactura);

        jbNuevaFactura.addActionListener(e -> {
            controlador.nuevaEntradaFactura();
            System.out.println("El boton jbNuevaFactura ha sido pulsado.");
        });

        jbBuscarFactura.addActionListener(e -> {
            controlador.buscaEntradaFactura();
            System.out.println("El boton jbBuscarFactura ha sido pulsado.");
        });

        jbListarFacturas.addActionListener(e -> {
            controlador.buscaListadoFacturas();
            System.out.println("El boton jbListarFacturas ha sido pulsado.");
        });

        jbLimparFactura.addActionListener(e -> {
            jtaInfoClientes.setText("");
            System.out.println("El boton jbLimpiarFactura ha sido pulsado.");
        });

        JPanel jpFacturas = new JPanel();

        jpFacturas.add(new JLabel("NIF: "));
        jpFacturas.add(jtfNIFFactura);
        jpFacturas.add(new JLabel("Codigo:  "));
        jpFacturas.add(jtfCodFactura);
        jpFacturas.add(new JLabel("Dia inicio:  "));
        jpFacturas.add(jtfDiaInicio);
        jpFacturas.add(new JLabel("Mes inicio:  "));
        jpFacturas.add(jtfMesInicio);
        jpFacturas.add(new JLabel("Anyo inicio:  "));
        jpFacturas.add(jtfAnyoInicio);
        jpFacturas.add(new JLabel("Dia final:  "));
        jpFacturas.add(jtfDiaFinal);
        jpFacturas.add(new JLabel("Mes final:  "));
        jpFacturas.add(jtfMesFinal);
        jpFacturas.add(new JLabel("Anyo final:  "));
        jpFacturas.add(jtfAnyoFinal);
        jpFacturas.add(jpBotonesFacturas,BorderLayout.PAGE_END);
        jlNumeroEntradasFacturas= new JLabel("Numero clientes con facturas: 0");
        jpFacturas.add(jlNumeroEntradasFacturas);

        jpFacturas.setLayout(new GridLayout(10,2));

        //Pestanya listados periodo

        JButton jbListar = new JButton("Listar");
        JButton jbLimparListado = new JButton("Limpiar");

        JPanel jpBotonesListados = new JPanel();
        jpBotonesListados.setLayout(new BoxLayout(jpBotonesListados, BoxLayout.LINE_AXIS));
        jpBotonesListados.add(jbListar);
        jpBotonesListados.add(jbLimparListado);

        jbListar.addActionListener(e -> {
            controlador.buscaListado();
            System.out.println("El boton jbListar ha sido pulsado.");
        });

        jbLimparListado.addActionListener(e -> {
            jtaInfoClientes.setText("");
            System.out.println("El boton jbLimpiarListado ha sido pulsado.");
        });

        JRBCliente = new JRadioButton();
        JRBLlamada = new JRadioButton();
        JRBFactura = new JRadioButton();
        jtfNIFListadoPeriodo = new JTextField(10);
        jtfAnyoInicioPeriodo = new JTextField(10);
        jtfMesInicioPeriodo = new JTextField(10);
        jtfDiaInicioPeriodo = new JTextField(10);
        jtfDiaFinalPeriodo = new JTextField(10);
        jtfMesFinalPeriodo = new JTextField(10);
        jtfAnyoFinalPeriodo = new JTextField(10);

        ButtonGroup grupoBototesRadioPeriodo = new ButtonGroup();
        grupoBototesRadioPeriodo.add(JRBCliente);
        grupoBototesRadioPeriodo.add(JRBLlamada);
        grupoBototesRadioPeriodo.add(JRBFactura);

        JPanel jpRadioBottonPeriodo = new JPanel();
        jpRadioBottonPeriodo.setLayout(new BoxLayout(jpRadioBottonPeriodo, BoxLayout.X_AXIS));
        jpRadioBottonPeriodo.add(new JLabel("Indica el tipo de datos a listar segun el periodo:    "));
        jpRadioBottonPeriodo.add(new JLabel("Listar Clientes: "));
        jpRadioBottonPeriodo.add(JRBCliente);
        jpRadioBottonPeriodo.add(new JLabel("Listar Llamadas: "));
        jpRadioBottonPeriodo.add(JRBLlamada);
        jpRadioBottonPeriodo.add(new JLabel("Listar Facturas: "));
        jpRadioBottonPeriodo.add(JRBFactura);

        JPanel jpClientesListadoPestanyas = new JPanel();
        jpClientesListadoPestanyas.add(new JLabel("NIF: "));
        jpClientesListadoPestanyas.add(jtfNIFListadoPeriodo);
        jpClientesListadoPestanyas.add(new JLabel("Dia inicio:  "));
        jpClientesListadoPestanyas.add(jtfDiaInicioPeriodo);
        jpClientesListadoPestanyas.add(new JLabel("Mes inicio:  "));
        jpClientesListadoPestanyas.add(jtfMesInicioPeriodo);
        jpClientesListadoPestanyas.add(new JLabel("Anyo inicio:  "));
        jpClientesListadoPestanyas.add(jtfAnyoInicioPeriodo);
        jpClientesListadoPestanyas.add(new JLabel("Dia final:  "));
        jpClientesListadoPestanyas.add(jtfDiaFinalPeriodo);
        jpClientesListadoPestanyas.add(new JLabel("Mes final:  "));
        jpClientesListadoPestanyas.add(jtfMesFinalPeriodo);
        jpClientesListadoPestanyas.add(new JLabel("Anyo final:  "));
        jpClientesListadoPestanyas.add(jtfAnyoFinalPeriodo);
        jpClientesListadoPestanyas.add(jpRadioBottonPeriodo,BorderLayout.NORTH);
        jpClientesListadoPestanyas.add(jpBotonesListados);
        jpClientesListadoPestanyas.setLayout(new GridLayout(10,2));

        //Anyado a pestanayas

        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Clientes", jpClientes);
        pestanyas.add("Llamadas",jpLlamadas);
        pestanyas.add("Facturas",jpFacturas);
        pestanyas.add("Listar periodos", jpClientesListadoPestanyas);

        //Panel de informacion

        jtaInfoClientes = new JTextArea(10,50);
        JScrollPane scroll = new JScrollPane(jtaInfoClientes);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Ventana

        JFrame ventana = new JFrame("Aplicación de telefonia");
        Container contenedor = ventana.getContentPane();
        contenedor.add(pestanyas, BorderLayout.NORTH);
        contenedor.add(scroll,BorderLayout.CENTER);
        contenedor.add(jpBotonesFichero,BorderLayout.SOUTH);
        ventana.pack();
        ventana.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        ventana.setVisible(true);
    }



    //Implementacion getters

    //Getters pestanya clientes
    @Override
    public String getNIF() { return jtfNIF.getText(); }

    @Override
    public String getNombre() {
        return jtfNombre.getText();
    }

    @Override
    public String getApellidos() {
        return jtfApellidos.getText();
    }

    @Override
    public String getCodPostal() { return jtfCodPostal.getText(); }

    @Override
    public String getPoblacion() { return jtfPoblacion.getText(); }

    @Override
    public String getProvincia() { return jtfProvincia.getText(); }

    @Override
    public String getEmail() { return jtfEmail.getText(); }

    @Override
    public boolean getjrbNoche(){
        return jrbNoche.isSelected();
    }

    @Override
    public boolean getjrbTarde(){  return jrbTarde.isSelected();}

    @Override
    public boolean getjrbBasica(){
        return jrbBasica.isSelected();
    }

    @Override
    public void actualizaCliente(){
        int nEntradas = modeloVista.getnClientes();
        jlNumeroEntradasClientes.setText("  Numero clientes: " + nEntradas);
    }

    @Override
    public void nuevoResultadoCliente(){jtaInfoClientes.append(modeloVista.getCliente() + "\n");}

    //Getters pestanya llamadas

    @Override
    public String getNIFLlamada() { return jtfNIFLlamada.getText(); }

    @Override
    public String getTelefonoLlamada() {
        return jtfTelefonoLlamada.getText();
    }


    @Override
    public String getHora(){
        return jtfHora.getText();
    }

    @Override
    public String getDia(){
        return jtfDia.getText();
    }

    @Override
    public String getMes(){
        return jtfMes.getText();
    }
    @Override
    public String getAnyo(){
        return jtfAnyo.getText();
    }

    @Override
    public String getDuracion(){
        return jtfDuracion.getText();
    }

    @Override
    public void nuevoResultadoLlamada(){jtaInfoClientes.append(modeloVista.getLlamada() + "\n");}

    @Override
    public void actualizaLlamada(){
        int nEntradas = modeloVista.getnLlamadas();
        jlNumeroEntradasLlamadas.setText("  Numero clientes con llamadas:: " + nEntradas);
    }

    //Getters pestanya facturacion

    @Override
    public String getNIFFactura(){
        return jtfNIFFactura.getText();
    }

    @Override
    public int getCodFactura(){
        return parseInt(jtfCodFactura.getText());
    }

    @Override
    public int getDiaInicioPeriodo(){
        return parseInt(jtfDiaInicio.getText());
    }

    @Override
    public int getMesInicioPeriodo(){
        return parseInt(jtfMesInicio.getText());
    }

    @Override
    public int getAnyoInicioPeriodo(){
        return parseInt(jtfAnyoInicio.getText());
    }

    @Override
    public int getDiaFinalPeriodo(){
        return parseInt(jtfDiaFinal.getText());
    }

    @Override
    public int getMesFinalPeriodo(){
        return parseInt(jtfMesFinal.getText());
    }

    @Override
    public int getAnyoFinalPeriodo(){
        return parseInt(jtfAnyoFinal.getText());
    }


    @Override
    public  void nuevoResultadoFactura(){
        jtaInfoClientes.append(modeloVista.getFactura() + "\n");
    }

    @Override
    public void actualizaFactura(){
        int nEntradas = modeloVista.getnFacturas();
        jlNumeroEntradasFacturas.setText("  Numero clientes con facturas: " + nEntradas);
    }

    //Getter pestanya listado periodo

    @Override
    public boolean getCliente(){
        return JRBCliente.isSelected();
    }
    @Override
    public boolean getFactura(){  return JRBFactura.isSelected();}

    @Override
    public boolean getLlamada(){
        return JRBLlamada.isSelected();
    }

    @Override
    public String getNIFListadoPeriodo(){
        return jtfNIFListadoPeriodo.getText();
    }
    @Override
    public int getAnyoInicioPeriodoListar(){
        return parseInt(jtfAnyoInicioPeriodo.getText());
    }

    @Override
    public int getMesInicioPeriodoListar(){
        return parseInt(jtfMesInicioPeriodo.getText());
    }

    @Override
    public int getDiaInicioPeriodoListar(){
        return parseInt(jtfDiaInicioPeriodo.getText());
    }

    @Override
    public int getAnyoFinalPeriodoListar(){
        return parseInt(jtfAnyoFinalPeriodo.getText());
    }

    @Override
    public int getMesFinalPeriodoListar(){
        return parseInt(jtfMesFinalPeriodo.getText());
    }

    @Override
    public int getDiaFinalPeriodoListar(){
        return parseInt(jtfDiaFinalPeriodo.getText());
    }
}