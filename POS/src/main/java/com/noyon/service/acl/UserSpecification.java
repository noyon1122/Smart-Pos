package com.noyon.service.acl;
import org.springframework.data.jpa.domain.Specification;
import com.noyon.entity.acl.User;

public class UserSpecification {

	public static Specification<User> filterUsers(
			Long plazas,
			Long role,
			String username,
			String fullName
			)
	{
		return (root,query,cb) ->{
			var predicate=cb.conjunction();
			
			if(plazas !=null) {
				predicate=cb.and(
						predicate,
						cb.equal(root.get("plazas").get("id"), plazas)
						);
			}
			
			if(role !=null) {
				predicate=cb.and(
						predicate,
						cb.equal(root.get("userRoles").get("role").get("id"), role)
						);
			}
			
		   if(username !=null)
		   {
			   predicate=cb.and(
					   predicate,
					   cb.equal(root.get("username"), username)
					   );
		   }
		   if(fullName !=null)
		   {
			   predicate=cb.and(
					   predicate,
					   cb.equal(root.get("fullName"), fullName)
					   );
		   }
		   
			return predicate;
		};
	
	}
}
