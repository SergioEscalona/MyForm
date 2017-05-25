package sergioescalona.myform.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import sergioescalona.myform.R;

public class ThirdActivity extends AppCompatActivity {

    private ImageButton imageButtonConfirm;
    private Button buttonShare;
    private String name;
    private int age;
    private int typeOfMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);

        // Activar la flecha para volver al activity principal
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        imageButtonConfirm = (ImageButton) findViewById(R.id.buttonConfirm);
        buttonShare = (Button) findViewById(R.id.buttonToShare);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            name = bundle.getString("name");
            age = bundle.getInt("age");
            typeOfMessage = bundle.getInt("option");
        }


        imageButtonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                imageButtonConfirm.setVisibility(View.INVISIBLE);
                buttonShare.setVisibility(View.VISIBLE);
                Toast.makeText(ThirdActivity.this, createMessage(name, age, typeOfMessage), Toast.LENGTH_SHORT).show();

            }
        });

        // Evento click del botón share
        buttonShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TEXT, createMessage(name, age, typeOfMessage));
                startActivity(intent);
            }
        });
    }



    private String createMessage(String name, int age, int typeOfMessage) {
        if (typeOfMessage == SecondActivity.GREETER) {
            return "Hola " + name + ", ¿Cómo llevas esos " + age + " años? #MyForm";
        } else {
            return "Espero verte pronto " + name + ", antes que cumplas " + (age + 1) + ".. #MyForm";
        }
    }
}


