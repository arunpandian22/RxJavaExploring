package me.arun.arunrxjavaexploring.RoomDB.models;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.RoomWarnings;

import java.util.Date;

@SuppressWarnings(RoomWarnings.PRIMARY_KEY_FROM_EMBEDDED_IS_DROPPED)
@Entity
public class Student
{
  public   String Name;
  @PrimaryKey(autoGenerate = true)
  public   int id;
   public String Address;
   public boolean isMale;
   public Date createdAt;
   @Embedded
  public   Marks marks;
}
