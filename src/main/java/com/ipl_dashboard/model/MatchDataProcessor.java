package com.ipl_dashboard.model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;


public class MatchDataProcessor implements ItemProcessor<MatchInput, Match> {
        private static final Logger log = LoggerFactory.getLogger(MatchItemProcessor.class);

        @Override
        public Match process(final MatchInput matchInput)throws Exception {

            Match match = new Match();
            match.setId(Long.parseLong(matchInput.getId()));
            match.setCity(matchInput.getCity());

            match.setDate(LocalDate.parse(matchInput.getDate()));
            match.setPlayer_of_match(matchInput.getPlayer_of_match());
            match.setVenue(matchInput.getVenue());

            //Set team1 and team2 depending on the innings order
            String firstInningsTeam,secondInningsTeam;

            if("bat".equalsIgnoreCase(matchInput.getToss_decision())){
                firstInningsTeam=matchInput.getToss_winner();
                secondInningsTeam= matchInput.getToss_winner().equalsIgnoreCase(matchInput.getTeam1())
                        ?matchInput.getTeam2():matchInput.getTeam1();}
            else {
                secondInningsTeam = matchInput.getToss_winner();
            }   firstInningsTeam=matchInput.getToss_winner().equalsIgnoreCase(matchInput.getTeam1())
                    ?matchInput.getTeam2():matchInput.getTeam1();

            match.setTeam1(firstInningsTeam);
            match.setTeam2(secondInningsTeam);

            match.setTossWinner(matchInput.getToss_winner());
            match.setTossDecision(matchInput.getToss_decision());
            match.setResult(matchInput.getResult());
            match.setResultMargin(match.getResultMargin());

            match.setUmpire1(matchInput.getUmpire1());
            match.setUmpire2(matchInput.getUmpire2());


            return match;

        }


        }

    }


