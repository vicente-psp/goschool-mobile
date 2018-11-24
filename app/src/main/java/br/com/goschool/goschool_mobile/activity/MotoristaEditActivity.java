package br.com.goschool.goschool_mobile.activity;

import android.app.ProgressDialog;
import android.content.Intent;
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

public class MotoristaEditActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_motorista_edit);

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText autor = (EditText) findViewById(R.id.edAutor);
        final EditText ano = (EditText) findViewById(R.id.edAno);

        Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("ID", 0));

        final ILivrosREST iLivrosREST = ILivrosREST.retrofit.create(ILivrosREST.class);
        final Call<Motorista> call = iLivrosREST.getLivroPorId(id);
        dialog = new ProgressDialog(MotoristaEditActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        call.enqueue(new Callback<Motorista>() {
            @Override
            public void onResponse(Call<Motorista> call, Response<Motorista> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Motorista motorista = response.body();
                nome.setText(motorista.getNome());
                autor.setText(motorista.getAutor());
                ano.setText(String.valueOf(motorista.getAno()));
            }

            @Override
            public void onFailure(Call<Motorista> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();

            }
        });

        Button alterar = (Button) findViewById(R.id.btnEditLivro);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MotoristaEditActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();                Motorista motorista = new Motorista();
                motorista.setId(Integer.parseInt(id));
                motorista.setNome(nome.getText().toString());
                motorista.setAutor(autor.getText().toString());
                motorista.setAno(Integer.parseInt(ano.getText().toString()));
                Call<Void> call = iLivrosREST.alteraLivro(id, motorista);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Motorista alterado com sucesso", Toast.LENGTH_SHORT).show();
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
        Button remover = (Button) findViewById(R.id.btnDeleteLivro);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MotoristaEditActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();
                Call<Void> call = iLivrosREST.removeLivro(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Motorista removido com sucesso", Toast.LENGTH_SHORT).show();
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
