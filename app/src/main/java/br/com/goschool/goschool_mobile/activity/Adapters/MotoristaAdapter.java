package br.com.goschool.goschool_mobile.activity.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.goschool.goschool_mobile.R;
import br.com.goschool.goschool_mobile.activity.Modulos.Motorista;

public class MotoristaAdapter extends ArrayAdapter<Motorista> {
    private final Context context;
    private final List<Motorista> elementos;

    public MotoristaAdapter(Context context, List<Motorista> elementos) {
        super(context, R.layout.registro_motorista, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.registro_motorista, parent, false);

        TextView titulo = (TextView) rowView.findViewById(R.id.txtNome);
        TextView ano = (TextView) rowView.findViewById(R.id.txtAno);
        TextView autor = (TextView) rowView.findViewById(R.id.txtAutor);

        titulo.setText(elementos.get(position).getNome());
        autor.setText(elementos.get(position).getAutor());
        ano.setText(Integer.toString(elementos.get(position).getAno()));

        return rowView;
    }

}
