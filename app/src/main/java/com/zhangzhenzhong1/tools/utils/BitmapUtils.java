package com.zhangzhenzhong1.tools.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@SuppressLint("DefaultLocale")
public class BitmapUtils {

	public static String saveImageToSd(Bitmap bitmap, String filePath,
									   String imageName, boolean isRecycel) {
		String path = null;
		FileOutputStream fos = null;
		try {
			File dir = new File(filePath);
			if (!dir.isDirectory()) {
				if (dir.exists()) {
					dir.delete();
				}
				dir.mkdirs();
			}
			File f = new File(dir, imageName);
			fos = new FileOutputStream(f);

			Bitmap b = bitmap;

			String format = imageName.substring(imageName.lastIndexOf(".") + 1,
					imageName.length());
			format = format.trim().toLowerCase();
			if (format != null && format.equals("jpg")) {
				b.compress(CompressFormat.JPEG, 100, fos);
			} else {
				b.compress(CompressFormat.PNG, 50, fos);
			}

			fos.flush();
			if (isRecycel) {
				if (bitmap != null) {
					bitmap.recycle();
					bitmap = null;
				}
			}
			path = f.getAbsolutePath();
			return path;
		} catch (Exception e) {
			e.printStackTrace();
			return path;
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					return path;
				}
			}
		}
	}

	/**
	 * Converts the provided byte array from YUV420SP into an RGBA bitmap.
	 * 
	 * @param context
	 * @param yuv420sp
	 *            The YUV420SP data
	 * @param width
	 *            Width of the data's picture
	 * @param height
	 *            Height of the data's picture
	 * @return A decoded bitmap
	 * @throws NullPointerException
	 * @throws IllegalArgumentException
	 */
	public static Bitmap decodeYUV420SP(Context context, byte[] yuv420sp,
										int width, int height) throws NullPointerException,
			IllegalArgumentException {

		Bitmap bmp = null;

		final int frameSize = width * height;
		int[] rgb = new int[frameSize];

		for (int j = 0, yp = 0; j < height; j++) {
			int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
			for (int i = 0; i < width; i++, yp++) {
				int y = (0xff & ((int) yuv420sp[yp])) - 16;
				if (y < 0) {
					y = 0;
				}
				if ((i & 1) == 0) {
					v = (0xff & yuv420sp[uvp++]) - 128;
					u = (0xff & yuv420sp[uvp++]) - 128;
				}

				int y1192 = 1192 * y;
				int r = (y1192 + 1634 * v);
				int g = (y1192 - 833 * v - 400 * u);
				int b = (y1192 + 2066 * u);

				if (r < 0) {
					r = 0;
				} else if (r > 262143) {
					r = 262143;
				}
				if (g < 0) {
					g = 0;
				} else if (g > 262143) {
					g = 262143;
				}
				if (b < 0) {
					b = 0;
				} else if (b > 262143) {
					b = 262143;
				}

				rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000)
						| ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
			}
		}

		bmp = Bitmap.createBitmap(rgb, width, height, Bitmap.Config.ARGB_8888);

		return bmp;
	}

	public static byte[] decodeYUV420SPArray(Context context, byte[] yuv420sp,
											 int width, int height) throws NullPointerException,
			IllegalArgumentException {

		Bitmap bmp = null;

		final int frameSize = width * height;
		int[] rgb = new int[frameSize];

		for (int j = 0, yp = 0; j < height; j++) {
			int uvp = frameSize + (j >> 1) * width, u = 0, v = 0;
			for (int i = 0; i < width; i++, yp++) {
				int y = (0xff & ((int) yuv420sp[yp])) - 16;
				if (y < 0) {
					y = 0;
				}
				if ((i & 1) == 0) {
					v = (0xff & yuv420sp[uvp++]) - 128;
					u = (0xff & yuv420sp[uvp++]) - 128;
				}

				int y1192 = 1192 * y;
				int r = (y1192 + 1634 * v);
				int g = (y1192 - 833 * v - 400 * u);
				int b = (y1192 + 2066 * u);

				if (r < 0) {
					r = 0;
				} else if (r > 262143) {
					r = 262143;
				}
				if (g < 0) {
					g = 0;
				} else if (g > 262143) {
					g = 262143;
				}
				if (b < 0) {
					b = 0;
				} else if (b > 262143) {
					b = 262143;
				}

				rgb[yp] = 0xff000000 | ((r << 6) & 0xff0000)
						| ((g >> 2) & 0xff00) | ((b >> 10) & 0xff);
			}
		}

		bmp = Bitmap.createBitmap(rgb, width, height, Bitmap.Config.ARGB_8888);
		if (bmp != null) {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			bmp.compress(Bitmap.CompressFormat.PNG, 100, baos);
			return baos.toByteArray();
		}

		return null;
	}
	/**
	 * // 实例化字节数组输出流
	 * @param bitmap
	 * @return
	 */
	public static byte[] getBytes(Bitmap bitmap) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.PNG, 0, baos);// 压缩位图
		return baos.toByteArray();// 创建分配字节数组
	}
	/**
	 * //从字节数组解码位图
	 * @param data
	 * @return
	 */
	public static Bitmap getBitmap(byte[] data) {
		return BitmapFactory.decodeByteArray(data, 0, data.length);
	}
	// public static Bitmap sacleFileSize(File file, int maxNumOfPixels) {
	//
	// Bitmap resizeBmp = null;
	// try{
	// BitmapFactory.Options options = new BitmapFactory.Options();
	// options.inJustDecodeBounds = true;
	// options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	// BitmapFactory.decodeFile(file.getPath(), options);
	// options.inSampleSize = computeSampleSize(options, UNCONSTRAINED,
	// maxNumOfPixels);
	// options.inJustDecodeBounds = false;
	// options.inDither = false;
	// options.inPreferredConfig = Bitmap.Config.ARGB_8888;
	// resizeBmp= BitmapFactory.decodeFile(file.getPath(), options);
	// }catch (OutOfMemoryError ex) {
	// ex.printStackTrace();
	// }
	//
	// return resizeBmp;
	// }
	//
	//
	//
	// public static int computeSampleSize(BitmapFactory.Options options,
	// int minSideLength, int maxNumOfPixels) {
	// int initialSize = computeInitialSampleSize(options, minSideLength,
	// maxNumOfPixels);
	//
	// int roundedSize;
	// if (initialSize <= 8) {
	// roundedSize = 1;
	// while (roundedSize < initialSize) {
	// roundedSize <<= 1;
	// }
	// } else {
	// roundedSize = (initialSize + 7) / 8 * 8;
	// }
	//
	// return roundedSize;
	// }
}
