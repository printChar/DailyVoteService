package se.amaiwan.model.repository.dao;

import se.amaiwan.database.ConnectToDatabase;
import se.amaiwan.model.VoteDao;
import se.amaiwan.model.dao.Vote;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoteDaoSql implements VoteDao {

    private final Connection conn = ConnectToDatabase.createConnection();
    private final String SQL_CREATE_VOTE = "INSERT INTO vote (date, likes, okay, dislikes) VALUES (?, ?, ?, ?)";
    private final String SQL_GET_VOTE_BY_DATE = "SELECT * FROM vote WHERE date=?";
    private final String SQL_GET_ALL_VOTES = "SELECT * FROM vote";
    private final String SQL_UPDATE_VOTE = "UPDATE vote SET likes=?, okay=?, dislikes=? WHERE date=?";


    @Override
    public void createVote(Vote vote) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_CREATE_VOTE)) {
            pstmt.setDate(1, Date.valueOf(vote.getDate()));
            pstmt.setInt(2, vote.getLikes());
            pstmt.setInt(3, vote.getOkay());
            pstmt.setInt(4, vote.getDislikes());
            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VoteDaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public Vote getVoteByDate(LocalDate date) {

        Vote vote = new Vote();

        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_VOTE_BY_DATE)) {
            pstmt.setDate(1, Date.valueOf(date));
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    pstmt.setDate(1, Date.valueOf(vote.getDate()));
                  /*  pstmt.setInt(2, vote.getLikes());
                    pstmt.setInt(3, vote.getOkay());
                    pstmt.setInt(4, vote.getDislikes());*/

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(VoteDaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vote;
    }

    @Override
    public List<Vote> findAllVotes() {
        ArrayList<Vote> allVotes = new ArrayList();

        try (PreparedStatement pstmt = conn.prepareStatement(SQL_GET_ALL_VOTES);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Vote vote = new Vote();
                vote.setDate(rs.getDate(1).toLocalDate());
                vote.setLikes(rs.getInt(2));
                vote.setOkay(rs.getInt(3));
                vote.setDislikes(rs.getInt(4));
                allVotes.add(vote);
            }

        } catch (SQLException ex) {
            Logger.getLogger(VoteDaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return allVotes;
    }

    @Override
    public void updateVote(Vote vote) {
        try (PreparedStatement pstmt = conn.prepareStatement(SQL_UPDATE_VOTE)) {
            pstmt.setInt(1, vote.getLikes());
            pstmt.setInt(2, vote.getOkay());
            pstmt.setInt(3, vote.getDislikes());
            pstmt.setDate(4, Date.valueOf(vote.getDate()));

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(VoteDaoSql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
