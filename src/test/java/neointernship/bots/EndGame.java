package neointernship.bots;

import neointernship.bots.functionsH.bonus.debut.FunctionsDebut;
import neointernship.chess.game.console.ConsoleBoardWriter;
import neointernship.chess.game.console.IConsoleBoardWriter;
import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.gameplay.figureactions.PossibleActionList;
import neointernship.chess.game.gameplay.gamestate.controller.draw.Position;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.piece.*;
import neointernship.chess.game.model.mediator.IMediator;
import neointernship.chess.game.model.mediator.Mediator;
import neointernship.chess.game.model.player.Bot;
import neointernship.chess.game.model.player.BotMixidStrategy;
import neointernship.chess.game.model.player.MiniMaxBot;
import neointernship.chess.game.model.playmap.board.Board;
import neointernship.chess.game.model.playmap.board.IBoard;
import neointernship.chess.game.model.playmap.field.Field;
import neointernship.chess.game.model.playmap.field.IField;
import neointernship.chess.game.story.IStoryGame;
import neointernship.chess.game.story.StoryGame;
import neointernship.tree.BuilderTreeWithBonus;
import neointernship.tree.HelperBuilderTree;
import neointernship.tree.INode;
import neointernship.tree.Node;
import org.junit.Test;

import java.util.Collection;

public class EndGame {
    private final static IBoard board = new Board();

    @Test
    public void test(){
        final IMediator mediator = new Mediator();

        final Figure kingWhite = new King(Color.WHITE);
        final IField fieldKingWhite = new Field(5,6);
        mediator.addNewConnection(fieldKingWhite,kingWhite);

        final Figure figureWhite1 = new Pawn(Color.WHITE);
        final IField fieldWhite1 = new Field(6,4);
        mediator.addNewConnection(fieldWhite1,figureWhite1);

        final Figure figureWhite2 = new Rook(Color.WHITE);
        final IField fieldWhite2 = new Field(4,0);
        mediator.addNewConnection(fieldWhite2,figureWhite2);

        /*-------------------------------------------------------------------------*/

        final Figure kingBlack = new King(Color.BLACK);
        final IField fieldKingBlack = new Field(2,2);
        mediator.addNewConnection(fieldKingBlack,kingBlack);

        final Figure figureBlack1 = new Pawn(Color.BLACK);
        final IField fieldBlack1 = new Field(2,5);
        mediator.addNewConnection(fieldBlack1,figureBlack1);

       /* final Figure figureBlack2 = new Knight(Color.BLACK);
        final IField fieldBlack2 = new Field(4,5);
        mediator.addNewConnection(fieldBlack2,figureBlack2);*/

        final IConsoleBoardWriter printer = new ConsoleBoardWriter(mediator,board);
        printer.printBoard();
        /*-------------------------------------------------------------------------*/


        final IStoryGame storyGame = new StoryGame(mediator);
        final IPossibleActionList possibleActionList = new PossibleActionList(board,mediator,storyGame);
        possibleActionList.updateRealLists();

        final Bot bot = new BotMixidStrategy(Color.WHITE);
        IAnswer answer = bot.getAnswer(mediator,possibleActionList);

        ConsoleBoardWriter.printAnswer(answer);

    }
    private int countStep(final IMediator mediator,final IPossibleActionList possibleActionList){
        int result = 0;

        for(final Figure figure : mediator.getFigures()){
            result += possibleActionList.getRealList(figure).size();
        }
        return result;
    }
}
