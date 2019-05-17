package org.deguet.roomdemo;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import org.deguet.roomdemo.evt.newObject;
import org.deguet.roomdemo.modele.DemoObjet;

public class MonDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // Get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();


        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        final View v = inflater.inflate(R.layout.dialog, null);
        final EditText editViewNomObjet = (EditText) v.findViewById(R.id.nomObjet);

        builder.setView(v)
                // Add action buttons
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        if(editViewNomObjet != null)
                        {
                            DemoObjet o = new DemoObjet();
                            o.nom = editViewNomObjet.getText().toString();
                            newObject evt = new newObject();
                            evt.demoObjet = o;
                            MonBus.bus.post(evt);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        MonDialog.this.getDialog().cancel();
                    }
                });

        return builder.create();
    }
}
