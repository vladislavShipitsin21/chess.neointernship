package neointernship.chess.game.model.enums;

public enum Color {
    BLACK, WHITE, BOTH;

    public static Color swapColor(Color color){
        return color == Color.WHITE ? Color.BLACK : Color.WHITE;
    }
}
