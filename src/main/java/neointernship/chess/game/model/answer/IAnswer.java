package neointernship.chess.game.model.answer;

import neointernship.chess.game.model.playmap.field.IField;

public interface IAnswer {
    /**
     * из какой клетки выходит фигура ( где раньше стояла)
     * @return
     */
    IField getOut();

    /**
     * На какую клетку передвигается
     * @return
     */
    IField getIn();
}