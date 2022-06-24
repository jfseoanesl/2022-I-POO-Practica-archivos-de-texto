/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package persistencia;

import Excepciones.ExcepcionArchivo;
import Excepciones.ExcepcionEscritura;
import Excepciones.ExcepcionLectura;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.AireAcondicionado;

/**
 *
 * @author Jairo F
 */
public class ImpArchivoTexto implements IAiresCrud {

    private File archivo;
    private FileWriter modoEscritura;
    private Scanner modoLectura;

    public ImpArchivoTexto() {
        this("Aires.inv");
    }

    public ImpArchivoTexto(String path) {
        this.archivo = new File(path);
    }

    @Override
    public void registrarAire(AireAcondicionado a) throws ExcepcionArchivo {
        PrintWriter pw = null;
        try {

            this.modoEscritura = new FileWriter(this.archivo, true);
            pw = new PrintWriter(this.modoEscritura);
            pw.println(a.getDataFileFormat());

        } catch (IOException e) {
            throw new ExcepcionArchivo("Error al abrir o crear archivo en modo escritura");
        } finally {
            if (pw != null) {
                pw.close();
            }
        }
    }
    
    private AireAcondicionado cargar(String data[]){
        
        int serial = Integer.valueOf(data[0]);
        String marca = data[1];
        int caballos = Integer.valueOf(data[2]);
        double temp = Double.valueOf(data[3]);
        
        return new AireAcondicionado(serial, marca, caballos, temp);
    }

    @Override
    public List<AireAcondicionado> leer() throws ExcepcionArchivo {
        List<AireAcondicionado> lista;
        try {
            this.modoLectura = new Scanner(this.archivo);
            lista = new ArrayList();
            while(this.modoLectura.hasNext()){
                String[] data = this.modoLectura.nextLine().split(";");
                AireAcondicionado a = this.cargar(data);
                lista.add(a);
            }
            return lista;
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Error al leer archivo en modo lectura");
           
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }

    }

    @Override
    public AireAcondicionado buscar(AireAcondicionado a) throws ExcepcionArchivo {
        AireAcondicionado buscado = null;
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String[] data = this.modoLectura.nextLine().split(";");
                buscado = this.cargar(data);
                if(buscado.getnSerial()==a.getnSerial()){
                    return buscado;
                }    
            }
            return null;
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Error al buscar archivo en modo lectura");
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }
    
    private void renombrarArchivo(File tmp) throws IOException{
        if(!tmp.exists())
            tmp.createNewFile();
        
        if(!this.archivo.delete())
            throw new IOException("Error al eliminar el archivo principal");
        
        if(!tmp.renameTo(this.archivo))
            throw new IOException("Error al renombrar el archivo tmp");
        
    }

    @Override
    public AireAcondicionado eliminar(AireAcondicionado a) throws ExcepcionArchivo{
        AireAcondicionado eliminado = null;
        ImpArchivoTexto archivoTemporal = new ImpArchivoTexto("Aires.tmp");
        try {
            this.modoLectura = new Scanner(this.archivo);
            while(this.modoLectura.hasNext()){
                String[] data = this.modoLectura.nextLine().split(";");
                AireAcondicionado aux = this.cargar(data);
                if(aux.getnSerial()==a.getnSerial()){
                    eliminado=aux;
                }    
                else{
                   archivoTemporal.registrarAire(aux);
                }
            }
            this.modoLectura.close();
            this.renombrarArchivo(archivoTemporal.archivo);
            return eliminado;
        } catch (FileNotFoundException e) {
            throw new ExcepcionArchivo("Error al buscar archivo en modo lectura");
        }
        catch(IOException e){
            throw new ExcepcionArchivo(e.getMessage());
        }
        finally{
            if(this.modoLectura!=null)
                this.modoLectura.close();
        }
    }

    /**
     * @return the archivo
     */
    public File getArchivo() {
        return archivo;
    }

    /**
     * @param archivo the archivo to set
     */
    public void setArchivo(File archivo) {
        this.archivo = archivo;
    }

    /**
     * @return the modoEscritura
     */
    public FileWriter getModoEscritura() {
        return modoEscritura;
    }

    /**
     * @param modoEscritura the modoEscritura to set
     */
    public void setModoEscritura(FileWriter modoEscritura) {
        this.modoEscritura = modoEscritura;
    }

    /**
     * @return the modoLectura
     */
    public Scanner getModoLectura() {
        return modoLectura;
    }

    /**
     * @param modoLectura the modoLectura to set
     */
    public void setModoLectura(Scanner modoLectura) {
        this.modoLectura = modoLectura;
    }

    @Override
    public List<AireAcondicionado> filtrar(int serial) throws ExcepcionArchivo {
         List<AireAcondicionado> lista = this.leer();
         List<AireAcondicionado> listaFiltrada = new ArrayList();
         for(AireAcondicionado a: lista){
             String serialLista = String.valueOf(a.getnSerial());
             String serialFiltrada = String.valueOf(serial);
             if(serialLista.contains(serialFiltrada)){
                 listaFiltrada.add(a);
             }
         }
         return listaFiltrada; 
    }
    
    

}
