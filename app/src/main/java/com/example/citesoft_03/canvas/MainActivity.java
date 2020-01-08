package com.example.citesoft_03.canvas;
/*Import*/
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {
    /*Declaration*/
    String archivo;
    String carpeta;
    File file;
    private Button creator_circles;
    private Button delete_circles;
    private Button creator_line;
    private Button creator_rectangle;
    private Button paleta_blue;
    private Button paleta_green;
    private Button paleta_red;
    private Button paleta_celeste;
    private Button save_1;
    private Button load_1;
    private Button segmenta;
    private LinearLayout imagen;
    private LinearLayout includee;
    private list_Figura lista_figura;
    private int color[]={183,149,11};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Initialitation*/
        creator_circles = (Button) findViewById(R.id.button_circle);
        creator_line = (Button) findViewById(R.id.button_line);
        creator_rectangle = (Button) findViewById(R.id.button_rectangle);
        delete_circles = (Button) findViewById(R.id.button_delete);
        paleta_blue = (Button) findViewById(R.id.paleta_blue);
        paleta_green = (Button) findViewById(R.id.paleta_green);
        paleta_red = (Button) findViewById(R.id.paleta_red);
        paleta_celeste = (Button) findViewById(R.id.paleta_celeste);
        save_1 = (Button) findViewById(R.id.button_save);
        load_1 =(Button) findViewById(R.id.button_load);
        segmenta = (Button) findViewById(R.id.button_segmenta);
        archivo = "miarchivo";
        carpeta = "/carpeta/";
        //crear_archivo_json();
        lista_figura = new list_Figura(this);
        imagen = (LinearLayout) findViewById(R.id.img2);
        imagen.addView(lista_figura);
        creator_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    lista_figura.addCirculo(100,100,100);
                    lista_figura.actualizar();
            }
        });
        creator_rectangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    lista_figura.addRectangulo(0, 100, 400, 300);
                    lista_figura.actualizar();
            }
        });
        creator_line.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    lista_figura.addLinea(50, 50, 150, 400);
                    lista_figura.actualizar();
            }
        });
        save_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color azul
                //escribir_archivo_json();
                writeJson();
                //System.out.println(lista_figura.toStringSegmentation());
              /*  try {
                    FileWriter data = new FileWriter(Environment.getExternalStorageDirectory() +"/Canvas/Archivos/data.json");
                    data.write(lista_figura.toStringSegmentation());
                    data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }*/
            }
        });
        load_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color azul

            }
        });

        paleta_blue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color azul
                color=Util.color2;
                lista_figura.cambiarColor(color);
                lista_figura.actualizar();
            }
        });

        paleta_green.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color verde
                color=Util.color3;
                lista_figura.cambiarColor(color);
                lista_figura.actualizar();
            }
        });

        paleta_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color rojo
                color=Util.color4;
                lista_figura.cambiarColor(color);
                lista_figura.actualizar();
            }
        });

        paleta_celeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //codigo de color celeste
                color=Util.color7;
                lista_figura.cambiarColor(color);
                lista_figura.actualizar();
            }
        });

        delete_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_figura.deleteFigure();
                lista_figura.actualizar();
            }
        });
        segmenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,TwoActivity.class);
                startActivity(i);
                //lista_figura.after();
                //lista_figura.actualizar();
            }
        });
    }
    public void crear_archivo_json(){
        String file_path= (Environment.getExternalStorageDirectory() +  carpeta );
        //  String file_path= ("/storage" +  carpeta );
        File localFile = new File(file_path);
        if(!localFile.exists()){
            localFile.mkdirs();
        }
        this.file = new File(localFile,archivo);
            try{
                file.createNewFile();

            }catch (Exception e){
                e.printStackTrace();
            }
    }
    //acuerdate que lo modificaste
    public void escribir_archivo_json(){
            FileWriter fileWriter=null;
            PrintWriter printWriter = null;
            try{
                fileWriter = new FileWriter(file);
                printWriter = new PrintWriter(fileWriter);
                printWriter.print(lista_figura.toString());
                printWriter.flush();
                printWriter.close();
            }catch (Exception e){
                e.printStackTrace();
            } finally {
                try{
                    if(null!=fileWriter){
                        fileWriter.close();
                    }

                }catch (Exception e2){
                    e2.printStackTrace();
                }

            }
        }

    public void readJson() {
        this.lista_figura.clear_list();
        String jsonString = IOHelper.stringFromAsset(this, "circles.json");
        try {
            //JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray circulos_saved = new JSONArray(jsonString);

            String result = "";
            for (int i = 0; i < circulos_saved.length(); i++) {
                JSONObject circ = circulos_saved.getJSONObject(i);
                //new Gson().fromJson(city.toString(), City.class);

               // this.lista_figura.add(circ.getLong("x"),circ.getLong("y"),circ.getLong("radio"));
            }
            this.lista_figura.actualizar();
        } catch (Exception e) {
            System.out.print("dooooo");
        }
    }

    public void writeJson() {
       // IOHelper.writeToFile(this, "circles.json", this.lista_figura.toString());
    }
}