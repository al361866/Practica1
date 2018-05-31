package telecomunicaciones.swing.principal;

import telecomunicaciones.swing.controlador.Controlador;
import telecomunicaciones.swing.modelo.ModeloControlador;
import telecomunicaciones.swing.vista.Vista;

import javax.swing.*;

public class PrincipalSwing {

    public static void main(String args[]){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Vista vista = new Vista();
                ModeloControlador modelo = new ModeloControlador();
                Controlador controlador = new Controlador();

                vista.setControlador(controlador);
                vista.setModeloControlador(modelo);
                vista.setModeloVista(modelo);
                modelo.setVistaControlador(vista);
                modelo.setVistaModelo(vista);
                controlador.setModeloControlador(modelo);
                controlador.setVista(vista);

            }
        });
    }
}
