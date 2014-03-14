package jackpot;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Jackpot {

    private int[] resultado = new int[3];
    private float saldo = 0;
    private float deposito = 1000;
    private final float VALOR_APUESTA = 0.50F;
    private final int CEREZA = 0;
    private final int CAMPANA = 1;
    private final int TREBOL = 2;
    private final int MONEDA = 3;
    private final int SIETE = 4;

    public float jugar() {
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            resultado[i] = random.nextInt(5);
        }
        saldo -= VALOR_APUESTA;
        float premio = getPremio();
        saldo += premio;
        return premio;
    }

    public ImageIcon getImagen(int indice) {
        try {
            ImageIcon imagen = new ImageIcon(getClass().getResource("/imagenes/" + resultado[indice] + ".png"));
            return imagen;
        } catch (NullPointerException ex) {
            Logger.getLogger(Jackpot.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public int getPremio() {
        if (resultado[0] == CEREZA && resultado[1] == CEREZA) {
            return 5;
        } else if (resultado[0] == CEREZA) {
            return 1;
        } else if (resultado[0] == CAMPANA && resultado[1] == CAMPANA && resultado[2] == CAMPANA) {
            return 10;
        } else if (resultado[0] == CAMPANA && resultado[1] == CAMPANA && resultado[2] == SIETE) {
            return 10;
        } else if (resultado[0] == TREBOL && resultado[1] == TREBOL && resultado[2] == TREBOL) {
            return 15;
        } else if (resultado[0] == TREBOL && resultado[1] == TREBOL && resultado[2] == SIETE) {
            return 15;
        } else if (resultado[0] == MONEDA && resultado[1] == MONEDA && resultado[2] == MONEDA) {
            return 20;
        } else if (resultado[0] == MONEDA && resultado[1] == MONEDA && resultado[2] == SIETE) {
            return 20;
        } else if (resultado[0] == SIETE && resultado[1] == SIETE && resultado[2] == SIETE) {
            return 100;
        } else {
            return 0;
        }
    }

    public void introducirMoneda(float valor) {
        this.saldo += valor;
        deposito += valor;
    }

    public float getSaldo() {
        return saldo;
    }

    public float getDeposito() {
        return deposito;
    }

    public void cobrar() {
        deposito -= saldo;
        saldo = 0;
    }
    
    //Intento de hacer que se vayan cambiando las imágenes hasta aparecer la combinación ganadora
    public void rotarNumeros(JLabel jLabelImagen1, JLabel jLabelImagen2,JLabel jLabelImagen3){
        int vueltas = 15;
        Random generador = new Random();
        for (int i = 0; i <= vueltas ; i++) {
                    try {
                        int numeroAleatorio1 = generador.nextInt(5);
                        int numeroAleatorio2 = generador.nextInt(5);
                        int numeroAleatorio3 = generador.nextInt(5);
                        jLabelImagen1.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + numeroAleatorio1 + ".png")));
                        jLabelImagen1.paintImmediately(0, 0, jLabelImagen1.getWidth(), jLabelImagen1.getHeight());
                        jLabelImagen1.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + resultado[0] + ".png")));
                        Thread.sleep(150);
                        jLabelImagen2.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + numeroAleatorio2 + ".png")));
                        jLabelImagen2.paintImmediately(0, 0, jLabelImagen2.getWidth(), jLabelImagen2.getHeight());
                        jLabelImagen2.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + resultado[1] + ".png")));
                        Thread.sleep(150);
                        jLabelImagen3.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + numeroAleatorio3 + ".png")));
                        jLabelImagen3.paintImmediately(0, 0, jLabelImagen1.getWidth(), jLabelImagen1.getHeight());
                        jLabelImagen3.setIcon(new ImageIcon(getClass().getResource("/imagenes/" + resultado[2] + ".png")));
                        Thread.sleep(150);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Jackpot.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
    }
}
