package neointernship.chess.logger;

import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.player.IPlayer;
import neointernship.chess.game.model.playmap.field.IField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GameLogger implements IGameLogger {
    final Logger logger;
    private final IPlayer playerOne;
    private final IPlayer playerTwo;


    public GameLogger(final IPlayer playerOne, final IPlayer playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.logger = LoggerFactory.getLogger(GameLogger.class);
    }

    @Override
    public void logStartGame() {
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
