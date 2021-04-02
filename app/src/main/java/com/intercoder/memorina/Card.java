package com.intercoder.memorina;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;

//TODO: can be done as two separate files
class Card {
    Paint p = new Paint();
    int color_index;
    float x, y, col, row, max_width, max_height;
    int width, height;
    boolean isOpen = false;
    float scale_factor = (float) 557/350;
    Context ctx;
    Resources res;
    //TODO: failed

    int[] res_icons = {R.drawable.card_1, R.drawable.card_2, R.drawable.card_3, R.drawable.card_4,
            R.drawable.card_5, R.drawable.card_6, R.drawable.card_7, R.drawable.card_8,
            R.drawable.card_9, R.drawable.card_10};

    public Card(float x, float y, float col, float row, int color, Context ctx) {
        this.color_index = color;
        this.x = x;
        this.y = y;
        this.col = col;
        this.row = row;
        this.ctx = ctx;
        res = ctx.getResources();

    }

    public void draw(Canvas c) {
        width = c.getWidth();
        height = c.getHeight();

        max_width = (float) width / 5;
        max_height = (float) max_width * scale_factor;

        float shift_x = max_width/2;
        float shift_y = max_height/2;
        float w = width/col;
        float h = height/row;

        Drawable image;
        if(isOpen && color_index != -1) {
            image = res.getDrawable(res_icons[color_index], null);
        }else{
            image = res.getDrawable(R.drawable.card_closed, null);
        }
        image.setBounds((int)(x*w - shift_x + w/2), (int)(y*h - shift_y + h/2),
                (int)(x*w+shift_x + w/2),(int)(y*h + shift_y + h/2));
        image.draw(c);
    }

    public boolean flip (float touch_x, float touch_y) {
        float shift_x = max_width/2;
        float shift_y = max_height/2;
        float w = width/col;
        float h = height/row;
        if (touch_x >= (x*w - shift_x + w/2) && touch_x <= (x*w + shift_x + w/2) &&
                touch_y >= (y*h - shift_y + h/2) && touch_y <= y*h + shift_y + h/2) {
            isOpen =! isOpen;
            return true;
        }
        return false;
    }

}
