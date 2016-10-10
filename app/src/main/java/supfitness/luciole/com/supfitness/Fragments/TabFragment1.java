package supfitness.luciole.com.supfitness.Fragments;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Date;

import supfitness.luciole.com.supfitness.Dialogs.WeightDialog;
import supfitness.luciole.com.supfitness.Entity.Weight;
import supfitness.luciole.com.supfitness.R;
import supfitness.luciole.com.supfitness.WeightListAdapter;

public class TabFragment1 extends Fragment implements View.OnClickListener{

    private Button addButton;
    public static ListView weightView;
    public static WeightListAdapter adapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        //On récupère l'ensemble des entités "Weight" qu'on assigne à un adapter pour notre liste
        adapter = new WeightListAdapter(view.getContext(), Weight.getAll(getContext()));
        weightView = (ListView)view.findViewById(R.id.WeightList);
        weightView.setAdapter(adapter);

        //On récupère le boutton d'ajout et on défini son evenement sur la classe actuelle
        addButton = (Button)view.findViewById(R.id.add_weight_button);
        addButton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //Si le boutton appuyé est celui d'ajout
            case R.id.add_weight_button:
                //On affiche la "Dialog" qui ajoutera une valeur si ça n'a pas été fait le jour actuel
                if(Weight.getWeightByDate(new Date()) == 0){
                    DialogFragment fragment = new WeightDialog();
                    fragment.show(getActivity().getFragmentManager(), "add_weight_dialog");
                }
                else{
                    Toast.makeText(getContext(), R.string.value_already_added, Toast.LENGTH_LONG).show();
                }
                break;
        }
    }
}