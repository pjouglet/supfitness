package supfitness.luciole.com.supfitness.Dialogs;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import supfitness.luciole.com.supfitness.Entity.Weight;
import supfitness.luciole.com.supfitness.Fragments.TabFragment1;
import supfitness.luciole.com.supfitness.R;

/**
 * Created by Luciole on 23/03/2016.
 */
public class WeightDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        LayoutInflater inflater = getActivity().getLayoutInflater();

        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final View view = inflater.inflate(R.layout.dialog_add_weight, null);

        builder.setView(view);

        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
             // Lorsque l'on clique sur le bouton "OK, on ajoute le poids choisi
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NumberPicker weightPicker = (NumberPicker)view.findViewById(R.id.weight_picker);
                Weight.addWeight(weightPicker.getValue());
                TabFragment1.adapter.clear();
                TabFragment1.adapter.addAll(Weight.getAll(view.getContext()));
                TabFragment1.adapter.notifyDataSetChanged();
                WeightDialog.this.getDialog().cancel();
            }
        });

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            //lorqu'on clique sur le bouton, on ferme le dialog
            @Override
            public void onClick(DialogInterface dialog, int which) {
                WeightDialog.this.getDialog().cancel();
            }
        });

        return builder.create();
    }
}
