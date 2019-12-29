package com.example.citesoft_03.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.io.File;
import java.util.ArrayList;

public class list_Figura extends View {
    int selected;
    protected ArrayList<Figura> segmentation;
    protected ArrayList<Figura> mis_figuras;
    protected float globalx;
    protected float globaly;
    private int color[]={183,149,11};
    private LinearLayout includee;
    int figura = -1;
    private CheckBox checkBox;
    protected list_show_view d;
    public list_Figura(Context context,CheckBox checkBox, LinearLayout includee) {
        super(context);
        d = new list_show_view(context,checkBox);
        this.includee = includee;
        includee.addView(d);
        mis_figuras = new ArrayList<Figura>();
        segmentation = new ArrayList<Figura>();
        this.checkBox = checkBox;
    }
    public void addCirculoSegmentation(float x, float y, float radio) {
        d.addCirculoSegmentation(x*100/315,y*180/514,radio*100*180/(315*514));
        d.invalidate();
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

    public void addCirculo(float x, float y, float radio) {
        float[] intervals = new float[]{0.0f, 0.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint pincela;
        pincela = new Paint();
        pincela.setAntiAlias(true);
        pincela.setARGB(250, color[0],color[1],color[2]);
        pincela.setStrokeWidth(4);
        pincela.setStyle(Paint.Style.STROKE);
        pincela.setPathEffect(dashPathEffect);
        Circulo aux = new Circulo(x, y, radio, pincela,color);
        this.mis_figuras.add(aux);
    }

    public void addRectangulo(float x, float y, float lado1, float lado2) {
        float[] intervals = new float[]{0.0f, 0.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint pincela;
        pincela = new Paint();
        pincela.setAntiAlias(true);
        pincela.setARGB(250, color[0],color[1],color[2]);
        pincela.setStrokeWidth(4);
        pincela.setStyle(Paint.Style.STROKE);
        pincela.setPathEffect(dashPathEffect);
        Rectangulo aux = new Rectangulo(x, y, lado1, lado2, pincela,color);
        this.mis_figuras.add(aux);
    }

    public void addElipse(float x, float y, float x1, float y1) {
        float[] intervals = new float[]{0.0f, 0.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint pincela;
        pincela = new Paint();
        pincela.setAntiAlias(true);
        pincela.setARGB(250, color[0],color[1],color[2]);
        pincela.setStrokeWidth(4);
        pincela.setStyle(Paint.Style.STROKE);
        pincela.setPathEffect(dashPathEffect);
        Elipse aux = new Elipse(x, y, x1, y1, pincela,color);
        this.mis_figuras.add(aux);
    }

    public void addLinea(float x, float y, float x1, float y1) {
        float[] intervals = new float[]{0.0f, 0.0f};
        float phase = 0;
        DashPathEffect dashPathEffect = new DashPathEffect(intervals, phase);
        Paint pincela;
        pincela = new Paint();
        pincela.setAntiAlias(true);
        pincela.setARGB(250, color[0],color[1],color[2]);
        pincela.setStrokeWidth(4);
        pincela.setStyle(Paint.Style.STROKE);
        pincela.setPathEffect(dashPathEffect);
        Linea aux = new Linea(x, y, x1, y1, pincela,color);
        this.mis_figuras.add(aux);
    }

    public void deleteFigure() {
        if (mis_figuras.size()>0 && this.selected != -1 ) {
            mis_figuras.remove(this.selected);
            figura = -1;
        }
    }
    public void after(){
        if(segmentation.size() > 0){
            segmentation.remove(segmentation.size()-1);
        }
    }
    public void cambiarColor(int [] color) {
        this.color = color;
        if (mis_figuras.size() != 0 && this.selected != -1) {
            mis_figuras.get(this.selected).setColor(this.color);
            mis_figuras.get(this.selected).getPaint().setARGB(255,this.color[0],this.color[1],this.color[2]);
            invalidate();
        }
    }
    public String toString(){
        String list_json = "[\n";
        for(int i=0;i<this.mis_figuras.size();i++){
            list_json = list_json+mis_figuras.get(i).toString()+"\n";
            if(i<this.mis_figuras.size()-1){
                list_json = list_json+",";
            }
        }
        return list_json+"]";
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
    public void clear_list(){
        this.mis_figuras.clear();
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
        for (int i = 0; i < mis_figuras.size(); i++) {
            if (mis_figuras.get(i) instanceof Circulo) {
                Circulo temp = (Circulo) mis_figuras.get(i);
                canvas.drawCircle(temp.getX(), temp.getY(), temp.getRadio(), temp.getPaint());
                canvas.drawCircle(temp.getX()+temp.getRadio(), temp.getY(), 10, Util.Circle_Small(temp.getColor()));
                if(i==figura){
                    canvas.drawCircle(temp.getX()+temp.getRadio(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                }
            } else if (mis_figuras.get(i) instanceof Rectangulo) {
                Rectangulo temp = (Rectangulo) mis_figuras.get(i);
                canvas.drawRect(temp.getX(), temp.getY(), temp.getX1(), temp.getY1(), temp.getPaint());
                canvas.drawCircle(temp.getX(), temp.getY(), 10,  Util.Circle_Small(temp.getColor()));
                canvas.drawCircle(temp.getX1(), temp.getY1(), 10,  Util.Circle_Small(temp.getColor()));
                if(i==figura){
                    canvas.drawCircle(temp.getX(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                    canvas.drawCircle(temp.getX1(), temp.getY1(), 30, Util.Circle_Transparente(temp.getColor()));
                }
            } else if (mis_figuras.get(i) instanceof Linea) {
                Linea temp = (Linea) mis_figuras.get(i);
                canvas.drawLine(temp.getX(), temp.getY(), temp.getFinX(), temp.getFinY(), temp.getPaint());
                canvas.drawCircle(temp.getX(), temp.getY(), 10,  Util.Circle_Small(temp.getColor()));
                canvas.drawCircle(temp.getFinX(), temp.getFinY(), 10,  Util.Circle_Small(temp.getColor()));
                if(i==figura){
                    canvas.drawCircle(temp.getX(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                    canvas.drawCircle(temp.getFinX(), temp.getFinY(), 30, Util.Circle_Transparente(temp.getColor()));
                }
            } else if (mis_figuras.get(i) instanceof Elipse) {
                Elipse temp = (Elipse) mis_figuras.get(i);
                RectF rectangulo1 = new RectF(temp.getX(), temp.getY(), temp.getX1(), temp.getY1());
                canvas.drawOval (rectangulo1, temp.getPaint());
                canvas.drawCircle(temp.getX(), temp.getY(), 10, Util.Circle_Small(temp.getColor()));
                canvas.drawCircle(temp.getX1(), temp.getY1(), 10, Util.Circle_Small(temp.getColor()));
                if(i==figura){
                    canvas.drawCircle(temp.getX(), temp.getY(), 30, Util.Circle_Transparente(temp.getColor()));
                    canvas.drawCircle(temp.getX1(), temp.getY1(), 30, Util.Circle_Transparente(temp.getColor()));
                }
            } else {
                System.out.println("Esperando Tipos");
            }
        }
    }
    public void actualizar() {
        invalidate();
    }
    float getx_pasado = 0;
    float gety_pasado = 0;
    public boolean onTouchEvent(MotionEvent event) {
        float getx = event.getX();
        float gety = event.getY();
        this.globalx = getx;
        this.globaly = gety;
        int acci = event.getAction();
        if (acci == MotionEvent.ACTION_DOWN) {
            getx_pasado=getx;
            gety_pasado=gety;
            for (int i = 0; i < mis_figuras.size(); i++) {
                //mis_figuras.get(i).getPaint().setStrokeWidth(4);
                if (mis_figuras.get(i) instanceof Circulo) {
                    Circulo temp = (Circulo) mis_figuras.get(i);
                    double cenx = getx - mis_figuras.get(i).getX();
                    double ceny = gety - mis_figuras.get(i).getY();
                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    if (distancia <= temp.getRadio()&& !checkBox.isChecked()) {
                        figura = i;
                        this.selected = i;
                        invalidate();
                    }
                } else if (mis_figuras.get(i) instanceof Rectangulo) {
                    Rectangulo temp = (Rectangulo) mis_figuras.get(i);
                    if (getx <= temp.getX1() && getx >= temp.getX() && gety >= temp.getY() && gety <= temp.getY1()&& !checkBox.isChecked()) {
                        figura = i;
                        this.selected = i;
                        invalidate();
                    }
                } else if (mis_figuras.get(i) instanceof Linea) {
                    Linea temp = (Linea) mis_figuras.get(i);
                    if (temp.distancia(getx, gety) <= 20&& !checkBox.isChecked()) {
                        figura = i;
                        this.selected = i;
                        invalidate();
                    }
                    //add canvas
                } else if (mis_figuras.get(i) instanceof Elipse) {
                    Elipse temp = (Elipse) mis_figuras.get(i);
                    if (getx <= temp.getX1() && getx >= temp.getX() && gety >= temp.getY() && gety <= temp.getY1()&& !checkBox.isChecked()) {
                        figura = i;
                        this.selected = i;
                        invalidate();
                    }
                }
                if (figura > -1 && !checkBox.isChecked()) {
                    this.selected = figura;
                    //mis_figuras.get(figura).getPaint().setStrokeWidth(9);
                }
            }
        }
       /* if(acci == MotionEvent.ACTION_UP){
            if(checkBox.isChecked()){
                if(segmentation.isEmpty()) {
                    addCirculoSegmentation(getx, gety, 9);
                }
            }
        }*/
        if (acci == MotionEvent.ACTION_MOVE) {
            if(checkBox.isChecked()){
                if(!segmentation.isEmpty()) {
                    boolean drawS = false;
                    Figura aux = segmentation.get(segmentation.size()-1);
                    float centrox = aux.getX() - event.getX();
                    float centroy = aux.getY() - event.getY();
                    //50 y 60 es el intervalo de posible segmentacion
                    if (Math.sqrt(centrox * centrox + centroy * centroy) > 50 && Math.sqrt(centrox * centrox + centroy * centroy)<60) {
                        drawS = true;
                    }
                    if(drawS)
                        addCirculoSegmentation(getx, gety, 9);
                }else{
                    addCirculoSegmentation(getx, gety, 9);
                }
            }
            if (figura > -1) {
                if (mis_figuras.get(figura) instanceof Circulo) {
                    Circulo temp=(Circulo)mis_figuras.get(figura);
                    float cenx = getx - (temp.getX()+temp.getRadio());
                    float ceny = gety - temp.getY();
                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    float cenx1 = getx - temp.getX();
                    float ceny1 = gety - temp.getY();
                    float distancia2 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                    if (distancia <= 40) {
                        temp.setRadio(temp.getRadio()-(getx_pasado-getx));
                    } else if (distancia2 <= temp.getRadio()-40) {
                        temp.setX(temp.getX()-(getx_pasado-getx));
                        temp.setY(temp.getY()-(gety_pasado-gety));
                    }
                } else if (mis_figuras.get(figura) instanceof Rectangulo) {
                    Rectangulo aux = (Rectangulo) mis_figuras.get(figura);
                    //implementado SECCION DE REDIX
                    double cenx = getx - aux.getX();
                    double ceny = gety - aux.getY();
                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    double cenx1 = getx - aux.getX1();
                    double ceny1 = gety - aux.getY1();
                    float mx = (aux.getX() + aux.getX1()) / 2;
                    float my = (aux.getY() + aux.getY1()) / 2;
                    double cx = getx - mx;
                    double cy = gety - my;
                    float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                    float largo = (aux.getX1() - aux.getX()) / 2;
                    float ancho = (aux.getY() - aux.getY1()) / 2;
                    float minimo;
                    if (largo <= ancho)
                        minimo = largo;
                    else
                        minimo = ancho;
                    float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                    if (distancia <= 60) {
                        aux.setX(getx);
                        aux.setY(gety);
                    } else if (distancia1 <= 60) {
                        aux.setX1(getx);
                        aux.setY1(gety);
                        //} else if (distancia2 <= minimo * 2 / 3) {
                    } else if (distancia2 <= largo*2/3 || distancia2<= ancho*2/3) {
                        aux.setX1(getx + largo);
                        aux.setY1(gety - ancho);
                        aux.setX(getx - largo);
                        aux.setY(gety + ancho);
                    }
                } else if (mis_figuras.get(figura) instanceof Linea) {
                    Linea temp = (Linea) mis_figuras.get(figura);

                    double cenx = getx - temp.getX();
                    double ceny = gety - temp.getY();
                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);

                    double cenx1 = getx - temp.getFinX();
                    double ceny1 = gety - temp.getFinY();

                    float mx = (temp.getX() + temp.getFinX()) / 2;
                    float my = (temp.getY() + temp.getFinY()) / 2;
                    double cx = getx - mx;
                    double cy = gety - my;
                    float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                    float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                    if (distancia <= 40) {
                        temp.setX(getx);
                        temp.setY(gety);
                    } else if (distancia1 <= 40) {
                        temp.setFinX(getx);
                        temp.setFinY(gety);
                    } else if (distancia2 <= 30) {
                        float largo = (temp.getFinX() - temp.getX()) / 2;
                        float ancho = (temp.getY() - temp.getFinY()) / 2;
                        temp.setFinX(getx + largo);
                        temp.setFinY(gety - ancho);
                        temp.setX(getx - largo);
                        temp.setY(gety + ancho);
                    }
                    //add canvas
                } else if (mis_figuras.get(figura) instanceof Elipse) {
                    Elipse aux = (Elipse) mis_figuras.get(figura);
                    //implementado SECCION DE REDIX
                    double cenx = getx - aux.getX();
                    double ceny = gety - aux.getY();
                    float distancia = (float) Math.sqrt(cenx * cenx + ceny * ceny);
                    double cenx1 = getx - aux.getX1();
                    double ceny1 = gety - aux.getY1();
                    float mx = (aux.getX() + aux.getX1()) / 2;
                    float my = (aux.getY() + aux.getY1()) / 2;
                    double cx = getx - mx;
                    double cy = gety - my;
                    float distancia2 = (float) Math.sqrt(cx * cx + cy * cy);
                    float largo = (aux.getX1() - aux.getX()) / 2;
                    float ancho = (aux.getY() - aux.getY1()) / 2;
                    float minimo;
                    if (largo <= ancho)
                        minimo = largo;
                    else
                        minimo = ancho;
                    float distancia1 = (float) Math.sqrt(cenx1 * cenx1 + ceny1 * ceny1);
                    if (distancia <= 60) {
                        aux.setX(getx);
                        aux.setY(gety);
                    } else if (distancia1 <= 60) {
                        aux.setX1(getx);
                        aux.setY1(gety);
                        //} else if (distancia2 <= minimo * 2 / 3) {
                    } else if (distancia2 <= largo*2/3 || distancia2<= ancho*2/3) {
                        aux.setX1(getx + largo);
                        aux.setY1(gety - ancho);
                        aux.setX(getx - largo);
                        aux.setY(gety + ancho);
                    }
                    //add canvas
                } else {
                    File file;
                    System.out.println("TIPO NO RECONOCIDO");
                }
            }
            getx_pasado=getx;
            gety_pasado=gety;
            invalidate();
        }
        return true;
    }
}
