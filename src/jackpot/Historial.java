/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package jackpot;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Lou
 */
public class Historial {

    private Date FechaHora = new Date();
    private float saldo;
    private float deposito;
    private int premio;
    private NumberFormat formatoEuro = NumberFormat.getCurrencyInstance();

    public Historial(Date FechaHora, float saldo, float deposito, int premio) {
        this.FechaHora = FechaHora;
        this.saldo = saldo;
        this.deposito = deposito;
        this.premio = premio;
    }

    public Date getFechaHora() {
        return FechaHora;
    }

    public void setFechaHora(Date FechaHora) {
        this.FechaHora = FechaHora;
    }

    public float getSaldo() {
        return saldo;
    }


    public float getDeposito() {
        return deposito;
    }


    public int getPremio() {
        return premio;
    }


    public String toString() {
        String partida = "";
        partida += "Fecha/Hora: " + FechaHora + " \n";
        partida += "Saldo: " + formatoEuro.format(saldo) + " \n";
        partida += "Depósito: " + formatoEuro.format(deposito) + " \n";
        partida += "Premio: " + formatoEuro.format(premio) + " \n";
        partida += "----------------------------";
        return partida;
    }
}
