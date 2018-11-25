package br.com.goschool.goschool_mobile.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.goschool.goschool_mobile.R;
import br.com.goschool.goschool_mobile.Modulos.Motorista;

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

        TextView nomeMotorista = (TextView) rowView.findViewById(R.id.txtMotororistaNome);
        TextView cpf = (TextView) rowView.findViewById(R.id.txtMotororistaCPF);
        TextView cnh = (TextView) rowView.findViewById(R.id.txtMotororistaCNH);

        nomeMotorista.setText(elementos.get(position).getMotororistcpfme());
        cnh.setText(elementos.get(position).getMotororistaCNH());
        cpf.setText(Integer.toString(elementos.get(position).getMotororistaCPF()));

        return rowView;
    }

}
