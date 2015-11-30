package cronometro.ufsc.br.cronometro;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Handler handler;

    private TCPIPClientSocket tcp;

    private EditText editText_ip;
    private Button button_ip;

    private Button button_1min;
    private Button button_2min;
    private Button button_3min;

    private Button button_iniciar;
    private Button button_parar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_ip = (EditText) findViewById(R.id.edittext_ip);
        button_ip = (Button) findViewById(R.id.button_conectar);
        button_ip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        tcp = new TCPIPClientSocket(editText_ip.getText().toString(), 9999);
                        disableComponentes();
                    }
                }).start();
            }
        });

        button_1min = (Button) findViewById(R.id.button_1min);
        button_1min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcp.sendMessage("1");
            }
        });

        button_2min = (Button) findViewById(R.id.button_2min);
        button_2min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcp.sendMessage("2");
            }
        });

        button_3min = (Button) findViewById(R.id.button_3min);
        button_3min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcp.sendMessage("3");
            }
        });

        button_iniciar = (Button) findViewById(R.id.button_iniciar);
        button_iniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcp.sendMessage("4");
            }
        });

        button_parar = (Button) findViewById(R.id.button_parar);
        button_parar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tcp.sendMessage("5");
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        handler = new Handler();
    }

    protected void onDestroy() {
        super.onDestroy();
    }

    private void disableComponentes() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this,"Conectado com sucesso!", Toast.LENGTH_LONG).show();
                editText_ip.setEnabled(false);
                button_ip.setEnabled(false);
            }
        });
    }
}
