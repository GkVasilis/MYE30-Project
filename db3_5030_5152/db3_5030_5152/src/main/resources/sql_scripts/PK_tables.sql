CREATE TABLE journals {
    journal_ID INT NOT NULL AUTO_ΙNCREMENT,
    journal_name VARCHAR(100),
    publisher VARCHAR(100),
    PRIMARY KEY (journal_ID)
    };

CREATE TABLE conferences {
    conference_ID INT NOT NULL AUTO_ΙNCREMENT,
    conference_name VARCHAR(100),
    PRIMARY KEY (conference_ID)
    };

CREATE TABLE authors {
    author_ID INT NOT NULL AUTO_ΙNCREMENT,
    author_name VARCHAR(100),
    article_ID INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    PRIMARY KEY (author_ID,author_name),
    FOREIGN KEY (article_ID, title)
    REFERENCES articles(article_ID, title)
    ON DELETE CASCADE ON UPDATE CASCADE
};


