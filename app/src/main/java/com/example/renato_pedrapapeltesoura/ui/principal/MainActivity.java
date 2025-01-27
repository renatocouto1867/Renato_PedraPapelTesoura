package com.example.renato_pedrapapeltesoura.ui.principal;

//<a href="https://www.flaticon.com/br/icones-gratis/pedra-papel-tesoura" title="pedra papel tesoura ícones">Pedra papel tesoura ícones criados por Freepik - Flaticon</a>
//https://www.youtube.com/watch?v=mybZyqlE1tw - Pedra- Papel ou Tesoura - Paco O Marinheiro | O Reino Infantil

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.renato_pedrapapeltesoura.R;
import com.example.renato_pedrapapeltesoura.ui.inicial.InicialFragment;

public class MainActivity extends AppCompatActivity {
    private MainActivityViewModel viewModel;

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

        iniciarFragment(InicialFragment.newInstance());

    }

    @Nullable
    @Override
    public View onCreateView(@Nullable View parent, @NonNull String name, @NonNull Context context, @NonNull AttributeSet attrs) {
        View view = super.onCreateView(parent, name, context, attrs);

        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.carregaHistorico(this);


        return view;
    }

    private void iniciarFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.salvaHistorico(this);
    }
}