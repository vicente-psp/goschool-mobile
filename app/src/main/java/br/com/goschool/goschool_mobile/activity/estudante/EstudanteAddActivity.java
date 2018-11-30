package br.com.goschool.goschool_mobile.activity.estudante;

import android.app.ProgressDialog;
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

public class EstudanteAddActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudante_add);

        final EditText nome = (EditText) findViewById(R.id.edNomeEstudante);
        final EditText cep = (EditText) findViewById(R.id.edcep);
        final EditText cpf = (EditText) findViewById(R.id.edcpf);
        Button adicionar = (Button) findViewById(R.id.btnAddEstudante);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(EstudanteAddActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();
                Estudante estudante = new Estudante();
                estudante.setEstudanteNome(nome.getText().toString());
                estudante.setEstudanteCEP(cep.getText().toString());
                estudante.setEstudanteCPF(Integer.parseInt(cpf.getText().toString()));
                IMostoristaREST iMostoristaREST = IMostoristaREST.retrofit.create(IMostoristaREST.class);
                final Call<Void> call = iMostoristaREST.insereEstudante(estudante);
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