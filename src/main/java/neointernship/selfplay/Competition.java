package neointernship.selfplay;

import java.util.*;

import static neointernship.selfplay.TypeBot.*;

public class Competition {

    public static void startCompetition(final HashSet<TypeBot> typeBots,final int countGame){
        int idRoom = 0;
        final List<Room> rooms = new LinkedList<>();
        for(final TypeBot typeBot1 : typeBots){
            for(final TypeBot typeBot2 : typeBots){
                if(typeBot1 != typeBot2){
                    idRoom++;
                    final Room room = new Room(typeBot1,2,typeBot2,2,idRoom,countGame);
                    room.run();
                    rooms.add(room);
                }
            }
        }
//        for(final Room room : rooms){
//            try {
//                room.join();
//            } catch (final InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
    public static void main(final String[] args) {
        System.out.println("Start competition");

        final HashSet<TypeBot> typeBots = new HashSet<>();
//        typeBots.add(RANDOM);
        typeBots.add(MIXID);
       // typeBots.add(MINI_MAX_AB);
        typeBots.add(MINI_MAX);
//        typeBots.add(MONTE_KARLO);
//        typeBots.add(EXPECTIMAX);

        Room room = new Room(MIXID,2,MINI_MAX,2,1,1);
        room.run();

//        startCompetition(typeBots,1);

        System.out.println("finish competition");
    }
}
