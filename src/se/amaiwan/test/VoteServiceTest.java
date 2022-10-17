package se.amaiwan.test;

import org.junit.jupiter.api.Test;
import se.amaiwan.model.dao.Vote;
import se.amaiwan.model.repository.service.VoteService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoteServiceTest {

    @Test
    void findAllVotes() {
        VoteService voteService = new VoteService();
        List<Vote> allVotes = voteService.findAll();
        assertEquals(2, allVotes.size());
    }

    @Test
    void getVoteByDate() {
        VoteService voteService = new VoteService();
        LocalDate todaysDate = LocalDate.now();
        Vote vote = voteService.getByDate(todaysDate);
        assertEquals(todaysDate, vote.getDate());
    }

    @Test
    void increase_likes_when_update_vote() {
        VoteService voteService = new VoteService();
        Vote vote = new Vote();
        vote.setLikes(2);
        voteService.update(vote);
        System.out.println(vote.toString());
        assertEquals(2, vote.getLikes());
    }
}