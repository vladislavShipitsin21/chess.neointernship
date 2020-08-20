package neointernship.selfplay;

import neointernship.chess.game.model.enums.Color;
import neointernship.chess.game.model.figure.factory.Factory;
import neointernship.chess.game.model.player.*;

import java.util.*;

import static neointernship.selfplay.TypeBot.*;

public class Competition {

    public static void start(final HashSet<TypeBot> typeBots){
        int idRoom = 0;
        final List<Room> rooms = new LinkedList<>();
        for(final TypeBot typeBot1 : typeBots){
            for(final TypeBot typeBot2 : typeBots){
                if(typeBot1 != typeBot2){
                    idRoom++;
                    final Room room = new Room(typeBot1,2,typeBot2,2,idRoom,1);
                    room.start();
                    rooms.add(room);
                }
            }
        }
        for(final Room room : rooms){
            try {
                room.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(final String[] args) {
        System.out.println("Start competition");

        final HashSet<TypeBot> typeBots = new HashSet<>();
       // typeBots.add(RANDOM);
        typeBots.add(MINI_MAX);
       // typeBots.add(MINI_MAX_AB);
        typeBots.add(MINI_MAX_THREAD);
        //typeBots.add(EXPECTIMAX);

        start(typeBots);

        System.out.println("finish competition");
    }
}
