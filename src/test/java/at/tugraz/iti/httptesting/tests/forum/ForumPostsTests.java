package at.tugraz.iti.httptesting.tests.forum;

import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import javax.ws.rs.WebApplicationException;

import org.joda.time.DateTime;
import org.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import at.tugraz.iti.httptesting.ForumPost;
import at.tugraz.iti.httptesting.ForumPosts;

import static org.junit.Assert.*;

public class ForumPostsTests {

	@BeforeClass public static void setupBeforeAllTests() {
		// Example
	}

	@AfterClass public static void teardownAfterAllTests() {
		// Example		
	}

	@Before public void setupBeforeEachTest() {
		// Example		
	}

	@After public void teardownAfterEachTest() {
		// Example		
	}

	@Test
	public void forumPosts_initializedWithCorrectPostNumber() {

		// Arrange (empty)

		// Act
		ForumPosts forumPosts = new ForumPosts();

		// Assert
		assertEquals(7, forumPosts.getNumReceivedPosts());
	}

	@Test
	public void forumPosts_getNewestPosts() {

		// Arrange (empty)
		ForumPosts forumPosts = new ForumPosts();

		// Act
		List<ForumPost> newestPosts = forumPosts.getNewestPosts();

		// Assert
		assertEquals(5, newestPosts.size());
	}

	@Test
	public void forumPosts_newPost_postCountIncreased() {
		// Arrange
		ForumPosts forumPosts = new ForumPosts();
		int numPosts = forumPosts.getNumReceivedPosts();

		// Act
		forumPosts.createNewPost(new ForumPost("test", "test", new DateTime(DateTime.now())));
		int numNewPosts = forumPosts.getNumReceivedPosts();

		// Assert
		assertEquals(numPosts + 1, numNewPosts);
	}


	@Test
	public void forumPosts_createNewPost_GivenJsonObject() throws ParseException {
		// Arrange
		JSONObject postAsJson = new JSONObject();
		postAsJson.put("author", "User");
		postAsJson.put("message", "Msg");
		postAsJson.put("pubdate", "2018-05-14 09:52:35");

		ForumPosts forumPosts = new ForumPosts();

		// Act
		forumPosts.createNewPost(postAsJson);
		ForumPost newPost = forumPosts.getAllPosts().get(forumPosts.getNumReceivedPosts() - 1);

		// Assert
		assertEquals("User", newPost.getAuthor());
		assertEquals("Msg", newPost.getMessage());
		assertEquals("2018-05-14 09:52:35", newPost.getPubDate().toString("yyyy-MM-dd hh:mm:ss"));
	}

}
