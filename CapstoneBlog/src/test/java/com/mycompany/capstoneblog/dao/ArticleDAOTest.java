/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneblog.dao;

import com.mycompany.capstoneblog.dto.Article;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author apprentice
 */
public class ArticleDAOTest {

    private ArticleDAO dao;
    LocalDate today;
    LocalDate testDate;
    ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");

    public ArticleDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        today = LocalDate.now();
        ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = ctx.getBean("ArticleDAO", ArticleDAO.class);
        dao.resetTestData();
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getActiveArticles method, of class ArticleDAO.
     */
    @Test
    public void testGetActiveArticles() {
        System.out.println("getActiveArticles");
        dao.populateTestData();
        ArrayList<Article> test = new ArrayList<>(dao.getActiveArticles());
        Assert.assertEquals(3, test.size());

        boolean isStaticPageChecker = false;
        for (Article x : test) {
            if (x.isStaticPage()) {
                isStaticPageChecker = true;
            }
        }
        Assert.assertFalse(isStaticPageChecker);
        
        boolean postingDateChecker = true;
        for (Article x : test) {
            if (x.getPostingDate().toLocalDate().isAfter(today)) {
                postingDateChecker = false;
            } 
        }
        Assert.assertTrue(postingDateChecker);
       
        boolean takeDownDateChecker = true;
        for (Article x : test) {
            if (x.getTakeDownDate().toLocalDate().isBefore(today) || 
                    x.getPostingDate().toLocalDate().isEqual(today)) {
                postingDateChecker = false;
            }
        }
        Assert.assertTrue(takeDownDateChecker);
    }

    /**
     * Test of getFutureArticles method, of class ArticleDAO.
     */
    @Test
    public void testGetFutureArticlesPlusCRUDArticle() {
        System.out.println("getFutureArticles + CRUD Operations");
        Article a = new Article();;
        a.setActiveStatus(4);
        a.setArticleBody("<p>This is totally a test.</p>");
        a.setPostingDate(Date.valueOf("2016-10-12"));
        a.setArticleHead("Testing Stuff");
        a.setStaticPage(false);
        Article b = new Article();
        b.setActiveStatus(4);
        b.setArticleBody("<p>This is totally a test also.</p>");
        b.setPostingDate(Date.valueOf("2016-10-12"));
        b.setArticleHead("Testing Stuff 2nd Edition");
        b.setStaticPage(false);
        dao.populateTestData();

        dao.addArticle(a);
        dao.addArticle(b);
        ArrayList<Article> test = new ArrayList<>(dao.getFutureArticles());
        Assert.assertEquals(2, test.size());

        boolean postingDateChecker = true;
        for (Article x : test) {
            if (x.getPostingDate().toLocalDate().isBefore(today)) {
                postingDateChecker = false;
            }
        }
        Assert.assertTrue(postingDateChecker);

        a.setArticleBody("<p>Changed the text here.");
        Assert.assertFalse(a.equals(dao.getArticleByID(1)));

    }

    /**
     * Test of getArticlesForReview method, of class ArticleDAO.
     */
    @Test
    public void testGetArticlesForReview() {
        System.out.println("getArticlesForReview");
        Article a = new Article();
        a.setArticleID(1);
        a.setActiveStatus(3);
        a.setArticleBody("<p>This is totally a test.</p>");
        a.setPostingDate(Date.valueOf("2016-10-12"));
        a.setArticleHead("Testing Stuff");
        a.setStaticPage(false);
        Article b = new Article();
        a.setArticleID(2);
        b.setActiveStatus(3);
        b.setArticleBody("<p>This is totally a test also.</p>");
        b.setPostingDate(Date.valueOf("2016-10-12"));
        b.setArticleHead("Testing Stuff 2nd Edition");
        b.setStaticPage(false);
        dao.populateTestData();

        dao.addArticle(a);
        dao.addArticle(b);
        ArrayList<Article> test = new ArrayList<>(dao.getArticlesForReview());
        Assert.assertEquals(2, test.size());
    }

    /**
     * Test of getStaticPageHeaders method, of class ArticleDAO.
     */
    @Test
    public void testGetStaticPageHeaders() {
        System.out.println("getStaticPageHeaders");
        dao.populateTestData();
        ArrayList<Article> headers = new ArrayList<>(dao.getStaticPageHeaders());
        Assert.assertEquals(4, headers.size());
        Assert.assertEquals("JJJJJ", dao.getArticleByID(40).getArticleHead());
    }

    /**
     * Test of changeArticleStatus method, of class ArticleDAO.
     */
    @Test
    public void testChangeArticleStatus() {
        System.out.println("changeArticleStatus");
        dao.populateTestData();
        Assert.assertEquals(1, dao.getArticleByID(40).getActiveStatus());
        dao.changeArticleStatus(5, 40);
        Assert.assertEquals(5, dao.getArticleByID(40).getActiveStatus());
    }

    /**
     * Test of searchByHashTag method, of class ArticleDAO.
     */
    @Test
    public void testSearchByHashTag() {
        System.out.println("searchByHashTag");

    }

    /**
     * Test of grabHashtags method, of class ArticleDAO.
     */
    @Test
    public void testGrabHashtags() {
        System.out.println("grabHashtags");
        String test = dao.grabHashtags("#testTag1, lanoanbaoing kanf; alkdnf klnan@@%%"
                        + "ALKN /KNAIN #testTag2 aklsdnv alk n v89 #testTag3");
        Assert.assertTrue(test.contains("testTag1"));
        Assert.assertTrue(test.contains("testTag2"));
        Assert.assertTrue(test.contains("testTag3"));
    }

    /**
     * Test of getAllHashTags method, of class ArticleDAO.
     */
    @Test
    public void testGetAllHashTags() {
        System.out.println("getAllHashTags");
        dao.populateTestData();
        ArrayList<String> listOfTestTags = dao.getAllHashTags();
        Assert.assertEquals(10, listOfTestTags.size());
    }

}
