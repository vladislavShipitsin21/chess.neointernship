package neointernship.chess.game.model.player;

import neointernship.chess.game.gameplay.figureactions.IPossibleActionList;
import neointernship.chess.game.model.answer.IAnswer;
import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.mediator.IMediator;

public abstract class Bot extends Player {


    protected final Time time;

    public Bot(final String name,final Color color) {
        super(name + " bot",color);
        time = new Time();
    }

    @Override
    public abstract IAnswer getAnswer(final IMediator mediator, final IPossibleActionList possibleActionList);

    public final void printInfoTime() {
        System.out.println(getName() + " среднее время  : " + time.getAverageTime());
        System.out.println(getName() + " самый долгий ход : " + time.getMaxTime());
        System.out.println(getName() + " количестно выхода за пределы 5 сек : " + time.getCountUnlegal());
    }

    public Time getTime() {
        return time;
    }
}
