package com.calculadorabss.gorydev.currencytests;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.text.NumberFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editCantidad;
    private Button btnCalcular;
    private TextView txtSalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editCantidad = (EditText)findViewById(R.id.editCantidad);
        btnCalcular = (Button)findViewById(R.id.btnCalcular);
        txtSalida = (TextView)findViewById(R.id.txtSalida);

        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calcular();
            }
        });

        editCantidad.addTextChangedListener(new NumberTextWatcher(editCantidad));
    }

    public void calcular(){
        double numero = Double.parseDouble(editCantidad.getText().toString().replace(",",""));
        NumberFormat defaultFormat = NumberFormat.getCurrencyInstance(getCurrentLocale(getBaseContext()));
        txtSalida.setText(defaultFormat.format(numero));
        Log.i("Conversion: ","La cantidad a calcular: " + defaultFormat.format(numero));

    }


    @TargetApi(Build.VERSION_CODES.N)
    public static Locale getCurrentLocale(Context c) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.i("Conversion","El locale usado es: " + c.getResources().getConfiguration().getLocales().get(0));
            return c.getResources().getConfiguration().getLocales().get(0);
        } else {
            //noinspection deprecation
            Log.i("Conversion","El locale usado es: " + c.getResources().getConfiguration().locale);
            return c.getResources().getConfiguration().locale;
        }
    }
}
