package com.kodetr.resepmakanan;

import android.database.Cursor;

public interface MakananInterface {

    public Cursor read();

    public void create(ResepMakanan makanan);

    public void update(ResepMakanan makanan);

    public void delete(int id);

}
