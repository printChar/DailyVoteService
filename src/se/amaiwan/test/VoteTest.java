package se.amaiwan.test;

import org.junit.jupiter.api.Test;
import se.amaiwan.model.dao.Vote;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class VoteTest {

    @Test
    void todays_date_when_get_date(){
        Vote vote = new Vote();
        LocalDate date = vote.getDate();
        LocalDate todaysDate = LocalDate.now();
        assertEquals(todaysDate, date);
    }

    @Test
    void zero_likes_when_create_vote(){
        Vote vote = new Vote();
        int voteLikes = vote.getLikes();
        assertEquals(0, voteLikes);
    }

    @Test
    void zero_okay_when_create_vote(){
        Vote vote = new Vote();
        int voteOkay = vote.getOkay();
        assertEquals(0, voteOkay);
    }

    @Test
    void zero_dislikes_when_create_vote(){
        Vote vote = new Vote();
        int voteDislike = vote.getDislikes();
        assertEquals(0, voteDislike);
    }

}