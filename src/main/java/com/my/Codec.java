package com.my;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by alex.bykovsky on 3/22/17.
 */
public class Codec {

	private static final String PREFIX = "http://tinyurl.com/";

	private Map<Integer, String> decodeData = new HashMap<>();

	private Map<String, Integer> backwardDecodeData = new HashMap<>();

	private Base64.Encoder base64Encoder = Base64.getEncoder();

	private Base64.Decoder base64Decoder = Base64.getDecoder();

	private Random random = new Random();

	// Encodes a URL to a shortened URL.
	public String encode(String longUrl) {

		Integer key;
		if (backwardDecodeData.containsKey(longUrl)) {
			key = backwardDecodeData.get(longUrl);
			return base64Encoder.encodeToString(toBytes(key));
		} else {
			key = (int)(random.nextDouble() * Integer.MAX_VALUE);

			decodeData.put(key, longUrl);
			backwardDecodeData.put(longUrl, key);

			return base64Encoder.encodeToString(toBytes(key));
		}
	}

	// Decodes a shortened URL to its original URL.
	public String decode(String shortUrl) {
		Integer value = toInt(base64Decoder.decode(shortUrl.getBytes()));
		return decodeData.get(value);
	}

	private static byte[] toBytes(int i) {
		byte[] result = new byte[4];

		result[0] = (byte) (i >> 24);
		result[1] = (byte) (i >> 16);
		result[2] = (byte) (i >> 8);
		result[3] = (byte) (i /*>> 0*/);

		return result;
	}

	private static int toInt(byte[] bytes) {

		int result = 0;
		for(int i = 0; i < bytes.length; i++) {
			int shift = bytes.length - i - 1;
			if(i == 0) {
				result |= Integer.valueOf(bytes[i]) << (shift * 8);
			} else {
				result |= Integer.valueOf(bytes[i] & 0xFF) << (shift * 8);
			}
		}
		return result;
	}

	private static class Base64 {

		private Base64() {}

		public static Base64.Encoder getEncoder() {
			return Base64.Encoder.RFC4648;
		}

		public static Base64.Decoder getDecoder() {
			return Base64.Decoder.RFC4648;
		}

		public static class Encoder {

			private final byte[] newline;
			private final int linemax;
			private final boolean doPadding;

			private Encoder(byte[] newline, int linemax, boolean doPadding) {
				this.newline = newline;
				this.linemax = linemax;
				this.doPadding = doPadding;
			}

			private static final char[] toBase64 = {
					'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
					'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
					'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
					'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
					'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '/'
			};

			static final Base64.Encoder RFC4648 = new Base64.Encoder(null, -1, true);

			private final int outLength(int srclen) {
				int len = 0;
				if (doPadding) {
					len = 4 * ((srclen + 2) / 3);
				} else {
					int n = srclen % 3;
					len = 4 * (srclen / 3) + (n == 0 ? 0 : n + 1);
				}
				if (linemax > 0)                                  // line separators
					len += (len - 1) / linemax * newline.length;
				return len;
			}

			public byte[] encode(byte[] src) {
				int len = outLength(src.length);          // dst array size
				byte[] dst = new byte[len];
				int ret = encode0(src, 0, src.length, dst);
				if (ret != dst.length)
					return Arrays.copyOf(dst, ret);
				return dst;
			}

			@SuppressWarnings("deprecation")
			public String encodeToString(byte[] src) {
				byte[] encoded = encode(src);
				return new String(encoded, 0, 0, encoded.length);
			}

			private int encode0(byte[] src, int off, int end, byte[] dst) {
				char[] base64 = toBase64;
				int sp = off;
				int slen = (end - off) / 3 * 3;
				int sl = off + slen;
				if (linemax > 0 && slen  > linemax / 4 * 3)
					slen = linemax / 4 * 3;
				int dp = 0;
				while (sp < sl) {
					int sl0 = Math.min(sp + slen, sl);
					for (int sp0 = sp, dp0 = dp ; sp0 < sl0; ) {
						int bits = (src[sp0++] & 0xff) << 16 |
								(src[sp0++] & 0xff) <<  8 |
								(src[sp0++] & 0xff);
						dst[dp0++] = (byte)base64[(bits >>> 18) & 0x3f];
						dst[dp0++] = (byte)base64[(bits >>> 12) & 0x3f];
						dst[dp0++] = (byte)base64[(bits >>> 6)  & 0x3f];
						dst[dp0++] = (byte)base64[bits & 0x3f];
					}
					int dlen = (sl0 - sp) / 3 * 4;
					dp += dlen;
					sp = sl0;
					if (dlen == linemax && sp < end) {
						for (byte b : newline){
							dst[dp++] = b;
						}
					}
				}
				if (sp < end) {               // 1 or 2 leftover bytes
					int b0 = src[sp++] & 0xff;
					dst[dp++] = (byte)base64[b0 >> 2];
					if (sp == end) {
						dst[dp++] = (byte)base64[(b0 << 4) & 0x3f];
						if (doPadding) {
							dst[dp++] = '=';
							dst[dp++] = '=';
						}
					} else {
						int b1 = src[sp++] & 0xff;
						dst[dp++] = (byte)base64[(b0 << 4) & 0x3f | (b1 >> 4)];
						dst[dp++] = (byte)base64[(b1 << 2) & 0x3f];
						if (doPadding) {
							dst[dp++] = '=';
						}
					}
				}
				return dp;
			}
		}

		public static class Decoder {

			private Decoder() {
			}

			private static final int[] fromBase64 = new int[256];

			static {
				Arrays.fill(fromBase64, -1);
				for (int i = 0; i < Base64.Encoder.toBase64.length; i++)
					fromBase64[Base64.Encoder.toBase64[i]] = i;
				fromBase64['='] = -2;
			}

			static final Decoder RFC4648 = new Decoder();

			public byte[] decode(byte[] src) {
				byte[] dst = new byte[outLength(src, 0, src.length)];
				int ret = decode0(src, 0, src.length, dst);
				if (ret != dst.length) {
					dst = Arrays.copyOf(dst, ret);
				}
				return dst;
			}

			private int outLength(byte[] src, int sp, int sl) {
				int[] base64 = fromBase64;
				int paddings = 0;
				int len = sl - sp;
				if (len == 0)
					return 0;
				if (len < 2) {

					throw new IllegalArgumentException(
							"Input byte[] should at least have 2 bytes for base64Encoder bytes");
				}
				if (src[sl - 1] == '=') {
					paddings++;
					if (src[sl - 2] == '=')
						paddings++;
				}
				if (paddings == 0 && (len & 0x3) != 0)
					paddings = 4 - (len & 0x3);
				return 3 * ((len + 3) / 4) - paddings;
			}

			private int decode0(byte[] src, int sp, int sl, byte[] dst) {
				int[] base64 = fromBase64;
				int dp = 0;
				int bits = 0;
				int shiftto = 18;       // pos of first byte of 4-byte atom
				while (sp < sl) {
					int b = src[sp++] & 0xff;
					if ((b = base64[b]) < 0) {
						if (b == -2) {         // padding byte '='
							if (shiftto == 6 && (sp == sl || src[sp++] != '=') ||
									shiftto == 18) {
								throw new IllegalArgumentException(
										"Input byte array has wrong 4-byte ending unit");
							}
							break;
						}
						else
							throw new IllegalArgumentException(
									"Illegal base64Encoder character " +
											Integer.toString(src[sp - 1], 16));
					}
					bits |= (b << shiftto);
					shiftto -= 6;
					if (shiftto < 0) {
						dst[dp++] = (byte) (bits >> 16);
						dst[dp++] = (byte) (bits >> 8);
						dst[dp++] = (byte) (bits);
						shiftto = 18;
						bits = 0;
					}
				}
				// reached end of byte array or hit padding '=' characters.
				if (shiftto == 6) {
					dst[dp++] = (byte) (bits >> 16);
				} else if (shiftto == 0) {
					dst[dp++] = (byte) (bits >> 16);
					dst[dp++] = (byte) (bits >> 8);
				} else if (shiftto == 12) {
					// dangling single "x", incorrectly encoded.
					throw new IllegalArgumentException(
							"Last unit does not have enough valid bits");
				}
				// anything left is invalid, if is not MIME.
				// if MIME, ignore all non-base64Encoder character
				while (sp < sl) {
					throw new IllegalArgumentException(
							"Input byte array has incorrect ending byte at " + sp);
				}
				return dp;
			}
		}
	}
}