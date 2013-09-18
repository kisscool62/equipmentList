
package views.html

import play.templates._
import play.templates.TemplateMagic._

import play.api.templates._
import play.api.templates.PlayMagic._
import models._
import controllers._
import java.lang._
import java.util._
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import play.api.i18n._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.data._
import play.api.data.Field
import play.mvc.Http.Context.Implicit._
import views.html._
/**/
object index extends BaseScalaTemplate[play.api.templates.Html,Format[play.api.templates.Html]](play.api.templates.HtmlFormat) with play.api.templates.Template4[com.avaje.ebean.Page[Product],String,String,String,play.api.templates.Html] {

    /**/
    def apply/*1.2*/(currentPage: com.avaje.ebean.Page[Product], currentSortBy: String, currentOrder: String, currentFilter: String):play.api.templates.Html = {
        _display_ {
def /*32.6*/header/*32.12*/(key:String, title:String):play.api.templates.Html = {_display_(

Seq[Any](format.raw/*32.42*/("""
        <th class=""""),_display_(Seq[Any](/*33.21*/key/*33.24*/.replace(".","_"))),format.raw/*33.41*/(""" header """),_display_(Seq[Any](/*33.50*/if(currentSortBy == key){/*33.76*/{if(currentOrder == "asc") "headerSortDown" else "headerSortUp"}})),format.raw/*33.140*/("""">
            <a href=""""),_display_(Seq[Any](/*34.23*/link(0, key))),format.raw/*34.35*/("""">"""),_display_(Seq[Any](/*34.38*/title)),format.raw/*34.43*/("""</a>
        </th>
    """)))};def /*6.6*/link/*6.10*/(newPage:Int, newSortBy:String) = {{

        var sortBy = currentSortBy
        var order = currentOrder

        if(newSortBy != null) {
            sortBy = newSortBy
            if(currentSortBy == newSortBy) {
                if(currentOrder == "asc") {
                    order = "desc"
                } else {
                    order = "asc"
                }
            } else {
                order = "asc"
            }
        }

        // Generate the link
        routes.Application.products(newPage, sortBy, order, currentFilter)

    }};
Seq[Any](format.raw/*1.114*/("""

    """),format.raw/*5.46*/("""
    """),format.raw/*27.6*/("""

    """),format.raw/*31.41*/("""
    """),format.raw/*36.6*/("""
"""),_display_(Seq[Any](/*37.2*/main("Don't forget your equipment!!")/*37.39*/ {_display_(Seq[Any](format.raw/*37.41*/("""

    <h1 id="homeTitle">"""),_display_(Seq[Any](/*39.25*/Messages("products.list.title", currentPage.getTotalRowCount))),format.raw/*39.86*/("""</h1>

        """),_display_(Seq[Any](/*41.10*/if(flash.containsKey("success"))/*41.42*/ {_display_(Seq[Any](format.raw/*41.44*/("""
            <div class="alert-message warning">
                <strong>Done!</strong> """),_display_(Seq[Any](/*43.41*/flash/*43.46*/.get("success"))),format.raw/*43.61*/("""
            </div>
        """)))})),format.raw/*45.10*/("""

        <div id="actions">

            <form action=""""),_display_(Seq[Any](/*49.28*/link(0, "name"))),format.raw/*49.43*/("""" method="GET">
                <input type="search" id="searchbox" name="f" value=""""),_display_(Seq[Any](/*50.70*/currentFilter)),format.raw/*50.83*/("""" placeholder="Filter by product name...">
                <input type="submit" id="searchsubmit" value="Filter by name" class="btn primary">
            </form>

            <a class="btn success" id="add" href=""""),_display_(Seq[Any](/*54.52*/routes/*54.58*/.Application.newProduct())),format.raw/*54.83*/("""">Add a new product</a>

        </div>

        """),_display_(Seq[Any](/*58.10*/if(currentPage.getTotalRowCount == 0)/*58.47*/ {_display_(Seq[Any](format.raw/*58.49*/("""

            <div class="well">
                <em>Nothing to display</em>
            </div>

        """)))}/*64.11*/else/*64.16*/{_display_(Seq[Any](format.raw/*64.17*/("""

            <table class="computers zebra-striped">
                <thead>
                    <tr>
                        """),_display_(Seq[Any](/*69.26*/header("name", "Product name"))),format.raw/*69.56*/("""
                        """),_display_(Seq[Any](/*70.26*/header("equipmentType.name", "Type"))),format.raw/*70.62*/("""
                        """),_display_(Seq[Any](/*71.26*/header("category.name", "Category"))),format.raw/*71.61*/("""
                    </tr>
                </thead>
                <tbody>

                    """),_display_(Seq[Any](/*76.22*/for(product <- currentPage.getList) yield /*76.57*/ {_display_(Seq[Any](format.raw/*76.59*/("""
                        <tr>
                            <td><a href=""""),_display_(Seq[Any](/*78.43*/routes/*78.49*/.Application.editProduct(product.id))),format.raw/*78.85*/("""">"""),_display_(Seq[Any](/*78.88*/product/*78.95*/.name)),format.raw/*78.100*/("""</a></td>
                            <td><a href=""""),_display_(Seq[Any](/*79.43*/routes/*79.49*/.Application.editProduct(product.id))),format.raw/*79.85*/("""">"""),_display_(Seq[Any](/*79.88*/product/*79.95*/.equipmentType.name)),format.raw/*79.114*/("""</a></td>
                            <td><a href=""""),_display_(Seq[Any](/*80.43*/routes/*80.49*/.Application.editProduct(product.id))),format.raw/*80.85*/("""">"""),_display_(Seq[Any](/*80.88*/product/*80.95*/.category.name)),format.raw/*80.109*/("""</a></td>
                        </tr>
                    """)))})),format.raw/*82.22*/("""

                </tbody>
            </table>

            <div id="pagination" class="pagination">
                <ul>
                    """),_display_(Seq[Any](/*89.22*/if(currentPage.hasPrev)/*89.45*/ {_display_(Seq[Any](format.raw/*89.47*/("""
                        <li class="prev">
                            <a href=""""),_display_(Seq[Any](/*91.39*/link(currentPage.getPageIndex - 1, null))),format.raw/*91.79*/("""">&larr; Previous</a>
                        </li>
                    """)))}/*93.23*/else/*93.28*/{_display_(Seq[Any](format.raw/*93.29*/("""
                        <li class="prev disabled">
                            <a>&larr; Previous</a>
                        </li>
                    """)))})),format.raw/*97.22*/("""
                    <li class="current">
                        <a>Displaying """),_display_(Seq[Any](/*99.40*/currentPage/*99.51*/.getDisplayXtoYofZ(" to "," of "))),format.raw/*99.84*/("""</a>
                    </li>
                    """),_display_(Seq[Any](/*101.22*/if(currentPage.hasNext)/*101.45*/ {_display_(Seq[Any](format.raw/*101.47*/("""
                        <li class="next">
                            <a href=""""),_display_(Seq[Any](/*103.39*/link(currentPage.getPageIndex + 1, null))),format.raw/*103.79*/("""">Next &rarr;</a>
                        </li>
                    """)))}/*105.23*/else/*105.28*/{_display_(Seq[Any](format.raw/*105.29*/("""
                        <li class="next disabled">
                            <a>Next &rarr;</a>
                        </li>
                    """)))})),format.raw/*109.22*/("""
                </ul>
            </div>

        """)))})),format.raw/*113.10*/("""

    """),_display_(Seq[Any](/*115.6*/helper/*115.12*/.form(action = routes.Application.upload, 'enctype -> "multipart/form-data")/*115.88*/ {_display_(Seq[Any](format.raw/*115.90*/("""

        <input type="file" name="inventory">
        <p>
            <input type="submit">
        </p>

    """)))})),format.raw/*122.6*/("""
""")))})))}
    }
    
    def render(currentPage:com.avaje.ebean.Page[Product],currentSortBy:String,currentOrder:String,currentFilter:String): play.api.templates.Html = apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def f:((com.avaje.ebean.Page[Product],String,String,String) => play.api.templates.Html) = (currentPage,currentSortBy,currentOrder,currentFilter) => apply(currentPage,currentSortBy,currentOrder,currentFilter)
    
    def ref: this.type = this

}
                /*
                    -- GENERATED --
                    DATE: Tue Sep 17 23:31:03 CEST 2013
                    SOURCE: /home/kisscool/workspace/java/equipmentList/app/views/index.scala.html
                    HASH: c1bcd1144e9b963e338a8642cd51cefc4c38a44e
                    MATRIX: 767->1|940->947|955->953|1049->983|1106->1004|1118->1007|1157->1024|1202->1033|1236->1059|1324->1123|1385->1148|1419->1160|1458->1163|1485->1168|1531->258|1543->262|2131->113|2164->252|2196->819|2230->941|2262->1192|2299->1194|2345->1231|2385->1233|2447->1259|2530->1320|2582->1336|2623->1368|2663->1370|2788->1459|2802->1464|2839->1479|2900->1508|2993->1565|3030->1580|3151->1665|3186->1678|3436->1892|3451->1898|3498->1923|3584->1973|3630->2010|3670->2012|3795->2119|3808->2124|3847->2125|4011->2253|4063->2283|4125->2309|4183->2345|4245->2371|4302->2406|4436->2504|4487->2539|4527->2541|4635->2613|4650->2619|4708->2655|4747->2658|4763->2665|4791->2670|4879->2722|4894->2728|4952->2764|4991->2767|5007->2774|5049->2793|5137->2845|5152->2851|5210->2887|5249->2890|5265->2897|5302->2911|5395->2972|5575->3116|5607->3139|5647->3141|5764->3222|5826->3262|5918->3336|5931->3341|5970->3342|6156->3496|6273->3577|6293->3588|6348->3621|6437->3673|6470->3696|6511->3698|6629->3779|6692->3819|6781->3889|6795->3894|6835->3895|7018->4045|7103->4097|7146->4104|7162->4110|7248->4186|7289->4188|7433->4300
                    LINES: 26->1|28->32|28->32|30->32|31->33|31->33|31->33|31->33|31->33|31->33|32->34|32->34|32->34|32->34|34->6|34->6|56->1|58->5|59->27|61->31|62->36|63->37|63->37|63->37|65->39|65->39|67->41|67->41|67->41|69->43|69->43|69->43|71->45|75->49|75->49|76->50|76->50|80->54|80->54|80->54|84->58|84->58|84->58|90->64|90->64|90->64|95->69|95->69|96->70|96->70|97->71|97->71|102->76|102->76|102->76|104->78|104->78|104->78|104->78|104->78|104->78|105->79|105->79|105->79|105->79|105->79|105->79|106->80|106->80|106->80|106->80|106->80|106->80|108->82|115->89|115->89|115->89|117->91|117->91|119->93|119->93|119->93|123->97|125->99|125->99|125->99|127->101|127->101|127->101|129->103|129->103|131->105|131->105|131->105|135->109|139->113|141->115|141->115|141->115|141->115|148->122
                    -- GENERATED --
                */
            