package de.anst.segment;

import java.util.List;

import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import de.anst.data.SuperRepository;

@RepositoryRestResource(path = "segment")
public interface SegmentRepository extends SuperRepository<Segment, Long> {
	public List<Segment> findByName(String name);


}
