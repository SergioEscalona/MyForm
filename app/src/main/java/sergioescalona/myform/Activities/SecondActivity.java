package sergioescalona.myform.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import sergioescalona.myform.R;

public class SecondActivity extends AppCompatActivity {

    //ELEMENTOS DE LA UI.
    private RadioButton radioGreeter;
    private RadioButton radioFarewell;
    private SeekBar seekBarAge;
    private TextView textViewAge;
    private Button btnNext;

    // OTROS VALORES
    private String name = "";
    private int age = 18;
    private final int minAge = 16;
    private final int maxAge = 60;
    public static final int GREETER = 1;
    public static final int FAREWELL = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Activar la flecha para volver al activity principal
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Recogemos el Bundle de la MainActivity con el String "name".
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            //Si el Bundle no está vacío, recogemos el nombre y lo volcamos en la variable
            //name.
            name = bundle.getString("name");
        }


        //INSTANCIAS A LOS ELEMENTOS DE LA UI.
        radioGreeter = (RadioButton) findViewById(R.id.radioButtonGreeter);
        radioFarewell = (RadioButton) findViewById(R.id.radioButtonFarewell);
        seekBarAge = (SeekBar) findViewById(R.id.seekBarAge);
        textViewAge = (TextView) findViewById(R.id.textViewCurrentAge);
        btnNext = (Button) findViewById(R.id.buttonToThirdActivity);


        //Recogemos la edad del SeekBar.

        seekBarAge.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int currentAge, boolean fromUser) {

                age = currentAge;
                textViewAge.setText(age + "");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            //Lo dejamos vacío.
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                age = seekBar.getProgress();
                textViewAge.setText(age + "");

                if (age > maxAge){

                    //Creamos un Toast que nos indica la restriccion de edad y esconde el botón "Siguiente"
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "La edad debe ser menor de "+maxAge+"años",Toast.LENGTH_SHORT).show();

                }else if (age < minAge){

                    //Creamos un Toast que nos indica la restriccion de edad y esconde el botón "Siguiente"
                    btnNext.setVisibility(View.INVISIBLE);
                    Toast.makeText(SecondActivity.this, "La edad debe ser mayor de "+minAge+"años",Toast.LENGTH_SHORT).show();

                }else {
                    //Si está correcto, volvemos a poner el botón visible.
                    btnNext.setVisibility(View.VISIBLE);
                }
            }
        });

        // Evento click del botón para pasar al siguiente Activity
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                intent.putExtra("name", name);
                intent.putExtra("age", age);
                // Si el botón de greeter esta activo, option valdrá 1, si no, 2
                int option = (radioGreeter.isChecked()) ? GREETER : FAREWELL;
                intent.putExtra("option", option);
                startActivity(intent);
                Toast.makeText(SecondActivity.this, seekBarAge.getProgress()+"", Toast.LENGTH_LONG).show();
            }
        });


    }
}