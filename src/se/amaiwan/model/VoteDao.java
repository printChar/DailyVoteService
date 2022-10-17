package se.amaiwan.model;

import se.amaiwan.model.dao.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteDao {

    void createVote(Vote vote);
    Vote getVoteByDate(LocalDate date);
    List<Vote> findAllVotes();
    void updateVote(Vote vote);

}
