package se.amaiwan.test;

import org.junit.jupiter.api.Test;
import se.amaiwan.model.dao.Vote;
import se.amaiwan.model.repository.dao.VoteDaoSql;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VoteDaoImplTest {

    @Test
    void findAllVotes() {
        VoteDaoSql voteDao = new VoteDaoSql();
        List<Vote> allVotes = voteDao.findAllVotes();
        assertEquals(1, allVotes.size());
    }

    @Test
    void getVoteByDate() {
        VoteDaoSql voteDao = new VoteDaoSql();
        LocalDate todaysDate = LocalDate.now();
        Vote vote = voteDao.getVoteByDate(todaysDate);
        System.out.println(vote.toString());
        assertEquals(todaysDate, vote.getDate());
    }
}