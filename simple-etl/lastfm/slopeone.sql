--creamos la tabla de opiniones
create table ratings( 
artist_id bigint not null, 
tag_id bigint not null, 
rating float not null default 0, 
primary key(artist_id, tag_id) 
);

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


