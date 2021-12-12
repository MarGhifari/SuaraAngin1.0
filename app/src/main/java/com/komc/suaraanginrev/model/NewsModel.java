package com.komc.suaraanginrev.model;

public class NewsModel {
    String author,
            title,
            description,
            newsUrl,
            urlToImage,
            publishedAt,
            content,
            source;

    public NewsModel(String author, String title, String description, String newsUrl, String urlToImage, String publishedAt, String content, String source) {
        this.author = author;
        this.title = title;
        this.description = description;
        this.newsUrl = newsUrl;
        this.urlToImage = urlToImage;
        this.publishedAt = publishedAt;
        this.content = content;
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public String getUrlToImage() {
        return urlToImage;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public String getContent() {
        return content;
    }

    public String getSource(){
        return source;
    }
}
