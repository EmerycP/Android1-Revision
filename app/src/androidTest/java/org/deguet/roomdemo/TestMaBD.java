package org.deguet.roomdemo;

import android.content.Context;
import android.util.Log;

import org.deguet.roomdemo.dao.MaBD;
import org.deguet.roomdemo.modele.DemoObjet;
import org.deguet.roomdemo.modele.DemoIgnore;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.runner.AndroidJUnit4;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class TestMaBD {

    @Test
    public void testPeu() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();
        for (int i = 0 ; i < 10 ; i++ ) {
            DemoObjet objet = new DemoObjet();
            objet.nom = "nomObjet " + i;
            objet.date = new Date();
            objet.quantite = i;
            bd.dao().creerObjet(objet);
        }
        List<DemoObjet> objets = bd.dao().tousObjets();
        assertEquals(10, objets.size());
        bd.close();
    }
    @Test
    public void testBeaucoup() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();
        for (int i = 0 ; i < 100 ; i++ ) {
            DemoObjet objet = new DemoObjet();
            objet.nom = "nomObjet " + i;
            objet.date = new Date();
            objet.quantite = i;
            bd.dao().creerObjet(objet);
        }
        List<DemoObjet> objets = bd.dao().tousObjets();
        assertEquals(100, objets.size());
        bd.close();
    }
    @Test
    public void testUnById() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();

        DemoObjet objet = new DemoObjet();
        objet.nom = "nomObjet";
        objet.date = new Date();
        objet.quantite = 3;

        //Objet du DAO possède le ID, l'objet créer non.
        Long id = bd.dao().creerObjet(objet);

        DemoObjet objets = bd.dao().objetById(id);
        assertEquals(objets.nom, objet.nom);
        assertEquals(objets.quantite, objet.quantite);
        assertEquals(objets.date, objet.date);

        bd.close();
    }

    @Test
    public void testDeleteUn() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();

        for (int i = 0 ; i < 10 ; i++ ) {
            DemoObjet objet = new DemoObjet();
            objet.nom = "nomObjet " + i;
            objet.date = new Date();
            objet.quantite = i;
            bd.dao().creerObjet(objet);
        }
        List<DemoObjet> objets = bd.dao().tousObjets();
        assertEquals(10, objets.size());
        bd.dao().DeleteTousObjets();
        objets = bd.dao().tousObjets();
        assertEquals(0, objets.size());
        bd.close();
    }

    @Test
    public void testUpdateChamp() {
        Context context = ApplicationProvider.getApplicationContext();
        MaBD bd = Room.inMemoryDatabaseBuilder(context, MaBD.class).build();
        // MaBD bd = Room.databaseBuilder(context, MaBD.class, "test").build();

        DemoObjet objet = new DemoObjet();
        objet.nom = "nomObjet";
        objet.date = new Date();
        objet.quantite = 3;
        Long id = bd.dao().creerObjet(objet);

        DemoObjet objectTest = bd.dao().objetById(id);
        assertEquals(objectTest.nom, objet.nom);
        assertEquals(objectTest.quantite, objet.quantite);
        assertEquals(objectTest.date, objet.date);

        bd.dao().updateNameObjet("allo", id);
        objectTest = bd.dao().objetById(id);
        assertEquals("allo", objectTest.nom);

        bd.close();
    }



}
