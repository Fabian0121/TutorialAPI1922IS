package com.example.apptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.apptutorial.metodos.Peticiones;
import com.example.apptutorial.metodos.Usuarios;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    TextInputEditText correo, password;
    Button btn_login;
    TextView txtR;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        correo = (TextInputEditText) findViewById(R.id.in_correo);
        password = (TextInputEditText) findViewById(R.id.in_correo);
        btn_login = (Button) findViewById(R.id.btnIniciarSesion);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(correo.getText().toString().equals("") || password.getText().toString().equals("") ){
                    Toast.makeText(getApplicationContext(), "Ingresa datos completos", Toast.LENGTH_LONG).show();
                }else{
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("https://viqoxwhm.lucusvirtual.es/api/user/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    Peticiones peticiones = retrofit.create(Peticiones.class);
                    Usuarios usuarios = new Usuarios();
                    usuarios.setEmail(correo.getText().toString());
                    usuarios.setPassword(password.getText().toString());
                    Call<Usuarios>  respuesta = peticiones.loginForm(usuarios);
                    respuesta.enqueue(new Callback<Usuarios>() {
                        @Override
                        public void onResponse(Call<Usuarios> call, Response<Usuarios> response) {
                            if (!response.isSuccessful()){
                                Toast.makeText(getApplicationContext(), "Bienvenido", Toast.LENGTH_LONG).show();
                                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(i);
                                finish();
                            }
                        }

                        @Override
                        public void onFailure(Call<Usuarios> call, Throwable t) {

                        }
                    });
                }
            }
        });
    }
    public void irRegistro(View view){
        Toast.makeText(getApplicationContext(), "Cargando...", Toast.LENGTH_LONG).show();
        /*Intent i = new Intent(LoginActivity.this, RegistroActivity.class);
        startActivity(i);*/
        Intent newIntent = new Intent(getApplicationContext(), RegistroActivity.class);
        startActivity(newIntent);
    }
}