CREATE TABLE journal_articles
(
    article_ID     INT NOT NULL,
    title          VARCHAR(500),
    journal_ID     INT NOT NULL,
    journal_name   VARCHAR(500),
    publisher      VARCHAR(100),
    cdrom          VARCHAR(100),
    crossref       VARCHAR(100),
    mdate          VARCHAR(100),
    published_year INT NOT NULL,
    url            VARCHAR(100),
    pages          VARCHAR(100),
    publtype       VARCHAR(100),
    journal_key    VARCHAR(100),
    PRIMARY KEY (journal_ID),
    FOREIGN KEY (journal_ID) REFERENCES journals (journal_ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (article_ID) REFERENCES articles (article_ID)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE conference_articles
(
    article_ID      INT NOT NULL,
    conference_ID   INT NOT NULL,
    conference_name VARCHAR(500),
    title           VARCHAR(500),
    cdrom           VARCHAR(100),
    crossref        VARCHAR(100),
    publtype        VARCHAR(100),
    url             VARCHAR(100),
    pages           VARCHAR(100),
    mdate           VARCHAR(100),
    published_year  INT NOT NULL,
    conference_key  VARCHAR(100),
    PRIMARY KEY (conference_ID),
    FOREIGN KEY (article_ID) REFERENCES articles(article_ID)
        ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (conference_ID) REFERENCES conferences (conference_ID)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE journal_rankings (
    journal_ID INT NOT NULL,
    j_rank INT NOT NULL,
    title VARCHAR(500) NOT NULL,
    bestSubjectArea VARCHAR(100),
    bestSubjectRank VARCHAR(100),
    country VARCHAR(100),
    bestCategories VARCHAR(100),
    journal_language VARCHAR(100),
    PRIMARY KEY (journal_ID, j_rank, title),
    FOREIGN KEY (journal_ID) REFERENCES journals(journal_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;

CREATE TABLE conference_rankings
(
    conference_ID INT NOT NULL,
    conf_rank_ID  INT NOT NULL,
    title         VARCHAR(100),
    c_rank        VARCHAR(100),
    primaryFoR    VARCHAR(100),
    PRIMARY KEY (conf_rank_ID),
    FOREIGN KEY (conference_ID) REFERENCES conferences (conference_ID)
        ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB;


CREATE TABLE conference_categories
(
    /*conference_ID INT NOT NULL ,*/
    primaryFoR VARCHAR(100),
    title      VARCHAR(100),
    PRIMARY KEY (primaryFoR, title)
/*,
    FOREIGN KEY (primaryFoR) REFERENCES conference_rankings (primaryFoR)
        ON DELETE CASCADE ON UPDATE CASCADE*/
) ENGINE=InnoDB;

