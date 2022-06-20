/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import Excepciones.ExcepcionArchivo;
import Excepciones.ExcepcionEscritura;
import Excepciones.ExcepcionLectura;
import java.util.List;
import modelo.AireAcondicionado;

/**
 *
 * @author Jairo F
 */
public interface IAiresCrud {
    void registrarAire(AireAcondicionado a) throws ExcepcionArchivo;
    List<AireAcondicionado> leer() throws ExcepcionArchivo;
    AireAcondicionado buscar(AireAcondicionado a)throws ExcepcionArchivo;
    AireAcondicionado eliminar(AireAcondicionado a)throws ExcepcionArchivo;
    
    
}
