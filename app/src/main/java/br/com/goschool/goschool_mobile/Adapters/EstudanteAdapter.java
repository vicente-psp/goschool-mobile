package br.com.goschool.goschool_mobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.goschool.goschool_mobile.Modulos.Estudante;
import br.com.goschool.goschool_mobile.R;

public class EstudanteAdapter extends ArrayAdapter<Estudante> {

    private final Context context;
    private final List<Estudante> elementos;

    public EstudanteAdapter(Context context, List<Estudante> elementos) {
        super(context, R.layout.registro_estudante, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.registro_estudante, parent, false);

        TextView nomeEstudante = (TextView) rowView.findViewById(R.id.txtEstudanteNome);
        TextView cpf = (TextView) rowView.findViewById(R.id.txtEstudanteCPF);
        TextView cep = (TextView) rowView.findViewById(R.id.txtEstudanteCEP);

        nomeEstudante.setText(elementos.get(position).getEstudanteNome());
        cep.setText(elementos.get(position).getEstudanteCEP());
        cpf.setText(Integer.toString(elementos.get(position).getEstudanteCPF()));

        return rowView;
    }

}
