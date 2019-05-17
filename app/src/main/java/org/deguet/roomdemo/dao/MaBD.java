package org.deguet.roomdemo.dao;

import org.deguet.roomdemo.modele.DemoObjet;
import org.deguet.roomdemo.modele.DemoIgnore;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {DemoObjet.class, DemoIgnore.class},
        version = 1)
@TypeConverters({Converters.class})
public abstract class MaBD   extends RoomDatabase{
    public abstract DemoDAO dao();
}
