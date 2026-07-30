
package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.enums.Type;


public record AttractionDto (
        String name,
        String description,
        Type type
){}
