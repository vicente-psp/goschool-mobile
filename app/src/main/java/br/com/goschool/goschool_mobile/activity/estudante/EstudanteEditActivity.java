package br.com.goschool.goschool_mobile.activity.estudante;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.goschool.goschool_mobile.aplication.IMostoristaREST;
import br.com.goschool.goschool_mobile.models.Estudante;
import br.com.goschool.goschool_mobile.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstudanteEditActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudante_edit);

        final EditText nome = (EditText) findViewById(R.id.edNome);
        final EditText cep = (EditText) findViewById(R.id.edcep);
        final EditText cpf = (EditText) findViewById(R.id.edcpf);

        Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("ID", 0));

        final IMostoristaREST iMostoristaREST = IMostoristaREST.retrofit.create(IMostoristaREST.class);
        final Call<Estudante> call = iMostoristaREST.getEstudantePorId(id);
        dialog = new ProgressDialog(EstudanteEditActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        call.enqueue(new Callback<Estudante>() {
            @Override
            public void onResponse(Call<Estudante> call, Response<Estudante> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Estudante estudante = response.body();
                nome.setText(estudante.getEstudanteNome());
                cep.setText(estudante.getEstudanteCEP());
                cpf.setText(String.valueOf(estudante.getEstudanteCPF()));
            }

            @Override
            public void onFailure(Call<Estudante> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();

            }
        });

        Button alterar = (Button) findViewById(R.id.btnEditEstudante);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(EstudanteEditActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();                Estudante estudante = new Estudante();
                estudante.setId(Integer.parseInt(id));
                estudante.setEstudanteNome(nome.getText().toString());
                estudante.setEstudanteCEP(cep.getText().toString());
                estudante.setEstudanteCPF(Integer.parseInt(cpf.getText().toString()));
                Call<Void> call = iMostoristaREST.alteraEstudante(id, estudante);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Estudante alterado com sucesso", Toast.LENGTH_SHORT).show();
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
        Button remover = (Button) findViewById(R.id.btnDeleteEstudante);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(EstudanteEditActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();
                Call<Void> call = iMostoristaREST.removeEstudante(id);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if (dialog.isShowing())
                            dialog.dismiss();
                        Toast.makeText(getBaseContext(), "Estudante removido com sucesso", Toast.LENGTH_SHORT).show();
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
