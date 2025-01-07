/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author USER
 */
public class Shipping {
    private int shippingID;
    private String tipeJasa;
    private String namaJasa;

    public Shipping(int shippingID, String tipeJasa, String namaJasa) {
        this.shippingID = shippingID;
        this.tipeJasa = tipeJasa;
        this.namaJasa = namaJasa;
    }

    // Getters and Setters

    public int getShippingID() {
        return shippingID;
    }

    public void setShippingID(int shippingID) {
        this.shippingID = shippingID;
    }

    public String getTipeJasa() {
        return tipeJasa;
    }

    public void setTipeJasa(String tipeJasa) {
        this.tipeJasa = tipeJasa;
    }

    public String getNamaJasa() {
        return namaJasa;
    }

    public void setNamaJasa(String namaJasa) {
        this.namaJasa = namaJasa;
    }
}
