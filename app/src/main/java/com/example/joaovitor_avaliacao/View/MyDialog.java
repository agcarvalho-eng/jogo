package com.example.joaovitor_avaliacao.View;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
public class MyDialog extends DialogFragment
{
    protected String title;
    protected DialogInterface.OnClickListener positivo;
    protected DialogInterface.OnClickListener negativo;

    public MyDialog(String title, DialogInterface.OnClickListener positivo, DialogInterface.OnClickListener negativo)
    {
        this.title = title;
        this.positivo = positivo;
        this.negativo = negativo;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title)
                .setPositiveButton("Sim", positivo)
                .setNegativeButton("Não", negativo);
        return builder.create();
    }
}