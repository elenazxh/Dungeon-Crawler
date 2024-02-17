package com.example.a2340a_team10.model;

import com.example.a2340a_team10.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Attempt implements Comparable<Attempt> {
    private int score;
    private int avatar;
    private String name;
    private String time;

    public Attempt() {
        //obtained the attributes of the current player
        int choice = Player.getPlayer().getCharacterChoice();
        this.name = Player.getPlayer().getName();
        this.score = Player.getPlayer().getScore();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        this.time = formatter.format(date);

        if (choice == R.drawable.female_elf) {
            this.avatar = R.drawable.elf_f_idle_anim_f0;
        } else if (choice == R.drawable.male_elf) {
            this.avatar = R.drawable.elf_m_idle_anim_f0;
        } else if (choice == R.drawable.witch) {
            this.avatar = R.drawable.wizzard_f_idle_anim_f0;
        } else if (choice == R.drawable.wizard) {
            this.avatar = R.drawable.wizzard_m_idle_anim_f0;
        }
    }

    public int getScore() {
        return score;
    }

    public String getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    public int getAvatar() {
        return avatar;
    }

    @Override
    public int compareTo(Attempt attempt) {
        return (int) (attempt.getScore() - score);
    }
}
