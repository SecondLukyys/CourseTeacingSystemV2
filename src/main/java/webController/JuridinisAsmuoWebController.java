package webController;

import com.google.gson.Gson;
import hibControllers.VartotojasHibController;
import model.FizinisAsmuo;
import model.JuridinisAsmuo;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class JuridinisAsmuoWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    VartotojasHibController userHibController = new VartotojasHibController(entityManagerFactory);


    @RequestMapping(value = "/juridinisAsmuo/getCompany", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUser(@RequestBody String userInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(userInfo, Properties.class);
        String login = properties.getProperty("login");
        String password = properties.getProperty("psw");
        return gson.toJson(userHibController.getUserrrr(login, password).toString());
    }

    @RequestMapping(value = "/juridinisAsmuo/updateCompany/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCompany(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        JuridinisAsmuo juridinisAsmuo = userHibController.getUserByIdJur(id);

        juridinisAsmuo.setLogin(properties.getProperty("login"));
        juridinisAsmuo.setPassword(properties.getProperty("password"));
        juridinisAsmuo.setCompanyEmailAddress(properties.getProperty("emailAddress"));
        juridinisAsmuo.setCompanyAddress(properties.getProperty("companyAddress"));

        userHibController.editUser(juridinisAsmuo);
        return "Success";
    }

    @RequestMapping(value = "/juridinisAsmuo/addCompany", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewCompany(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        JuridinisAsmuo juridinisAsmuo = new JuridinisAsmuo(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("companyName"), properties.getProperty("companyAddress"));
        userHibController.createUser(juridinisAsmuo);
        return "Success";
    }

    @RequestMapping(value = "/juridinisAsmuo/deleteCompany/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@PathVariable(name = "id") int id) {
        userHibController.removeUserById(id);
        return "Success";
    }



}
