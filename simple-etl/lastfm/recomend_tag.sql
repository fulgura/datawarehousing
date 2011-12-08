select 
	tag_id1, max(tags.tagvalue), sum(freq* (rating+ diff)) / sum(freq) as predicted 
from 
	ratings as r, 
	vw_freq_diff as fd,
	d_tag as tags 
where tags.tag_id = fd.tag_id1 
and fd.tag_id2= r.tag_id 
and fd.tag_id1< fd.tag_id2 
and artist_id= 53
and tag_id1 not in (select tag_id from ratings where artist_id= 53) 
group by tag_id1 
order by predicted desc


select * from d_tag