package br.com.goschool.goschool_mobile.activity.motorista;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.goschool.goschool_mobile.aplication.IMostoristaREST;
import br.com.goschool.goschool_mobile.models.Motorista;
import br.com.goschool.goschool_mobile.R;
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
        final EditText cnh = (EditText) findViewById(R.id.edcnh);
        final EditText cpf = (EditText) findViewById(R.id.edcpf);

        Intent intent = getIntent();
        final String id = Integer.toString(intent.getIntExtra("ID", 0));

        final IMostoristaREST iMostoristaREST = IMostoristaREST.retrofit.create(IMostoristaREST.class);
        final Call<Motorista> call = iMostoristaREST.getMotoristaPorId(id);
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
                nome.setText(motorista.getMotororistcpfme());
                cnh.setText(motorista.getMotororistaCNH());
                cpf.setText(String.valueOf(motorista.getMotororistaCPF()));
            }

            @Override
            public void onFailure(Call<Motorista> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Não foi possível fazer a conexão", Toast.LENGTH_SHORT).show();

            }
        });

        Button alterar = (Button) findViewById(R.id.btnEditMotorista);
        alterar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MotoristaEditActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();                Motorista motorista = new Motorista();
                motorista.setId(Integer.parseInt(id));
                motorista.setMotororistcpfme(nome.getText().toString());
                motorista.setMotororistaCNH(cnh.getText().toString());
                motorista.setMotororistaCPF(Integer.parseInt(cpf.getText().toString()));
                Call<Void> call = iMostoristaREST.alteraMotorista(id, motorista);
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
        Button remover = (Button) findViewById(R.id.btnDeleteMotorista);
        remover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MotoristaEditActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();
                Call<Void> call = iMostoristaREST.removeMotorista(id);
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
