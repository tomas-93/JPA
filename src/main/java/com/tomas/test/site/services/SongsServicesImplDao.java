package com.tomas.test.site.services;

import com.tomas.test.site.entity.SongsEntity;
import com.tomas.test.site.repository.SongsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tomas on 28/07/2016.
 */
@Service
public class SongsServicesImplDao implements SongsServicesDao
{

    @Inject
    SongsRepository songsRepository;

    @Override
    @Transactional
    public void addPerson(SongsEntity songsEntity)
    {
        songsRepository.save(songsEntity);
    }

    @Override
    @Transactional
    public void updatePerson(SongsEntity songsEntity)
    {
        this.songsRepository.delete(songsEntity.getId());
        this.songsRepository.save(songsEntity);
    }

    @Override
    @Transactional
    public List<SongsEntity> listPersons()
    {
        List<SongsEntity> list = new ArrayList<>();
        this.songsRepository.findAll().forEach(list::add);
        return list;
    }

    @Override
    @Transactional
    public SongsEntity getPersonById(long id)
    {
        return this.songsRepository.findOne(id);
    }

    @Override
    @Transactional
    public void removePerson(long id)
    {
        this.songsRepository.delete(id);
    }
}
