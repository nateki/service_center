package controllers;

import play.*;
import play.mvc.*;

@Check("ADMIN")
@With(Secure.class)
public class Services extends CRUD {
}
