// @GENERATOR:play-routes-compiler
// @SOURCE:C:/Users/jajubina/Documents/RestFulWebService/restfulwebservice/conf/routes
// @DATE:Thu Oct 04 15:12:33 EDT 2018

package router

import play.core.routing._
import play.core.routing.HandlerInvokerFactory._

import play.api.mvc._

import _root_.controllers.Assets.Asset
import _root_.play.libs.F

class Routes(
  override val errorHandler: play.api.http.HttpErrorHandler, 
  // @LINE:6
  HomeController_0: controllers.HomeController,
  // @LINE:28
  Assets_1: controllers.Assets,
  val prefix: String
) extends GeneratedRouter {

   @javax.inject.Inject()
   def this(errorHandler: play.api.http.HttpErrorHandler,
    // @LINE:6
    HomeController_0: controllers.HomeController,
    // @LINE:28
    Assets_1: controllers.Assets
  ) = this(errorHandler, HomeController_0, Assets_1, "/")

  def withPrefix(prefix: String): Routes = {
    router.RoutesPrefix.setPrefix(prefix)
    new Routes(errorHandler, HomeController_0, Assets_1, prefix)
  }

  private[this] val defaultPrefix: String = {
    if (this.prefix.endsWith("/")) "" else "/"
  }

  def documentation = List(
    ("""GET""", this.prefix, """controllers.HomeController.index"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """addbalance/usd/""" + "$" + """amount<[^/]+>""", """controllers.HomeController.addBalanceUSD(amount:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """getbalance""", """controllers.HomeController.getBalance"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """transactions""", """controllers.HomeController.getTransactions()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """transactions/""" + "$" + """transactionID<[^/]+>""", """controllers.HomeController.getTransaction(transactionID:String)"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selloffers""", """controllers.HomeController.getSellOffers"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """selloffers/""" + "$" + """offerid<[^/]+>""", """controllers.HomeController.getSellOffer(offerid:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """buy/""" + "$" + """maxrate<[^/]+>/""" + "$" + """amount<[^/]+>""", """controllers.HomeController.buy(maxrate:String, amount:String)"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """debug/confirm_fail""", """controllers.HomeController.confirmFail()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """debug/confirm_no_response""", """controllers.HomeController.confirmNoResponse()"""),
    ("""POST""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """debug/reset""", """controllers.HomeController.confirmReset()"""),
    ("""GET""", this.prefix + (if(this.prefix.endsWith("/")) "" else "/") + """assets/""" + "$" + """file<.+>""", """controllers.Assets.versioned(path:String = "/public", file:Asset)"""),
    Nil
  ).foldLeft(List.empty[(String,String,String)]) { (s,e) => e.asInstanceOf[Any] match {
    case r @ (_,_,_) => s :+ r.asInstanceOf[(String,String,String)]
    case l => s ++ l.asInstanceOf[List[(String,String,String)]]
  }}


  // @LINE:6
  private[this] lazy val controllers_HomeController_index0_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix)))
  )
  private[this] lazy val controllers_HomeController_index0_invoker = createInvoker(
    HomeController_0.index,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "index",
      Nil,
      "GET",
      this.prefix + """""",
      """ An example controller showing a sample home page""",
      Seq()
    )
  )

  // @LINE:8
  private[this] lazy val controllers_HomeController_addBalanceUSD1_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("addbalance/usd/"), DynamicPart("amount", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_addBalanceUSD1_invoker = createInvoker(
    HomeController_0.addBalanceUSD(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "addBalanceUSD",
      Seq(classOf[String]),
      "POST",
      this.prefix + """addbalance/usd/""" + "$" + """amount<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:10
  private[this] lazy val controllers_HomeController_getBalance2_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("getbalance")))
  )
  private[this] lazy val controllers_HomeController_getBalance2_invoker = createInvoker(
    HomeController_0.getBalance,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getBalance",
      Nil,
      "GET",
      this.prefix + """getbalance""",
      """""",
      Seq()
    )
  )

  // @LINE:12
  private[this] lazy val controllers_HomeController_getTransactions3_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("transactions")))
  )
  private[this] lazy val controllers_HomeController_getTransactions3_invoker = createInvoker(
    HomeController_0.getTransactions(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getTransactions",
      Nil,
      "GET",
      this.prefix + """transactions""",
      """""",
      Seq()
    )
  )

  // @LINE:14
  private[this] lazy val controllers_HomeController_getTransaction4_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("transactions/"), DynamicPart("transactionID", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getTransaction4_invoker = createInvoker(
    HomeController_0.getTransaction(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getTransaction",
      Seq(classOf[String]),
      "GET",
      this.prefix + """transactions/""" + "$" + """transactionID<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:16
  private[this] lazy val controllers_HomeController_getSellOffers5_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selloffers")))
  )
  private[this] lazy val controllers_HomeController_getSellOffers5_invoker = createInvoker(
    HomeController_0.getSellOffers,
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getSellOffers",
      Nil,
      "GET",
      this.prefix + """selloffers""",
      """""",
      Seq()
    )
  )

  // @LINE:18
  private[this] lazy val controllers_HomeController_getSellOffer6_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("selloffers/"), DynamicPart("offerid", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_getSellOffer6_invoker = createInvoker(
    HomeController_0.getSellOffer(fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "getSellOffer",
      Seq(classOf[String]),
      "GET",
      this.prefix + """selloffers/""" + "$" + """offerid<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:20
  private[this] lazy val controllers_HomeController_buy7_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("buy/"), DynamicPart("maxrate", """[^/]+""",true), StaticPart("/"), DynamicPart("amount", """[^/]+""",true)))
  )
  private[this] lazy val controllers_HomeController_buy7_invoker = createInvoker(
    HomeController_0.buy(fakeValue[String], fakeValue[String]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "buy",
      Seq(classOf[String], classOf[String]),
      "POST",
      this.prefix + """buy/""" + "$" + """maxrate<[^/]+>/""" + "$" + """amount<[^/]+>""",
      """""",
      Seq()
    )
  )

  // @LINE:22
  private[this] lazy val controllers_HomeController_confirmFail8_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("debug/confirm_fail")))
  )
  private[this] lazy val controllers_HomeController_confirmFail8_invoker = createInvoker(
    HomeController_0.confirmFail(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "confirmFail",
      Nil,
      "POST",
      this.prefix + """debug/confirm_fail""",
      """""",
      Seq()
    )
  )

  // @LINE:24
  private[this] lazy val controllers_HomeController_confirmNoResponse9_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("debug/confirm_no_response")))
  )
  private[this] lazy val controllers_HomeController_confirmNoResponse9_invoker = createInvoker(
    HomeController_0.confirmNoResponse(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "confirmNoResponse",
      Nil,
      "POST",
      this.prefix + """debug/confirm_no_response""",
      """""",
      Seq()
    )
  )

  // @LINE:26
  private[this] lazy val controllers_HomeController_confirmReset10_route = Route("POST",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("debug/reset")))
  )
  private[this] lazy val controllers_HomeController_confirmReset10_invoker = createInvoker(
    HomeController_0.confirmReset(),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.HomeController",
      "confirmReset",
      Nil,
      "POST",
      this.prefix + """debug/reset""",
      """""",
      Seq()
    )
  )

  // @LINE:28
  private[this] lazy val controllers_Assets_versioned11_route = Route("GET",
    PathPattern(List(StaticPart(this.prefix), StaticPart(this.defaultPrefix), StaticPart("assets/"), DynamicPart("file", """.+""",false)))
  )
  private[this] lazy val controllers_Assets_versioned11_invoker = createInvoker(
    Assets_1.versioned(fakeValue[String], fakeValue[Asset]),
    play.api.routing.HandlerDef(this.getClass.getClassLoader,
      "router",
      "controllers.Assets",
      "versioned",
      Seq(classOf[String], classOf[Asset]),
      "GET",
      this.prefix + """assets/""" + "$" + """file<.+>""",
      """ Map static resources from the /public folder to the /assets URL path""",
      Seq()
    )
  )


  def routes: PartialFunction[RequestHeader, Handler] = {
  
    // @LINE:6
    case controllers_HomeController_index0_route(params@_) =>
      call { 
        controllers_HomeController_index0_invoker.call(HomeController_0.index)
      }
  
    // @LINE:8
    case controllers_HomeController_addBalanceUSD1_route(params@_) =>
      call(params.fromPath[String]("amount", None)) { (amount) =>
        controllers_HomeController_addBalanceUSD1_invoker.call(HomeController_0.addBalanceUSD(amount))
      }
  
    // @LINE:10
    case controllers_HomeController_getBalance2_route(params@_) =>
      call { 
        controllers_HomeController_getBalance2_invoker.call(HomeController_0.getBalance)
      }
  
    // @LINE:12
    case controllers_HomeController_getTransactions3_route(params@_) =>
      call { 
        controllers_HomeController_getTransactions3_invoker.call(HomeController_0.getTransactions())
      }
  
    // @LINE:14
    case controllers_HomeController_getTransaction4_route(params@_) =>
      call(params.fromPath[String]("transactionID", None)) { (transactionID) =>
        controllers_HomeController_getTransaction4_invoker.call(HomeController_0.getTransaction(transactionID))
      }
  
    // @LINE:16
    case controllers_HomeController_getSellOffers5_route(params@_) =>
      call { 
        controllers_HomeController_getSellOffers5_invoker.call(HomeController_0.getSellOffers)
      }
  
    // @LINE:18
    case controllers_HomeController_getSellOffer6_route(params@_) =>
      call(params.fromPath[String]("offerid", None)) { (offerid) =>
        controllers_HomeController_getSellOffer6_invoker.call(HomeController_0.getSellOffer(offerid))
      }
  
    // @LINE:20
    case controllers_HomeController_buy7_route(params@_) =>
      call(params.fromPath[String]("maxrate", None), params.fromPath[String]("amount", None)) { (maxrate, amount) =>
        controllers_HomeController_buy7_invoker.call(HomeController_0.buy(maxrate, amount))
      }
  
    // @LINE:22
    case controllers_HomeController_confirmFail8_route(params@_) =>
      call { 
        controllers_HomeController_confirmFail8_invoker.call(HomeController_0.confirmFail())
      }
  
    // @LINE:24
    case controllers_HomeController_confirmNoResponse9_route(params@_) =>
      call { 
        controllers_HomeController_confirmNoResponse9_invoker.call(HomeController_0.confirmNoResponse())
      }
  
    // @LINE:26
    case controllers_HomeController_confirmReset10_route(params@_) =>
      call { 
        controllers_HomeController_confirmReset10_invoker.call(HomeController_0.confirmReset())
      }
  
    // @LINE:28
    case controllers_Assets_versioned11_route(params@_) =>
      call(Param[String]("path", Right("/public")), params.fromPath[Asset]("file", None)) { (path, file) =>
        controllers_Assets_versioned11_invoker.call(Assets_1.versioned(path, file))
      }
  }
}
