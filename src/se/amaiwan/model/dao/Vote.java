package se.amaiwan.model.dao;

import java.time.LocalDate;

public class Vote {

    private LocalDate date;
    private int likes;
    private int okay;
    private int dislikes;

    public Vote(LocalDate date, int likes, int okay, int dislikes) {
        this.date = date;
        this.likes = likes;
        this.okay = okay;
        this.dislikes = dislikes;
    }

    public Vote() {
        this.date = LocalDate.now();
        this.likes = 0;
        this.okay = 0;
        this.dislikes = 0;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getOkay() {
        return okay;
    }

    public void setOkay(int okay) {
        this.okay = okay;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    @Override
    public String toString() {
        return String.format("Datum: %s, Ogillar: %s, Okej: %s, Gillar: %s", date, dislikes, okay, likes);
    }
}
