package neointernship.chess.logger;


import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;


public class GameLogger implements IGameLogger{
    final Logger logger;

    public GameLogger(final int lobbyId) {
        this.logger = Logger.getLogger(GameLogger.class);

        try {
            final PatternLayout patternLayout = new PatternLayout();
            patternLayout.setConversionPattern("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n");
            logger.addAppender(new FileAppender(patternLayout, "logs\\gameLog" + lobbyId + ".txt", false));

        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logStartGame(final IPlayer playerOne, final IPlayer playerTwo) {
        logger.info("Игра между игроком " + playerOne.getName() + " и игроком " + playerTwo.getName() + " началась!");
    }

    @Override
    public void logPlayerMoveAction(final IPlayer player, final Figure figure,
                                    final IField startField, final IField finalField) {
        logger.info("Игрок " + player.getName() + " сделал ход фигурой " + figure.getName() + " из клетки (" +
                startField.getXCoord() + ";" + startField.getYCoord() + ") в клетку (" + finalField.getXCoord() + ";"
                + finalField.getYCoord() + ")");
    }

    @Override
    public void logPlayerWrongAction(final IPlayer player, final IField field) {
        logger.warn("Игрок " + player.getName() + " попыталя сделать ход из клетки (" + field.getXCoord() + ";" +
                    + field.getYCoord() + "), но он невозможен");
    }

    @Override
    public void logPlayerWin(final Color color) {
        logger.info(color + " игрок победил!");
    }

    @Override
    public void logStalemate() {
        logger.info("Ничья!");
    }
}
