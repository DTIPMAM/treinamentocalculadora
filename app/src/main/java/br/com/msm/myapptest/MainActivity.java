package br.com.msm.myapptest;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.snackbar.Snackbar;

import br.com.msm.librarycalculadora.Calcular;
import br.com.msm.librarycalculadora.Calculos;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, Calcular {


    TextView result;
    Calculos clr;
    private TextView rV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        result = findViewById(R.id.textView);

        findViewById(R.id.btn_dividir).setOnClickListener(this);
        findViewById(R.id.btn_multiplicar).setOnClickListener(this);
        findViewById(R.id.btn_subtrair).setOnClickListener(this);
        findViewById(R.id.btn_somar).setOnClickListener(this);
        findViewById(R.id.btn_0).setOnClickListener(this);
        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
        findViewById(R.id.btn_3).setOnClickListener(this);
        findViewById(R.id.btn_4).setOnClickListener(this);
        findViewById(R.id.btn_5).setOnClickListener(this);
        findViewById(R.id.btn_6).setOnClickListener(this);
        findViewById(R.id.btn_7).setOnClickListener(this);
        findViewById(R.id.btn_8).setOnClickListener(this);
        findViewById(R.id.btn_9).setOnClickListener(this);
        findViewById(R.id.btn_ponto).setOnClickListener(this);


        findViewById(R.id.btn_c).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                rV.setText("0");
                clr.setStr("");
            }
        });

        rV =   findViewById(R.id.resultado);

        clr = new Calculos(this);

        //falta criar metodo para validar calculos com multiplos operadores
        findViewById(R.id.btn_igual).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                result.setText(rV.getText().toString());
                clr.setStr(rV.getText().toString());
                rV.setText("0");

            }
        });


        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (clr != null && clr.getStr().length() > 3) {

                    String s = clr.getStr();
                    String s2 = s.subSequence(0, clr.getStr().length() - 2).toString();
                    clr.setStr(s2);
                    setText(s.subSequence(clr.getStr().length() - 2, clr.getStr().length() - 1).toString());
                } else if (clr != null && clr.getStr().length() == 3) {
                    String s = clr.getStr();
                    String s2 = s.subSequence(0, 1).toString();
                    clr.setStr(s2);
                    setText(s.subSequence(1, 2).toString());
                } else if (clr != null && clr.getStr().length() == 2) {
                    String s = clr.getStr();
                    String s2 = s.subSequence(0, 1).toString();
                    clr.setStr("");
                    setText(s2);
                } else {
                    result.setText("");
                    rV.setText("0");
                    clr.setStr("");
                }

            }
        });


    }


    private void setText(String s) {
        clr.cAll(s, this);

    }


    @Override
    public void onClick(View v) {

        int id = v.getId();

        if (id == R.id.btn_somar) {
            setText("+");
        } else if (id == R.id.btn_subtrair) {

            setText("-");

        } else if (id == R.id.btn_multiplicar) {

            setText("x");

        } else if (id == R.id.btn_dividir) {
            setText("÷");

        } else if (id == R.id.btn_0) {

            setText("0");

        } else if (id == R.id.btn_1) {
            setText("1");

        } else if (id == R.id.btn_2) {


            setText("2");
        } else if (id == R.id.btn_3) {

            setText("3");
        } else if (id == R.id.btn_4) {
            setText("4");


        } else if (id == R.id.btn_5) {
            setText("5");


        } else if (id == R.id.btn_6) {

            setText("6");

        } else if (id == R.id.btn_7) {

            setText("7");
        } else if (id == R.id.btn_8) {

            setText("8");
        } else if (id == R.id.btn_9) {
            setText("9");

        } else if (id == R.id.btn_ponto) {

            setText(".");

        }

    }


    @Override
    public void setResult(double r, String e) {

        if (e == null) {
            rV.setText(String.valueOf(r));
        } else {
            rV.setText("0");
            Snackbar.make(result, e, Snackbar.LENGTH_LONG).setAction("Action", null).show();
        }


    }

    @Override
    public void setResultString(String s) {

        result.setText(s);

    }
}
