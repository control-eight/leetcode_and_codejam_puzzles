package com.my.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by alex.bykovsky on 10/1/17.
 */
public class Game {

	public static void main(String[] args) {
		new Game().startGame(new GameConditions(new ArrayList<>(Arrays.asList(1,2,3,4,5)),
				new ArrayList<>(Arrays.asList(1,2,3,4,5)), new ArrayList<>(Arrays.asList(1,2,3,4,5)),
				5,5,5));


		int numberOfGames = 1000;
		int numberOfCards = 15;
		Random random = new Random(numberOfGames);

		int sum = 0;
		for(int i = 0; i < 1000; i++) {

			List<Integer> weapons = new ArrayList<>(Arrays.asList(1,2,3,4,5));
			List<Integer> people = new ArrayList<>(Arrays.asList(1,2,3,4,5));
			List<Integer> rooms = new ArrayList<>(Arrays.asList(1,2,3,4,5));

			sum  += new Game(). startGame(new GameConditions(weapons, people, rooms, ((int)(random.nextDouble() * 5) + 1),
					((int)(random.nextDouble() * 5) + 1),
			((int)(random.nextDouble() * 5) + 1)));
		}
		System.out.println("avg: " + ((double)sum/numberOfGames));
	}

	public int startGame(GameConditions gameCond) {

		System.out.println(gameCond);

		Integer weapon = gameCond.weapons.get(0);
		Integer person = gameCond.people.get(0);
		Integer room = gameCond.rooms.get(0);

		int moves = 1;
		System.out.println("#" + moves);
		System.out.println("p: " + weapon + " " + person + " " + room);

		while(weapon != gameCond. weapon || person != gameCond. person || room != gameCond. room) {
			if(weapon != gameCond. weapon) {
				System.out.println("m: w(" + weapon + ")");
				gameCond.weapons.remove(0);
				weapon =  gameCond.weapons.get(0);
			} else if(person != gameCond. person) {
				System.out.println("m: p(" + person + ")");
				gameCond.people.remove(0);
				person =  gameCond.people.get(0);
			} else if(room != gameCond. room) {
				System.out.println("m: r(" + room + ")");
				gameCond.rooms.remove(0);
				room =  gameCond.rooms.get(0);
			}

			System.out.println("#" + (++moves));
			System.out.println("p: " + weapon + " " + person + " " + room);
		}
		System.out.println("Correct answer: " + weapon + " " + person + " " + room);
		System.out.println("Numbers: " + moves);
		return moves;
	}

	private static class GameConditions {

		List<Integer> weapons;
		List<Integer> people;
		List<Integer> rooms;
		int weapon; int person; int room;

		public GameConditions(List<Integer> weapons, List<Integer> people, List<Integer> rooms, int weapon, int person, int room) {
			this.weapons = weapons;
			this.people = people;
			this.rooms = rooms;
			this.weapon = weapon;
			this.person = person;
			this.room = room;
		}

		@Override
		public String toString() {
			return "GameConditions{" +
					"weapons=" + weapons +
					", people=" + people +
					", rooms=" + rooms +
					", weapon=" + weapon +
					", person=" + person +
					", room=" + room +
					'}';
		}
	}
}
