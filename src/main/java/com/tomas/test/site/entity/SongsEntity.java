package com.tomas.test.site.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Tomas on 28/07/2016.
 */
@Entity
@Table(name = "songs")
public class SongsEntity implements Serializable
{
    private static final long serialVersionUID = 1L;

    private long id;
    private String name;
    private String art;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long getId()
    {
        return id;
    }

    public void setId(long id)
    {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    @Basic
    @Column(name = "art")
    public String getArt()
    {
        return art;
    }

    public void setArt(String art)
    {
        this.art = art;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SongsEntity that = (SongsEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (art != null ? !art.equals(that.art) : that.art != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (art != null ? art.hashCode() : 0);
        return result;
    }
}
