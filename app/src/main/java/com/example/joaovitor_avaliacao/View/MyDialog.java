package com.example.joaovitor_avaliacao.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class MyDialog extends DialogFragment
{
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Deseja jogar novamente?")
                .setPositiveButton(android.R.string.ok, (dialogInterface, i) -> {
                })
                .setNegativeButton(android.R.string.cancel, (dialogInterface, i) -> {
                    Toast.makeText(getContext(), "Operação cancelada!", Toast.LENGTH_SHORT).show();
                });
        return builder.create();
    }
}