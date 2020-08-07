package neointernship.tree;

import neointernship.bots.modeling.Progressing;
import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.AnswerSimbol;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.Figure;
import neointernship.chess.game.model.figure.piece.King;
import neointernship.chess.game.model.figure.piece.Rook;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.StoryGame;
import org.junit.Test;

import static neointernship.chess.game.model.enums.Color.WHITE;

public class TestBuilder {
    @Test
    public void testLevel1(){
        IMediator mediator = new Mediator();
        StoryGame storyGame = new StoryGame(mediator);

        Figure whiteKing = new King(WHITE);
        IField fieldWhiteKing = new Field(0,2);
        mediator.addNewConnection(fieldWhiteKing,whiteKing);

        Figure whiteQueen = new Rook(WHITE);
        IField fieldQ = new Field(7,1);
        mediator.addNewConnection(fieldQ,whiteQueen);

        Figure blackKing = new King(Color.BLACK);
        IField fieldBlackKing = new Field(0,0);
        mediator.addNewConnection(fieldBlackKing,blackKing);


        PossibleActionList list = new PossibleActionList(new Board(),mediator,storyGame);
        list.updateRealLists();

        Position startPosition = new Position(mediator,list);

        BuilderTree builderTree = new BuilderTree(4,WHITE);

        INode root = builderTree.getTree(startPosition);
        System.out.println();


    }
}
