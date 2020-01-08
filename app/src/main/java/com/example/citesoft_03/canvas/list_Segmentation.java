package com.example.citesoft_03.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.provider.Settings;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class list_Segmentation extends View {

    protected ArrayList<Figura> segmentation;
    protected float globalx;
    protected float globaly;
    private int color[]={183,149,11};
    private LinearLayout includee;
    private CheckBox checkBox;
    protected list_show_view zoom;
    public list_Segmentation(Context context,CheckBox checkBox, LinearLayout includee){
        super(context);
        this.includee = includee;
        this.includee.setVisibility(INVISIBLE);
        segmentation = new ArrayList<Figura>();
        this.checkBox = checkBox;
        zoom = new list_show_view(context,checkBox);
        includee.addView(zoom);
    }
    public void addCirculoSegmentation(float x, float y, float radio) {
        zoom.addCirculoSegmentation(x*100/315,y*180/514,radio*100*180/(315*514));
        zoom.invalidate();
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
    public void clear_list(){
        this.segmentation.clear();
    }
    protected void onDraw(Canvas canvas) {
        for(int i=0;i<segmentation.size();i++){
            if (segmentation.get(i) instanceof Circulo) {
                Circulo temp = (Circulo) segmentation.get(i);
                if(i==segmentation.size()-1){
                    temp.getPaint().setStyle(Paint.Style.STROKE);
                }else{
                    temp.getPaint().setStyle(Paint.Style.FILL);
                }
                canvas.drawCircle(temp.getX(), temp.getY(), temp.getRadio(), temp.getPaint());
            }else{
                System.out.println("DEMO SOLO SEGMENTO CON CIRCULOS ...");
            }
        }
    }
    public void actualizar(){
        invalidate();
    }
    public String toStringSegmentation(){
        String list_json = "[\n";
        for(int i=0;i<this.segmentation.size();i++){
            list_json = list_json+segmentation.get(i).toString()+"\n";
            if(i<this.segmentation.size()-1){
                list_json = list_json+",";
            }
        }
        return list_json+"]";
    }
    public boolean onTouchEvent(MotionEvent event){
        float getx = event.getX();
        float gety = event.getY();
        this.globalx = getx;
        this.globaly = gety;
        if (event.getAction() == MotionEvent.ACTION_MOVE) {
            if (checkBox.isChecked()) {
                includee.setVisibility(VISIBLE);
                if (!segmentation.isEmpty()) {
                    boolean drawS = false;
                    Figura aux = segmentation.get(segmentation.size() - 1);
                    float centrox = aux.getX() - event.getX();
                    float centroy = aux.getY() - event.getY();
                    //50 y 60 es el intervalo de posible segmentacion
                    if (Math.sqrt(centrox * centrox + centroy * centroy) > 50 && Math.sqrt(centrox * centrox + centroy * centroy) < 60) {
                        drawS = true;
                    }
                    if (drawS)
                        addCirculoSegmentation(getx, gety, 9);
                } else {
                    addCirculoSegmentation(getx, gety, 9);
                }
            } else {
                includee.setVisibility(INVISIBLE);
            }
            invalidate();
        }
        if(event.getAction() == MotionEvent.ACTION_UP){
            includee.setVisibility(INVISIBLE);
        }
        return true;
    }
}
