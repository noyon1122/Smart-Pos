package com.noyon.service.acl;

import org.springframework.data.jpa.domain.Specification;
import com.noyon.entity.acl.Menu;

public class MenuSpecification {

    public static Specification<Menu> filterMenus(
            Long parentMenu,
            String title,
            String urlPath
    ) {
        return (root, query, cb) -> {

            var predicate = cb.conjunction(); // always TRUE initially

            if (parentMenu != null) {
                predicate = cb.and(
                    predicate,
                    cb.equal(root.get("parentMenu").get("id"), parentMenu)
                );
            }

            if (title != null && !title.isEmpty()) {
                predicate = cb.and(
                    predicate,
                    cb.like(
                        cb.lower(root.get("title")),
                        "%" + title.toLowerCase() + "%"
                    )
                );
            }

            if (urlPath != null && !urlPath.isEmpty()) {
                predicate = cb.and(
                    predicate,
                    cb.like(
                        cb.lower(root.get("urlPath")),
                        "%" + urlPath.toLowerCase() + "%"
                    )
                );
            }

            return predicate;
        };
    }
}
