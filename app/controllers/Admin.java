package controllers;

import play.*;
import play.mvc.*;
import play.data.validation.*;

import java.util.*;

import models.*;

public class Admin extends Controller {
 public static void index() {
List<WorkUpdate> new_workUpdates = WorkUpdate.find("byStatus","new").fetch();
    render(new_workUpdates);
            }
 public static void form(Long id) {
     if(id != null) {
        WorkUpdate workUpdate = WorkUpdate.findById(id);
        render(workUpdate);
     }
    }
public static void save(Long id, String status)    {
WorkUpdate workUpdate = WorkUpdate.findById(id);
workUpdate.status = status;
workUpdate.save();
index();
}


}
