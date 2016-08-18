package com.mycompany.capstoneblog.dao;

import com.mycompany.capstoneblog.dto.Article;
import com.mycompany.capstoneblog.dto.Hashtag;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import org.jsoup.Jsoup;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public class ArticleDAODbImpl implements ArticleDAO {

    private JdbcTemplate jdbcTemplate;

    // Statuses are as follows:
    //=========================
    // 1: Active
    // 2: Inactive
    // 3: Pending for review
    // 4: Future (approved but haven't posted yet)
    //Single Article CRUD
    //===================
    private static final String SQL_SELECT_ARTICLE
            = "SELECT * FROM Article WHERE articleID = ?";

    private static final String SQL_INSERT_ARTICLE
            = "INSERT INTO Article (articleHead, articleBody, postingDate, takeDownDate, activeStatus, staticPage, hashtag) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SQL_CHANGE_ARTICLE_STATUS
            = "UPDATE Article SET activeStatus = ? WHERE articleID = ?";

    private static final String SQL_UPDATE_ARTICLE
            = "UPDATE Article SET articleHead = ?, articleBody = ?, postingDate = ?, takeDownDate = ?, activeStatus = ?, staticPage = ?, hashtag = ? WHERE articleID = ?";

    private static final String SQL_DELETE_ARTICLE
            = "DELETE FROM Article WHERE articleID = ?";

    //Lists of Articles
    //===================
    private static final String SQL_SELECT_ACTIVE_ARTICLES
            = "SELECT * FROM Article WHERE postingDate <= now() and takeDownDate > now() AND staticPage = false AND activeStatus !=3 AND activeStatus !=4 ORDER BY postingDate DESC, articleHead";

    private static final String SQL_SELECT_ARTICLES_FOR_REVIEW
            = "SELECT * FROM Article WHERE activeStatus = 3 ORDER BY postingDate DESC, articleHead";

    private static final String SQL_SELECT_ARTICLES_PENDING_PUBLISH
            = "SELECT * FROM Article WHERE activeStatus = 4 ORDER BY postingDate DESC, articleHead";

    private static final String SQL_SELECT_STATIC_PAGE_HEADERS
            = "SELECT articleID, articleHead FROM Article WHERE staticPage = true AND activeStatus = 1 ORDER BY postingDate DESC, articleHead";

    //Hashtags
    //=========
    //Double check query
    private static final String SQL_SELECT_ALL_HASHTAGS
            = "SELECT hashtag FROM Article";

    //Double check query
    private static final String SQL_SELECT_BY_HASHTAG
            = "SELECT * FROM Article  WHERE hashtag like ?";

    //Test 
    //=====
    private static final String SQL_POPULATE_TEST_ARTICLES = "INSERT INTO `Article` (`articleID`, `articleHead`, `articleBody`, `postingDate`, `takeDownDate`, `activeStatus`, `staticPage`, `hashtag`) VALUES\n"
            + "(35, 'DON\\'T DIE IN A TRENCH', 'jahgkjdsfghkjafadhsgkjsdfahgkjahfkgjhsdfkjhsdkjf', '2016-07-31', '2016-09-21', 1, 0, 'trenchlyfe trenches safety sexy'),\n"
            + "(37, 'COMMON TRENCH MISTAKES', '<p>gfdhfh</p>\\n', '2016-08-08', '2016-08-31', 1, 0, 'trenchlyfe neverforget'),\n"
            + "(39, 'TRENCHESSSSSS', '<p>ddasfdsfd</p>\\n', '2016-08-02', '2016-08-31', 1, 0, 'mmmtrenches safetyfirst'),\n"
            + "(40, 'JJJJJ', '<p>jkh</p>\\n', '2016-08-09', '2016-08-09', 1, 1, 'neverforget trenches sexy'),\n"
            + "(42, 'TRENCHES CAN BE DANGEROUS!', '<p>zLorem ipsum dolor sit amet, sagittis ut nulla ut euismod, donec quis rhoncus non pulvinar, eros nisl ipsum libero porta commodo. Arcu egestas bibendum consectetuer mauris, voluptas mauris sollicitudin aliquet pellentesque cursus aliquam. Orci justo taciti amet phasellus duis non. Eget erat tellus est, nec in, nulla lacus dictum cras sit, fusce in, lectus mi pede euismodcxvcxvx cx. Sit vel quvisque cursuxc z. Nec ac ultrices, nibh dui lectus. Ultricies nibh pede ipsum labore consectetuer, penatibus ac sed dolor mauris malesuada, penatibus consectetuer lobortis, id aenean nascetur dictum maecenas pede. Vestibulum dictumst arcu interdum vel nulla morbi. Elit parturient ac sodales magna, ipsum neque, ac augue orci leo lacus nulla ornare, nam sed erat, dolores odio eget ultricies nunc duis. Neque etiam id interdum sit, ultricies duis et suspendisse. Amet praesent ipsum, morbi urna dapibus amet, luctus blandit torquent, facilisis pellentesque id enim. Suscipit vitae suscipit, donec est turpis sapien, risus nunc. Morbi in volutpat, vestibulum eu mauris erat, tristique at aenean non, nam pulvinar fusce non, mauris felis neque molestie sed sed praesent.</p>\\n', '2016-08-09', '2016-09-29', 1, 1, 'thetrench ts'),\n"
            + "(43, 'TRENCHES = DEATH', '<p>loadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;adloadadsjlk dsfi jddspoaf ldsj &nbsp;d adslj ds da&#39;sf asdlifjfd &nbsp;ad</p>\\n', '2016-08-09', '2016-08-31', 1, 1, 'ts trenchlyfe'),\n"
            + "(44, 'STATIC PAGE', '<p>egestas bibendum consectetuer mauris, voluptas mauris sollicitudin aliquet pellentesque cursus aliquam. Orci justo taciti amet phasellus duis non. Eget erat tellus est, nec in, nulla lacus dictum cras sit, fusce in, lectus mi pede euismod. Sit vel quisque cursus. Nec ac ultrices, nibh dui lectus. Ultricies nibh pede ipsum labore consectetuer, penatibus ac sed dolor mauris malesuada, penatibus consectetuer lobortis, id aenean nascetur dictum maecenas pede. Vestibulum dictumst arcu interdum vel nulla morbi. Elit parturient ac sodales magna, ipsum neque, ac augue orci leo lacus nulla ornare, nam sed erat, dolores odio eget ultricies nunc duis. Neque etiam id interdum sit, ultricies duis et suspendisse. Amet praesent ipsum, morbi urna dapibus amet, luctus blandit torquent, facilisis pellentesque id enim. Suscipit vitae suscipit, donec est turpis sapien, risus nunc. Morbi in volutpat, vestibulum eu mauris erat, tristique at aenean non, nam pulvinar fusc</p>\\n', '2016-08-10', '2016-08-10', 1, 1, 'safetyfirst'),\n"
            + "(47, 'dafdsdf', '<p>dsafdasfd</p>\\n', '2016-08-10', '2016-08-10', 1, 0, 'safety mmmtrenches');";

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Single Article CRUD
    //======================
    @Override
    public Article getArticleByID(int articleID) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ARTICLE,
                    new ArticleMapper(), articleID);
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public Article addArticle(Article article) {

        if (article.getActiveStatus() != 1 && article.getActiveStatus() != 4) {

            article.setActiveStatus(3);
        }
        jdbcTemplate.update(SQL_INSERT_ARTICLE,
                article.getArticleHead(),
                article.getArticleBody(),
                article.getPostingDate(),
                article.getTakeDownDate(),
                article.getActiveStatus(),
                article.isStaticPage(),
                grabHashtags(article.getArticleBody()));

        article.setArticleID(jdbcTemplate.queryForObject("select LAST_INSERT_ID()",
                Integer.class));
        return new Article();
    }

    @Override
    public void editArticle(Article article) {
        jdbcTemplate.update(SQL_UPDATE_ARTICLE, article.getArticleHead(),
                article.getArticleBody(), article.getPostingDate(),
                article.getTakeDownDate(), article.getActiveStatus(),
                article.isStaticPage(), grabHashtags(article.getArticleBody()),
                article.getArticleID());
    }

    @Override
    public void removeArticle(int articleID) {
        jdbcTemplate.update(SQL_DELETE_ARTICLE, articleID);
    }

    @Override
    public void changeArticleStatus(int status, int articleID) {
        jdbcTemplate.update(SQL_CHANGE_ARTICLE_STATUS, status, articleID);
    }

    //Get Lists
    //==========
    @Override
    public List<Article> getActiveArticles() {
        return jdbcTemplate.query(SQL_SELECT_ACTIVE_ARTICLES, new ArticleMapper());
    }

    @Override
    public List<Article> getFutureArticles() {
        return jdbcTemplate.query(SQL_SELECT_ARTICLES_PENDING_PUBLISH, new ArticleMapper());
    }

    @Override
    public List<Article> getArticlesForReview() {
        return jdbcTemplate.query(SQL_SELECT_ARTICLES_FOR_REVIEW, new ArticleMapper());
    }

    @Override
    public List<Article> getStaticPageHeaders() {
        return jdbcTemplate.query(SQL_SELECT_STATIC_PAGE_HEADERS, new NavMapper());
    }

    //Hashtags
    //=========
    @Override
    public String grabHashtags(String articleBody) {
        String noHTMLTags = Jsoup.parse(articleBody).text();
        noHTMLTags = noHTMLTags.replaceAll("[^#A-Za-z0-9]", " ");
        String[] words = noHTMLTags.split(" ");

        HashSet<String> hashtags = new HashSet<>();
        String tags = "";

        for (String word : words) {
            if (word.startsWith("#")) {
                hashtags.add(word.substring(1));
            }
        }
        for (String w : hashtags) {
            tags += (" " + w);
        }
        return tags;
    }

    @Override
    public List<Article> searchByHashTag(String hashtag) {
        return jdbcTemplate.query(SQL_SELECT_BY_HASHTAG, new ArticleMapper(), "%" + hashtag + "%");
    }

    @Override
    public ArrayList<String> getAllHashTags() {
        ArrayList<String> tagsFromDB = new ArrayList<>(jdbcTemplate.query(SQL_SELECT_ALL_HASHTAGS, new StringMapper("hashtag")));
        StringBuilder allTags = new StringBuilder();

        tagsFromDB.forEach(tag -> allTags.append(" ").append(tag));
        String allTagsTogether = allTags.toString();
        String[] words = allTagsTogether.split(" ");

        HashSet<String> hashtags = new HashSet<>();
        for (String word : words) {
            hashtags.add(word);
        }
        return new ArrayList<>(hashtags);
    }

    @Override
    public ArrayList<Hashtag> countHashtags() {
        ArrayList<String> tagsFromDB = new ArrayList<>(jdbcTemplate.query(SQL_SELECT_ALL_HASHTAGS, new StringMapper("hashtag")));
        StringBuilder allTags = new StringBuilder();

        tagsFromDB.forEach(tag -> allTags.append(" ").append(tag));
        String allTagsTogether = allTags.toString();
        String[] words = allTagsTogether.split(" ");

        ArrayList<String> everyInstance = new ArrayList<>();
        for (String word : words) {
            everyInstance.add(word);
        }
        ArrayList<String> hashtags = getAllHashTags();

        HashMap<String, Hashtag> hashtagMap = new HashMap<>();
        for (String hashtag : hashtags) {
            if(hashtag.equals(" ")==false && hashtag.equals("null")==false && hashtag.equals("") == false){
            Hashtag h = new Hashtag();
            h.setCount(0);
            h.setName(hashtag);
            hashtagMap.put(hashtag, h);
            }}
        double oldCount;
        for (String instance : everyInstance) {
            if(instance.equals(" ")==false && instance.equals("null")==false && instance.equals("") == false){
            oldCount = hashtagMap.get(instance).getCount();
            hashtagMap.get(instance).setCount(oldCount + 1);
            }}

        
        OptionalDouble maxCount = hashtagMap.values().stream()             
                .mapToDouble(h -> h.getCount())
                .max();

        double minSize = 1;
        double deltaSize = 5;
        double percentOfMaxCount;
        
        for (Hashtag t : hashtagMap.values()) {
            percentOfMaxCount = t.getCount() / maxCount.getAsDouble();
            t.setSize((deltaSize * percentOfMaxCount) + minSize);
        }
        ArrayList<Hashtag> test = new ArrayList<>(hashtagMap.values());
        return test;
    }

    //Test Methods
    //============
    @Override
    public void populateTestData() {
        jdbcTemplate.update(SQL_POPULATE_TEST_ARTICLES);
    }

    @Override
    public void resetTestData() {
        jdbcTemplate.update("DELETE FROM Article");
    }

    private static final class ArticleMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet rs, int rowNum) throws SQLException {
            Article article = new Article();
            article.setArticleBody(rs.getString("articleBody"));
            article.setActiveStatus(rs.getInt("activeStatus"));
            article.setArticleID(rs.getInt("articleID"));
            article.setArticleHead(rs.getString("articleHead"));
            article.setPostingDate(rs.getDate("postingDate"));
            article.setTakeDownDate(rs.getDate("takeDownDate"));
            article.setHashtags(rs.getString("hashtag"));
            return article;
        }
    }

    private static final class NavMapper implements RowMapper<Article> {

        @Override
        public Article mapRow(ResultSet rs, int i) throws SQLException {
            Article article = new Article();
            article.setArticleID(rs.getInt("articleID"));
            article.setArticleHead(rs.getString("articleHead"));
            return article;
        }
    }

    private static final class StringMapper implements RowMapper<String> {

        private String column;

        public StringMapper(String column) {
            this.column = column;
        }

        @Override
        public String mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getString(column);
        }
    }
}
