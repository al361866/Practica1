package telecomunicaciones.gestion.Main;

import es.uji.belfern.generador.GeneradorDatosINE;
import telecomunicaciones.InterfazFechas;
import telecomunicaciones.gestion.Clientes.Cliente;
import telecomunicaciones.gestion.Clientes.Direccion;
import telecomunicaciones.gestion.Clientes.factoria.Clientes.FactoriaClientes;
import telecomunicaciones.gestion.Exception.DniAlreadyExists;
import telecomunicaciones.gestion.Exception.DniNotFound;
import telecomunicaciones.gestion.Exception.FechaFueraRango;
import telecomunicaciones.gestion.Facturas.Factura;
import telecomunicaciones.gestion.Llamadas.Llamada;
import telecomunicaciones.gestion.Tarifas.Tarifa;
import telecomunicaciones.gestion.Tarifas.factoria.Tarifa.FactoriaTarifaClientes;
import telecomunicaciones.swing.modelo.GestorClientes;
import telecomunicaciones.swing.modelo.GestorFacturas;
import telecomunicaciones.swing.modelo.GestorLlamadas;

import java.io.*;
import java.util.*;

public class Main {

    GestorClientes listaClientes = new GestorClientes();
    GestorLlamadas listaLlamadas = new GestorLlamadas();
    GestorFacturas listaFacturas = new GestorFacturas();

    public Main(){
        super();
    }

    private void ejecutar(){

        byte opcion;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Recuperando datos de fichero.....");

        try{
            FileInputStream fis = new FileInputStream("listasapp.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            listaClientes = (GestorClientes)ois.readObject();
            listaFacturas = (GestorFacturas) ois.readObject();
            listaLlamadas = (GestorLlamadas) ois.readObject();
            ois.close();
        }catch (IOException e){
            System.out.println("Aun no existe fichero");
        }catch (ClassNotFoundException e){
            e.getStackTrace();

        }

        do {
            System.out.println(OpcionesMenu.getMenu());
            System.out.print("Elije una opción:");
            opcion = scanner.nextByte();
            OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);




            switch (opcionMenu) {

                case DAR_ALTA_CLIENTE:
                    darAlta(listaClientes);
                    break;

                case DAR_BAJA_CLIENTE:

                    darBaja(listaClientes);
                    break;

                case CAMBIAR_TARIFA_CLIENTE:

                    cambiarTarifa(listaClientes);
                    break;

                case RECUPERAR_DATOS_CLIENTE:
                    recuperaDatos(listaClientes);
                    break;

                case RECUPERAR_LISTADO_CLIENTES:

                    recuperarlistadoClientes(listaClientes);
                    break;

                case DAR_ALTA_LLAMADA:

                    darAltaLlamada(listaLlamadas,listaClientes);
                    break;

                case LISTAR_LLAMADAS:

                    listaLlamadas(listaLlamadas);
                    break;

                case EMITIR_FACTURA:

                    emiteFactura(listaFacturas,listaClientes);
                    break;

                case RECUPERAR_DATOS_FACTURA:

                    recuperaDatosFact(listaFacturas);
                    break;

                case RECUPERAR_FACTURAS:

                   recuperarFact(listaFacturas);
                    break;

                case MOSTRAR_CLIENTES_ALTA_PERIODO:

                   muestreolistadoClientesFechas(listaClientes);
                    break;

                case MOSTRAR_LLAMADAS_CLIENTE_PERIODO:
                    muestreoClientePeriodoLlamadas(listaLlamadas);
                    break;

                case MOSTRAR_FACTURAS_CLIENTE_PERIODO:
                    muestreoClientePeriodoFactura(listaFacturas);
                    break;

                case SALIR:

                    grabarDatosFichero(listaClientes,listaLlamadas,listaFacturas);

                    System.out.println("Adios!");
                    break;

            }
            System.out.println("\n");
            System.out.println("\n");
            System.out.println("\n");
        }while (opcion != 13);
    }





    public void grabarDatosFichero(GestorClientes listaClientes, GestorLlamadas listaLlamadas, GestorFacturas listaFacturas){
        try {
            FileOutputStream fos = new FileOutputStream("listasapp.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(listaClientes);
            oos.writeObject(listaFacturas);
            oos.writeObject(listaLlamadas);
            oos.close();
        }catch (IOException e){
            e.getStackTrace();
        }
    }

    public void darAlta(GestorClientes mapaClientes){

        Scanner scanner = new Scanner(System.in);
        System.out.print("Anyadir particular(p) o empresa(e) ?");
        String tipo = scanner.next();
        switch (tipo) {
            case "p": {

                anyadirParticular(mapaClientes);
                break;
            }

            case "e": {

                anyadirEmpresa(mapaClientes);
                break;
            }

            default:

                System.out.println("Error, debes indicarme si desas anyadir una empresa o un particular.");
                break;
        }
    }

    public void darBaja(GestorClientes mapaClientes){

        try {

            System.out.println("Introduce nif del cliente a borrar: ");
            Scanner scanner = new Scanner(System.in);
            String bajaNif = scanner.next();
            mapaClientes.borrarmapaClientes(bajaNif);
            System.out.println("Cliente borrado");

        }catch (DniNotFound e){
            e.getStackTrace();
        }
    }

    public void cambiarTarifa(GestorClientes mapaClientes){
        FactoriaTarifaClientes factoriaTarifaClientes = new FactoriaTarifaClientes();
        System.out.println("Introduce el nif del cliente del que quieres cambiar la tarifa: ");
        Scanner scanner = new Scanner(System.in);
        String client = scanner.next();
        Tarifa anterior = mapaClientes.getmapaClientes().get(client).getTarifa();
        System.out.println("Que tipo de tarifa deseas?  T(tardes reducidas),  N(noches reducidas), B(tarifa base sin reducciones.)");
        String tipo = scanner.next();
        mapaClientes.getmapaClientes().get(client).setTarifa(factoriaTarifaClientes.getBase());
        mapaClientes.getmapaClientes().get(client).elegirTarifa(tipo);

        System.out.println("Cambiando tarifa actual " + anterior.toString() + " por " + mapaClientes.getmapaClientes().get(client).getTarifa());
    }

    public void recuperaDatos(GestorClientes mapaClientes){

        try {
            System.out.println("Introduce el nif del cliente del que quieres recuperar los datos: ");
            Scanner scanner = new Scanner(System.in);
            String client = scanner.next();

            System.out.println("---------->Recuperando datos");
            System.out.println(mapaClientes.recuperarDatos(client));

        }catch (DniNotFound e){
            e.getStackTrace();
        }
    }

    public void recuperarlistadoClientes(GestorClientes mapaClientes){

        System.out.println("Recuperando la lista de clientes...");
        for (String key : mapaClientes.getmapaClientes().keySet()) {
            Cliente cliente = mapaClientes.getmapaClientes().get(key);
            System.out.println(cliente);
        }
    }

    public void darAltaLlamada(GestorLlamadas mapaLlamadas, GestorClientes mapaClientes){
        System.out.println("Introduce el nif del cliente del que quieres dar de alta una llamada: ");
        Scanner scanner = new Scanner(System.in);
        String nif = scanner.next();

        System.out.println("Introduce el numero de telefono al que has llamado: ");
        int llamada = scanner.nextInt();
        System.out.println("Introduce la duracion de la llamada realizada: ");
        double duracion = scanner.nextDouble();
        System.out.println("Introduce la hora de la llamada realizada: ");
        int hora = scanner.nextInt();

        System.out.println("Introduce la fecha de la llamada: ");
        Date date = introducirFecha();
        System.out.println("---------->Añadiendo llamada");

        Cliente cliente = mapaClientes.getmapaClientes().get(nif);
        mapaLlamadas.altaLlamada(cliente,nif,llamada,date,hora,duracion);
    }

    public void listaLlamadas(GestorLlamadas mapaLlamadas){
        System.out.println("Introduce el nif del cliente del que quieres obtener las llamadas: ");
        Scanner scanner = new Scanner(System.in);
        String nif = scanner.next();

        List<Llamada> llamadasCliente = mapaLlamadas.getListaTotalLlamadas().get(nif);
        for (Llamada llamada : llamadasCliente) {
            System.out.println(llamada);
        }
    }

    public void emiteFactura(GestorFacturas mapaFacturas, GestorClientes mapaClientes){

        try {

            System.out.println("Introduce el nif del cliente del que quieres emitir la factura: ");
            Scanner scanner = new Scanner(System.in);
            String nif= scanner.next();

            System.out.println("Introduce el codigo de factura: ");
            int cod = scanner.nextInt();

            System.out.println("Introduce la fecha de inicio del periodo de facturacion: ");
            Date fechaIni = introducirFecha();

            System.out.println("Introduce la fecha de final del periodo de facturacion: ");
            Date fechaFin = introducirFecha();
            Date date = new Date();
            date.getTime();

            Cliente cliente = mapaClientes.getmapaClientes().get(nif);
            mapaFacturas.emitirFactura(cliente,cod,date,fechaIni,fechaFin);

        }catch (FechaFueraRango e){
            e.printStackTrace();

        }
    }

    public void recuperaDatosFact(GestorFacturas mapaFacturas){

        System.out.println("Introduce el nif del cliente a recuperar alguna factura: ");
        Scanner scanner = new Scanner(System.in);
        String nif = scanner.next();

        System.out.println("Introduce el codigo de la factura a obtener: ");
        scanner = new Scanner(System.in);
        int cod = scanner.nextInt();

        System.out.println("---------->Recuperando datos factura");
        System.out.println(mapaFacturas.getListaFacturasTotal().get(nif).get(cod));
    }

    public void recuperarFact(GestorFacturas mapaFacturas){

        System.out.println("Introduce el nif del cliente para recuperar las facturas: ");
        Scanner scanner = new Scanner(System.in);
        String nif = scanner.next();

        HashMap<Integer,Factura> mapaFacturasCliente = mapaFacturas.getListaFacturasTotal().get(nif);

        for (Integer cod : mapaFacturasCliente.keySet()) {
            Factura factura = mapaFacturasCliente.get(cod);
            System.out.println(factura);
        }
    }

    public void muestreolistadoClientesFechas(GestorClientes mapaClientes){
        try {

            HashSet<Cliente> conjuntoCli = new HashSet<>(mapaClientes.getmapaClientes().values());

            System.out.println("Introduce la fecha de inicio del periodo: ");
            Date fechaIni = introducirFecha();

            System.out.println("Introduce la fecha de final del periodo: ");
            Date fechaFin = introducirFecha();

            HashSet<Cliente> retorno = muestraPeriodo(conjuntoCli,fechaIni,fechaFin);

            for (Cliente aRetorno : retorno) {
                System.out.println(aRetorno.toString());
            }

        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
    }

    public void muestreoClientePeriodoLlamadas(GestorLlamadas mapaLlamadas){

        try {

            System.out.println("Introduce el nif del cliente para recuperar las llamadas: ");
            Scanner scanner = new Scanner(System.in);
            String nif = scanner.next();

            List<Llamada> value = mapaLlamadas.getListaTotalLlamadas().get(nif);
            HashSet<Llamada> conjuntoLlamadas = new HashSet<>(value);

            System.out.println("Introduce la fecha de inicio del periodo: ");
            Date fechaIni = introducirFecha();

            System.out.println("Introduce la fecha de final del periodo: ");
            Date fechaFin = introducirFecha();

            HashSet<Llamada> llamadasPer = muestraPeriodo(conjuntoLlamadas,fechaIni,fechaFin);

            for (Llamada aLlamadasPer : llamadasPer) {
                System.out.println(aLlamadasPer.toString());
            }

        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
    }

    public void muestreoClientePeriodoFactura(GestorFacturas mapaFacturas){

        try {

            System.out.println("Introduce el nif del cliente para recuperar las facturas: ");
            Scanner scanner = new Scanner(System.in);
            String cliente = scanner.next();

            HashMap<Integer,Factura> mapaFacturasTotal = mapaFacturas.getListaFacturasTotal().get(cliente);
            HashSet<Factura> conjuntoFacturas = new HashSet<>(mapaFacturasTotal.values());

            System.out.println("Introduce la fecha de inicio del periodo: ");
            Date fechaIni = introducirFecha();

            System.out.println("Introduce la fecha de final del periodo: ");
            Date fechaFin = introducirFecha();

            HashSet<Factura> conjuntoFacturasPeriodo = muestraPeriodo(conjuntoFacturas,fechaIni,fechaFin);

            for (Factura aConjuntoFacturasPeriodo : conjuntoFacturasPeriodo) {
                System.out.println(aConjuntoFacturasPeriodo.toString());
            }

        }catch (FechaFueraRango e){
            e.printStackTrace();
        }
    }

    public Date introducirFecha(){

        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduce el dia: ");
        int dia = scanner.nextInt();

        System.out.println("Introduce el mes: ");
        int mes = scanner.nextInt();

        System.out.println("Introduce el anyo: ");
        int anyo = scanner.nextInt();

        return new Date(anyo, mes, dia);
    }

    //Metodos case darALta

    public void anyadirParticular(GestorClientes mapaClientes){
        try {

            GeneradorDatosINE datosINE = new GeneradorDatosINE();
            String nif, nombre, tipo;
            Random codpos = new Random();
            System.out.println("Introduce el nif del cliente: ");
            Scanner scanner = new Scanner(System.in);
            nif = scanner.next();

            System.out.println("Introduce el nombre del cliente: ");
            scanner = new Scanner(System.in);
            nombre = scanner.next();

            Direccion direccionCliente = new Direccion(datosINE.getProvincia(), datosINE.getPoblacion(datosINE.getProvincia()), codpos.nextInt(10000));
            String email = nombre + "@uji.es";
            FactoriaClientes factoria = new FactoriaClientes();
            Cliente particular = factoria.getParticular(nif, nombre, datosINE.getApellido(), email, direccionCliente);
            System.out.println("Introduce la tarifa que desees: Tardes reducidas(T) , Noches reducidas(N)");
            tipo = scanner.next();
            particular.elegirTarifa(tipo);
            System.out.println(particular.getTarifa());


            System.out.println("Introduce la fecha de alta: ");
            Date date = introducirFecha();
            particular.setFecha(date);
            mapaClientes.anyadirmapaClientes(nif, particular);
            System.out.println("Particular: " + nombre + " anyadido con exito.");
        }catch (DniAlreadyExists e){
            e.printStackTrace();
        }
    }

    public void anyadirEmpresa(GestorClientes mapaClientes){
        try {
            GeneradorDatosINE datosINE = new GeneradorDatosINE();
            String nif, nombre, tipo;
            Random codpos = new Random();
            System.out.println("Introduce el cif de la empresa: ");
            Scanner scanner = new Scanner(System.in);
            nif = scanner.next();

            System.out.println("Introduce el nombre de la empresa: ");
            scanner = new Scanner(System.in);
            nombre = scanner.next();

            Direccion direccionCliente = new Direccion(datosINE.getProvincia(), datosINE.getPoblacion(datosINE.getProvincia()), codpos.nextInt(10000));

            FactoriaClientes factoriaEmpresa = new FactoriaClientes();
            Cliente empresa = factoriaEmpresa.getEmpresa(nif, nombre, "empresa@uji.es", direccionCliente);

            System.out.println("Introduce la tarifa que desees: Tardes reducidas(T) , Noches reducidas(N)");
            tipo = scanner.next();
            empresa.elegirTarifa(tipo);

            System.out.println("Introduce la fecha de alta: ");
            Date date = introducirFecha();
            empresa.setFecha(date);

            mapaClientes.anyadirmapaClientes(nif, empresa);
            System.out.println("Empresa: " + nombre + " anyadida con exito.");
        }catch (DniAlreadyExists e){
            e.printStackTrace();
        }
    }

    public <T extends InterfazFechas>HashSet<T> muestraPeriodo(HashSet<T> conjunto, Date fechaInicio, Date fechaFin)throws FechaFueraRango{

        if(fechaInicio.compareTo(fechaFin)>0) throw new FechaFueraRango();

        HashSet<T> muestreo = new HashSet<>();
        for (T nuevo : conjunto) {
            Date fechaActual = nuevo.getFecha();

            if (fechaActual.compareTo(fechaInicio) >= 0 && fechaActual.compareTo(fechaFin) <= 0) {
                muestreo.add(nuevo);
            }
        }
        return muestreo;
    }
    public static void main(String[] args) {

        new Main().ejecutar();
    }
}