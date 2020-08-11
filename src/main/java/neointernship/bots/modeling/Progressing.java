package neointernship.bots.modeling;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.GameStateController;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.story.IStoryGame;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static neointernship.chess.game.model.enums.Color.swapColor;

public class Progressing {

    private static int MAX_DEPTH = 0;

    public static void print(Position position, int depth) {
        ConsoleBoardWriter printer = new ConsoleBoardWriter(position.getMediator(), new Board());
        System.out.println("глубина : " + depth);
        printer.printPosition(position);
    }

    public static IGameState getStatePosition(final Position position, final Color activeColor) {
        PossibleActionList possibleActionList = position.getPossibleActionList();
        IMediator mediator = position.getMediator();
        IStoryGame storyGame = possibleActionList.getStoryGame();
        GameStateController gameStateController =
                new GameStateController(possibleActionList, mediator, storyGame);

        gameStateController.updateWithoutUpdateList(activeColor);

        return gameStateController.getState();
    }

    public static boolean isTerminal(final IGameState gameState) {
        return gameState.getValue() != EnumGameState.ALIVE;
    }

    private static double getMaxPrice(final Set<Position> positions) {
        return positions.stream().max(Position::compareTo).get().getPrice();
    }

    private static double getMinPrice(final Set<Position> positions) {
        return positions.stream().min(Position::compareTo).get().getPrice();
    }

    private static double subProgress(final Set<Position> positions,
                                      int depth,
                                      final Color playerColor) {

        final boolean isMax = depth % 2 == 0;
        int startLabel = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        final Color currentColor = isMax ? playerColor : swapColor(playerColor);

        // достиг максимальной глубины
        if (depth >= MAX_DEPTH) {
            for (Position position : positions) {
                final IGameState gameState = getStatePosition(position, currentColor);
                position.setPrice(TargetFunction.price(position, playerColor, gameState));
            }
            return isMax ? getMinPrice(positions) : getMaxPrice(positions);
        }

        depth++;

        for (Position position : positions) {

            position.setPrice(startLabel);
            final IGameState gameState = getStatePosition(position, currentColor);
            // если достиг терминального узла
            if (isTerminal(gameState)) {

                position.setPrice(TargetFunction.price(position, playerColor, gameState));

            } else {
                final Set<Position> result = Modeling.modeling(position, currentColor).keySet();

                double resultIter = subProgress(result, depth, playerColor);
                // выбрать максимум из результата функции и текущим состоянием позиции
                if (isMax) {
                    position.setPrice(Math.max(position.getPrice(), resultIter));
                } else {
                    position.setPrice(Math.min(position.getPrice(), resultIter));
                }
            }
        }
        return getMaxPrice(positions);
    }

    public static IAnswer getSolution(final Position startPpositions,
                                      final Color activeColor,
                                      final int maxDepth) {
        MAX_DEPTH = maxDepth;
        final Map<Position, IAnswer> resultMap = Modeling.modeling(startPpositions, activeColor);

        for (Position position : resultMap.keySet()) {
            // ожидаю что у первых потомков цена уже посчитана и записанна
            Set<Position> positions = new HashSet<>();
            positions.add(position);
            subProgress(positions, 1, activeColor);
        }
        // максимизирую свой выигрыш
        Position finishPosition = resultMap.keySet().stream().max(Position::compareTo).get();
        return resultMap.get(finishPosition);
    }
}
