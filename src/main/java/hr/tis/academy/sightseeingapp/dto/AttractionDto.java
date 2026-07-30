
package hr.tis.academy.sightseeingapp.dto;

import hr.tis.academy.sightseeingapp.enums.Type;


public record attraction (
        String name,
        String description,
        Type type
){}
