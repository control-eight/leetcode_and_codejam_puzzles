package com.my.leetcode.robot_room_cleaner;

public class RobotImpl implements Robot {

    private int[][] area;

    private int cleanCount;

    private int cleanCountRequired;

    public enum Direction {
        LEFT,
        RIGHT,
        TOP,
        DOWN
    }

    private Direction dir = Direction.TOP;

    private int posR;

    private int posC;

    public RobotImpl(int[][] area, int r, int c) {
        this.area = area;
        for (int[] ints : area) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    cleanCountRequired++;
                }
            }
        }
        this.posR = r;
        this.posC = c;
    }

    @Override
    public boolean move() {
        switch (dir) {
            case TOP: {
                if (posR <= 0 || this.area[this.posR-1][this.posC] == 0) return false;
                this.posR--;
                break;
            }
            case LEFT: {
                if (posC <= 0 || this.area[this.posR][this.posC-1] == 0) return false;
                this.posC--;
                break;
            }
            case DOWN: {
                if (posR >= this.area.length - 1 || this.area[this.posR+1][this.posC] == 0) return false;
                this.posR++;
                break;
            }
            case RIGHT: {
                if (posC >= this.area[this.posR].length - 1 || this.area[this.posR][this.posC+1] == 0) return false;
                this.posC++;
                break;
            }
        }
        return true;
    }

    @Override
    public void turnLeft() {
        switch (dir) {
            case TOP: {
                this.dir = Direction.LEFT;
                break;
            }
            case LEFT: {
                this.dir = Direction.DOWN;
                break;
            }
            case DOWN: {
                this.dir = Direction.RIGHT;
                break;
            }
            case RIGHT: {
                this.dir = Direction.TOP;
                break;
            }
        }
    }

    @Override
    public void turnRight() {
        switch (dir) {
            case TOP: {
                this.dir = Direction.RIGHT;
                break;
            }
            case LEFT: {
                this.dir = Direction.DOWN;
                break;
            }
            case DOWN: {
                this.dir = Direction.LEFT;
                break;
            }
            case RIGHT: {
                this.dir = Direction.TOP;
                break;
            }
        }
    }

    @Override
    public void clean() {
        //System.out.println(this.posR + ":" + this.posC);
        /*if (this.posR == 2 && this.posC == 0) {
            System.out.println();
        }*/
        this.cleanCount++;
    }

    @Override
    public String toString() {
        return "RobotImpl{" +
                "cleanCount=" + cleanCount +
                ", cleanCountRequired=" + cleanCountRequired +
                '}';
    }
}
