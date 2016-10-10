package supfitness.luciole.com.supfitness;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import supfitness.luciole.com.supfitness.Entity.Weight;
import supfitness.luciole.com.supfitness.Fragments.TabFragment1;

/**
 * Created by Luciole on 23/03/2016.
 */
public class WeightListAdapter extends ArrayAdapter<Weight> {

    public WeightListAdapter(Context context, ArrayList<Weight> weights){
        super(context, 0, weights);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        //On récupère l'entité courante
        final Weight weight = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_weight, null);
        }

        /* On récupère les élement de la liste d'affichage*/
        final TextView weightDate = (TextView)convertView.findViewById(R.id.weight_date);
        final TextView weightWeight = (TextView)convertView.findViewById(R.id.weight_weight);
        Button deleteButton = (Button)convertView.findViewById(R.id.delete_weight_button);

        deleteButton.setOnClickListener(new View.OnClickListener() {
            /**
             * Lorsque l'on clique sur le bouton delete, on supprime la ligne associée.
             */
            @Override
            public void onClick(View v) {
                Weight.deleteWeight(weightDate.getText().toString());
                //Recharge les données de ls liste
                TabFragment1.adapter.clear();
                TabFragment1.adapter.addAll(Weight.getAll(getContext()));
                TabFragment1.adapter.notifyDataSetChanged();
            }
        });

        //On assigne le texte de la ligne à ce qui est contenu dans l'item
        weightDate.setText(weight.getDate());
        weightWeight.setText(String.valueOf(weight.getWeigth()) + "Kg");
        return convertView;
    }
}
