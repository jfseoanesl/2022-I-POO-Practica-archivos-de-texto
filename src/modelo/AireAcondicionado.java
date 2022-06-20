/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.io.Serializable;

/**
 *
 * @author Jairo F
 */
public class AireAcondicionado implements Serializable{
    private int nSerial;
    private String marca;
    private int nCaballos;
    private double tmpMinima;

    public AireAcondicionado() {
    }

    public AireAcondicionado(int nSerial) {
        this.nSerial = nSerial;
    }

    public AireAcondicionado(int nSerial, String marca, int nCaballos, double tmpMinima) {
        this.nSerial = nSerial;
        this.marca = marca;
        this.nCaballos = nCaballos;
        this.tmpMinima = tmpMinima;
    }

    /**
     * @return the nSerial
     */
    public int getnSerial() {
        return nSerial;
    }

    /**
     * @param nSerial the nSerial to set
     */
    public void setnSerial(int nSerial) {
        this.nSerial = nSerial;
    }

    /**
     * @return the marca
     */
    public String getMarca() {
        return marca;
    }

    /**
     * @param marca the marca to set
     */
    public void setMarca(String marca) {
        this.marca = marca;
    }

    /**
     * @return the nCaballos
     */
    public int getnCaballos() {
        return nCaballos;
    }

    /**
     * @param nCaballos the nCaballos to set
     */
    public void setnCaballos(int nCaballos) {
        this.nCaballos = nCaballos;
    }

    /**
     * @return the tmpMinima
     */
    public double getTmpMinima() {
        return tmpMinima;
    }

    /**
     * @param tmpMinima the tmpMinima to set
     */
    public void setTmpMinima(double tmpMinima) {
        this.tmpMinima = tmpMinima;
    }

    @Override
    public String toString() {
        return "AireAcondicionado{" + "nSerial=" + nSerial + ", marca=" + marca + ", nCaballos=" + nCaballos + ", tmpMinima=" + tmpMinima + '}';
    }
    
    public String getDataFileFormat(){
        return nSerial + ";" + marca + ";" + nCaballos + ";" + tmpMinima ;
    }
    
}
