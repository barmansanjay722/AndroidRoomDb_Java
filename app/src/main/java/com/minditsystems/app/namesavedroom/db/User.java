package com.minditsystems.app.namesavedroom.db;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "Product_Name")
    public String productName;

    @ColumnInfo(name = "Product_Brand")
    public String productBrand;

    @ColumnInfo(name = "Description")
    public String description;
}
