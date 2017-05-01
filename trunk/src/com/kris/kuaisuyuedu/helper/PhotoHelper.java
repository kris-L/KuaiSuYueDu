package com.kris.kuaisuyuedu.helper;

import java.io.File;   
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.kris.kuaisuyuedu.data.Const;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

/**
 * 
 * 图片选择帮助类
 * 
 * @author Harlan
 * 
 */
public class PhotoHelper {

	private static final String TAG = "PhotoHelper";

	/**
	 * 头像名称
	 */
	public static final String IMAGE_FACE = "faceImage.jpg";

	public static final String IMAGE_CAR = "carImage.jpg";

	public static String image = "default.jpeg";

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * 本地图片请求
	 */
	public static final int IMAGE_REQUEST_CODE = 0;

	/**
	 * 拍照请求
	 */
	public static final int CAMERA_REQUEST_CODE = 1;

	/**
	 * 裁剪图片请求
	 */
	public static final int RESULT_REQUEST_CODE = 2;

	private static String[] items = new String[] { "选择本地图片", "拍照" };

	private Context context;

	public PhotoHelper(Context context) {
		this.context = context;
	}

	/**
	 * 选择图片对话框
	 */
	public void showDialog() {
		new AlertDialog.Builder(context).setIcon(null).setTitle("设置头像")
				.setItems(items, new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						switch (which) {
						// 本地图片
						case 0:
							selectiAction();
							break;
						// 拍照
						case 1:
							camcerAction();
							break;
						}
					}
				})
				.setNegativeButton("取消", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				}).show();
	}

	/**
	 * 拍照
	 */
	public void camcerAction() {
		Intent intentFromCapture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// 判断存储卡是否可以用，可用进行存储
		if (Environment.MEDIA_MOUNTED.equals(Environment
				.getExternalStorageState())) {
			intentFromCapture.putExtra(MediaStore.EXTRA_OUTPUT,
					Uri.fromFile(getFile()));
		}

		((Activity) context).startActivityForResult(intentFromCapture,
				CAMERA_REQUEST_CODE);
	}

	/**
	 * 选择图片
	 */
	public void selectiAction() {
		Intent intentFromGallery = new Intent();
		intentFromGallery.setType("image/*"); // 设置文件类型
		intentFromGallery.setAction(Intent.ACTION_GET_CONTENT);
		((Activity) context).startActivityForResult(intentFromGallery,
				IMAGE_REQUEST_CODE);
	}

	/**
	 * 裁剪图片
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		// 设置裁剪
		intent.putExtra("crop", "true");
		// aspectX aspectY 是宽高的比例
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		// outputX outputY 是裁剪图片宽高
		intent.putExtra("outputX", 320);
		intent.putExtra("outputY", 320);
		intent.putExtra("return-data", true);

		// 启用人脸识别
		intent.putExtra("noFaceDetection", true);
		((Activity) context)
				.startActivityForResult(intent, RESULT_REQUEST_CODE);
	}

	/**
	 * 创建缓存目录
	 */
	public File getFile() {
		String path = Environment.getExternalStorageDirectory().getPath()
				+ File.separatorChar + Const.APP_STORAGE_PATH
				+ File.separatorChar + "platePicture";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		return new File(file, image);
	}

	/**
	 * 压缩图片
	 * 
	 * @param data
	 * @return
	 */
	public void getCropBitmap(Intent data) {
		Bundle extras = data.getExtras();
		if (data != null && extras != null) {
			Bitmap photo = extras.getParcelable("data");
			try {
				FileOutputStream fos = new FileOutputStream(getFile());
				photo.compress(Bitmap.CompressFormat.JPEG, 90, fos);// 压缩率30%
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
//			return drawable;
		}
//		return null;
	}

	/**
	 * 缩小图片质量及大小
	 * 
	 * @param uri
	 * @param file
	 *            输出路径
	 */
	public void compressByUri(Uri uri, File file) {
		try {
			Bitmap bitmap = MediaStore.Images.Media.getBitmap(
					context.getContentResolver(), uri);
			float width = bitmap.getWidth();
			float height = bitmap.getHeight();
			Log.i(TAG, "width" + width);
			Log.i(TAG, "height" + height);
			float scale = 0;
			if (width > 480) {
				scale = 480 / width;
			}
			Log.i(TAG, "scale" + scale);
			Matrix m = new Matrix();
			m.postScale(scale, scale);
			Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, (int) width,
					(int) height, m, true);
			FileOutputStream fos = new FileOutputStream(file);
			newBitmap.compress(Bitmap.CompressFormat.JPEG, 92, fos);// 压缩率5%
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 缩小图片质量及大小
	 * 
	 * @param file
	 *            图片路径
	 */
	public void compressByFile(File file) {
		try {
			Bitmap bitmap = new BitmapFactory().decodeFile(file.toString());
			float width = bitmap.getWidth();
			float height = bitmap.getHeight();
			Log.i(TAG, "width" + width);
			Log.i(TAG, "height" + height);
			float scale = 0;
			if (width > 960) {
				scale = 960 / width;
			}
			Log.i(TAG, "scale" + scale);
			Matrix m = new Matrix();
			m.postScale(scale, scale);
			Bitmap newBitmap = Bitmap.createBitmap(bitmap, 0, 0, (int) width,
					(int) height, m, true);
			FileOutputStream fos = new FileOutputStream(file);
			newBitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);// 压缩率5%
			fos.flush();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取图片的旋转的角度
	 *
	 * @param path
	 *            图片绝对路径
	 * @return 图片的旋转角度
	 */
	public int getBitmapDegree(String path) {
	    int degree = 0;
	    try {
	        // 从指定路径下读取图片，并获取其EXIF信息
	        ExifInterface exifInterface = new ExifInterface(path);
	        // 获取图片的旋转信息
	        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION,
	                ExifInterface.ORIENTATION_NORMAL);
	        switch (orientation) {
	        case ExifInterface.ORIENTATION_ROTATE_90:
	            degree = 90;
	            break;
	        case ExifInterface.ORIENTATION_ROTATE_180:
	            degree = 180;
	            break;
	        case ExifInterface.ORIENTATION_ROTATE_270:
	            degree = 270;
	            break;
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return degree;
	}
	
	/**
	 * 将图片按照某个角度进行旋转
	 *
	 * @param bm
	 *            需要旋转的图片
	 * @param degree
	 *            旋转角度
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree) {
	    Bitmap returnBm = null;
	  
	    // 根据旋转角度，生成旋转矩阵
	    Matrix matrix = new Matrix();
	    matrix.postRotate(degree);
	    try {
	        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
	        returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
	    } catch (OutOfMemoryError e) {
	    }
	    if (returnBm == null) {
	        returnBm = bm;
	    }
	    if (bm != returnBm) {
	        bm.recycle();
	    }
	    return returnBm;
	}	

	/**
	 * 将图片按照某个角度进行旋转,并保存在指定位置
	 *
	 * @param bm
	 *            需要旋转的图片
	 * @param degree
	 *            旋转角度
	 * @param path
	 *            图片保存的位置           
	 * @return 旋转后的图片
	 */
	public static Bitmap rotateBitmapByDegree(Bitmap bm, int degree,File file) {
	    Bitmap returnBm = null;
	  
	    // 根据旋转角度，生成旋转矩阵
	    Matrix matrix = new Matrix();
	    matrix.postRotate(degree);
	    try {
	        // 将原始图片按照旋转矩阵进行旋转，并得到新的图片
	        returnBm = Bitmap.createBitmap(bm, 0, 0, bm.getWidth(), bm.getHeight(), matrix, true);
	    } catch (OutOfMemoryError e) {
	    }
	    if (returnBm == null) {
	        returnBm = bm;
	    }
	    if (bm != returnBm) {
	        bm.recycle();
	    }
	    
		try {
			FileOutputStream out = new FileOutputStream(file);
			returnBm.compress(Bitmap.CompressFormat.JPEG, 90, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    return returnBm;
	}
	
}
