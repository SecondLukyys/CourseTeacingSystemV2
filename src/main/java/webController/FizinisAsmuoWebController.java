package webController;

import com.google.gson.Gson;
import hibControllers.VartotojasHibController;
import model.FizinisAsmuo;
import model.JuridinisAsmuo;
import model.Vartotojas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class FizinisAsmuoWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    VartotojasHibController userHibController = new VartotojasHibController(entityManagerFactory);


    @RequestMapping(value = "/fizinisAsmuo/getUser", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getUser(@RequestBody String userInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(userInfo, Properties.class);
        String login = properties.getProperty("login");
        String password = properties.getProperty("psw");
        return gson.toJson(userHibController.getUserrrr(login, password).toString());
    }


    @RequestMapping(value = "/fizinisAsmuo/updatePerson/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        FizinisAsmuo fizinisAsmuo =  userHibController.getUserByIdFiz(id);

        fizinisAsmuo.setLogin(properties.getProperty("login"));
        fizinisAsmuo.setPassword(properties.getProperty("password"));
        fizinisAsmuo.setName(properties.getProperty("name"));
        fizinisAsmuo.setSurename(properties.getProperty("surename"));

        userHibController.editUser(fizinisAsmuo);
        return "Success";
    }

    @RequestMapping(value = "/fizinisAsmuo/addPerson", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewPerson(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        FizinisAsmuo fizinisAsmuo = new FizinisAsmuo(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"), properties.getProperty("surname"));
        userHibController.createUser(fizinisAsmuo);
        return "Success";
    }

    @RequestMapping(value = "/fizinisAsmuo/deleteUser/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@PathVariable(name = "id") int id) {
        userHibController.removeUserById(id);
        return "Success";
    }

}
