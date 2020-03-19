package br.com.msm.librarycalculadora;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class Calculos {

    private Context ctx;
    private ArrayList<operacao> listOp;
    private String str = "";

    public Calculos(Context ctx) {
        this.ctx = ctx;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void cAll(String s, Calcular c) {

        //5+  6+7-8
        //  str += s;

        if ((s.equals(".") || s.equals("+") || s.equals("-") || s.equals("x") || s.equals("÷"))) {

            if (str.length() > 1) {

                CharSequence r = str.subSequence(str.length() - 1, str.length());

                if (!r.equals(".") && !r.equals("+") && !r.equals("-") && !r.equals("x") && !r.equals("÷")) {
                    str += s;
                }

            } else if (str.length() == 1) {
                if (!(str.equals(".") || str.equals("+") || str.equals("-") || str.equals("x") || str.equals("÷"))) {
                    str += s;
                }
            }
        } else {
            str += s;

        }
        c.setResultString(str);

        if ((str.contains("x") || str.contains("+") || str.contains("-") || str.contains("÷")) && !(s.equals("x") || s.equals("+") || s.equals("-") || s.equals("÷"))) {

            listOp = new ArrayList<>();
            String[] ars = str.split("[x|\\-|\\÷|\\+]");

            String[] aro = str.split("[^x|^\\-|^\\÷|^\\+]");

            String lastElemento = ars[ars.length - 1];

            ArrayList<String> arno = new ArrayList<>();
            for (int i = 0; i < aro.length; i++) {
                if (!aro[i].isEmpty()) {
                    arno.add(aro[i]);
                }
            }

            Log.d("MainActivity ", " arno " + arno.size() + "  ars " + ars.length);

            if (ars.length - arno.size() != 1) {

                c.setResult(0, "Formato inválido. Formato valido \"3X3+3+9-2\"");

            } else {

                for (int i = 0; i < arno.size(); i++) {
                    Log.d("MainActivity ", " arno " + arno.get(i) + " POS " + i);
                    listOp.add(new operacao(Double.parseDouble(ars[i]), arno.get(i)));
                }

                if ((str.contains("x") || str.contains("÷")) && (str.contains("-") || str.contains("+"))) {
                    operacaoSSMD(listOp, Double.parseDouble(lastElemento), c);
                } else if ((str.contains("-") || str.contains("+"))) {
                    operacaoSS(listOp, Double.parseDouble(lastElemento), c);
                } else if ((str.contains("x") || str.contains("÷"))) {
                    operacaoMD(listOp, Double.parseDouble(lastElemento), c);
                }

            }

        }


    }

    private void operacaoSS(ArrayList<operacao> Op, double lastv, Calcular c) {

        c.setResult(calculoSS(Op, lastv), null);

    }

    private double calculoSS(ArrayList<operacao> Op, double lastv) {


        double r = Op.get(0).getV();

        String lastOP = Op.get(Op.size() - 1).getOp();


        for (int i = 0; i < Op.size() - 1; i++) {
            String op = Op.get(i).getOp();

            if (op.equals("+")) {
                r += Op.get(i + 1).getV();
            } else if (op.equals("-")) {
                r -= Op.get(i + 1).getV();
            }

        }
        if (lastOP.equals("+")) {
            r += lastv;
        } else if (lastOP.equals("-")) {
            r -= lastv;
        }


        return r;
    }

    private double calculoMD(ArrayList<operacao> Op, double lastv) {

        double r = Op.get(0).getV();

        String lastOP = Op.get(Op.size() - 1).getOp();
        for (int i = 0; i < Op.size() - 1; i++) {
            String op = Op.get(i).getOp();

            if (op.equals("x")) {
                r *= Op.get(i + 1).getV();
            } else if (op.equals("÷")) {
                r /= Op.get(i + 1).getV();
            }

        }
        if (lastOP.equals("x")) {
            r *= lastv;
        } else if (lastOP.equals("÷")) {
            r /= lastv;
        }


        return r;
    }

    private double calculoSSMD(ArrayList<operacao> Op, double lastv) {

        ArrayList<operacao> newOp = new ArrayList<>();

        for (int i = 0; i < Op.size(); i++) {

            String op = Op.get(i).getOp();  // 1 = + // 2 == ÷

            if (op.equals("x") || op.equals("÷")) {
                double n1 = Op.get(i).getV();  // 2  == 4
                double n2;
                if (i + 1 == Op.size()) {
                    n2 = lastv;
                } else {
                    n2 = Op.get(i + 1).getV();
                }


                double r;
                if (op.equals("x")) {
                    r = n1 * n2;
                } else {
                    r = n1 / n2;
                }


                if (i + 1 == Op.size()) {
                    lastv = r;
                } else {
                    Op.set(i + 1, new operacao(r, Op.get(i + 1).getOp()));
                }


            } else {

                newOp.add(new operacao(Op.get(i).getV(), op));
            }

        }


        return calculoSS(newOp, lastv);

    }

    private void operacaoMD(ArrayList<operacao> Op, double lastv, Calcular c) {

        c.setResult(calculoMD(Op, lastv), null);

    }

    private void operacaoSSMD(ArrayList<operacao> Op, double lastv, Calcular c) {

        c.setResult(calculoSSMD(Op, lastv), null);

    }


}
