package com.runtai.pullupsuspension.utils;

public class FraConstans {

	public static boolean DEBUG = false;

	public static String APP_ROOT_PATH = "MeiBusiness";

	public static String APP_FILE_CACHE_PATH = "fileCache" ;
	/**
	 * 图片缓存的目录
	 */
	public static final String APP_IMAGE_LOADER = "/imageLoader/";

	/**
	 * 记录错误日志到本地
	 */
	public static String APP_ERROR_LOG = "/"+APP_ROOT_PATH+"/log/";

	public static final String SIG_SECRET_KEY = "e#!(HN3gfu!^723)_()rn3";
	// 版本号
	public final static String HTTP_VERSION = "version";

	// 客户端认证编码
	public final static String HTTP_SIGN = "sign";
	// 平台类型（苹果为ios，安卓为android）
	public final static String HTTP_PLAT_TYPE = "platType";
	public final static String PLAT_TYPE = "android";
	// 客户端唯一标识,
	public final static String HTTP_CLIENT_ID = "clientID";
	// 平台类型 （用户平台默认为0，理发师平台默认为1， 理发店平台为2）
	public final static String HTTP_ACCOUNT_TYPE = "accountType";
	public final static String ACCOUNT_TYPE = "2";
	// sessionid
	public final static String SESSIONID = "sessionid";



}
