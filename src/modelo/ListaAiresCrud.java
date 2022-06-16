/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Excepciones.ExcepcionEscritura;
import Excepciones.ExcepcionLectura;
import java.util.ArrayList;
import java.util.List;
import persistencia.IAiresCrud;
import persistencia.ImpArchivoTexto;

/**
 *
 * @author Jairo F
 */
public class ListaAiresCrud implements IAiresCrud {
    IAiresCrud datos = new ImpArchivoTexto();

    @Override
    public void registrarAire(AireAcondicionado a) throws ExcepcionEscritura {
        this.datos.registrarAire(a);
    }

    @Override
    public List<AireAcondicionado> leer() throws ExcepcionLectura {
        List<AireAcondicionado> lista = new ArrayList(this.datos.leer());
        return lista;
    }

    @Override
    public AireAcondicionado buscar(AireAcondicionado a) throws ExcepcionLectura {
        return this.datos.buscar(a);
    }

    @Override
    public AireAcondicionado eliminar(AireAcondicionado a) throws ExcepcionEscritura, ExcepcionLectura {
        return this.datos.eliminar(a);
    }
}
