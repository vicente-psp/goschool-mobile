package br.com.goschool.goschool_mobile.activity.motorista;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.com.goschool.goschool_mobile.Aplication.IMostoristaREST;
import br.com.goschool.goschool_mobile.Modulos.Motorista;
import br.com.goschool.goschool_mobile.R;
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
        final EditText cnh = (EditText) findViewById(R.id.edcnh);
        final EditText cpf = (EditText) findViewById(R.id.edcpf);
        Button adicionar = (Button) findViewById(R.id.btnAddMotorista);
        adicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = new ProgressDialog(MotoristaAddActivity.this);
                dialog.setMessage("Carregando...");
                dialog.setCancelable(false);
                dialog.show();
                Motorista motorista = new Motorista();
                motorista.setMotororistcpfme(nome.getText().toString());
                motorista.setMotororistaCNH(cnh.getText().toString());
                motorista.setMotororistaCPF(Integer.parseInt(cpf.getText().toString()));
                IMostoristaREST iMostoristaREST = IMostoristaREST.retrofit.create(IMostoristaREST.class);
                final Call<Void> call = iMostoristaREST.insereMotorista(motorista);
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
