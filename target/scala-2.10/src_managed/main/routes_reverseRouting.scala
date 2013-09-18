// @SOURCE:/home/kisscool/workspace/java/equipmentList/conf/routes
// @HASH:0a2c3a10933b98822cb7c9db6995eaa6bfef6ccc
// @DATE:Wed Sep 11 22:53:53 CEST 2013

import Routes.{prefix => _prefix, defaultPrefix => _defaultPrefix}
import play.core._
import play.core.Router._
import play.core.j._

import play.api.mvc._
import play.libs.F

import Router.queryString


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers {

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:6
class ReverseApplication {
    

// @LINE:13
def newEquipment(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "equipments")
}
                                                

// @LINE:14
def deleteEquipment(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "equipments/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                                                

// @LINE:21
def deleteProduct(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "products/" + implicitly[PathBindable[Long]].unbind("id", id) + "/delete")
}
                                                

// @LINE:19
def newProduct(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "products")
}
                                                

// @LINE:12
def equipments(): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "equipments")
}
                                                

// @LINE:22
def upload(): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "upload")
}
                                                

// @LINE:18
def products(p:Int = 0, s:String = "name", o:String = "asc", f:String = ""): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "products" + queryString(List(if(p == 0) None else Some(implicitly[QueryStringBindable[Int]].unbind("p", p)), if(s == "name") None else Some(implicitly[QueryStringBindable[String]].unbind("s", s)), if(o == "asc") None else Some(implicitly[QueryStringBindable[String]].unbind("o", o)), if(f == "") None else Some(implicitly[QueryStringBindable[String]].unbind("f", f)))))
}
                                                

// @LINE:6
def index(): Call = {
   Call("GET", _prefix)
}
                                                

// @LINE:20
def editProduct(id:Long): Call = {
   Call("POST", _prefix + { _defaultPrefix } + "products/" + implicitly[PathBindable[Long]].unbind("id", id))
}
                                                
    
}
                          

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(file:String): Call = {
   Call("GET", _prefix + { _defaultPrefix } + "assets/" + implicitly[PathBindable[String]].unbind("file", file))
}
                                                
    
}
                          
}
                  


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.javascript {

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:6
class ReverseApplication {
    

// @LINE:13
def newEquipment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.newEquipment",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "equipments"})
      }
   """
)
                        

// @LINE:14
def deleteEquipment : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteEquipment",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "equipments/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:21
def deleteProduct : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.deleteProduct",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "products/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id) + "/delete"})
      }
   """
)
                        

// @LINE:19
def newProduct : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.newProduct",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "products"})
      }
   """
)
                        

// @LINE:12
def equipments : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.equipments",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "equipments"})
      }
   """
)
                        

// @LINE:22
def upload : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.upload",
   """
      function() {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "upload"})
      }
   """
)
                        

// @LINE:18
def products : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.products",
   """
      function(p,s,o,f) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "products" + _qS([(p == null ? null : (""" + implicitly[QueryStringBindable[Int]].javascriptUnbind + """)("p", p)), (s == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("s", s)), (o == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("o", o)), (f == null ? null : (""" + implicitly[QueryStringBindable[String]].javascriptUnbind + """)("f", f))])})
      }
   """
)
                        

// @LINE:6
def index : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.index",
   """
      function() {
      return _wA({method:"GET", url:"""" + _prefix + """"})
      }
   """
)
                        

// @LINE:20
def editProduct : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Application.editProduct",
   """
      function(id) {
      return _wA({method:"POST", url:"""" + _prefix + { _defaultPrefix } + """" + "products/" + (""" + implicitly[PathBindable[Long]].javascriptUnbind + """)("id", id)})
      }
   """
)
                        
    
}
              

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at : JavascriptReverseRoute = JavascriptReverseRoute(
   "controllers.Assets.at",
   """
      function(file) {
      return _wA({method:"GET", url:"""" + _prefix + { _defaultPrefix } + """" + "assets/" + (""" + implicitly[PathBindable[String]].javascriptUnbind + """)("file", file)})
      }
   """
)
                        
    
}
              
}
        


// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:9
// @LINE:6
package controllers.ref {

// @LINE:22
// @LINE:21
// @LINE:20
// @LINE:19
// @LINE:18
// @LINE:14
// @LINE:13
// @LINE:12
// @LINE:6
class ReverseApplication {
    

// @LINE:13
def newEquipment(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.newEquipment(), HandlerDef(this, "controllers.Application", "newEquipment", Seq(), "POST", """""", _prefix + """equipments""")
)
                      

// @LINE:14
def deleteEquipment(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteEquipment(id), HandlerDef(this, "controllers.Application", "deleteEquipment", Seq(classOf[Long]), "POST", """""", _prefix + """equipments/$id<[^/]+>/delete""")
)
                      

// @LINE:21
def deleteProduct(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.deleteProduct(id), HandlerDef(this, "controllers.Application", "deleteProduct", Seq(classOf[Long]), "POST", """""", _prefix + """products/$id<[^/]+>/delete""")
)
                      

// @LINE:19
def newProduct(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.newProduct(), HandlerDef(this, "controllers.Application", "newProduct", Seq(), "POST", """""", _prefix + """products""")
)
                      

// @LINE:12
def equipments(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.equipments(), HandlerDef(this, "controllers.Application", "equipments", Seq(), "GET", """ Equipments""", _prefix + """equipments""")
)
                      

// @LINE:22
def upload(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.upload(), HandlerDef(this, "controllers.Application", "upload", Seq(), "POST", """""", _prefix + """upload""")
)
                      

// @LINE:18
def products(p:Int, s:String, o:String, f:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.products(p, s, o, f), HandlerDef(this, "controllers.Application", "products", Seq(classOf[Int], classOf[String], classOf[String], classOf[String]), "GET", """ Products""", _prefix + """products""")
)
                      

// @LINE:6
def index(): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.index(), HandlerDef(this, "controllers.Application", "index", Seq(), "GET", """ Home page""", _prefix + """""")
)
                      

// @LINE:20
def editProduct(id:Long): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Application.editProduct(id), HandlerDef(this, "controllers.Application", "editProduct", Seq(classOf[Long]), "POST", """""", _prefix + """products/$id<[^/]+>""")
)
                      
    
}
                          

// @LINE:9
class ReverseAssets {
    

// @LINE:9
def at(path:String, file:String): play.api.mvc.HandlerRef[_] = new play.api.mvc.HandlerRef(
   controllers.Assets.at(path, file), HandlerDef(this, "controllers.Assets", "at", Seq(classOf[String], classOf[String]), "GET", """ Map static resources from the /public folder to the /assets URL path""", _prefix + """assets/$file<.+>""")
)
                      
    
}
                          
}
                  
      