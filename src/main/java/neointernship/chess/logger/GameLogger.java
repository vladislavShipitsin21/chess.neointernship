package neointernship.chess.logger;


import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import java.io.IOException;


public class GameLogger implements IGameLogger{
    final Logger logger;

    public GameLogger(final int lobbyId) {
        this.logger = Logger.getLogger(GameLogger.class);

        try {
            logger.addAppender(new FileAppender(new PatternLayout(), "logs\\gameLog" + lobbyId + ".txt"));
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void logStartGame(final IPlayer playerOne, final IPlayer playerTwo) {
        logger.info("Игра между игроком " + playerOne + " и игроком " + playerTwo + " началась!");
    }

    @Override
    public void logPlayerMoveAction(final IPlayer player, final Figure figure,
                                    final IField startField, final IField finalField) {
        logger.info("Игрок " + player.getName() + " сделал ход фигурой " + figure.getName() + " из клетки (" +
                startField.getXCoord() + ";" + startField.getYCoord() + ") в клетку (" + finalField.getXCoord() + ";"
                + finalField.getYCoord() + ")");
    }

    @Override
    public void logPlayerWrongAction(final IPlayer player, final Figure figure, final IField field) {
        logger.warn("Игрок " + player.getName() + " попыталя сделать ход фигурой " + figure.getName() + " из клетки ("
                + field.getXCoord() + ";" + field.getYCoord() + ")");
    }

    @Override
    public void logPlayerWrongField(final IPlayer player, final IField field) {
        logger.warn("Игрок " + player.getName() + " попытался сделать ход фигурой из клетки ("
                + field.getXCoord() + ";" + field.getYCoord() + "), но фигуры там нет");
    }
}
