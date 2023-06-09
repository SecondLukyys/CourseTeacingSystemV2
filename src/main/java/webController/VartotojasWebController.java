package webController;

import com.google.gson.Gson;
import hibControllers.VartotojasHibController;
import model.FizinisAsmuo;
import model.Vartotojas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class VartotojasWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    VartotojasHibController userHibController = new VartotojasHibController(entityManagerFactory);


    @RequestMapping(value = "/vartotojas/allUsers", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers(){
        return userHibController.getAllUsers().toString();
    }

    @RequestMapping(value = "/vartotojas/addUser", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFizPerson(@RequestBody String request){
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Vartotojas user = new FizinisAsmuo(properties.getProperty("login"), properties.getProperty("password"), properties.getProperty("name"), properties.getProperty("surename"), properties.getProperty("phoneNumber"), properties.getProperty("emailAddress"));
        userHibController.createUser(user);
        return "success";
    }

    @RequestMapping(value = "/vartotojas/authenticate", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String checkUser(@RequestBody String userInfo){

        Gson gson = new Gson();
        Properties properties = gson.fromJson(userInfo, Properties.class);
        String login = properties.getProperty("login");
        String password = properties.getProperty("psw");

        Vartotojas vartotojas = userHibController.getUserrrr(login, password);

        return vartotojas.getLogin();
    }

}
