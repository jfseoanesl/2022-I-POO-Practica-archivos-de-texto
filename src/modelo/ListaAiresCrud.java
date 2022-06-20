/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Excepciones.ExcepcionArchivo;
import java.util.ArrayList;
import java.util.List;
import persistencia.IAiresCrud;
import persistencia.ImpArchivoObjeto;
import persistencia.ImpArchivoTexto;

/**
 *
 * @author Jairo F
 */
public class ListaAiresCrud implements IAiresCrud {
    private IAiresCrud datos = new ImpArchivoTexto();
    //private IAiresCrud datos = new ImpArchivoObjeto();
        
    @Override
    public void registrarAire(AireAcondicionado a) throws ExcepcionArchivo {
        this.datos.registrarAire(a);
    }

    @Override
    public List<AireAcondicionado> leer() throws ExcepcionArchivo {
        List<AireAcondicionado> lista = new ArrayList(this.datos.leer());
        return lista;
    }

    @Override
    public AireAcondicionado buscar(AireAcondicionado a) throws ExcepcionArchivo {
        return this.datos.buscar(a);
    }

    @Override
    public AireAcondicionado eliminar(AireAcondicionado a) throws ExcepcionArchivo {
        return this.datos.eliminar(a);
    }
}
