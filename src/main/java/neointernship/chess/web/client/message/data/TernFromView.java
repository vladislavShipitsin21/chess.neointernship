package neointernship.chess.web.client.message.data;

public class TernFromView implements Data<Integer[]> {
    private final Integer[] integers;

    public TernFromView(final Integer[] integers){
        this.integers = integers;
    }

    @Override
    public Integer[] getData() {
        return integers;
    }
}
