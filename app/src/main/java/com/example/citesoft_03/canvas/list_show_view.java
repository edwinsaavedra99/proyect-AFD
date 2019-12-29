package com.example.citesoft_03.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class list_show_view extends View {

    private CheckBox checkBox;
    private int color[]={183,149,11};
    protected ArrayList<Figura> segmentation;

    public list_show_view(Context context, CheckBox checkBox) {
        super(context);
        this.checkBox = checkBox;
        segmentation = new ArrayList<Figura>();
    }
    public void addCirculoSegmentation(float x, float y, float radio) {
        System.out.println("error");
        float[] intervals = new float[]{0.0f, 0.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint pincela;
        pincela = new Paint();
        pincela.setAntiAlias(true);
        pincela.setARGB(250, color[0],color[1],color[2]);
        pincela.setStrokeWidth(4);
        pincela.setStyle(Paint.Style.FILL);
        pincela.setPathEffect(dashPathEffect);
        Circulo aux = new Circulo(x, y, radio, pincela,color);
        this.segmentation.add(aux);
    }
    public void after(){
        if(segmentation.size() > 0){
            segmentation.remove(segmentation.size()-1);
        }
    }
    protected void onDraw(Canvas canvas) {
        for (int i = 0; i < segmentation.size(); i++) {
            System.out.println("draw");
            if (segmentation.get(i) instanceof Circulo) {
                Circulo temp = (Circulo) segmentation.get(i);
                if (i == segmentation.size() - 1) {
                    temp.getPaint().setStyle(Paint.Style.STROKE);
                } else {
                    temp.getPaint().setStyle(Paint.Style.FILL);
                }
                canvas.drawCircle(temp.getX(), temp.getY(), temp.getRadio(), temp.getPaint());
            } else {
                System.out.println("DEMO SOLO SEGMENTO CON CIRCULOS ...");
            }
        }
    }
}
