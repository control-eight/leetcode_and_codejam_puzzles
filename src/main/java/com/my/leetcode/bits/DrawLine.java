package com.my.leetcode.bits;

/**
 * Created by alex.bykovsky on 10/8/17.
 */
public class DrawLine {

	public static void main(String[] args) {
		byte[] screen = new byte[30];
		new DrawLine().drawline(screen, 3, 7, 21, 3);
		print(screen, 3);

		screen = new byte[30];
		new DrawLine().drawLine(screen, 3, 79, 92, 3);
		print(screen, 3);
	}

	//(x1, y) -> (x2, y)
	private void drawline(byte[] screen, int width, int x1, int x2, int y) {

		int byteStart = x1 / 8 + y * width;
		int byteEnd = x2 / 8 + y * width;

		int bitStart = x1 % 8 - 1;
		int bitEnd = x2 % 8 - 1;

		for(int i = byteStart; i <= byteEnd; i++) {
			if(i == byteStart) {
				bitStart = 1 << (8 - bitStart);
				screen[i] |= bitStart - 1;
			} else if(i == byteEnd) {
				bitEnd = 1 << (bitEnd - 1);
				screen[i] |= bitEnd - 1;
				screen[i] = (byte) ~screen[i];
			} else {
				screen[i] |= ((1 << 8) - 1);
			}
		}
	}

	private static void print(byte[] screen, int width) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < screen.length; i++) {

			byte b = screen[i];

			int value = b >= 0? b: (128 + 128 + b);

			int check = 1 << 7;

			while (check > 0) {
				if ((value & check) > 0) {
					sb.append("1");
				} else {
					sb.append("0");
				}
				check >>= 1;
			}

			if((i + 1) % width == 0) {
				sb.append("\n");
			}
		}
		System.out.println(sb);
	}

	void drawLine(byte[] screen, int width, int x1, int x2, int y) {
		int start_offset = x1 % 8;
		int first_full_byte = x1 / 8;
		if (start_offset != 0) {
			first_full_byte++;
		}

		int end_offset = x2 % 8;
		int last_full_byte = x2 / 8;
		if (end_offset != 7) {
			last_full_byte--;
		}

		//Set full bytes
		for (int b = first_full_byte; b <= last_full_byte; b++) {
			screen[(width / 8) * y + b] = (byte) 0xFF;
		}

		//Create masks for start and end of line
		byte start_mask = (byte) (0xFF >> start_offset);
		byte end_mask = (byte) ~(0xFF >> (end_offset + 1));

		//Set start and end of line
		if ((x1 / 8) == (x2 / 8)) {//x1 and x2 are in the same byte
			byte mask = (byte) (start_mask & end_mask);
			screen[(width / 8) * y + (x1 / 8)] |= mask;
		} else {
			if(start_offset != 0) {
				int byte_number = (width / 8) * y + first_full_byte - 1;
				screen[byte_number] |= start_mask;
			}
			if(end_offset != 7) {
				int byte_number = (width / 8) * y + last_full_byte + 1;
				screen[byte_number] |= end_mask;
			}
		}
	}
}