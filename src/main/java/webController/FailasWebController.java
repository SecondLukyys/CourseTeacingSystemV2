package webController;

import com.google.gson.Gson;
import hibControllers.FailasHibController;
import hibControllers.KatalogasHibController;
import model.Failas;
import model.Katalogas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class FailasWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    FailasHibController failasHibController = new FailasHibController(entityManagerFactory);

    @RequestMapping(value = "/failas/getFile/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getFile(@PathVariable(name = "id") int id) {
        return failasHibController.getFileById(id).toString();
    }

    @RequestMapping(value = "/failas/updateFile/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFile(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Failas failas = failasHibController.getFileById(id);

        failas.setFileName(properties.getProperty("fileName"));
        failas.setInformation(properties.getProperty("information"));

        failasHibController.editFile(failas);
        return "Success";
    }

    @RequestMapping(value = "/failas/addFile", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewFile(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Failas failas = new Failas(properties.getProperty("fileName"), properties.getProperty("information"));
        failasHibController.createFile(failas);
        return "Success";
    }

    @RequestMapping(value = "/failas/deleteFile/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateFile(@PathVariable(name = "id") int id) {
        failasHibController.removeFile(id);
        return "Success";
    }

}
