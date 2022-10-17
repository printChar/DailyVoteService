package se.amaiwan.model.repository.service;

import se.amaiwan.model.dao.Vote;
import se.amaiwan.model.repository.dao.VoteDaoSql;
import se.amaiwan.model.repository.dao.VoteDaoTxt;

import java.time.LocalDate;
import java.util.List;

public class VoteService {

    private final VoteDaoSql voteDaoImpl = new VoteDaoSql();
    private final VoteDaoTxt voteDaoTxt = new VoteDaoTxt();

    public void create(Vote vote) {
        voteDaoImpl.createVote(vote);
    }

    public Vote getByDate(LocalDate date) {
        return voteDaoImpl.getVoteByDate(date);
    }

    public List<Vote> findAll() {
        return voteDaoImpl.findAllVotes();
    }

    public void update(Vote vote) {
        voteDaoImpl.updateVote(vote);
    }

    public void save(Vote vote) {
        voteDaoTxt.createVote(vote);
    }

    public List<Vote> showAll(){
        return voteDaoTxt.findAllVotes();
    }

}
