package org.example;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CerereCompleta implements Comparable<CerereCompleta> {

    private Date data;

    private int prioritate;
    private String text;

    CerereCompleta(Date data, int prioritate, String text) {
        this.data = data;
        this.prioritate = prioritate;
        this.text = text;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public int getPrioritate() {
        return prioritate;
    }

    public void setPrioritate(int prioritate) {
        this.prioritate = prioritate;
    }

    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        String data = dateFormat.format(this.data);
        String retur = data+" - "+this.text;
        return retur;
    }

    @Override
    public int compareTo(CerereCompleta o) {
        return this.getData().compareTo(o.getData());
    }
}
