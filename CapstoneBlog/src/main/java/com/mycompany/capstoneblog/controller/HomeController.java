/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneblog.controller;

import com.mycompany.capstoneblog.dao.ArticleDAO;
import com.mycompany.capstoneblog.dto.Article;
import com.mycompany.capstoneblog.dto.Hashtag;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author apprentice
 */
@Controller
public class HomeController {
    private ArticleDAO dao;
    
    @Inject
    public HomeController(ArticleDAO dao) {
        this.dao = dao;
    }

    @RequestMapping(value = {"/staticPages"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getStaticPages() {
        return dao.getStaticPageHeaders();
    }

    @RequestMapping(value = "/removeArticle/{articleID}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeArticle(@PathVariable("articleID") int articleID) {
        dao.removeArticle(articleID);
    }

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home(HttpServletRequest req) {
        return "home";
    }
    
    @RequestMapping(value = {"/employeeView"}, method = RequestMethod.GET)
    public String employeeView(HttpServletRequest req) {
        return "employeeView";
    }
    
    @RequestMapping(value = {"/publicLanding", "/"}, method = RequestMethod.GET)
    public String publicLanding(HttpServletRequest req) {
        return "publicLanding";
    }

    @RequestMapping(value = {"/article"}, method = RequestMethod.POST)
    @ResponseBody
    public String addArticle(@RequestBody Article article) {
        dao.addArticle(article);
        return "home";
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.POST)
    @ResponseBody
    public void putArticle(@PathVariable("id") int id, @RequestBody Article article) {
        article.setArticleID(id);
        dao.editArticle(article);
    }

    @RequestMapping(value = "/article/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Article getArticle(@PathVariable("id") int id) {
        return dao.getArticleByID(id);
    }

    @RequestMapping(value = {"/activeArticles"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getActiveArticles() {
        return dao.getActiveArticles();
    }

    @RequestMapping(value = {"/articlesForReview"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Article> getArticlesForReview() {
        return dao.getArticlesForReview();
    }

    @RequestMapping(value = {"/approve"}, method = RequestMethod.POST)
    @ResponseBody
    public void approveArticle(@RequestBody Article article) {
        dao.changeArticleStatus(1, article.getArticleID());
        //return articlesForReview;
    }
    
    
    @RequestMapping(value = {"/allHashtags"}, method = RequestMethod.GET)
    @ResponseBody
    public ArrayList<String> getAllHashtags() {
        return dao.getAllHashTags();
    }
    
    @RequestMapping(value = "/search/{hashtag}", method = RequestMethod.GET)
    @ResponseBody
    public List<Article> searchArticles(@PathVariable("hashtag") String hashtag) {
        return dao.searchByHashTag(hashtag);
    }
    
    @RequestMapping(value = {"/countHashtags"}, method = RequestMethod.GET)
    @ResponseBody
    public List<Hashtag> countHashtags() {
        List<Hashtag> hashtagList = dao.countHashtags();
        return hashtagList;
    }
}
