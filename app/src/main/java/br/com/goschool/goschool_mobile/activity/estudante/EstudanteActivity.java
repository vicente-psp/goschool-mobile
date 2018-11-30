package br.com.goschool.goschool_mobile.activity.estudante;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.goschool.goschool_mobile.adapters.EstudanteAdapter;
import br.com.goschool.goschool_mobile.aplication.IMostoristaREST;
import br.com.goschool.goschool_mobile.models.Estudante;
import br.com.goschool.goschool_mobile.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EstudanteActivity extends AppCompatActivity {

    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estudante);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EstudanteActivity.this, EstudanteAddActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        final ListView lista = (ListView) findViewById(R.id.lvMotorista);
        IMostoristaREST iMostoristaREST = IMostoristaREST.retrofit.create(IMostoristaREST.class);
        dialog = new ProgressDialog(EstudanteActivity.this);
        dialog.setMessage("Carregando...");
        dialog.setCancelable(false);
        dialog.show();
        final Call<List<Estudante>> call = iMostoristaREST.getEstudante();
        call.enqueue(new Callback<List<Estudante>>() {
            @Override
            public void onResponse(Call<List<Estudante>> call, Response<List<Estudante>> response) {
                if (dialog.isShowing())
                    dialog.dismiss();
                final List<Estudante> listaEstudantes = response.body();
                if (listaEstudantes != null) {
                    EstudanteAdapter adapter = new EstudanteAdapter(getBaseContext(), listaEstudantes);
                    lista.setAdapter(adapter);
                    lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(EstudanteActivity.this, EstudanteEditActivity.class);
                            intent.putExtra("ID", listaEstudantes.get(i).getId());
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onFailure(Call<List<Estudante>> call, Throwable t) {
                if (dialog.isShowing())
                    dialog.dismiss();
                Toast.makeText(getBaseContext(), "Problema de acesso", Toast.LENGTH_LONG).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
