package neointernship.chess.game.model.answer;

public class AnswerSimbol implements IAnswer {
    final int startX;
    final int startY;
    final int finalX;
    final int finalY;
    char simbol;

    private static final RepositiryChar repositiryChar = new RepositiryChar();

    public AnswerSimbol(char startC,char startI,char finishC,char finishI) {
        this.startX  = repositiryChar.getX(startI);
        this.startY = repositiryChar.getY(startC);

        this.finalX = repositiryChar.getX(finishI);
        this.finalY = repositiryChar.getY(finishC);

        simbol = 'Q';
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
