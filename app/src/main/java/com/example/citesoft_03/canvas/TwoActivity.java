package com.example.citesoft_03.canvas;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import java.io.File;
public class TwoActivity extends AppCompatActivity {
    private float xBegin;
    private float yBeging;
    private Button delete_circles;
    private LinearLayout imagen;
    private list_Segmentation lista_figura;
    private CheckBox checkBox;
    private  LinearLayout includee;
    private int color[]={183,149,11};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_segmentation);
        checkBox=(CheckBox) findViewById(R.id.check1);
        includee = (LinearLayout) findViewById(R.id.img3);
        includee.setVisibility(View.INVISIBLE);
        lista_figura = new list_Segmentation(this,checkBox,includee);
        delete_circles = (Button) findViewById(R.id.button_delete2);
        imagen = (LinearLayout) findViewById(R.id.imgfemur);
        imagen.addView(lista_figura);
        delete_circles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_figura.after();
                lista_figura.actualizar();
            }
        });
    }
}