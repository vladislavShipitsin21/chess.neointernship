package neointernship.chess.game.model.player;

public class Time {
    private long startTime;
    private long finishTime;

    private double allTime;
    private int countAddress;

    private long maxTime;
    private int countUnlegal;

    private final static long MAX_TIME = 5000;

    public Time() {
        this.startTime = 0;
        this.finishTime = 0;

        this.allTime = 0;
        this.countAddress = 0;

        maxTime = 0;
        countUnlegal = 0;
    }
    public final void start() {
        startTime = System.currentTimeMillis();
    }
    public final void finish() {
        finishTime = System.currentTimeMillis();
    }
    public final long getTime(){
        final long divTime = finishTime - startTime;

        allTime += divTime;
        countAddress++;

        if(divTime >= maxTime){
            maxTime = divTime;
        }
        if(divTime >= MAX_TIME){
            countUnlegal++;
        }

        return divTime;
    }
    public final double getAllTime(){
        return allTime;
    }
    public final double getAverageTime(){
        return allTime / countAddress;
    }
    public final double getMaxTime(){
        return maxTime;
    }
    public final double getCountUnlegal(){
        return countUnlegal;
    }
}
