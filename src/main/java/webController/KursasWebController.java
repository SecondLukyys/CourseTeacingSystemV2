package webController;

import com.google.gson.Gson;
import hibControllers.KursasHibController;
import hibControllers.VartotojasHibController;
import model.FizinisAsmuo;
import model.Kursas;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Controller
public class KursasWebController {

    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("CourseManagementSystem");
    KursasHibController kursasHibController = new KursasHibController(entityManagerFactory);


    @RequestMapping(value = "/kursas/allCourses", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getAllUsers(){
        return kursasHibController.getAllCourses().toString();
    }

    @RequestMapping(value = "/kursas/getCourse", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String getCourse(@RequestBody String courseInfo) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(courseInfo, Properties.class);
        String name = properties.getProperty("name");
        String diff = properties.getProperty("diff"); // course difficulty
        return gson.toJson(kursasHibController.getKursasss(name, diff).toString());
    }

    @RequestMapping(value = "/kursas/updateCourse/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updateCourse(@RequestBody String request, @PathVariable(name = "id") int id) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);

        Kursas kursas = kursasHibController.getCourseById(id);

        kursas.setCourseName(properties.getProperty("name"));
        kursas.setCourseDiffuculty(properties.getProperty("difficulty"));
        kursas.setApproximiteStrudyTime(properties.getProperty("studyTime"));
        kursas.setCourseDescription(properties.getProperty("description"));

        kursasHibController.editCourse(kursas);
        return "Success";
    }

    @RequestMapping(value = "/kursas/addCourse", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String addNewCourse(@RequestBody String request) {
        Gson gson = new Gson();
        Properties properties = gson.fromJson(request, Properties.class);
        Kursas kursas = new Kursas(properties.getProperty("name"), properties.getProperty("difficulty"), properties.getProperty("studyTime"), properties.getProperty("description"));
        kursasHibController.createCourse(kursas);
        return "Success";
    }

    @RequestMapping(value = "/kursas/deleteCourse/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    @ResponseBody
    public String updatePerson(@PathVariable(name = "id") int id) {
        kursasHibController.removeCourse(id);
        return "Success";
    }



}
