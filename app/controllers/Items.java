package controllers;

import play.*;
import play.mvc.*;

@Check("ADMIN")
@With(Secure.class)
public class Items extends CRUD {
}
