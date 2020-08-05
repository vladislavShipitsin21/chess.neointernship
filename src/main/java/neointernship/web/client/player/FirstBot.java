package neointernship.web.client.player;

import neointernship.bots.functionsH.TargetFunction;
import neointernship.bots.modeling.Modeling;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.enums.EnumGameState;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.web.client.GUI.Input.IInput;
import neointernship.web.client.GUI.board.view.BoardView;
import neointernship.web.client.communication.message.ClientCodes;
import neointernship.web.client.communication.message.TurnStatus;

import java.util.*;

public class FirstBot extends APlayer {

    private BoardView boardView;
    private IPossibleActionList possibleActionList;
    private final Random random;
    private final IInput input;

    public FirstBot(final Color color, final String name, final IInput input) {
        super(color, name);
        this.random = new Random();
        this.input = input;
    }

    @Override
    public void init(final IMediator mediator, final IBoard board, final Color color) {
        super.init(mediator, board, color);
        this.possibleActionList = new PossibleActionList(board, mediator, storyGame);

        this.boardView = new BoardView(mediator, board);
        if (!input.isVoid()) boardView.display();
    }

    @Override
    public void updateMediator(final IAnswer answer, final TurnStatus turnStatus) throws InterruptedException {
        super.updateMediator(answer, turnStatus);
        if (!input.isVoid()) {
            boardView.update();
            boardView.display();
        }
    }

    @Override
    public String getAnswer() {

        String turn = "";

        final List<Character> integers = Arrays.asList('8', '7', '6', '5', '4', '3', '2', '1');
        final List<Character> chars = Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h');

        possibleActionList.updateRealLists();

        Position startPosition = new Position(mediator,possibleActionList);

        Map<Position,IAnswer> positionIAnswerMap = Modeling.modeling(startPosition,getColor());

        Collection<Position> finishPositions = positionIAnswerMap.keySet();

        // оценить каждую позицию

        for(Position position : finishPositions){
           position.setPrice(TargetFunction.price(position,getColor()));
        }
//        Position finish = finishPositions.stream().sorted(Position::compareTo).collect(Collectors.toList()).get(0);
        Position finish = finishPositions.stream().max(Position::compareTo).get();


        IAnswer answer = positionIAnswerMap.get(finish);

        final IField startField = new Field(answer.getStartX(),answer.getStartY());
        final IField finishField = new Field(answer.getFinalX(),answer.getFinalY());

        turn += turn + chars.get(startField.getYCoord()) + integers.get(startField.getXCoord()) + "-" +
                chars.get(finishField.getYCoord()) + integers.get(finishField.getXCoord());

        return turn;
    }

    @Override
    public char getTransformation() {
        return 'Q';
    }

    @Override
    public ClientCodes getHandShakeAnswer() throws InterruptedException {
        input.getHandShakeAnswer();
        return ClientCodes.YES;
    }

    @Override
    public void endGame(final EnumGameState enumGameState, final Color color) throws InterruptedException {
        input.endGame(enumGameState, color);
        boardView.dispose();
    }

}
