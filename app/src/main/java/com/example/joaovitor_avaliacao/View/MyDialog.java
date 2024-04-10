package com.example.joaovitor_avaliacao.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.joaovitor_avaliacao.Model.Jogada;

import java.util.ArrayList;

public class MyDialog extends DialogFragment
{
    private String title;

    public MyDialog(String title) {
        this.title = title;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setPositiveButton("Sim", (dialogInterface, i) -> {
                    startActivity(new Intent(getContext(), MainActivity.class));
                })
                .setNegativeButton("Não", (dialogInterface, i) -> {
                    startActivity(new Intent(getContext(), PontuacoesActivity.class));
                });
        return builder.create();
    }
}