/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author USER
 */
public class Payment {
    private int PaymentID;
    private String TipePayment;
    private String NamaPayment;

    public Payment(int paymentID, String tipePayment, String namaPayment) {
        this.PaymentID = paymentID;
        this.TipePayment = tipePayment;
        this.NamaPayment = namaPayment;
    }

    // Getters and Setters

    public int getPaymentID() {
        return PaymentID;
    }

    public void setPaymentID(int PaymentID) {
        this.PaymentID = PaymentID;
    }

    public String getTipePayment() {
        return TipePayment;
    }

    public void setTipePayment(String TipePayment) {
        this.TipePayment = TipePayment;
    }

    public String getNamaPayment() {
        return NamaPayment;
    }

    public void setNamaPayment(String NamaPayment) {
        this.NamaPayment = NamaPayment;
    }
}
