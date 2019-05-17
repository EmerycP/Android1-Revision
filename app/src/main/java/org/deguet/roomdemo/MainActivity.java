package org.deguet.roomdemo;

import android.os.Bundle;
import android.view.View;

import org.deguet.roomdemo.dao.MaBD;
import org.deguet.roomdemo.evt.newObject;
import org.deguet.roomdemo.modele.DemoObjet;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.squareup.otto.Subscribe;

public class MainActivity extends AppCompatActivity {

    public monAdapteur adapteur;
    public List<GUIObjet> listObjets = new ArrayList<>();
    public MaBD bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bd = Room.databaseBuilder(getApplicationContext(), MaBD.class, "pipo")
                .allowMainThreadQueries()
                .build();


        RecyclerView recycler = findViewById(R.id.recyclerView);
        recycler.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler.setLayoutManager(layoutManager);

        adapteur = new monAdapteur(listObjets, getBaseContext());
        recycler.setAdapter(adapteur);
        adapteur.notifyDataSetChanged();



    }

    public void clickDialog(View view) {
        DialogFragment df = new MonDialog();
        df.show(getSupportFragmentManager(), "AjouterItem");
    }

    public void click(View view) {
        GUIObjet objet = new GUIObjet();
        DemoObjet dO = new DemoObjet();
        objet.demoObjet = dO;
        objet.demoObjet.nom = "Objet " + listObjets.size();
        objet.quantite = 1;
        listObjets.add(objet);
        adapteur.notifyDataSetChanged();
    }


    //Bus
    @Subscribe
    public void ajoutObjet(newObject obj)
    {
        //Long id = bd.dao().creerObjet(obj.demoObjet);
        //obj.demoObjet.id = id;
        GUIObjet objet = new GUIObjet();
        objet.demoObjet = obj.demoObjet;
        objet.quantite = 1;
        listObjets.add(objet);
        adapteur.notifyDataSetChanged();
    }
    @Override
    protected void onPause() {
        super.onPause();
        MonBus.bus.unregister(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MonBus.bus.register(this);
    }
}
