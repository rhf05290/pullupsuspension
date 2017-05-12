package com.runtai.pullupsuspension.utils;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.ClipboardManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.io.UnsupportedEncodingException;


public class FraCommUtil {


	public static void callPhone(Context ctx, String phoneUrl) {
		Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneUrl));
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		ctx.startActivity(intent);
	}

	/**
	 * 查询当前设备是否显示了虚拟按键
	 *
	 * api17及以上才会调用，17一下return false
	 * @param windowManager
	 * @return
	 */
	@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
	public static boolean hasSoftKeys(WindowManager windowManager) {

		if(Build.VERSION.SDK_INT <17){
			return false;
		}

		Display d = windowManager.getDefaultDisplay();

		DisplayMetrics realDisplayMetrics = new DisplayMetrics();
		d.getRealMetrics(realDisplayMetrics);

		int realHeight = realDisplayMetrics.heightPixels;
		int realWidth = realDisplayMetrics.widthPixels;

		DisplayMetrics displayMetrics = new DisplayMetrics();
		d.getMetrics(displayMetrics);

		int displayHeight = displayMetrics.heightPixels;
		int displayWidth = displayMetrics.widthPixels;

		return (realWidth - displayWidth) > 0
				|| (realHeight - displayHeight) > 0;
	}


	public static void dismissInput(Activity act) {

		InputMethodManager systemService = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);

		View currentFocus = act.getCurrentFocus();

		if (currentFocus != null) {
			systemService.hideSoftInputFromWindow(currentFocus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);

		}

	}

	public static void showInput(Activity act) {

		InputMethodManager systemService = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);

		View currentFocus = act.getCurrentFocus();

		if (currentFocus != null) {
			systemService.showSoftInput(currentFocus, 0);

		}

	}

	// 判断wifi网络是否可用
	public static boolean isWifiDataEnable(Context context) {
		try {
			ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			boolean isWifiDataEnable = false;
			isWifiDataEnable = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).isConnectedOrConnecting();
			return isWifiDataEnable;

		} catch (Exception e) {
			// TODO Auto-generated catch block
			return false;
		}
	}

	public static String getMac(Context ctx) {

		String macAddress = "0";

		WifiManager wifi = (WifiManager) ctx.getSystemService(Context.WIFI_SERVICE);

		WifiInfo info = wifi.getConnectionInfo();

		macAddress = info.getMacAddress();

		return macAddress;

	}

	public static String getPackageName(Context act) {
		PackageManager pm = act.getPackageManager();
		try {
			PackageInfo packInfo = pm.getPackageInfo(act.getPackageName(), 0);
			return packInfo.packageName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String getVersionName(Context act) {
		PackageManager pm = act.getPackageManager();
		try {
			PackageInfo packInfo = pm.getPackageInfo(act.getPackageName(), 0);
			return packInfo.versionName;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return "";
		}
	}

	public static int getVersionCode(Context act) {
		PackageManager pm = act.getPackageManager();
		try {
			PackageInfo packInfo = pm.getPackageInfo(act.getPackageName(), 0);
			return packInfo.versionCode;
		} catch (NameNotFoundException e) {
			e.printStackTrace();
			return 1;
		}
	}

	@SuppressWarnings("deprecation")
	public static File getUriToFile(Activity ctx, Uri uri) {
		File file = null;
		try {
			String[] proj = { MediaStore.Images.Media.DATA };

			Cursor actualimagecursor = ctx.managedQuery(uri, proj, null, null, null);

			int actual_image_column_index = actualimagecursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);

			actualimagecursor.moveToFirst();

			String img_path = actualimagecursor.getString(actual_image_column_index);

			file = new File(img_path);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return file;
	}

	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * get Screen metrics
	 *
	 * @return
	 */
	public static DisplayMetrics getScreenMetrics(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		return dm;
	}


	public static File getImgPath() {

		if (isSDcardExist()) {
			File sdCard = FraCommUtil.getSDCard();
			return new File(sdCard + "/" + FraConstans.APP_ROOT_PATH + FraConstans.APP_IMAGE_LOADER);
		}

		return null;
	}



	public static boolean isSDcardExist() {

		return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
	}

	public static File getSDCard() {

		return Environment.getExternalStorageDirectory();

	}

	/**
	 * 获得当前进程的名字
	 *
	 * @param context
	 * @return 进程号
	 */
	public static String getCurProcessName(Context context) {

		int pid = android.os.Process.myPid();

		ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

		for (ActivityManager.RunningAppProcessInfo appProcess : activityManager.getRunningAppProcesses()) {

			if (appProcess.pid == pid) {
				return appProcess.processName;
			}
		}
		return null;
	}

	public static boolean isNetworkConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null) {
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static void copyText(String paramString, Context paramContext) {
		((ClipboardManager) paramContext.getSystemService("clipboard")).setText(paramString.trim());
	}


	static final int GB_SP_DIFF = 160;
	// 存放国标一级汉字不同读音的起始区位码
	static final int[] secPosValueList = { 1601, 1637, 1833, 2078, 2274, 2302,
			2433, 2594, 2787, 3106, 3212, 3472, 3635, 3722, 3730, 3858, 4027,
			4086, 4390, 4558, 4684, 4925, 5249, 5600 };
	// 存放国标一级汉字不同读音的起始区位码对应读音
	static final char[] firstLetter = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
			'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x',
			'y', 'z' };
	// 获取一个汉字的首字母
	public static Character getFirstLetter(char ch) {

		byte[] uniCode = null;
		try {
			uniCode = String.valueOf(ch).getBytes("GBK");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
		if (uniCode[0] < 128 && uniCode[0] > 0) { // 非汉字
			return null;
		} else {
			return convert(uniCode);
		}
	}

	/**
	 * 获取一个汉字的拼音首字母。 GB码两个字节分别减去160，转换成10进制码组合就可以得到区位码
	 * 例如汉字“你”的GB码是0xC4/0xE3，分别减去0xA0（160）就是0x24/0x43
	 * 0x24转成10进制就是36，0x43是67，那么它的区位码就是3667，在对照表中读音为‘n’
	 */
	static char convert(byte[] bytes) {
		char result = '-';
		int secPosValue = 0;
		int i;
		for (i = 0; i < bytes.length; i++) {
			bytes[i] -= GB_SP_DIFF;
		}
		secPosValue = bytes[0] * 100 + bytes[1];
		for (i = 0; i < 23; i++) {
			if (secPosValue >= secPosValueList[i]
					&& secPosValue < secPosValueList[i + 1]) {
				result = firstLetter[i];
				break;
			}
		}
		return result;
	}

}
