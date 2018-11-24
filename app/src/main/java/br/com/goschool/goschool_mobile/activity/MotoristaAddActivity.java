package br.com.goschool.goschool_mobile.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.goschool.goschool_mobile.R;
import br.com.goschool.goschool_mobile.activity.Aplication.ILivrosREST;
import br.com.goschool.goschool_mobile.activity.Modulos.Motorista;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MotoristaAddActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista_add);

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText autor = (EditText) findViewById(R.id.edAutor);
        final EditText ano = (EditText) findViewById(R.id.edAno);
        Button adicionar = (Button) findViewById(R.id.btnAddLivro);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MotoristaAddActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();
                Motorista motorista = new Motorista();
                motorista.setNome(nome.getText().toString());
                motorista.setAutor(autor.getText().toString());
                motorista.setAno(Integer.parseInt(ano.getText().toString()));
                ILivrosREST iLivrosREST = ILivrosREST.retrofit.create(ILivrosREST.class);
                final Call<Void> call = iLivrosREST.insereLivro(motorista);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Motorista inserido com sucesso", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
