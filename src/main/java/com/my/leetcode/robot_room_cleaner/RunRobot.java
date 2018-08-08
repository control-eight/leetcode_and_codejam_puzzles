package com.my.leetcode.robot_room_cleaner;

public class RunRobot {

    public static void main(String[] args) {
        int[][] area = new int[][]{
                {1,1,1,1,1,0,1,1},
                {1,1,1,1,1,0,1,1},
                {1,0,1,1,1,1,1,1},
                {0,0,0,1,0,0,0,0},
                {1,1,1,1,1,1,1,1}
        };
        Robot robot = new RobotImpl(area, 1, 3);
        new Solution().cleanRoom(robot);
        System.out.println(robot.toString());
    }
}
