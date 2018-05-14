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

public class ForumPostTests {

	String dateFormat = "yyyy-MM-dd hh:mm:ss";
	
	@Test
	public void forumPosts_marshallPost_givenPost() {
		
		// Arrange
		DateTime now = new DateTime(DateTime.now());
		ForumPost aForumPost = new ForumPost("testAuthor", "testMessage", new DateTime(DateTime.now()));
		
		// Act
		JSONObject forumPostAsJson = ForumPost.marshall(aForumPost);
		
		// Assert
		assertEquals("testAuthor", forumPostAsJson.get("author"));
		assertEquals("testMessage", forumPostAsJson.get("message"));
		assertEquals(now.toString(dateFormat), forumPostAsJson.get("pubdate"));
		
	}
	
	@Test
	public void forumPosts_unmarshallPost_givenPostAsJson() throws ParseException {
		
		// Arrange
		JSONObject postAsJson = new JSONObject();
		postAsJson.put("author", "testAuthor");
		postAsJson.put("message", "testMessage");
		postAsJson.put("pubdate", "2018-05-14 09:52:35");
		
		// Act
		ForumPost forumPost = ForumPost.unMarshall(postAsJson);
				
		// Assert
		assertEquals("testAuthor", forumPost.getAuthor());
		assertEquals("testMessage", forumPost.getMessage());
		assertEquals("2018-05-14 09:52:35", forumPost.getPubDate().toString("yyyy-MM-dd hh:mm:ss"));
	}

}
