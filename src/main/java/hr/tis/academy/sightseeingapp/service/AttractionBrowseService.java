package hr.tis.academy.sightseeingapp.service;

import hr.tis.academy.sightseeingapp.dto.AttractionRowDto;
import hr.tis.academy.sightseeingapp.dto.NewAttractionFormDto;

import java.util.List;

public interface AttractionBrowseService {
    List<AttractionRowDto> getAllAttractionRows();
    void createAttraction(NewAttractionFormDto form);
}