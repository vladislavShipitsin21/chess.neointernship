package neointernship.selfplay;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.player.Bot;

import java.util.ArrayList;
import java.util.Collection;

public class Room extends Thread {
    private final int idRoom;
    private final TypeBot typeBot1;
    private final int maxDepth1;
    private final TypeBot typeBot2;
    private final int maxDepth2;
    private final int countGame;

    public Room(final TypeBot typeBot1,final int maxDepth1,
                final TypeBot typeBot2, final int maxDepth2,
                final int idRoom,final int countGame) {
        this.typeBot1 = typeBot1;
        this.typeBot2 = typeBot2;

        this.maxDepth1 = maxDepth1;
        this.maxDepth2 = maxDepth2;

        this.idRoom = idRoom;
        this.countGame = countGame;
    }

    @Override
    public void run(){
        System.out.println("start room : " + idRoom);

        final Collection<Thread> threads = new ArrayList<>();

        final Bot bot1 = FactoryBot.getBot(typeBot1,Color.WHITE,maxDepth1);
        final Bot bot2 = FactoryBot.getBot(typeBot2,Color.BLACK,maxDepth2);


        for (int i = 0; i < countGame; i++) {
            final LocalLobby localLobby = new LocalLobby(bot1,bot2,idRoom * 10 + i);
            localLobby.start();
            threads.add(localLobby);
        }
        /*final Bot bot3 = FactoryBot.getBot(typeBot2,Color.WHITE,maxDepth2);
        final Bot bot4 = FactoryBot.getBot(typeBot1,Color.BLACK,maxDepth1);

        for (int i = 0; i < countGame; i++) {
            final LocalLobby localLobby = new LocalLobby(bot3,bot4,idRoom * 10 + i + countGame);
            localLobby.start();
            threads.add(localLobby);
        }*/

        for(final Thread lobby : threads){
            try {
                lobby.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }



        System.out.println("finish room : " + idRoom);
    }
}
