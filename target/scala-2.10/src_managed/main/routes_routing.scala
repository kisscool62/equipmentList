// @SOURCE:/home/kisscool/workspace/java/equipmentList/conf/routes
// @HASH:0a2c3a10933b98822cb7c9db6995eaa6bfef6ccc
// @DATE:Wed Sep 11 22:53:53 CEST 2013


import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString

object Routes extends Router.Routes {

private var _prefix = "/"

def setPrefix(prefix: String) {
  _prefix = prefix  
  List[(String,Routes)]().foreach {
    case (p, router) => router.setPrefix(prefix + (if(prefix.endsWith("/")) "" else "/") + p)
  }
}

def prefix = _prefix

lazy val defaultPrefix = { if(Routes.prefix.endsWith("/")) "" else "/" } 


// @LINE:6
private[this] lazy val controllers_Application_index0 = Route("GET", PathPattern(List(StaticPart(Routes.prefix))))
        

// @LINE:9
private[this] lazy val controllers_Assets_at1 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("assets/"),DynamicPart("file", """.+""",false))))
        

// @LINE:12
private[this] lazy val controllers_Application_equipments2 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("equipments"))))
        

// @LINE:13
private[this] lazy val controllers_Application_newEquipment3 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("equipments"))))
        

// @LINE:14
private[this] lazy val controllers_Application_deleteEquipment4 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("equipments/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
        

// @LINE:18
private[this] lazy val controllers_Application_products5 = Route("GET", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("products"))))
        

// @LINE:19
private[this] lazy val controllers_Application_newProduct6 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("products"))))
        

// @LINE:20
private[this] lazy val controllers_Application_editProduct7 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("products/"),DynamicPart("id", """[^/]+""",true))))
        

// @LINE:21
private[this] lazy val controllers_Application_deleteProduct8 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("products/"),DynamicPart("id", """[^/]+""",true),StaticPart("/delete"))))
        

// @LINE:22
private[this] lazy val controllers_Application_upload9 = Route("POST", PathPattern(List(StaticPart(Routes.prefix),StaticPart(Routes.defaultPrefix),StaticPart("upload"))))
        
def documentation = List(("""GET""", prefix,"""controllers.Application.index()"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """assets/$file<.+>""","""controllers.Assets.at(path:String = "/public", file:String)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """equipments""","""controllers.Application.equipments()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """equipments""","""controllers.Application.newEquipment()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """equipments/$id<[^/]+>/delete""","""controllers.Application.deleteEquipment(id:Long)"""),("""GET""", prefix + (if(prefix.endsWith("/")) "" else "/") + """products""","""controllers.Application.products(p:Int ?= 0, s:String ?= "name", o:String ?= "asc", f:String ?= "")"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """products""","""controllers.Application.newProduct()"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """products/$id<[^/]+>""","""controllers.Application.editProduct(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """products/$id<[^/]+>/delete""","""controllers.Application.deleteProduct(id:Long)"""),("""POST""", prefix + (if(prefix.endsWith("/")) "" else "/") + """upload""","""controllers.Application.upload()""")).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
  case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
  case l => s ++ l.asInstanceOf[List[(String,String,String)]] 
}}
       
    
def routes:PartialFunction[RequestHeader,Handler] = {        

// @LINE:6
case controllers_Application_index0(params) => {
   call { 
        invokeHandler(controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Nil,"GET", """ Home page""", Routes.prefix + """"""))
   }
}
        

// @LINE:9
case controllers_Assets_at1(params) => {
   call(Param[String]("path", Right("/public")), params.fromPath[String]("file", None)) { (path, file) =>
        invokeHandler(controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]),"GET", """ Map static resources from the /public folder to the /assets URL path""", Routes.prefix + """assets/$file<.+>"""))
   }
}
        

// @LINE:12
case controllers_Application_equipments2(params) => {
   call { 
        invokeHandler(controllers.Application.equipments(), HandlerDef(this, "controllers.Application", "equipments", Nil,"GET", """ Equipments""", Routes.prefix + """equipments"""))
   }
}
        

// @LINE:13
case controllers_Application_newEquipment3(params) => {
   call { 
        invokeHandler(controllers.Application.newEquipment(), HandlerDef(this, "controllers.Application", "newEquipment", Nil,"POST", """""", Routes.prefix + """equipments"""))
   }
}
        

// @LINE:14
case controllers_Application_deleteEquipment4(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Application.deleteEquipment(id), HandlerDef(this, "controllers.Application", "deleteEquipment", Seq(classOf[Long]),"POST", """""", Routes.prefix + """equipments/$id<[^/]+>/delete"""))
   }
}
        

// @LINE:18
case controllers_Application_products5(params) => {
   call(params.fromQuery[Int]("p", Some(0)), params.fromQuery[String]("s", Some("name")), params.fromQuery[String]("o", Some("asc")), params.fromQuery[String]("f", Some(""))) { (p, s, o, f) =>
        invokeHandler(controllers.Application.products(p, s, o, f), HandlerDef(this, "controllers.Application", "products", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]),"GET", """ Products""", Routes.prefix + """products"""))
   }
}
        

// @LINE:19
case controllers_Application_newProduct6(params) => {
   call { 
        invokeHandler(controllers.Application.newProduct(), HandlerDef(this, "controllers.Application", "newProduct", Nil,"POST", """""", Routes.prefix + """products"""))
   }
}
        

// @LINE:20
case controllers_Application_editProduct7(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Application.editProduct(id), HandlerDef(this, "controllers.Application", "editProduct", Seq(classOf[Long]),"POST", """""", Routes.prefix + """products/$id<[^/]+>"""))
   }
}
        

// @LINE:21
case controllers_Application_deleteProduct8(params) => {
   call(params.fromPath[Long]("id", None)) { (id) =>
        invokeHandler(controllers.Application.deleteProduct(id), HandlerDef(this, "controllers.Application", "deleteProduct", Seq(classOf[Long]),"POST", """""", Routes.prefix + """products/$id<[^/]+>/delete"""))
   }
}
        

// @LINE:22
case controllers_Application_upload9(params) => {
   call { 
        invokeHandler(controllers.Application.upload(), HandlerDef(this, "controllers.Application", "upload", Nil,"POST", """""", Routes.prefix + """upload"""))
   }
}
        
}
    
}
        