package neointernship.chess.game.model.answer;

public class Answer implements IAnswer {
    final int startX;
    final int startY;
    final int finalX;
    final int finalY;
    final char simbol;

    public Answer(final int startX, final int startY, final int finalX, final int finalY, char simbol) {
        this.startX = startX;
        this.startY = startY;
        this.finalX = finalX;
        this.finalY = finalY;

        this.simbol = simbol;
    }

    @Override
    public int getStartX() {
        return startX;
    }

    @Override
    public int getStartY() {
        return startY;
    }

    @Override
    public int getFinalX() {
        return finalX;
    }

    @Override
    public int getFinalY() {
        return finalY;
    }

    @Override
    public char getSimbol() {
        return simbol;
    }
}
