package KoloFortuny.sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class Field extends Label{
    private char sign=' ';
    private int width=50;
    private int heigth=50;

    public Field(char fromString) {
            super();
           // this.minWidth(width);
           // this.minHeight(heigth);
            sign = fromString;
            this.setPadding(new Insets(5,5,5,5));
            this.setMinSize(width,heigth);
            this.setAlignment(Pos.CENTER);
            this.setFont(new Font("Verdana",25));
            if(!(fromString=='_')) {
                this.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, null, new BorderWidths(2))));
                this.setText("  ");
            }
        }


    public void GetSign()
    {
        System.out.println(sign);
    }
    public void SetSign(char si)
    {
        sign=si;
    }
}
