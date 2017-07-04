package com.zhzx.uip.commons.utils;

import java.util.ResourceBundle;

public class Constant {

	private static ResourceBundle res = ResourceBundle.getBundle("com/fds/resource/Appkey");
	//网站名称
	public static String SSI_WEBSITE_NAME = res.getString("ssi.website.name");
	//网站域名
	public static String SSI_WEBSITE_DOMAIN = res.getString("ssi.website.domain");
	//网站地址
	public static String SSI_WEBSITE_URL = res.getString("ssi.website.url");
	
	//主站根目录
	public static String BASE_URL = res.getString("ssi.main.base.url");
	
	//图片上传文件夹路径
	public static String SSI_IMAGE_UPLOAD_PATH= res.getString("ssi.image.upload.path");
	
	//图片服务器的URL
	public static String SSI_IMAGE_SERVICE_URL= res.getString("ssi.image.service.url");
	
	//模板文件根目录
	public static String WORK_TEMPLATE_PATH= res.getString("work.template.path");
	
	//线上文件生成根目录
	public static String WORK_WWW_PATH= res.getString("work.wwww.path");
	//预览文件生成根目录
	public static String WORK_PREVIEW_PATH= res.getString("work.preview.path");

    //ID总长度为：日期（8）+分隔符+随机码
    //生成的ID随机码长度
    public static Integer ID_LENGTH = 4;
    //分隔符
    public static String ID_SPLIT = "_";

	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>图片上传根目录文件夹<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 23, 2011 <br>
	 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
	 */
	public static enum PicBaseDir  {
		PHOTO(res.getString("ssi.image.photo.dir")),//普通相册目录
		ICON(res.getString("ssi.image.icon.dir")),//头像相册目录
		WBPIC(res.getString("ssi.image.wbpic.dir")),//微博相册目录 
		VEDIO(res.getString("ssi.image.vedio.dir"));//微博相册目录 
		String key;
		PicBaseDir(String key) {
			this.key = key;
		}
		public String getValue() {
			return (key);
		}
	}
	

	/**
	 * 
	 * <br>
	 * <b>功能：</b>系统相册类型<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 23, 2011 <br>
	 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
	 */
	public static enum AlbumSysFlag {
		NOR_PHOTO(0),//普通相册
		SYS_ICON(1),//系统相册 头像
		SYS_WBPIC(2),//系统相册 微博
		SYS_VOIDE(3);//系统视频图片
		Integer key;
		AlbumSysFlag(Integer key) {
			this.key = key;
		}
		public Integer getValue() {
			return (key);
		}
	}
	
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>系统相册名称<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 23, 2011 <br>
	 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
	 */
	public static enum AlbumSysName{
		SYS_ICON("用户头像"),//系统相册 头像
		SYS_WBPIC("微博贴图");//系统相册 微博贴图
		String key;
		AlbumSysName(String key) {
			this.key = key;
		}
		public String getValue() {
			return (key);
		}
	}
	
	/**
	 * 
	 * <br>
	 * <b>功能：</b>  0=整站可见|1=全好友可见|2=指定好友可见|3=仅自己可见|4=凭密码可见<br>
	 * <b>作者：</b>罗泽军<br>
	 * <b>日期：</b> Dec 23, 2011 <br>
	 * <b>版权所有：<b>版权所有(C) 2011，WWW.VOWO.COM<br>
	 */
	public static enum AlbumVisible {
		ALL(0),//0=整站可见
		Firend(1),//1=全好友可见
		Specified(2),//2=指定好友可见
		Privated(3),//3=仅自己可见
		PassWord(4);//4=凭密码可见
		Integer key;
		AlbumVisible(Integer key) {
			this.key = key;
		}
		public Integer getValue() {
			return (key);
		}
	}
	
//	private Integer type;//   0=微薄评论|1=图片评论|2=日志评论|3=分享评论|4=留言
	
	public static enum CommentType {
		WB(0),//0=微薄评论
		PIC(1),//1=图片评论
		BLOG(2),//2=日志评论
		SHARE(3),//3=分享评论
		MSG(4);//4=留言
		Integer key;
		CommentType(Integer key) {
			this.key = key;
		}
		public Integer getValue() {
			return (key);
		}
	}
}