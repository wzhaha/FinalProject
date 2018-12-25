package com.food.test.finalproject.utils;

//import com.yiw.circledemo.bean.CircleItem;
//import com.yiw.circledemo.bean.CommentItem;
//import com.yiw.circledemo.bean.FavortItem;
//import com.yiw.circledemo.bean.PhotoInfo;
//import com.yiw.circledemo.bean.User;

import com.food.test.finalproject.bean.CircleItem;
import com.food.test.finalproject.bean.CommentItem;
import com.food.test.finalproject.bean.FavortItem;
import com.food.test.finalproject.bean.PhotoInfo;
import com.food.test.finalproject.bean.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 
* @ClassName: DatasUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author yiw
* @date 2015-12-28 下午4:16:21 
*
 */
public class DatasUtil {
	public static final String[] CONTENTS = {
			"刘老板，联系方式：18810959899,公司官网： http://mis.bjtu.edu.cn.",
			"改革开放是我们党的一次伟大觉醒，正是这个伟大觉醒孕育了我们党从理论到实践的伟大创造。改革开放是中国人民和中华民族发展史上一次伟大革命，正是这个伟大革命推动了中国特色社会主义事业的伟大飞跃！",
			"建立中国共产党、成立中华人民共和国、推进改革开放和中国特色社会主义事业，是五四运动以来我国发生的三大历史性事件，是近代以来实现中华民族伟大复兴的三大里程碑。",
			"40年的实践充分证明，改革开放是党和人民大踏步赶上时代的重要法宝，是坚持和发展中国特色社会主义的必由之路，是决定当代中国命运的关键一招，也是决定实现“两个一百年”奋斗目标、实现中华民族伟大复兴的关键一招。" };
	public static final String[] HEADIMG = {
			"https://img2.woyaogexing.com/2018/12/22/9607fc6633c54399a58ad17310efdfb0!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/27c0d1b8697b4cc78c609d97a05e34aa!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/3a5b4f8e35f44081b93d23129615d5e9!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/35fe509b537a424b8ae3c920823b0c0f!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/44e3cfc3f18840269d6fc0e448df9541!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/5c79ae811cc1431594a4090f20b0a054!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/aabc2fc9fe7845bfb1b0595df9d79969!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/150f76d964154f83a492e9aee2dc1e01!400x400.jpeg",
            "https://img2.woyaogexing.com/2018/12/22/71a6c12ee09949c6b3a06ac96c0ccce6!400x400.jpeg",
			"https://img2.woyaogexing.com/2018/12/22/34723dfa041f46a1a80618a92dde0c38!400x400.jpeg",
			"https://img2.woyaogexing.com/2018/12/22/772781a6ff534110aa3b33f697cc21a1!400x400.jpeg",
			"https://img2.woyaogexing.com/2018/12/22/d7d443a853b742968ff13cb542c61ffb!400x400.jpeg"};

	public static List<User> users = new ArrayList<User>();
	public static List<PhotoInfo> PHOTOS = new ArrayList<>();
	/**
	 * 动态id自增长
	 */
	private static int circleId = 0;
	/**
	 * 点赞id自增长
	 */
	private static int favortId = 0;
	/**
	 * 评论id自增长
	 */
	private static int commentId = 0;
	public static final User curUser = new User("0", "自己", HEADIMG[0]);
	static {
		User user1 = new User("1", "张三", HEADIMG[1]);
		User user2 = new User("2", "李四", HEADIMG[2]);
		User user3 = new User("3", "隔壁老王", HEADIMG[3]);
		User user4 = new User("4", "赵六", HEADIMG[4]);
		User user5 = new User("5", "田七", HEADIMG[5]);
		User user6 = new User("6", "Naoki", HEADIMG[6]);
		User user7 = new User("7", "这个名字是不是很长，哈哈！因为我是用来测试换行的", HEADIMG[7]);

		users.add(curUser);
		users.add(user1);
		users.add(user2);
		users.add(user3);
		users.add(user4);
		users.add(user5);
		users.add(user6);
		users.add(user7);

		PhotoInfo p1 = new PhotoInfo();
		p1.url = "https://s1.st.meishij.net/r/115/102/588115/s588115_154555440147327.jpg";
		p1.w = 328;
		p1.h = 440;

		PhotoInfo p2 = new PhotoInfo();
		p2.url = "https://s1.st.meishij.net/r/105/225/1306355/s1306355_154375123420868.jpg";
		p2.w = 328;
		p2.h = 440;

		PhotoInfo p3 = new PhotoInfo();
		p3.url = "https://s1.st.meishij.net/r/147/198/4174647/s4174647_154098464345073.jpg";
		p3.w = 328;
		p3.h = 440;

		PhotoInfo p4 = new PhotoInfo();
		p4.url = "https://s1.st.meishij.net/r/19/99/9087269/s9087269_154561859991657.jpg";
		p4.w = 328;
		p4.h = 440;

		PhotoInfo p5 = new PhotoInfo();
		p5.url = "https://s1.st.meishij.net/r/216/197/6174466/s6174466_154350283513462.jpg";
		p5.w = 328;
		p5.h = 440;

		PhotoInfo p6 = new PhotoInfo();
		p6.url = "https://s1.st.meishij.net/r/44/110/4152544/s4152544_154520112273289.jpg";
		p6.w = 328;
		p6.h = 440;

		PhotoInfo p7 = new PhotoInfo();
		p7.url = "https://s1.st.meishij.net/r/13/90/4835013/s4835013_154099486252608.jpg";
		p7.w = 328;
		p7.h = 440;

		PhotoInfo p8 = new PhotoInfo();
		p8.url = "https://s1.st.meishij.net/r/231/13/6878481/s6878481_154563345590522.jpg";
		p8.w = 328;
		p8.h = 440;

		PhotoInfo p9 = new PhotoInfo();
		p9.url = "http://site.meishij.net/r/124/42/3885624/s3885624_150180911918252.jpg";
		p9.w = 328;
		p9.h = 440;

		PhotoInfo p10 = new PhotoInfo();
		p10.url = "http://site.meishij.net/r/02/83/5458252/s5458252_150184485716388.jpg";
		p10.w = 328;
		p10.h = 440;

		PHOTOS.add(p1);
		PHOTOS.add(p2);
		PHOTOS.add(p3);
		PHOTOS.add(p4);
		PHOTOS.add(p5);
		PHOTOS.add(p6);
		PHOTOS.add(p7);
		PHOTOS.add(p8);
		PHOTOS.add(p9);
		PHOTOS.add(p10);
	}

	public static List<CircleItem> createCircleDatas() {
		List<CircleItem> circleDatas = new ArrayList<CircleItem>();
		for (int i = 0; i < 15; i++) {
			CircleItem item = new CircleItem();
			User user = getUser();
			item.setId(String.valueOf(circleId++));
			item.setUser(user);
			item.setContent(getContent());
			item.setCreateTime("12月24日");

			item.setFavorters(createFavortItemList());
			item.setComments(createCommentItemList());
			int type = getRandomNum(10) % 2;
			if (type == 0) {
				item.setType("1");// 链接
				item.setLinkImg("https://s1.st.meishij.net/r/115/102/588115/s588115_154555440147327.jpg");
				item.setLinkTitle("美食分享汇");
			} else if(type == 1){
				item.setType("2");// 图片
				item.setPhotos(createPhotos());
			}else {
				item.setType("3");// 视频
				String videoUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.mp4";
                String videoImgUrl = "http://yiwcicledemo.s.qupai.me/v/80c81c19-7c02-4dee-baca-c97d9bbd6607.jpg";
				item.setVideoUrl(videoUrl);
                item.setVideoImgUrl(videoImgUrl);
			}
			circleDatas.add(item);
		}

		return circleDatas;
	}

	public static User getUser() {
		return users.get(getRandomNum(users.size()));
	}

	public static String getContent() {
		return CONTENTS[getRandomNum(CONTENTS.length)];
	}

	public static int getRandomNum(int max) {
		Random random = new Random();
		int result = random.nextInt(max);
		return result;
	}

	public static List<PhotoInfo> createPhotos() {
		List<PhotoInfo> photos = new ArrayList<PhotoInfo>();
		int size = getRandomNum(PHOTOS.size());
		if (size > 0) {
			if (size > 9) {
				size = 9;
			}
			for (int i = 0; i < size; i++) {
				PhotoInfo photo = PHOTOS.get(getRandomNum(PHOTOS.size()));
				if (!photos.contains(photo)) {
					photos.add(photo);
				} else {
					i--;
				}
			}
		}
		return photos;
	}

	public static List<FavortItem> createFavortItemList() {
		int size = getRandomNum(users.size());
		List<FavortItem> items = new ArrayList<FavortItem>();
		List<String> history = new ArrayList<String>();
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				FavortItem newItem = createFavortItem();
				String userid = newItem.getUser().getId();
				if (!history.contains(userid)) {
					items.add(newItem);
					history.add(userid);
				} else {
					i--;
				}
			}
		}
		return items;
	}

	public static FavortItem createFavortItem() {
		FavortItem item = new FavortItem();
		item.setId(String.valueOf(favortId++));
		item.setUser(getUser());
		return item;
	}
	
	public static FavortItem createCurUserFavortItem() {
		FavortItem item = new FavortItem();
		item.setId(String.valueOf(favortId++));
		item.setUser(curUser);
		return item;
	}

	public static List<CommentItem> createCommentItemList() {
		List<CommentItem> items = new ArrayList<CommentItem>();
		int size = getRandomNum(10);
		if (size > 0) {
			for (int i = 0; i < size; i++) {
				items.add(createComment());
			}
		}
		return items;
	}

	public static CommentItem createComment() {
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent("哈哈");
		User user = getUser();
		item.setUser(user);
		if (getRandomNum(10) % 2 == 0) {
			while (true) {
				User replyUser = getUser();
				if (!user.getId().equals(replyUser.getId())) {
					item.setToReplyUser(replyUser);
					break;
				}
			}
		}
		return item;
	}
	
	/**
	 * 创建发布评论
	 * @return
	 */
	public static CommentItem createPublicComment(String content){
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		return item;
	}
	
	/**
	 * 创建回复评论
	 * @return
	 */
	public static CommentItem createReplyComment(User replyUser, String content){
		CommentItem item = new CommentItem();
		item.setId(String.valueOf(commentId++));
		item.setContent(content);
		item.setUser(curUser);
		item.setToReplyUser(replyUser);
		return item;
	}
	
	
	public static CircleItem createVideoItem(String videoUrl, String imgUrl){
		CircleItem item = new CircleItem();
		item.setId(String.valueOf(circleId++));
		item.setUser(curUser);
		//item.setContent(getContent());
		item.setCreateTime("12月24日");

		//item.setFavorters(createFavortItemList());
		//item.setComments(createCommentItemList());
        item.setType("3");// 图片
        item.setVideoUrl(videoUrl);
        item.setVideoImgUrl(imgUrl);
		return item;
	}
}
