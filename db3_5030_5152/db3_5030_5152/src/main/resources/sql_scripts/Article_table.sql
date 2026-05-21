CREATE TABLE articles
(
    article_ID     INT          NOT NULL,
    title          VARCHAR(100) NOT NULL,
    /*article_journal_id INT,
    article_conference_id INT,*/
    published_year INT          NOT NULL,
    PRIMARY KEY (article_ID)
) ENGINE=InnoDB;