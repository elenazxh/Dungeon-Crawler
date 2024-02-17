package com.example.a2340a_team10.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.a2340a_team10.R;
import com.example.a2340a_team10.model.Player;

public class LoseAlert extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.lose_title)
                .setMessage(R.string.lose_message)
                .setPositiveButton(R.string.restart, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent restart = new Intent(LoseAlert.this.requireContext(),
                                WelcomeScreen.class);
                        startActivity(restart);
                        Player.clear();
                    }
                })
                .setNegativeButton(R.string.view_ranking, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onCancel(dialogInterface);
                    }
                })
                .setIcon(R.drawable.lose_icon);

        return builder.create();
    }


}
