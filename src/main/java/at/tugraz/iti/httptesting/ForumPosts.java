package at.tugraz.iti.httptesting;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.json.JSONObject;

public class ForumPosts {

	public ForumPosts() {
		SimpleForum.allPosts = new ArrayList<ForumPost>();

		DateTime dateTime = new DateTime(System.currentTimeMillis());

		SimpleForum.allPosts.add(new ForumPost("User 1", "Message 1", dateTime));
		SimpleForum.allPosts.add(new ForumPost("User 2", "Message 2", dateTime));
		SimpleForum.allPosts.add(new ForumPost("User 3", "Message 3", dateTime));
		SimpleForum.allPosts.add(new ForumPost("User 1", "Message 4", dateTime));
		SimpleForum.allPosts.add(new ForumPost("User 2", "Message 5", dateTime));
		SimpleForum.allPosts.add(new ForumPost("User 3", "Message 6", dateTime));
		SimpleForum.allPosts.add(new ForumPost("simon", "hello world", dateTime));
	}
	
	public int getNumReceivedPosts() {
		return SimpleForum.allPosts.size();
	}

	public List<ForumPost> getAllPosts() {
		return SimpleForum.allPosts;
	}

	public List<ForumPost> getNewestPosts() {
		List<ForumPost> newestPosts = new ArrayList<ForumPost>();
		for (int i = 0; i < 5; i++) newestPosts.add(SimpleForum.allPosts.get(SimpleForum.allPosts.size() - i - 1));
		return newestPosts;
	}

	public void createNewPost(ForumPost newPost) {
		SimpleForum.allPosts.add(newPost);
	}

	public void createNewPost(JSONObject postsObject) throws ParseException {
		ForumPost newPost = ForumPost.unMarshall(postsObject);
		SimpleForum.allPosts.add(newPost);
	}

}
