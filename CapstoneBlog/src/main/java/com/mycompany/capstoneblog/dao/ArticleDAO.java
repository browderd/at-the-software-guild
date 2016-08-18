/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.capstoneblog.dao;

import com.mycompany.capstoneblog.dto.Article;
import com.mycompany.capstoneblog.dto.Hashtag;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author apprentice
 */
public interface ArticleDAO {

    // Single Article CRUD
    //======================
    public Article getArticleByID(int articleID);

    public void changeArticleStatus(int status, int articleID);

    public void removeArticle(int articleID);

    public void editArticle(Article article);

    public Article addArticle(Article article);
    
    //Get Lists
    //==========
    public List<Article> getActiveArticles();

    public List<Article> getFutureArticles();

    public List<Article> getArticlesForReview();

    public List<Article> getStaticPageHeaders();
    
    //Hashtags
    //=========
    public String grabHashtags(String articleBody);

    public ArrayList<String> getAllHashTags();
    
    public List<Article> searchByHashTag(String hashtag);
    
    public ArrayList<Hashtag> countHashtags();
    
    //Test Methods
    //============
    public void populateTestData ();
    
    public void resetTestData();

}


