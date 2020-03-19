package br.com.msm.librarycalculadora;

public class operacao {


     private  double v;
     private String op;

    public double getV() {
        return v;
    }

    public operacao(double v) {
        this.v = v;
    }

    public void setV(double v) {
        this.v = v;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public operacao(double v, String op) {
        this.v = v;
        this.op = op;
    }
}
