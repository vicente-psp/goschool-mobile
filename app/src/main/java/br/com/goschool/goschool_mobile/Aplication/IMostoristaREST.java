package br.com.goschool.goschool_mobile.Aplication;

import java.util.List;

import br.com.goschool.goschool_mobile.Modulos.Estudante;
import br.com.goschool.goschool_mobile.Modulos.Motorista;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IMostoristaREST {
    @POST("")
    Call<Void> insereMotorista(@Body Motorista motorista);

    @GET("")
    Call<List<Motorista>> getMotorista();

    @GET("")
    Call<Motorista> getMotoristaPorId(@Path("id") String id);

    @PUT("")
    Call<Void> alteraMotorista(@Path("id") String id, @Body Motorista motorista);

    @DELETE("")
    Call<Void> removeMotorista(@Path("id") String id);

    @POST("")
    Call<Void> insereEstudante(@Body Estudante estudante);

    @GET("")
    Call<List<Estudante>> getEstudante();

    @GET("")
    Call<Estudante> getEstudantePorId(@Path("id") String id);

    @PUT("")
    Call<Void> alteraEstudante(@Path("id") String id, @Body Estudante estudante);

    @DELETE("")
    Call<Void> removeEstudante(@Path("id") String id);

    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

}
