-- Table: d_artist

DROP TABLE d_artist;

CREATE TABLE d_artist
(
  artist_id bigint NOT NULL,
  creationdate abstime,
  mbid character varying(255),
  name character varying(255),
  pictureurl character varying(255),
  url character varying(255),
  wikilastchanged abstime,
  wikisummary character varying(1024),
  wikitext character varying(2048),
  CONSTRAINT d_artist_pkey PRIMARY KEY (artist_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_artist
  OWNER TO dw;
  

-- Table: d_tag

DROP TABLE d_tag;

CREATE TABLE d_tag
(
  tag_id bigint NOT NULL,
  creationdate abstime,
  tagvalue character varying(255),
  wikilastchanged abstime,
  wikisummary character varying(2048),
  wikitext character varying(8000),
  CONSTRAINT d_tag_pkey PRIMARY KEY (tag_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_tag
  OWNER TO dw;

  
  -- Table: d_user

DROP TABLE d_user;

CREATE TABLE d_user
(
  user_id bigint NOT NULL,
  age integer,
  creationdate abstime,
  gender character varying(255),
  name character varying(255),
  CONSTRAINT d_user_pkey PRIMARY KEY (user_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_user
  OWNER TO dw;

  
  -- Table: d_date_hierarchy

DROP TABLE d_date_hierarchy;

CREATE TABLE d_date_hierarchy
(
  id bigint NOT NULL,
  calendar abstime,
  date date,
  dateandtime abstime,
  day_name character varying(255),
  dayofmonth integer,
  month integer,
  month_name character varying(255),
  quarter integer,
  quarter_name character varying(255),
  "time" time without time zone,
  weekofmonth integer,
  year integer,
  CONSTRAINT d_date_hierarchy_pkey PRIMARY KEY (id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_date_hierarchy
  OWNER TO dw;

-- Table: d_artist_d_artist

DROP TABLE d_artist_d_artist;

CREATE TABLE d_artist_d_artist
(
  artistbean_artist_id bigint,
  similarartistlist_artist_id bigint
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_artist_d_artist
  OWNER TO dw;

-- Index: i_d_rttst_artistbean_artist_id

--DROP INDEX i_d_rttst_artistbean_artist_id;

CREATE INDEX i_d_rttst_artistbean_artist_id
  ON d_artist_d_artist
  USING btree
  (artistbean_artist_id );

-- Index: i_d_rttst_element

--DROP INDEX i_d_rttst_element;

CREATE INDEX i_d_rttst_element
  ON d_artist_d_artist
  USING btree
  (similarartistlist_artist_id );

  
 -- Table: d_artist_d_tag

DROP TABLE d_artist_d_tag;

CREATE TABLE d_artist_d_tag
(
  artistbean_artist_id bigint,
  taglist_tag_id bigint
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_artist_d_tag
  OWNER TO dw;

-- Index: i_d_rt_tg_artistbean_artist_id

-- DROP INDEX i_d_rt_tg_artistbean_artist_id;

CREATE INDEX i_d_rt_tg_artistbean_artist_id
  ON d_artist_d_tag
  USING btree
  (artistbean_artist_id );

-- Index: i_d_rt_tg_element

-- DROP INDEX i_d_rt_tg_element;

CREATE INDEX i_d_rt_tg_element
  ON d_artist_d_tag
  USING btree
  (taglist_tag_id );

  
-- Table: d_tag_d_tag

DROP TABLE d_tag_d_tag;

CREATE TABLE d_tag_d_tag
(
  tagbean_tag_id bigint,
  similartaglist_tag_id bigint
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_tag_d_tag
  OWNER TO dw;

-- Index: i_d_tg_tg_element

-- DROP INDEX i_d_tg_tg_element;

CREATE INDEX i_d_tg_tg_element
  ON d_tag_d_tag
  USING btree
  (similartaglist_tag_id );

-- Index: i_d_tg_tg_tagbean_tag_id

-- DROP INDEX i_d_tg_tg_tagbean_tag_id;

CREATE INDEX i_d_tg_tg_tagbean_tag_id
  ON d_tag_d_tag
  USING btree
  (tagbean_tag_id );



-- Table: d_user_d_user

DROP TABLE d_user_d_user;

CREATE TABLE d_user_d_user
(
  userbean_user_id bigint,
  frienduserlist_user_id bigint
)
WITH (
  OIDS=FALSE
);
ALTER TABLE d_user_d_user
  OWNER TO dw;

-- Index: i_d_sr_sr_element

-- DROP INDEX i_d_sr_sr_element;

CREATE INDEX i_d_sr_sr_element
  ON d_user_d_user
  USING btree
  (frienduserlist_user_id );

-- Index: i_d_sr_sr_userbean_user_id

-- DROP INDEX i_d_sr_sr_userbean_user_id;

CREATE INDEX i_d_sr_sr_userbean_user_id
  ON d_user_d_user
  USING btree
  (userbean_user_id );

-- Table: f_listening

DROP TABLE f_listening;

CREATE TABLE f_listening
(
  listening_id bigint NOT NULL,
  artist_id bigint,
  date_hierarchy_id bigint,
  user_id bigint,
  CONSTRAINT f_listening_pkey PRIMARY KEY (listening_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE f_listening
  OWNER TO dw;

-- Index: i_f_lsnng_artistbean

-- DROP INDEX i_f_lsnng_artistbean;

CREATE INDEX i_f_lsnng_artistbean
  ON f_listening
  USING btree
  (artist_id );

-- Index: i_f_lsnng_datehierarchybean

-- DROP INDEX i_f_lsnng_datehierarchybean;

CREATE INDEX i_f_lsnng_datehierarchybean
  ON f_listening
  USING btree
  (date_hierarchy_id );

-- Index: i_f_lsnng_userbean

-- DROP INDEX i_f_lsnng_userbean;

CREATE INDEX i_f_lsnng_userbean
  ON f_listening
  USING btree
  (user_id );


  -- Table: f_tagging

DROP TABLE f_tagging;

CREATE TABLE f_tagging
(
  tagging_id bigint NOT NULL,
  artist_id bigint,
  date_hierarchy_id bigint,
  tag_id bigint,
  user_id bigint,
  CONSTRAINT f_tagging_pkey PRIMARY KEY (tagging_id )
)
WITH (
  OIDS=FALSE
);
ALTER TABLE f_tagging
  OWNER TO dw;

-- Index: i_f_tggng_artistbean

-- DROP INDEX i_f_tggng_artistbean;

CREATE INDEX i_f_tggng_artistbean
  ON f_tagging
  USING btree
  (artist_id );

-- Index: i_f_tggng_datehierarchybean

-- DROP INDEX i_f_tggng_datehierarchybean;

CREATE INDEX i_f_tggng_datehierarchybean
  ON f_tagging
  USING btree
  (date_hierarchy_id );

-- Index: i_f_tggng_tagbean

-- DROP INDEX i_f_tggng_tagbean;

CREATE INDEX i_f_tggng_tagbean
  ON f_tagging
  USING btree
  (tag_id );

-- Index: i_f_tggng_userbean

-- DROP INDEX i_f_tggng_userbean;

CREATE INDEX i_f_tggng_userbean
  ON f_tagging
  USING btree
  (user_id );


 --creamos la tabla de opiniones
DROP TABLE ratings;
  
create table ratings( 
	artist_id bigint not null, 
	tag_id bigint not null, 
	rating float not null default 0, 

	primary key(artist_id, tag_id) 
);

DROP VIEW vw_freq_diff;

DROP TABLE freq_diff;
create table freq_diff ( 
tag_id1 bigint, 
tag_id2 bigint, 
freq float not null default 0, 
diff float not null default 0, 
primary key(tag_id1, tag_id2) 
);
 
create index idx_freq_diff1 on freq_diff(tag_id1, freq, diff, 
tag_id2); 
create index idx_freq_diff2 on freq_diff(tag_id2, freq, diff, 
tag_id1);

--creamos una vista para hacer sim√©trica la tabla de frecuencias y diferencias 

create view vw_freq_diff as 
select 
	tag_id1 as tag_id1, 
	tag_id2 as tag_id2, 
	freq, diff 
from 	
	freq_diff 
union all 
select 
	tag_id2 as tag_id2, 
	tag_id1 as tag_id1, 
	freq, -1 * diff 
from 
	freq_diff;



