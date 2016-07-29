package com.tomas.test.site.services;

import com.tomas.test.site.entity.SongsEntity;

import java.util.List;

/**
 * Created by Tomas on 28/07/2016.
 */
public interface SongsServicesDao
{
    void addPerson(SongsEntity songsEntity);
    void updatePerson(SongsEntity songsEntity);
    List<SongsEntity> listPersons();
    SongsEntity getPersonById(long id);
    void removePerson(long id);
}
