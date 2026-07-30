
package hr.tis.academy.dto;

import hr.tis.academy.enums.Type;


public record attraction (
        String name,
        String description,
        Type type
){}
