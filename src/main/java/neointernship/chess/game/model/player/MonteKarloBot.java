package neointernship.chess.game.model.player;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.activecolorcontroller.ActiveColorController;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.gameplay.gamestate.state.IGameState;
import neointernship.chess.game.gameplay.loop.GameLoop;
import neointernship.chess.game.gameplay.moveaction.commands.allow.AllowMoveCommand;
import neointernship.chess.game.gameplay.moveaction.commands.allow.IAllowCommand;
import neointernship.chess.game.model.answer.Answer;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.model.util.Pair;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.selfplay.LocalLobby;
import neointernship.tree.TerminalBoss;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MonteKarloBot extends Bot {

    final static Board board = new Board();
    final Random random = new Random();
    private final int countGame;

    public MonteKarloBot(final Color color,final int countGame) {
        super("MonteKarloBot", color);
        this.countGame = countGame;
    }

    @Override
    public IAnswer getAnswer(final IMediator mediator, final IPossibleActionList possibleActionList) {
        time.start();

        final Position startPosition = new Position(mediator,possibleActionList);
        final Modeling modeling = new Modeling(startPosition,getColor());

        final Collection<Pair<Position, IAnswer>> positions = modeling.getAll();
        IAnswer actualIAnswer = null;
        double maxValue = Integer.MIN_VALUE;

        for(final Pair<Position,IAnswer> pair : positions){

            final Position actualPosition = pair.getFirst();
            final double resultMonteKarlo = getPriceMotnteKarlo(actualPosition);

            if(resultMonteKarlo > maxValue){
                maxValue = resultMonteKarlo;
                actualIAnswer = pair.getSecond();
            }
        }
        time.finish();
        System.out.println(time.getCountAddress() + ") " + getName() + " - " + time.getTime());

        return actualIAnswer;
    }

    /**
     * запуск n - количества игр с рандомными ботами
     * @param actualPosition
     * @return средняя оценка позиции
     */
    private double getPriceMotnteKarlo(final Position actualPosition) {
        double averageValue = 0;

        for (int i = 0; i < countGame ; i++) {
            averageValue += getValueGame(actualPosition);
        }
        return averageValue / countGame;
    }

    /**
     * начиная с этой позиции запускаем рандомных ботов играть
     * @param actualPosition стартовая позиция
     * @return результат в терминальном узле
     */
    private double getValueGame(final Position actualPosition){

        final IMediator mediator = new Mediator(actualPosition.getMediator());
        final IStoryGame storyGame = new StoryGame(mediator);
        final IPossibleActionList possibleActionList = new PossibleActionList(board,mediator,storyGame);
        possibleActionList.updateRealLists();

        final ActiveColorController activeColorController = new ActiveColorController();

        // начальный цвет должен быть как у протиника, потому что мы на глубине 2
        if(activeColorController.getCurrentColor() == getColor()){
            activeColorController.update();
        }

        final GameLoop gameLoop = new GameLoop(mediator,possibleActionList,board,activeColorController,storyGame);
        while (gameLoop.isAlive()){
            gameLoop.doIteration(getRandomAnswer(mediator,possibleActionList,activeColorController.getCurrentColor()));
            activeColorController.update();
        }

        final IGameState gameState = gameLoop.getMatchResult();

        return getValueTerminal(gameState);
    }

    private double getValueTerminal(final IGameState gameState){
        if(gameState.getValue() == EnumGameState.MATE){
            return gameState.getColor() == getColor() ? -1 : 1;
        }
        return 0;
    }

    private IAnswer getRandomAnswer(final IMediator mediator, final IPossibleActionList possibleActionList,final Color activeColor) {

        final List<Figure> figures = (List<Figure>) mediator.getFigures(activeColor);
        List<IField> fields;
        Figure figure;
        int index;

        do {
            index = random.nextInt(figures.size());
            figure = figures.get(index);
            fields = (List<IField>) possibleActionList.getRealList(figure);
        } while (fields.isEmpty());

        index = random.nextInt(fields.size());
        final IField finalField = fields.get(index);

        final IField startField = mediator.getField(figure);

        return new Answer(startField.getXCoord(), startField.getYCoord(),
                finalField.getXCoord(), finalField.getYCoord(), 'Q');
    }
}
