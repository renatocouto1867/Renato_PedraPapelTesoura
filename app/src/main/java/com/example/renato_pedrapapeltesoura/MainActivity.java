package com.example.renato_pedrapapeltesoura;

//<a href="https://www.flaticon.com/br/icones-gratis/pedra-papel-tesoura" title="pedra papel tesoura ícones">Pedra papel tesoura ícones criados por Freepik - Flaticon</a>
//https://www.youtube.com/watch?v=mybZyqlE1tw - Pedra- Papel ou Tesoura - Paco O Marinheiro | O Reino Infantil

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}