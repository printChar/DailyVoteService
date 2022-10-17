package se.amaiwan.model.repository.dao;


import se.amaiwan.model.VoteDao;
import se.amaiwan.model.dao.Vote;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VoteDaoTxt implements VoteDao {

    private List<Vote> allSavedVotes = new ArrayList<>();
    @Override
    public void createVote(Vote vote) {

        BufferedWriter writer = null;
        boolean foundDate = false;

        try {
            for (int i = 0; i < allSavedVotes.size(); i++) {
                if (allSavedVotes.get(i).getDate().equals(LocalDate.now())) {
                    foundDate = true;
                    Vote foundVote = allSavedVotes.get(i);
                    foundVote.setLikes(vote.getLikes());
                    foundVote.setOkay(vote.getOkay());
                    foundVote.setDislikes(vote.getDislikes());
                    writer = Files.newBufferedWriter(Paths.get("DailyVotes.txt"), StandardCharsets.ISO_8859_1, StandardOpenOption.TRUNCATE_EXISTING);
                    writer.write(vote.getDate() + ";" + foundVote.getLikes() + ";" + foundVote.getOkay() + ";" + foundVote.getDislikes());
                    writer.newLine();
                    writer.close();
                    break;
                }
            }

            if (!foundDate) {
                writer = Files.newBufferedWriter(Paths.get("DailyVotes.txt"), StandardCharsets.ISO_8859_1, StandardOpenOption.APPEND);
                writer.write(vote.getDate() + ";" + vote.getLikes() + ";" + vote.getOkay() + ";" + vote.getDislikes());
                writer.newLine();
                writer.close();
                allSavedVotes.add(vote);

            }

        } catch (IOException ex) {
            Logger.getLogger(VoteDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Vote> findAllVotes() {

        String textLine = null;
        Vote vote = null;
        allSavedVotes.clear();

        try {
            BufferedReader reader = null;
            reader = getReading();

            while ((textLine = reader.readLine()) != null) {
                String[] textRow = textLine.split(";");
                vote = new Vote(LocalDate.parse(textRow[0]), Integer.parseInt(textRow[1]), Integer.parseInt(textRow[2]), Integer.parseInt(textRow[3]));
                allSavedVotes.add(vote);
            }
            reader.close();

        } catch (IOException e) {
            System.out.println(VoteDaoTxt.class.getName() + e.getMessage());
        }
        return allSavedVotes;
    }

    private BufferedReader getReading() {

        BufferedReader reader = null;

        try {
            reader = Files.newBufferedReader(Paths.get("DailyVotes.txt"), StandardCharsets.ISO_8859_1);
        } catch (IOException ex) {
            Logger.getLogger(VoteDaoTxt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return reader;
    }


    @Override
    public void updateVote(Vote vote) {

    }

    @Override
    public Vote getVoteByDate(LocalDate date) {
        return null;
    }
}
