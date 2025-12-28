package com.noyon.service.acl;
import org.springframework.data.jpa.domain.Specification;
import com.noyon.entity.acl.RequestMap;
public class RequestMapSpecification {

	public static Specification<RequestMap>filterRequestMaps(
			String url,
			String configAttribute
			)
	{
		return (root,query,cb)->{
			var predicate=cb.conjunction();
			
			if(url !=null) {
				predicate=cb.and(
						predicate,
						cb.equal(root.get("url"), url)
						);
			}
			
			if( configAttribute !=null) {
				predicate=cb.and(
						predicate,
						cb.equal(root.get("configAttribute"), configAttribute)
						);
			}
			return predicate;
		};
	}
}
