package com.codecool.sixhandshakes;

import com.codecool.sixhandshakes.finders.FriendChainCalculator;
import com.codecool.sixhandshakes.finders.FriendsOfFriendsFinder;
import com.codecool.sixhandshakes.finders.HandshakeCalculator;
import com.codecool.sixhandshakes.model.UserNode;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class SixHandshakes {
    private static List<UserNode> users;
    private static GraphPlotter graphPlotter;

    public static void main(String[] args) throws IOException {
        UserNode start = null;
        UserNode end = null;
        boolean checker;
        initSocialGraph();
        graphPlotter = new GraphPlotter(users);
        int menuanswer = printmenu();
        if (menuanswer == 1){
            outerloop:
            while(true){
                String name1first = users.get(5).getFirstName();
                String name1last = users.get(5).getLastName();
                String name2first = users.get(7).getFirstName();
                String name2last = users.get(7).getLastName();
                for (int j = 0; j < users.size();j++){
                    for (int i = 0; i < users.size();i++){
                        if (users.get(i).getFirstName() == name1first && users.get(i).getLastName() == name1last){
                            if (users.get(j).getFirstName() == name2first && users.get(j).getLastName() == name2last){
                                start = users.get(i);
                                end = users.get(j);
                                break outerloop;
                            }
                        }
                    }
                }
            }
            int count = HandshakeCalculator.getMinimumHandshakesBetween(start, end);
            System.out.println("Handshakes between " + start + " and " + end + " is " + count + " ");;

        }
        if (menuanswer == 2){
            int distance = 4;
            UserNode user = users.get(6);
            Set<UserNode> friends = FriendsOfFriendsFinder.getFriendsOfFriends(user, distance);
            System.out.println("Friends of friends with distance " + distance + " about " + user + " is:" + friends + " ");;
        }
        if (menuanswer == 3){
            UserNode beginuser = users.get(10);
            UserNode finaluser = users.get(20);
            List<UserNode> chain = FriendChainCalculator.getShortestRoutesBetween(beginuser, finaluser);
            System.out.println("Shortest path between " + beginuser + " and " + finaluser + " is: " + chain + " ");;
        }
        System.out.println("Done!");
    }

    private static void initSocialGraph() {
        RandomDataGenerator generator = new RandomDataGenerator();
        users = generator.generate();
    }

    private static void visualizeFriendCircle(Set<UserNode> friendCircle, UserNode user) {
        graphPlotter.highlightNodes(friendCircle, user);
    }

    private static void printRoutes(List<List<UserNode>> routes) {
        for (List<UserNode> nodes : routes) {
            System.out.print("\nRoute with " + nodes.size() + " steps:");
            for (int i = 0, nodesSize = nodes.size(); i < nodesSize; i++) {
                UserNode node = nodes.get(i);
                System.out.print(" " + node.getId());
                if (i < nodesSize - 1) {
                    System.out.print(" ->");
                }
            }
        }
        System.out.println();
    }

    private static int printmenu() throws IOException{
        int selection;
        Scanner number = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Minimum handshakes");
        System.out.println("2 - Friends of friends");
        System.out.println("3 - Shortest routes");


        selection = number.nextInt();
        number.close();
        return selection;  
    }
}
