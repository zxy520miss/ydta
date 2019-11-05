package com.nuo.ydta.service;

import com.nuo.ydta.domain.Camp;

import java.util.List;

public interface CampService {

    List<Camp> getCamps();

    void addCamp(Camp camp);

    void updateCamp(Camp camp);

    void deleteCamp(Camp camp);

}
