package com.example.apptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.apptutorial.Pokemon.Pokemon;
import com.example.apptutorial.metodos.Peticiones;

import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
Button consultar;
TextView datos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Random r = new Random();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        consultar = (Button) findViewById(R.id.btn_consulta);
        datos = (TextView) findViewById(R.id.contenido);
        //int valorDado = r.nextInt(6)+1;
        consultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://pokeapi.co/api/v2/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                Peticiones peticiones = retrofit.create(Peticiones.class);
                int valor = r.nextInt(20);
                Call<Pokemon> pokemon = peticiones.consulta(valor);
                pokemon.enqueue(new Callback<Pokemon>() {
                    @Override
                    public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                        if (!response.isSuccessful()){
                            datos.setText("");
                            datos.setText("Error intentalo de nuevo");
                        }else{
                            String valores = "";
                            Pokemon pokemon1 = response.body();
                            valores += "Id: "+pokemon1.getId() + "\n";
                            valores += "nombre: "+pokemon1.getName() + "\n";
                            valores += "Height: " + pokemon1.getHeight() + "\n";
                            valores += "weight: " + pokemon1.getHeight() + "\n";
                            valores += "Stats: " + "\n";
                            valores += "Stat 1 " + pokemon1.getStats().get(0).getStat().getName() + ":" + pokemon1.getStats().get(0).getBase_stat() + "\n";
                            valores += "Stat 2 " + pokemon1.getStats().get(1).getStat().getName() + ":" + pokemon1.getStats().get(1).getBase_stat() + "\n";
                            valores += "Stat 3 " + pokemon1.getStats().get(2).getStat().getName() + ":" + pokemon1.getStats().get(2).getBase_stat() + "\n";
                            valores += "Stat 4 " + pokemon1.getStats().get(3).getStat().getName() + ":" + pokemon1.getStats().get(3).getBase_stat() + "\n";
                            datos.setText("");
                            datos.setText(valores);
                        }
                    }

                    @Override
                    public void onFailure(Call<Pokemon> call, Throwable t) {
                        datos.setText("");
                        datos.setText("Revisa tu conexion");
                    }
                });
            }
        });

    }
}