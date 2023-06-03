package com.ip.posts.domain;

import java.time.LocalDate;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;

/**
 * Post domain.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer authorId;

    @Column
    private String text;

    @Column
    private LocalDate date;

    @Column String topic;

    @PrePersist
    private void onCreate() {
        date = LocalDate.now();
    }

    public Post(final Integer id, final Integer authorId, final String text, final LocalDate date, final String topic) {
        this.id = id;
        this.authorId = authorId;
        this.text = text;
        this.date = date;
    }

    protected Post() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Integer getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(final Integer authorId) {
        this.authorId = authorId;
    }

    public String getText() {
        return this.text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public void setDate(final LocalDate date) {
        this.date = date;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(final String topic) {
        this.topic = topic;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Post post = (Post)o;

        if (!Objects.equals(id, post.id)) return false;
        if (!Objects.equals(authorId, post.authorId)) return false;
        if (!Objects.equals(text, post.text)) return false;
        if (!Objects.equals(date, post.date)) return false;
        return Objects.equals(topic, post.topic);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (authorId != null ? authorId.hashCode() : 0);
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (topic != null ? topic.hashCode() : 0);
        return result;
    }
}
