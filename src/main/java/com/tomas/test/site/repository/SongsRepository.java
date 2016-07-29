package com.tomas.test.site.repository;

import com.tomas.test.site.entity.SongsEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Tomas on 28/07/2016.
 */
public interface SongsRepository extends CrudRepository<SongsEntity,Long>
{
}
