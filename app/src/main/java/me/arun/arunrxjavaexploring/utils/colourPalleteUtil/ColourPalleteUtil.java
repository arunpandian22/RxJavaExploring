package me.arun.arunrxjavaexploring.utils.colourPalleteUtil;

import android.graphics.Bitmap;
import android.support.v7.graphics.Palette;

/**
 * Created by arun on 25/2/18.
 */
public class ColourPalleteUtil {
   PalleteColourListener palleteColourListener;

    public void getImageColorData(Bitmap bitmap, final PalleteColourListener palleteColourListener){
        this.palleteColourListener=palleteColourListener;
        Palette.Builder builder = Palette.from(bitmap);

        builder.generate(new Palette.PaletteAsyncListener()
        {
            @Override
            public void onGenerated(Palette palette)
            {
              palleteColourListener.getColurPallete(palette);
            }
        }) ;
    }

}