package com.ttrpg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ttrpg.dto.CreateGameRequestDTO;
import com.ttrpg.model.Igra;
// import com.ttrpg.model.LokaliziranaIgra;
import com.ttrpg.model.MapLocation;
import com.ttrpg.model.OnlineIgra;
import com.ttrpg.repository.IgraRepository;

// import jakarta.validation.Valid;
@Service
public class IgraService {

    public List<Igra> findByGenre(String genre) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<Igra> findByName(String name) {
        // TODO Auto-generated method stub
        return null;
    }

    @Autowired
    private IgraRepository igraRepository;
    /*
     * public List<Igra> searchIgre(String title, Integer maxPlayer, Boolean
     * isPrivate, Boolean isHomebrew,
     * Boolean requiresForm, String startTs, String estLength, String recExp,
     * String commChannel, Integer rulesetId, Integer sysId, Integer gmUserId,
     * String templateLoc,
     * String location) {
     * return igraRepository.searchIgre(title, // gameName
     * null, // type (if not filtering)
     * null, // availability (if not filtering)
     * null, // createdBy (if not filtering)
     * isPrivate, // applicationRequired -> 'isPrivate'
     * null, // complexity (if not filtering)
     * estLength, // estimatedLength
     * startTs, // startTimestamp
     * recExp, // description (recExp here matches description)
     * null, // pravilnik (if not filtering)
     * requiresForm, // requiresForm
     * null, // currentPlayerCount (if not filtering)
     * maxPlayer, // maxPlayerCount
     * commChannel, // communicationChannel
     * isHomebrew, // isHomebrew
     * location);
     * }
     */

    public void s2DataLoader() {

        Igra game1 = new Igra(
                1L, "Game 1", "online", new MapLocation(45.8131, 15.978), "public", "user1",
                true, "Medium", "2 hours", "2024-11-10T15:00:00Z", "A fun and engaging online strategy game.",
                "Rules: Players must strategize to defeat opponents.", true, 5, 20, "Discord", false);

        Igra game2 = new Igra(
                2L, "Game 2", "offline", new MapLocation(44.8131, 16.978), "private", "user2",
                false, "Easy", "1 hour", "2024-11-12T18:00:00Z", "A quick and fun card game.",
                "Rules: Be the first to get rid of all your cards.", false, 3, 10, "Zoom", true);

        Igra game3 = new Igra(
                3L, "Game 3", "online", new MapLocation(46.8131, 17.978), "public", "user3",
                true, "Hard", "3 hours", "2024-11-14T16:00:00Z",
                "A complex online strategy game with multiple factions.",
                "Rules: Work with your team to control the board.", true, 10, 30, "Discord", false);

        // Example Igre (games)
        igraRepository.save(game1);

        igraRepository.save(game2);

        igraRepository.save(game3);

        // Add more sample data as needed
    }

    public void s3DataLoader() {
        Igra game1 = new Igra(
                11L, "Game 11", "online", new MapLocation(45.8131, 15.978), "public", "user1",
                true, "Medium", "2 hours", "2024-11-10T15:00:00Z", "A fun and engaging online strategy game.",
                "Rules: Players must strategize to defeat opponents.", true, 5, 20, "Discord", false);

        Igra game2 = new Igra(
                12L, "Game 12", "offline", new MapLocation(44.8131, 16.978), "private", "user2",
                false, "Easy", "1 hour", "2024-11-12T18:00:00Z", "A quick and fun card game.",
                "Rules: Be the first to get rid of all your cards.", false, 3, 10, "Zoom", true);

        Igra game3 = new Igra(
                13L, "Game 13", "online", new MapLocation(46.8131, 17.978), "public", "user3",
                true, "Hard", "3 hours", "2024-11-14T16:00:00Z",
                "A complex online strategy game with multiple factions.",
                "Rules: Work with your team to control the board.", true, 10, 30, "Discord", false);

        Igra game4 = new Igra(
                4L, "Game 4", "online", new MapLocation(47.8131, 14.978), "public", "user4",
                false, "Easy", "30 minutes", "2024-11-15T10:00:00Z", "A light, cooperative board game.",
                "Rules: Work together to solve the puzzle.", false, 2, 5, "Discord", true);

        Igra game5 = new Igra(
                5L, "Game 5", "offline", new MapLocation(45.2131, 15.078), "private", "user5",
                true, "Hard", "4 hours", "2024-11-18T20:00:00Z", "A challenging tabletop RPG.",
                "Rules: Role-play and complete quests as a team.", true, 4, 15, "In-person", true);

        Igra game6 = new Igra(
                6L, "Game 6", "online", new MapLocation(45.8131, 16.978), "public", "user6",
                false, "Medium", "1.5 hours", "2024-11-20T12:00:00Z", "A competitive trivia game.",
                "Rules: Answer questions to score points.", false, 6, 12, "Zoom", false);

        Igra game7 = new Igra(
                7L, "Game 7", "offline", new MapLocation(43.8131, 15.678), "private", "user7",
                true, "Easy", "45 minutes", "2024-11-22T14:00:00Z", "A fun party game with friends.",
                "Rules: Guess the charades.", true, 8, 20, "In-person", false);

        Igra game8 = new Igra(
                8L, "Game 8", "online", new MapLocation(46.0131, 15.978), "public", "user8",
                false, "Hard", "5 hours", "2024-11-25T18:00:00Z", "An intense online PvP game.",
                "Rules: Defeat the other team.", false, 15, 25, "Discord", true);

        Igra game9 = new Igra(
                9L, "Game 9", "offline", new MapLocation(45.7131, 15.278), "private", "user9",
                true, "Medium", "3 hours", "2024-11-30T17:00:00Z", "An escape room challenge.",
                "Rules: Solve clues to escape.", true, 3, 8, "In-person", false);

        Igra game10 = new Igra(
                10L, "Game 10", "online", new MapLocation(45.9131, 15.478), "public", "user10",
                true, "Medium", "2 hours", "2024-12-01T15:00:00Z", "A virtual team-building exercise.",
                "Rules: Complete tasks to earn points.", true, 10, 15, "Zoom", false);

        // Add all games to the repository
        igraRepository.saveAll(List.of(game1, game2, game3, game4, game5, game6, game7, game8, game9, game10));
    }

    /*
     * public List<Igra> searchIgre(String gameName, String type, String location,
     * String availability, String createdBy,
     * Boolean applicationRequired, String complexity, String estimatedLength,
     * String startTimestamp,
     * String description, String pravilnik, Boolean requiresForm, Integer
     * currentPlayerCount,
     * Integer maxPlayerCount, String communicationChannel, Boolean isHomebrew) {
     * // TODO Auto-generated method stub
     * return null;}
     */

    public void s4DataLoader() {
        Igra game20 = new Igra(
                20L, "Game 20", "online", new MapLocation(45.8131, 15.978), "public", "user1",
                true, "Medium", "2 hours", "2024-11-10T15:00:00Z", "A fun and engaging online strategy game.",
                "Rules: Players must strategize to defeat opponents.", true, 5, 20, "Discord", false);

        Igra game21 = new Igra(
                21L, "Game 21", "offline", new MapLocation(44.8131, 16.978), "private", "user2",
                false, "Easy", "1 hour", "2024-11-12T18:00:00Z", "A quick and fun card game.",
                "Rules: Be the first to get rid of all your cards.", false, 3, 10, "Zoom", true);

        Igra game22 = new Igra(
                22L, "Game 22", "online", new MapLocation(46.8131, 17.978), "public", "user3",
                true, "Hard", "3 hours", "2024-11-14T16:00:00Z",
                "A complex online strategy game with multiple factions.",
                "Rules: Work with your team to control the board.", true, 10, 30, "Discord", false);

        Igra game23 = new Igra(
                23L, "Game 23", "online", new MapLocation(47.8131, 14.978), "public", "user4",
                false, "Easy", "30 minutes", "2024-11-15T10:00:00Z", "A light, cooperative board game.",
                "Rules: Work together to solve the puzzle.", false, 2, 5, "Discord", true);

        Igra game24 = new Igra(
                24L, "Game 24", "offline", new MapLocation(45.2131, 15.078), "private", "user5",
                true, "Hard", "4 hours", "2024-11-18T20:00:00Z", "A challenging tabletop RPG.",
                "Rules: Role-play and complete quests as a team.", true, 4, 15, "In-person", true);

        Igra game25 = new Igra(
                25L, "Game 25", "online", new MapLocation(45.8131, 16.978), "public", "user6",
                false, "Medium", "1.5 hours", "2024-11-20T12:00:00Z", "A competitive trivia game.",
                "Rules: Answer questions to score points.", false, 6, 12, "Zoom", false);

        Igra game26 = new Igra(
                26L, "Game 26", "offline", new MapLocation(43.8131, 15.678), "private", "user7",
                true, "Easy", "45 minutes", "2024-11-22T14:00:00Z", "A fun party game with friends.",
                "Rules: Guess the charades.", true, 8, 20, "In-person", false);

        Igra game27 = new Igra(
                27L, "Game 27", "online", new MapLocation(46.0131, 15.978), "public", "user8",
                false, "Hard", "5 hours", "2024-11-25T18:00:00Z", "An intense online PvP game.",
                "Rules: Defeat the other team.", false, 15, 25, "Discord", true);

        Igra game28 = new Igra(
                28L, "Game 28", "offline", new MapLocation(45.7131, 15.278), "private", "business9",
                true, "Medium", "3 hours", "2024-11-30T17:00:00Z", "An escape room challenge.",
                "Rules: Solve clues to escape.", true, 3, 8, "In-person", false);

        Igra game29 = new Igra(
                29L, "Game 29", "online", new MapLocation(45.9131, 15.478), "public", "user10",
                true, "Medium", "2 hours", "2024-12-01T15:00:00Z", "A virtual team-building exercise.",
                "Rules: Complete tasks to earn points.", true, 10, 15, "Zoom", false);

        // Full Games with combined details
        Igra combinedGame30 = new Igra(
                30L, "Combined Game 30", "online", new MapLocation(45.5001, 15.978), "public", "business11",
                true, "Hard", "4 hours", "2024-12-05T19:00:00Z",
                "An intense team-building strategy game with real-time PvP.",
                "Rules: Work in teams to solve challenges while defending your base.", true, 8, 20, "Discord", false);

        Igra combinedGame31 = new Igra(
                31L, "Combined Game 31", "offline", new MapLocation(46.5001, 16.978), "private", "business12",
                false, "Easy", "1 hour", "2024-12-07T16:00:00Z", "A mix of charades and trivia, fun for parties.",
                "Rules: Act out clues and answer questions to score points.", true, 10, 15, "In-person", true);

        Igra combinedGame32 = new Igra(
                32L, "Combined Game 32", "online", new MapLocation(45.6001, 17.978), "public", "user13",
                true, "Medium", "2 hours", "2024-12-10T13:00:00Z",
                "An interactive online escape room with live actors.",
                "Rules: Solve puzzles with your team to escape the room.", true, 4, 8, "Discord", true);

        Igra combinedGame33 = new Igra(
                33L, "Combined Game 33", "online", new MapLocation(46.1001, 18.978), "private", "user14",
                true, "Hard", "3 hours", "2024-12-12T20:00:00Z", "A high-stakes PvP strategy game with alliances.",
                "Rules: Form alliances to conquer territories.", true, 6, 18, "Discord", false);

        Igra combinedGame34 = new Igra(
                34L, "Combined Game 34", "offline", new MapLocation(44.5001, 15.878), "public", "business15",
                true, "Medium", "1.5 hours", "2024-12-15T17:00:00Z", "A combination of role-playing and board game.",
                "Rules: Complete quests in a story-driven board game.", true, 3, 12, "In-person", true);

        // Full Games with larger player counts and mixed settings
        Igra combinedGame35 = new Igra(
                35L, "Combined Game 35", "online", new MapLocation(45.7001, 15.678), "public", "user16",
                false, "Medium", "2.5 hours", "2024-12-18T16:00:00Z",
                "A complex online game combining trivia, puzzles, and PvP.",
                "Rules: Answer trivia, solve puzzles, and defeat the opposition.", false, 15, 40, "Zoom", false);

        Igra combinedGame36 = new Igra(
                36L, "Combined Game 36", "offline", new MapLocation(46.8001, 16.478), "private", "business17",
                true, "Hard", "3.5 hours", "2024-12-20T14:00:00Z",
                "An immersive tabletop game with combat and puzzle-solving.",
                "Rules: Role-play characters, defeat enemies, and solve complex puzzles.", true, 5, 15, "In-person",
                true);

        Igra combinedGame37 = new Igra(
                37L, "Combined Game 37", "online", new MapLocation(44.6001, 15.978), "public", "business18",
                false, "Easy", "1 hour", "2024-12-22T11:00:00Z", "A fast-paced trivia game with a timer.",
                "Rules: Answer questions quickly to score the highest.", false, 10, 30, "Discord", false);

        // Save each game
        igraRepository.saveAll(
                List.of(game20, game21, game22, game23, game24, game25, game26, game27, game28, game29, combinedGame30,
                        combinedGame31, combinedGame32, combinedGame33, combinedGame34, combinedGame35, combinedGame36,
                        combinedGame37

                ));

    }

    public List<Igra> searchIgraService(String gameName) {
        return igraRepository.findByGameNameContaining(gameName);

    }

    public Igra createGame(CreateGameRequestDTO request) {
        Igra game = new Igra();

        if ("online".equalsIgnoreCase(request.getType())) {
            // Online igra
            OnlineIgra onlineGame = new OnlineIgra();
            onlineGame.setTimezone(request.getTimezone());
            game = onlineGame;

        }

        // Postavljanje zajedničkih atributa
        game.setGameName(request.getTitle());
        game.setType(request.getType());
        game.setLocation(request.getLocation());
        game.setAvailability(request.getAvailability());
        game.setCreatedBy(request.getCreatedBy());
        game.setApplicationRequired(request.isApplicationRequired());
        game.setComplexity(request.getComplexity());
        game.setEstimatedLength(request.getEstimatedLength());
        game.setStartTimestamp(request.getStartTimestamp());
        game.setDescription(request.getDescription());
        game.setPravilnik(request.getPravilnik());
        game.setRequiresForm(request.isRequiresForm());
        game.setCurrentPlayerCount(0);
        game.setMaxPlayerCount(request.getMaxPlayerCount());
        game.setCommunicationChannel(request.getCommunicationChannel());
        game.setIsHomebrew(request.isHomebrew());

        return igraRepository.save(game);
    }
}
