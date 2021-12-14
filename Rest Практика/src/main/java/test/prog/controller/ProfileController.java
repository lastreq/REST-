package test.prog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import test.prog.dao.ProfileDao;
import test.prog.model.Profile;
import test.prog.request.ProfileRequest;
import test.prog.service.ProfileService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;


@Controller


public class ProfileController {

    private static final Logger log = LoggerFactory.getLogger(ProfileController.class);
    @Autowired
    private ProfileDao profileDao;


    private final ProfileService profileService;


    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


    @GetMapping(value = "/getTest")
    public String GetTestGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {

        return "getTest";
    }


    @PostMapping(value = "/getTest")
    public String GetTestPost(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException {


        model.addAttribute("profile", profileService.getProfile(Integer.valueOf(request.getParameter("id"))));

        return "result";
    }

    @GetMapping("/postTest")
    public String PostTestGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {


        return "postTest";
    }


    @PostMapping("/postTest")
    public String PostTestPost(@Valid ProfileRequest request, HttpServletRequest requestHttp, HttpServletResponse response) throws ServletException {
        try {
            requestHttp.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        boolean match0 =   requestHttp.getParameter("firstName").matches("(^[A-Z]{1}[a-z]{1,10}$)|(^[А-Я]{1}[а-я]{1,10}$)");
        boolean match1 =   requestHttp.getParameter("lastName").matches("(^[A-Z]{1}[a-z]{1,10}$)|(^[А-Я]{1}[а-я]{1,10}$)");

        if(match0 && match1) {
            profileService.createProfile(
                    requestHttp.getParameter("firstName"),
                    requestHttp.getParameter("lastName")
            );


            log.info("Запись {} {} {} {} {} {} добавлена",
                    requestHttp.getParameter("firstName"),
                    requestHttp.getParameter("lastName"));

        }
        return "postTest";

    }


    @GetMapping("/deleteTest")
    public String doDeleteGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {


        return "deleteTest";
    }

    @PostMapping("/deleteTest")
    public String doDeletePost(HttpServletRequest requestHttp, HttpServletResponse response) throws ServletException {


        int ProfileId = Integer.valueOf(requestHttp.getParameter("id"));
        profileService.deleteProfile(ProfileId);

        log.info("Запись c ID {} удалена", ProfileId);
        return "deleteTest";
    }


    @RequestMapping(value = "/profile/get/{personId}", //
            method = RequestMethod.GET, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})

    @ResponseBody

    public Profile getProfile(@PathVariable int personId) {

        return profileService.getProfile(personId);

    }


    @RequestMapping(value = "/profile/post", //
            method = RequestMethod.POST, //
            produces = {MediaType.APPLICATION_JSON_VALUE, //
                    MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    public void createProfile(@Valid @RequestBody ProfileRequest request) {
        profileService.createProfile(
                request.getFirstName(),
                request.getLastName()
        );

        log.info("Запись {} {} {} {} {} {} добавлена",

                request.getFirstName(),
                request.getLastName() );



    }

    @RequestMapping(value = "/profile/delete/{personId}", //
            method = RequestMethod.DELETE, //
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseBody
    @DeleteMapping(value = "/{personId:\\d+}")
    public void deleteProfile(@PathVariable int personId) {
        profileService.deleteProfile(personId);

        log.info("Запись c ID {} удалена", personId);

    }

}