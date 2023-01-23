package com.codecool.sixhandshakes.finders;

import com.codecool.sixhandshakes.model.UserNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class FriendChainCalculator {
    static List<UserNode> chain = new ArrayList<>();
    
    public static List<UserNode> getShortestRoutesBetween(UserNode start, UserNode end) {       
        int count = 0;
        UserNode finalNode = end;
        Queue<UserNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(start);
        while (!nodeQueue.isEmpty()) {
            int queueSize = nodeQueue.size();
            outerloop:
            while (queueSize != 0) {
                UserNode currentNode = nodeQueue.element();
                if (currentNode == end) {
                    count++;
                    Collections.reverse(chain);
                    chain.add(finalNode);
                    return chain;
                }
                Set<UserNode> friends = currentNode.getFriends();
                for (UserNode currentFriend : friends) {
                    if (currentFriend == end) {
                        count++;
                        if (!chain.contains(currentNode)){
                            chain.add(currentNode);
                        }
                        end = currentNode;
                        nodeQueue.clear();
                        nodeQueue.add(start);
                        break outerloop;
                    }
                    if (!nodeQueue.contains(currentFriend)){
                        nodeQueue.add(currentFriend);
                    }
                }
                queueSize--;
                nodeQueue.poll();
            }
            count++;
        }
        return null;
    }
}
