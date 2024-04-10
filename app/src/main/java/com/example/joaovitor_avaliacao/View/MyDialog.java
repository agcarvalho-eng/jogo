package com.example.joaovitor_avaliacao.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class MyDialog extends DialogFragment
{
    protected String title;
    protected Context contextoAtual;

    public MyDialog(String title, Context contextoAtual)
    {
        this.title = title;
        this.contextoAtual = contextoAtual;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    startActivity(new Intent(contextoAtual, MainActivity.class));
                })
                .setNegativeButton("Não", (dialogInterface, i) -> {
                    startActivity(new Intent(contextoAtual, PontuacoesActivity.class));
                });
        return builder.create();
    }
}