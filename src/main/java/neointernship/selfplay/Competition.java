package neointernship.selfplay;

import java.util.*;

import static neointernship.selfplay.TypeBot.*;

public class Competition {

    public static void startCompetition(final HashSet<TypeBot> typeBots,final int countGame){
        int idRoom = 0;
        for(final TypeBot typeBot1 : typeBots){
            for(final TypeBot typeBot2 : typeBots){
                if(typeBot1 != typeBot2){
                    idRoom++;
                    final Room room = new Room(typeBot1,2,typeBot2,2,idRoom,countGame);
                    room.run();
                }
            }
        }
    }
    public static void main(final String[] args) {
        System.out.println("Start competition");

        final HashSet<TypeBot> typeBots = new HashSet<>();
//        typeBots.add(RANDOM);
        typeBots.add(MIXID);
//        typeBots.add(MINI_MAX_AB);
//        typeBots.add(MINI_MAX);
//        typeBots.add(MINI_MAX_THREAD);
//        typeBots.add(EXPECTIMAX);

       /* Room room = new Room(MIXID,2,MINI_MAX,2,1,1);
        room.run();*/

        for(int i = 0 ; i < 2 ; i++) {
            startCompetition(typeBots, 1);
        }

        System.out.println("finish competition");
    }
}
