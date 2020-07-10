package neointernship.chess.logger;

import neointernship.chess.game.model.figure.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;
import org.apache.log4j.Logger;


public class GameLogger implements IGameLogger{
    final Logger logger;

    public GameLogger() {
        this.logger = Logger.getLogger(GameLogger.class);
    }

    @Override
    public void logStartGame(final IPlayer playerOne, final IPlayer playerTwo) {
        logger.info("Игра между игроком " + playerOne + " и игроком " + playerTwo + " началась!");
    }

    @Override
    public void logPlayerMoveAction(final IPlayer player, final Figure figure,
                                    final IField startField, final IField finalField) {
        logger.info("Игрок + " + player.getName() + " сделал ход фигурой " + figure.getName() + " из клетки (" +
                startField.getXCoord() + ";" + startField.getYCoord() + ") в клетку (" + finalField.getXCoord() + ";"
                + finalField.getYCoord());
    }

    @Override
    public void logPlayerWrongAction(final IPlayer player, final Figure figure, final IField field) {
        logger.warn("Игрок + " + player.getName() + " попыталя сделать ход фигурой " + figure.getName() + " из клетки ("
                + field.getXCoord() + ";" + field.getYCoord() + ")");
    }

    @Override
    public void logPlayerWrongField(final IPlayer player, final IField field) {
        logger.warn("Игрок + " + player.getName() + " попыталя сделать ход фигурой из клетки ("
                + field.getXCoord() + ";" + field.getYCoord() + "), но фигуры там нет");
    }
}
