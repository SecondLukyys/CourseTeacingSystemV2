package webController;

import com.google.gson.Gson;
import hibControllers.KatalogasHibController;
import hibControllers.KursasHibController;
import model.Katalogas;
import model.Kursas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class KatalogasWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    KatalogasHibController katalogasHibController = new KatalogasHibController(entityManagerFactory);

    @RequestMapping(value = "/kursas/allCatalogues", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers(){
        return katalogasHibController.getAllCatalogues().toString();
    }

    @RequestMapping(value = "/katalogas/getCatalogue/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCatalogue(@PathVariable(name = "id") int id) {
        return katalogasHibController.getCatalogueById(id).toString();
    }


    @RequestMapping(value = "/katalogas/updateCatalogue/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCatalogue(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Katalogas katalogas = katalogasHibController.getCatalogueById(id);

        katalogas.setCatalogueName(properties.getProperty("catalogueName"));
        katalogas.setParent(properties.getProperty("parent"));
        katalogas.setRootName(properties.getProperty("rootName"));
        katalogas.setLevel(Integer.parseInt(properties.getProperty("level")));

        katalogasHibController.editCatalogue(katalogas);
        return "Success";
    }

    @RequestMapping(value = "/katalogas/addCatalogue", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewCatalogue(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Katalogas katalogas = new Katalogas(properties.getProperty("catalogueName"), properties.getProperty("parent"), properties.getProperty("rootName"), Integer.parseInt(properties.getProperty("level")));
        katalogasHibController.createCatalogue(katalogas);
        return "Success";
    }

    @RequestMapping(value = "/katalogas/deleteCatalogue/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCatalogue(@PathVariable(name = "id") int id) {
        Katalogas katalogas = katalogasHibController.getCatalogueById(id);
        katalogasHibController.removeCatalogueObject(katalogas);
        return "Success";
    }


}
