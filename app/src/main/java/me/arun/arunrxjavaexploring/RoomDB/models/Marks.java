package me.arun.arunrxjavaexploring.RoomDB.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Marks
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "mid")
   public int studentId;
  public   int sub1;
   public int sub2;
   public int sub3;
}
