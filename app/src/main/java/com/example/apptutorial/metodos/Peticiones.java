package com.example.apptutorial.metodos;

import com.example.apptutorial.Pokemon.Pokemon;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Peticiones {

    @POST("registro")
    Call<Usuarios> registroForm(@Body Usuarios usuarios);

    @POST("login")
    Call<Usuarios> loginForm(@Body Usuarios usuarios);

    @GET("pokemon/{id}/")
    Call<Pokemon> consulta(@Path("id") int ind);

}
