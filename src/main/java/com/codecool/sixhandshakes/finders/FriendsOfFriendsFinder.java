package com.codecool.sixhandshakes.finders;

import com.codecool.sixhandshakes.model.UserNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class FriendsOfFriendsFinder {
    public static Set<UserNode> getFriendsOfFriends(UserNode user, int distance) {
        int count = 0;
        Queue<UserNode> nodeQueue = new LinkedList<>();
        Set<UserNode> friends;
        Set<UserNode> friendslist = user.getFriends();
        nodeQueue.add(user);
        while (!nodeQueue.isEmpty()) {
            int queueSize = nodeQueue.size();

            while (queueSize != 0) {
                UserNode currentNode = nodeQueue.element();
                friends = currentNode.getFriends();
                if (count > 0){
                    for (UserNode friend : friends){
                        friendslist.add(friend);
                    }
                }
                if (count == distance) {
                    return friendslist;
                }
                for (UserNode currentFriend : friends) {
                    nodeQueue.add(currentFriend);
                }
                queueSize--;
                nodeQueue.poll();
            }
            count++;
        }
        return null;
    }
}
