package com.codecool.sixhandshakes.finders;

import com.codecool.sixhandshakes.model.UserNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class HandshakeCalculator {
    public int counter = 0;
    public static int getMinimumHandshakesBetween(UserNode start, UserNode end) {
        int count = 0;
        Queue<UserNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(start);
        while (!nodeQueue.isEmpty()) {
            int queueSize = nodeQueue.size();

            while (queueSize != 0) {
                UserNode currentNode = nodeQueue.element();
                if (currentNode == end) {
                    count++;
                    return count;
                }
                Set<UserNode> friends = currentNode.getFriends();
                for (UserNode currentFriend : friends) {
                    if (currentFriend == end) {
                        count++;
                        return count;
                    }
                    nodeQueue.add(currentFriend);
                }
                queueSize--;
                nodeQueue.poll();
            }
            count++;
        }
        return count;
    }
}
