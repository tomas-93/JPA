package com.tomas.test.site.controller;

import com.tomas.test.site.entity.SongsEntity;
import com.tomas.test.site.services.SongsServicesDao;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Created by Tomas on 28/07/2016.
 */
@Controller
public class SongsController
{
    @Inject
    private SongsServicesDao songsServicesDao;

    @RequestMapping(value = {"list"})
    public String event(Model model)
    {
        model.addAttribute("songs", this.songsServicesDao.listPersons());
        return "songs/list";
    }
    @RequestMapping(value = "add")
    public String add()
    {
        SongsEntity songsEntity = new SongsEntity();
        songsEntity.setName("Otra vez");
        songsEntity.setArt("Genitallica");
        this.songsServicesDao.addPerson(songsEntity);
        return  "redirect:/list";
    }
    @RequestMapping("delete/{id}")
    public String delete(@PathVariable long id)
    {
        this.songsServicesDao.removePerson(id);
        return  "redirect:/list";

    }
    @RequestMapping("update/{id}")
    public String update(@PathVariable long id)
    {
        SongsEntity songsEntity = this.songsServicesDao.getPersonById(id);
        songsEntity.setName("De la noche a la mañana");
        songsEntity.setArt("Elefante");
        this.songsServicesDao.addPerson(songsEntity);
        return  "redirect:/list";
    }
}
