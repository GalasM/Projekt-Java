package KoloFortuny.sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


class Field extends Label{

    Field(char fromString) {
            super();
            this.setPadding(new Insets(5,5,5,5));
        int width = 50;
        int heigth = 50;
        this.setMinSize(width, heigth);
            this.setAlignment(Pos.CENTER);
            this.setFont(new Font("Arial",25));
            if(!(fromString=='_')) {
                this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
                }
            }



   /* public void GetSign()
    {
        System.out.println(sign);
    }
    public void SetSign(char si)
    {
        sign=si;
    }*/
}
