CREATE TABLE journals
(
    journal_ID   INT NOT NULL,
    journal_name VARCHAR(255),
    publisher    VARCHAR(500),
    PRIMARY KEY (journal_ID)
) ENGINE=InnoDB;

CREATE TABLE conferences
(
    conference_ID   INT NOT NULL,
    conference_name VARCHAR(500),
    PRIMARY KEY (conference_ID)
) ENGINE=InnoDB;

CREATE TABLE authors
(
    author_ID   INT          NOT NULL,
    author_name VARCHAR(255),
    article_ID  INT          NOT NULL,
    title       VARCHAR(255) NOT NULL,
    PRIMARY KEY (author_ID, author_name, article_ID),
    FOREIGN KEY (article_ID)
    REFERENCES articles(article_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

