package com.example.apptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptutorial.metodos.Peticiones;
import com.example.apptutorial.metodos.Usuarios;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegistroActivity extends AppCompatActivity {
    DatePickerDialog picker;
    TextInputEditText nombre, apeliidoPat, apellidoMat, correo, contrasenia1, contrasenia2;
    EditText fechaNacimiento;
    Button btn;
    TextView txtIr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        txtIr = (TextView) findViewById(R.id.txt_irLogin);
        nombre = (TextInputEditText) findViewById(R.id.in_nombre);
        apeliidoPat = (TextInputEditText) findViewById(R.id.in_apellidoP);
        apellidoMat = (TextInputEditText) findViewById(R.id.in_apellidoM);
        correo  = (TextInputEditText) findViewById(R.id.in_correo);
        contrasenia1 = (TextInputEditText) findViewById(R.id.in_contrasenia1);
        contrasenia2 = (TextInputEditText) findViewById(R.id.in_contrasenia1);
        fechaNacimiento = (EditText) findViewById(R.id.in_fechaNacimieto);
        btn = (Button) findViewById(R.id.btn_Registrar);
        fechaNacimiento.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (hasFocus)
                    showDatePicker();
            }
        });
        //Metodo para hacer peticion a login
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nombre.getText().toString().equals("") || apeliidoPat.getText().toString().equals("") || apellidoMat.getText().toString().equals("") || correo.getText().toString().equals("") || contrasenia1.getText().toString().equals("") || contrasenia2.getText().toString().equals("") || fechaNacimiento.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Faltan campos por llenar", Toast.LENGTH_LONG).show();
                }else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://viqoxwhm.lucusvirtual.es/api/user/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Peticiones peticiones = retrofit.create(Peticiones.class);
                    Usuarios usuarios = new Usuarios();
                    usuarios.setName(nombre.getText().toString());
                    usuarios.setApPat(apeliidoPat.getText().toString());
                    usuarios.setApMat(apellidoMat.getText().toString());
                    usuarios.setEmail(correo.getText().toString());
                    usuarios.setPassword(contrasenia1.getText().toString());
                    usuarios.setValidate(contrasenia2.getText().toString());
                    usuarios.setFecha_nacimiento("2022-07-03");
                    usuarios.setCode("1970-01-01 02:07:38");
                    Call<Usuarios> respueta = peticiones.registroForm(usuarios);
                    respueta.enqueue(new Callback<Usuarios>() {
                        @Override
                        public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                            if(!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Ocurrio un error revise el correo sea valido", Toast.LENGTH_LONG).show();
                            }else{
                                Usuarios usuarios1 = response.body();
                                if (usuarios1.getMessage() != ""){
                                    Toast.makeText(getApplicationContext(), usuarios1.getMessage(), Toast.LENGTH_LONG).show();
                                    Intent i = new Intent(RegistroActivity.this, LoginActivity.class);
                                    startActivity(i);
                                    finish();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuarios> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "Revise su conexion", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });
        txtIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegistroActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showDatePicker() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        picker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fechaNacimiento.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);
            }
        }, year, month, day);
        picker.show();
    }

}