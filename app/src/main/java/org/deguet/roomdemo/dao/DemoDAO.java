package org.deguet.roomdemo.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import org.deguet.roomdemo.modele.DemoObjet;

import java.util.List;

@Dao
public abstract class DemoDAO {

    @Insert
    public abstract Long creerObjet(DemoObjet o);

    @Query("Select * from DemoObjet")
    public abstract List<DemoObjet> tousObjets();

    @Query("Select * from DemoObjet where id = :d")
    public abstract DemoObjet objetById(Long d);

    @Query("Delete from DemoObjet")
    public abstract void DeleteTousObjets();

    @Query("Update DemoObjet SET nom = :name where id = :id")
    public abstract void updateNameObjet(String name, Long id);


}
