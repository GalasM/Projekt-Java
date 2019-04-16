package KoloFortuny.sample;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import static java.lang.Character.*;


public class Field extends Label{
    private char sign=' ';
    private int width=50;
    private int heigth=50;

    public Field(char fromString) {
            super();
            this.minWidth(width);
            this.minHeight(heigth);
            sign = fromString;
            this.setPadding(new Insets(4,4,4,4));
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
