package me.arun.arunrxjavaexploring.RoomDB.localdb;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import me.arun.arunrxjavaexploring.RoomDB.dao.MarkDao;
import me.arun.arunrxjavaexploring.RoomDB.dao.StudentDao;
import me.arun.arunrxjavaexploring.RoomDB.models.Marks;
import me.arun.arunrxjavaexploring.RoomDB.models.Student;
import me.arun.arunrxjavaexploring.RoomDB.typeConverters.Converters;


@Database(entities = {Student.class, Marks.class}, version = 1, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;
    public abstract MarkDao markDao();
    public abstract StudentDao studentDao();
    public static AppDataBase getAppDatabase(Context context)
    {
        if (instance == null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "roomdb")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    //        .addMigrations(MIGRATION_1_2,MIGRATION_2_3)


}