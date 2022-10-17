package se.amaiwan.controller;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import se.amaiwan.model.dao.Vote;
import se.amaiwan.model.repository.service.VoteService;
import se.amaiwan.view.VoteView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VoteController {
    private VoteService model;
    private VoteView view;
    private Vote daoVote;

    public VoteController(VoteService model, VoteView view) {
        this.model = model;
        this.view = view;
        ControlVoteViewBtnHandler controlVoteViewBtnHandler = new ControlVoteViewBtnHandler();
        ControlVoteViewMenuHandler controlVoteViewMenuHandler = new ControlVoteViewMenuHandler();
        updateView();
    }

    private void updateView() {
        List<Vote> allVotes = model.findAll();
        daoVote = null;
        boolean foundVote = false;

        for (int i = 0; i < allVotes.size(); i++) {
            if (allVotes.get(i).getDate().equals(LocalDate.now())) {
                foundVote = true;
                daoVote = allVotes.get(i);
            }
        }

        if (!foundVote) {
            daoVote = new Vote();
            model.create(daoVote);
        }

        view.getBadCounterLabel().setText(String.valueOf(daoVote.getDislikes()));
        view.getOkayCounterLabel().setText(String.valueOf(daoVote.getOkay()));
        view.getHappyCounterLabel().setText(String.valueOf(daoVote.getLikes()));
    }

    private class ControlVoteViewBtnHandler implements EventHandler {
        public ControlVoteViewBtnHandler() {
            view.addHappyVoteBtnListener(this);
            view.addOkayVoteBtnListener(this);
            view.addBadVoteBtnListener(this);
        }

        @Override
        public void handle(Event event) {

            Button btn = (Button) event.getSource();
            Vote vote = getDaoVote();

            int likes = getVoteLikes();
            int okay = getVoteOkay();
            int dislikes = getVoteDislikes();

            if (btn == view.getHappyBtn()) {
                likes++;
                setVoteLikes(likes);
                model.update(vote);
            }

            if (btn == view.getOkayBtn()) {
                okay++;
                setVoteOkay(okay);
                model.update(vote);
            }

            if (btn == view.getBadBtn()) {
                dislikes++;
                setVoteDislikes(dislikes);
                model.update(vote);
            }

            view.getBadCounterLabel().setText(String.valueOf(vote.getDislikes()));
            view.getOkayCounterLabel().setText(String.valueOf(vote.getOkay()));
            view.getHappyCounterLabel().setText(String.valueOf(vote.getLikes()));
        }
    }
    private class ControlVoteViewMenuHandler implements EventHandler{
        public ControlVoteViewMenuHandler() {
            view.addMenuSaveToTextFileItem(this);
            view.addtMenuShowTextFileItem(this);
        }

        @Override
        public void handle(Event event) {

            MenuItem menuItem = (MenuItem) event.getSource();

            Vote todaysVote = getDaoVote();
            List<Vote> allSavedVotes = model.showAll();

            if(menuItem == view.getMenuShowTextFileItem()){
                for (Vote vote : allSavedVotes) {
                    view.getTextArea().appendText(vote.toString() + "\n");
                }
            }

            if(menuItem == view.getMenuSaveToTextFileItem()){
                model.save(todaysVote);
            }
        }
    }
    private Vote getDaoVote() {
        return daoVote;
    }
    private void setVoteLikes(int likes){
        daoVote.setLikes(likes);
    }
    private void setVoteOkay(int okay){
        daoVote.setOkay(okay);
    }
    private void setVoteDislikes(int dislikes){
        daoVote.setDislikes(dislikes);
    }
    private int getVoteLikes(){
        return daoVote.getLikes();
    }
    private int getVoteOkay(){
        return daoVote.getOkay();
    }
    private int getVoteDislikes(){
        return daoVote.getDislikes();
    }
}
