CREATE TABLE journal_articles {
    article_ID INT NOT NULL,
    title VARCHAR(100),
    journal_ID INT NOT NULL,
    journal_name VARCHAR(100),
    publisher VARCHAR(100),
    cdrom VARCHAR(100),
    crossref VARCHAR(100),
    mdate DATE,
    published_year INT NOT NULL,
    url VARCHAR(100),
    pages VARCHAR(100),
    publtype VARCHAR(100),
    journal_key VARCHAR(100),
    PRIMARY KEY (journal_ID),
    FOREIGN KEY (journal_ID, journal_name, publisher) REFERENCES journals(journal_ID, journal_name, publisher)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (article_ID, title) REFERENCES articles(article_ID, title)
    ON DELETE CASCADE ON UPDATE CASCADE
};



CREATE TABLE conference_articles {
    article_ID INT NOT NULL,
    conference_ID INT NOT NULL,
    conference_name VARCHAR(100),
    title VARCHAR(100),
    cdrom VARCHAR(100),
    crossref VARCHAR(100),
    publtype VARCHAR(100),
    url VARCHAR(100),
    pages VARCHAR(100),
    mdate DATE,
    published_year INT NOT NULL,
    conference_key VARCHAR(100),
    PRIMARY KEY (conference_ID) ,
    FOREIGN KEY (article_ID,title) REFERENCES aricles(article_ID,title)
    ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (conference_ID,conference_name) REFERENCES conferences (conference_ID,conference_name)
    ON DELETE CASCADE ON UPDATE CASCADE
};

CREATE journal_rankings {
    journal_ID INT NOT NULL,
    rank INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    bestSubjectArea VARCHAR(100),
    country VARCHAR(100),
    bestCategories VARCHAR(100),
    journal_language VARCHAR(100),
    PRIMARY KEY (journal_ID, rank, title),
    FOREIGN KEY (journal_ID) REFERENCES journals(journal_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
};


CREATE TABLE conference_rankings {
    conference_ID INT NOT NULL,
    conf_ID INT NOT NULL,
    title VARCHAR(100) NOT NULL,
    acronym VARCHAR(100),
    rank VARCHAR(100),
    PRIMARY KEY (conf_ID, title),
    FOREIGN KEY (conference_ID) REFERENCES conferences(conference_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
};


CREATE TABLE primaryFoRs {
    conf_ID INT NOT NULL,
    /*CSE convert to Int*/
    primaryFor INT NOT NULL,
    PRIMARY KEY (conf_ID, primaryFor),
    FOREIGN KEY (conf_ID) REFERENCES conference_rankings(conf_ID)
    ON DELETE CASCADE ON UPDATE CASCADE
};



CREATE TABLE conference_categories {
    primaryFor INT NOT NULL,
    category_name VARCHAR(100) NOT NULL,
    PRIMARY KEY (category_name)
};
