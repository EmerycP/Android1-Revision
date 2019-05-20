package org.deguet.roomdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import org.deguet.roomdemo.modele.DemoObjet;

import java.util.List;

    public class monAdapteur extends RecyclerView.Adapter<monAdapteur.MyViewHolder> {
        public List<GUIObjet> mDataset;
        public Context context;

        // Provide a reference to the views for each data item
        // Complex data items may need more than one view per item, and
        // you provide access to all the views for a data item in a view holder
        public static class MyViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView tvNomProduit;
            public Button btnPlus;
            public Button btnMoins;
            public TextView tvNombreItem;
            public MyViewHolder(LinearLayout v) {
                super(v);
                tvNomProduit = v.findViewById(R.id.textView);
                btnPlus = v.findViewById(R.id.buttonPlus);
                btnMoins = v.findViewById(R.id.buttonMoins);
                tvNombreItem = v.findViewById(R.id.textQuantite);
            }
        }

        // Provide a suitable constructor (depends on the kind of dataset)
        public monAdapteur(List<GUIObjet> myDataset, Context ctx) {
            mDataset = myDataset;
            this.context = ctx;
        }

        // Create new views (invoked by the layout manager)
        @Override
        public monAdapteur.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
            // create a new view
            LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            MyViewHolder vh = new MyViewHolder(v);
            return vh;
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            // - get element from your dataset at this position
            // - replace the contents of the view with that element
            final GUIObjet objetActuel = mDataset.get(position);

            holder.tvNomProduit.setText(objetActuel.demoObjet.nom);
            holder.btnPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    objetActuel.quantite += 1;
                    notifyDataSetChanged();
                }
            });
            holder.btnMoins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (objetActuel.quantite == 1)
                        mDataset.remove(position);
                    else
                        objetActuel.quantite -= 1;
                    notifyDataSetChanged();
                }
            });
            holder.tvNombreItem.setText(String.valueOf(objetActuel.quantite));
        }



        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
